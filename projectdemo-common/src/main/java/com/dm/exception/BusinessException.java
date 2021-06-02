package com.dm.exception;

import com.dm.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
  *@className BusinessException
  *@cescription 业务异常逻辑
  *@author dm
  *@date 2021/5/26 17:14
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends Exception {

    /**
     * 序列化版本编号
     */
    private static final long serialVersionUID = -7639262485980659320L;

    /**
     * 错误码
     */
    private long code;

    /**
     * 错误消息
     */
    private String message;

    public BusinessException() {
        this.code = ResultCode.ERROR_BUSINESS.getTypeValue();
        this.message = ResultCode.ERROR_BUSINESS.getTypeName();
    }

    public BusinessException(String message) {
        this.code = ResultCode.ERROR_BUSINESS.getTypeValue();
        this.message = message;
    }

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getTypeValue();
        this.message = resultCode.getTypeName();
    }
}
