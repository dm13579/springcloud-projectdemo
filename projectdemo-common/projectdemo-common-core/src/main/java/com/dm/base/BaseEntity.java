package com.dm.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

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
  *@className BaseEntity
  *@cescription 基础实体类
  *@author dm
  *@date 2021/6/1 10:51
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {

    /**
     * 序列化版本编号
     */
    private static final long serialVersionUID = -2229337319525563086L;

    /**
     * 主键ID
     */
    public String Id;

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 更新时间
     */
    public Date updateTime;

    /**
     * 是否删除
     */
    public Boolean deleted;
}
