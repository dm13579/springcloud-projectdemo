package com.dm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

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
  *@className NotAuthUrlProperties
  *@cescription 跳过认证中心认证路由地址
  *@author dm
  *@date 2021/5/20 16:40
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Data
@Component
@ConfigurationProperties(prefix = "gateway")
public class NotAuthUrlProperties {
    private List<String> shouldSkipUrls;
}
