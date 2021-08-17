package com.dm.filter;

import com.alibaba.fastjson.JSON;
import com.dm.config.MyRestTemplate;
import com.dm.exception.GateWayException;
import com.dm.properties.NotAuthUrlProperties;
import com.dm.result.ResultCode;
import com.dm.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.Map;
import java.util.Optional;

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
  *@className GlobalAuthFilter
  *@cescription TODO
  *@author dm
  *@date 2021/5/20 17:32
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Slf4j
//@Configuration
public class GlobalAuthFilter implements GlobalFilter, InitializingBean, Ordered {

    @Resource
    private MyRestTemplate restTemplate;

    @Resource
    private NotAuthUrlProperties notAuthUrlProperties;

    /**
     * jwt的公钥,需要网关启动,远程调用认证中心去获取公钥（属性设值的时候调用）
     */
    private PublicKey publicKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.publicKey = JwtUtils.generatePublicKey(restTemplate);
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String currentUrl = exchange.getRequest().getURI().getPath();
        // 不拦截
        if (shouldSkip(currentUrl)) {
            log.info("跳过拦截路径" + currentUrl);
            return chain.filter(exchange);
        }

        log.info("需要认证的URL:{}", currentUrl);

        // 解析出我们的Authorization的请求头
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");

        // 判断Authorization的请求头是否为空
        if (StringUtils.isEmpty(authorization)) {
            log.info("需要认证的URL:请求头为空");
            throw new GateWayException(ResultCode.AUTHORIZATION_HEADER_IS_EMPTY);
        }

        // 校验jwt,jwt错误，异常抛出
        Claims claims = JwtUtils.validateJwtToken(authorization, publicKey);

        // 将从jwt中获取到的信息存储到请求头中
        ServerWebExchange webExchange = wrapHeader(exchange, claims);

        return chain.filter(webExchange);
    }

    /**
     * 路径跳过匹配器
     *
     * @param currentUrl 当前路径
     * @return true-需要跳过授权中心认证 false-不需要跳过授权中心认证
     */
    private boolean shouldSkip(String currentUrl) {
        // 路径匹配器
        PathMatcher pathMatcher = new AntPathMatcher();

        for (String skipPath : notAuthUrlProperties.getShouldSkipUrls()) {
            if (pathMatcher.match(skipPath, currentUrl)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 把我们从jwt解析出来的用户信息存储到请求中
     */
    private ServerWebExchange wrapHeader(ServerWebExchange exchange, Claims claims) {

        String loginUserInfo = JSON.toJSONString(claims);

        log.info("jwt的用户信息:{}", loginUserInfo);

        Map additionalInfo = claims.get("additionalInfo", Map.class);
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("username", Optional.ofNullable(additionalInfo.get("name").toString()).orElse(""))
                .header("userId", Optional.ofNullable(additionalInfo.get("id").toString()).orElse(""))
                .header("nickName", Optional.ofNullable(additionalInfo.get("nickName").toString()).orElse("")).build();

        return exchange.mutate().request(request).build();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
