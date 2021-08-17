package com.dm.base;

/**
 * User: dm
 * Date: 2021/01/13
 * Time: 10:30
 * Description: 枚举通用方法
 */
public interface EnumType {

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    Integer getTypeValue();

    /**
     * 获取枚举名称
     *
     * @return 名称
     */
    String getTypeName();

    /**
     * 通过value枚举类型获取
     *
     * @param clazz 类
     * @param value 枚举值
     * @return 枚举类型
     */
    static EnumType getEnumTypeByValue(Class<? extends EnumType> clazz, Integer value) {

        EnumType[] enumConstants = clazz.getEnumConstants();

        if (enumConstants != null) {
            for (EnumType enumType : enumConstants) {
                if (enumType.getTypeValue().equals(value)) {
                    return enumType;
                }
            }
        }

        return null;
    }
}
