package com.dm.handler;

import com.dm.base.EnumType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
  *@className EnumTypeHandler
  *@cescription 枚举转换适配器
  *@author dm
  *@date 2021/5/20 14:21
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public class MyEnumTypeHandler<E extends Enum<?> & EnumType> extends BaseTypeHandler<EnumType> {

    private Class<E> type;

    public MyEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    /**
     * Java类型的参数转换为对应的数据库参数
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EnumType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getTypeValue());
    }

    /**
     * 字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public EnumType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return rs.wasNull() ? null : convertEnum(code);
    }

    /**
     * 字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public EnumType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return rs.wasNull() ? null : convertEnum(code);
    }

    /**
     * 存储过程后，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public EnumType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return cs.wasNull() ? null : convertEnum(code);
    }


    private E convertEnum(int value){
        try {
            E[] enumConstants = type.getEnumConstants();
            for (E e : enumConstants) {
                if (e.getTypeValue() == value) {
                    return e;
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by code value.", e);
        }
    }
}
