package com.dm.config;

import com.dm.entity.MyUserDetailsEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

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
  *@className MyTokenEnhancer
  *@cescription jwt自定义增强器
  *@author dm
  *@date 2021/5/19 13:27
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public class MyTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        MyUserDetailsEntity userDetails = (MyUserDetailsEntity) authentication.getPrincipal();

        Map<String, Object> additionalInfo = new HashMap<>(8);

        Map<String, Object> retMap = new HashMap<>(8);
        additionalInfo.put("id",userDetails.getUser().getId());
        additionalInfo.put("name",userDetails.getUser().getName());
        additionalInfo.put("nickname",userDetails.getUser().getNickname());
        retMap.put("additionalInfo",additionalInfo);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(retMap);
        return accessToken;
    }
}
