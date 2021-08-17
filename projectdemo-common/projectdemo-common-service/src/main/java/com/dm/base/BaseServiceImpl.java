package com.dm.base;

import com.dm.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

/**
  *                  ,;,,;
  *                ,;;'(    
  *      __      ,;;' ' \   
  *   /'  '\'~~'~' \ /'\.)  
  * ,;(      )    /  |.     
  *,;' \    /-.,,(   ) \    
  *     ) /       ) / )|    
  *     ||        ||  \)     
  *    (_\       (_\
  *@className BaseServiceImpl
  *@cescription TODO
  *@author dm
  *@date 2021/6/1 10:30
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public class BaseServiceImpl<M extends Mapper<T>, T extends BaseEntity> {

    @Autowired
    protected M mapper;

    @Autowired
    private RedisService redisService;

    /**
     * 正常缓存过期时间 24小时
     */
    private long expire = 24 * 60 * 60;

    /**
     * 空值缓存过期时间 1分钟
     */
    private long nullExpire = 1 * 60;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    /**
     * 按ID查询(做好缓存层)
     *
     * @param id ID
     * @return 返回对象
     */
    @SuppressWarnings("unchecked")
    public T queryById(String id){
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        T t = BeanUtils.instantiateClass((Class<T>) type.getActualTypeArguments()[0]);
        String key = constructRedisKeyName(t, id);
        if (redisService.exists(key)) {
            return (T) redisService.get(key);
        }

        t = mapper.selectByPrimaryKey(id);
        if (Optional.ofNullable(t).isPresent()) {
            redisService.put(key,t,expire);
        }else {
            redisService.put(key, null, nullExpire);
        }
        return t;
    }

    /**
     * 增加数据
     *
     *  @param t 实体
     */
    public void insert(T t){
       mapper.insert(t);
    }

    /**
     * 插入一条数据(如果为Null就忽略)
     *
     * @param t 实体
     */
    public void insertSelective(T t){
        mapper.insertSelective(t);
    }

    /**
     * 更新数据
     *
     * @param t 实体
     */
    public void updateById(T t){
        mapper.updateByPrimaryKey(t);
        redisService.remove(constructRedisKeyName(t));
    }

    /**
     * 对字段进行判断再更新(如果为Null就忽略更新)
     *
     * @param t 实体
     */
    public void updateSelectiveById(T t) {
        mapper.updateByPrimaryKeySelective(t);
        redisService.remove(constructRedisKeyName(t));
    }

    /**
     * 逻辑删除
     *
     * @param id 主键编号
     */
    @SuppressWarnings("unchecked")
    public void logicDeleted(String id){
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        T t = BeanUtils.instantiateClass((Class<T>) type.getActualTypeArguments()[0]);
        t.setDeleted(true);
        t.setId(id);
        mapper.updateByPrimaryKeySelective(t);
    }
    /**
     * 构造Redis key名称
     *
     * @param t 对象
     * @param id ID
     * @return 名称
     */
    private String constructRedisKeyName(T t,String id){
        t.setId(id);
        return constructRedisKeyName(t);
    }

    /**
     * 构造Redis key名称
     *
     * @param t 对象
     * @return 名称
     */
    private String constructRedisKeyName(T t){
        return t.getClass().getName().concat(":").concat(t.getId());
    }
}
