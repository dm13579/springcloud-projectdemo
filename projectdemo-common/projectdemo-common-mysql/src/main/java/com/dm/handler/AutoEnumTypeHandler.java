package com.dm.handler;

import com.dm.base.EnumType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
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
  *@className AutoEnumTypeHandler
  *@cescription 转换器定位枚举类（实现了我们自己的枚举类使用我们自己的）
  *@author dm
  *@date 2021/5/20 15:15
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@SuppressWarnings("unchecked")
public class AutoEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    /**
     * 枚举类型转换器
     */
    private BaseTypeHandler<E> typeHandler;

    public AutoEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        if (EnumType.class.isAssignableFrom(type)) {
            // 如果实现了 EnumType 则使用我们自定义的转换器
            typeHandler = new MyEnumTypeHandler(type);
        } else {
            // 默认转换器 也可换成 EnumOrdinalTypeHandler
            typeHandler = new EnumTypeHandler<>(type);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        typeHandler.setNonNullParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return (E) typeHandler.getNullableResult(rs, columnName);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return (E) typeHandler.getNullableResult(rs, columnIndex);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return (E) typeHandler.getNullableResult(cs, columnIndex);
    }
}
