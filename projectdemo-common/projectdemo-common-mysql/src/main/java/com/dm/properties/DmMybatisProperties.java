package com.dm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mybatis")
@Data
public class DmMybatisProperties {
    private String mapperLocations;
}
