package com.dm.base;

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
  *@className BaseService
  *@cescription TODO
  *@author dm
  *@date 2021/5/31 14:19
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public interface BaseService<T> {

    T queryById(String id);

    /**
     * 增加数据
     *
     * @param t 实体
     */
    void insert(T t);

    /**
     * 插入一条数据(如果为Null就忽略)
     *
     * @param t 实体
     */
    void insertSelective(T t);

    /**
     * 更新数据
     *
     * @param t 实体
     */
    void updateById(T t);

    /**
     * 对字段进行判断再更新(如果为Null就忽略更新)
     *
     * @param t 实体
     */
    void updateSelectiveById(T t);

    /**
     * 逻辑删除
     *
     * @param id 主键编号
     */
    void logicDeleted(String id);
}
