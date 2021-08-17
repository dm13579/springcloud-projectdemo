package com.dm.config;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
  *@className RibbonConfig
  *@cescription Ribbon配置类
  *@author dm
  *@date 2021/5/20 17:36
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Configuration
public class RibbonConfig {

    @Bean
    public RestTemplate restTemplate(DiscoveryClient discoveryClient) {
        return new MyRestTemplate(discoveryClient);
    }
}
