package com.dm.entity;

import lombok.Data;

import java.util.Map;

/**
 * 认证服务器返回的TokenInfo的封装
 *
 * @author dm
 * @date 2020/04/09
 * @since JDK1.8
 */
@Data
public class TokenInfo {

	private String access_token;

	private String tokenType;

	private String refresh_token;
	
	private String scope;
	
	private Map<String,String> additionalInfo;
 	
}
