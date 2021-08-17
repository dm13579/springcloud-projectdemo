package com.dm.exception;

import com.dm.result.ResultCode;
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
  *@className GateWayException
  *@cescription 网关异常
  *@author dm
  *@date 2021/5/26 17:04
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GateWayException extends Exception {

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

    public GateWayException() {
        this.code = ResultCode.BAD_GATEWAY.getTypeValue();
        this.message = ResultCode.BAD_GATEWAY.getTypeName();
    }

    public GateWayException(String message) {
        this.code = ResultCode.BAD_GATEWAY.getTypeValue();
        this.message = message;
    }

    public GateWayException(ResultCode resultCode) {
        this.code = resultCode.getTypeValue();
        this.message = resultCode.getTypeName();
    }
}
