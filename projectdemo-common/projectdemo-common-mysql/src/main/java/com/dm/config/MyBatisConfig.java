package com.dm.config;

import com.dm.handler.AutoEnumTypeHandler;
import com.dm.properties.DmMybatisProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * MyBatis配置类
 *
 * @author dm
 * @date 2021/05/19
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.dm.dao.mapper"})
@EnableConfigurationProperties(DmMybatisProperties.class)
@ConditionalOnClass({DmMybatisProperties.class})
public class MyBatisConfig {

    @Autowired(required = false)
    private DmMybatisProperties dmMybatisProperties;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        // 设置配置文件及mapper文件地址
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources(dmMybatisProperties.getMapperLocations()));

        SqlSessionFactory sqlSessionFactory = factory.getObject();
        // 取得类型转换注册器
        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
        // 注册默认枚举转换器
        typeHandlerRegistry.setDefaultEnumTypeHandler(AutoEnumTypeHandler.class);

        return sqlSessionFactory;
    }

}
