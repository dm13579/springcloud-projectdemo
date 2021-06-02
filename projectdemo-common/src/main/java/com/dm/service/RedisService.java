package com.dm.service;

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
  *@className RedisService
  *@cescription TODO
  *@author dm
  *@date 2021/5/31 15:56
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public interface RedisService {

    /**
     * redis 设值
     *
     * @param key    键
     * @param value  值
     * @param expire 超时时间
     */
    void put(String key, Object value, long expire);

    /**
     * redis 取值
     *
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * redis 删值
     *
     * @param key 键
     */
    void remove(String key);

    /**
     * 判断redis是否有key
     *
     * @param key 键
     * @return 是否存在
     */
    boolean exists(String key);

    /**
     * 自增操作
     *
     * @param key   键
     * @param delta 自增步长
     * @return 值
     */
    long increment(String key, long delta);
}
