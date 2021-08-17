package com.dm.enums;

import com.dm.base.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
