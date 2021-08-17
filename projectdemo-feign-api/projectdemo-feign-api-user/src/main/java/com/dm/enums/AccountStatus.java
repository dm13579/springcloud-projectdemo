package com.dm.enums;

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
 *@className AccountStatus
 *@cescription TODO
 *@author dm
 *@date 2021/5/19 14:27
 *@slogan: 我自横刀向天笑，笑完我就去睡觉
 *@version 1.0
 **/
@Getter
@AllArgsConstructor
public enum AccountStatus implements EnumType {

    /**
     * 启用
     */
    ENABLE(1, "启用"),

    /**
     * 禁用
     */
    DISABLE(2, "禁用"),
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
