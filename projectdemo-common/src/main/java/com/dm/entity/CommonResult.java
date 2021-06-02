package com.dm.entity;

import com.dm.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  *@className CommonResult
  *@cescription 返回结果
  *@author dm
  *@date 2021/5/31 14:27
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {
    /**
     * 返回码
     */
    private long code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getTypeValue(), ResultCode.SUCCESS.getTypeName(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data,String message) {
        return new CommonResult<>(ResultCode.SUCCESS.getTypeValue(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultCode.ERROR.getTypeValue(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param code 错误码
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(long code, String message) {
        return new CommonResult<T>(code, message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return new CommonResult<T>(ResultCode.ERROR.getTypeValue(), ResultCode.ERROR.getTypeName(), null);
    }
}
