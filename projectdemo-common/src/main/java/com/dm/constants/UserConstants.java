package com.dm.constants;

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
  *@className UserConstants
  *@cescription 用户常量
  *@author dm
  *@date 2021/5/31 15:56
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public class UserConstants {

    /**
     * 驗證碼長度
     */
    public static final Integer AUTH_CODE_LENGTH = 6;

    /**
     * 验证码前缀
     */
    public static final String REDIS_KEY_PREFIX_AUTH_CODE = "portal:authCode:";

    /**
     * 验证码过期时间（秒）
     */
    public static final long AUTH_CODE_EXPIRE_SECONDS = 90;

    /**
     * 登录验证
     */
    public static final String OAUTH_LOGIN_URL = "http://authcenter-service/oauth/token";

    /**
     * 访问username key值
     */
    public static final String USER_NAME = "username";

    /**
     * 访问password key值
     */
    public static final String PASS = "password";

    /**
     * 访问grant_type key值
     */
    public static final String GRANT_TYPE = "grant_type";

    /**
     * grant_type key refresh_token 值错误
     */
    public static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    /**
     * 访问scope key值
     */
    public static final String SCOPE = "scope";

    /**
     * 访问scope value值
     */
    public static final String SCOPE_AUTH = "read";

    /**
     * 用户服务第三方客户端
     */
    public static final String CLIENT_ID = "user-service";

    /**
     * 用户服务第三方客户端密码
     */
    public static final String CLIENT_SECRET = "test";

    public static final String TOKEN_HEAD = "bearer"
}
