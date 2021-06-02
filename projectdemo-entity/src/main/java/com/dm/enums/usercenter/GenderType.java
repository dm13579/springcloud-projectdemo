package com.dm.enums.usercenter;

import com.dm.base.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
  *@className GenderType
  *@cescription TODO
  *@author dm
  *@date 2021/5/19 14:27
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Getter
@AllArgsConstructor
public enum GenderType implements EnumType {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 男
     */
    MAN(1, "男"),

    /**
     * 女
     */
    WOMEN(2, "女"),
    ;

    /**
     * 枚举值
     */
    private Integer typeValue;

    /**
     * 枚举名称
     */
    private String typeName;
}
