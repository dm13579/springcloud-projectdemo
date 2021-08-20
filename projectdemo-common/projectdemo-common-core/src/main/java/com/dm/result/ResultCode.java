package com.dm.result;

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
  *@className ResultCode
  *@cescription 返回结果code
  *@author dm
  *@date 2021/5/26 17:05
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Getter
@AllArgsConstructor
public enum ResultCode implements EnumType {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败
     */
    ERROR(500, "操作失败"),

    /**
     * 网关服务异常
     */
    BAD_GATEWAY(502,"网关服务异常"),

    /**
     * 业务异常
     */
    ERROR_BUSINESS(503,"业务异常"),

    /**
     * 远程获取TokenKey异常
     */
    GET_TOKEN_KEY_ERROR(601,"远程获取TokenKey异常"),

    /**
     * 生成公钥异常
     */
    GEN_PUBLIC_KEY_ERROR(602,"生成公钥异常"),

    /**
     * token校验异常
     */
    JWT_TOKEN_EXPIRE(603,"token校验异常"),

    /**
     * 请求头中的token为空
     */
    AUTHORIZATION_HEADER_IS_EMPTY(600,"请求头中的token为空"),

    /**
     * 流控
     */
    TOMANY_REQUEST_ERROR(701,"请求太多，扛不住啦"),

    /**
     * 降级
     */
    BACKGROUD_DEGRADE_ERROR(702,"系统繁忙"),
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
