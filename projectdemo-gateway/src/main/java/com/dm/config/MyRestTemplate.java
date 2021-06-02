package com.dm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

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
  *@className MyRestTemplate
  *@cescription 自定义RestTemplate
  *@author dm
  *@date 2021/5/20 17:36
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Slf4j
public class MyRestTemplate extends RestTemplate {

    private DiscoveryClient discoveryClient;

    public MyRestTemplate(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        Assert.notNull(url, "URI is required");
        Assert.notNull(method, "HttpMethod is required");
        ClientHttpResponse response = null;
        try {
            // 拦截请求，从注册中心获取地址由规则获取一个路由
            log.info("请求的url路径为:{}",url);
            url = replaceUrl(url);
            log.info("替换后的路径:{}",url);

            ClientHttpRequest request = createRequest(url, method);
            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }
            response = request.execute();
            handleResponse(url, method, response);
            return (responseExtractor != null ? responseExtractor.extractData(response) : null);
        }
        catch (IOException ex) {
            String resource = url.toString();
            String query = url.getRawQuery();
            resource = (query != null ? resource.substring(0, resource.indexOf('?')) : resource);
            throw new ResourceAccessException("I/O error on " + method.name() +
                    " request for \"" + resource + "\": " + ex.getMessage(), ex);
        }
        finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private URI replaceUrl(URI url) {

        // 服务名称
        String serviceName = url.getHost();
        log.info("调用微服务名称：{}",serviceName);

        // 解析请求path
        String requestPath = url.getPath();
        log.info("请求Path：{}",requestPath);

        // 通过serviceName去注册中心获取对应ip
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
        if (serviceInstanceList.isEmpty()) {
            throw new RuntimeException("没有可用的微服务实例列表:"+serviceName);
        }

        String chooserTargetip = chooseTargetIp(serviceInstanceList);

        log.info("随机选举的服务IP:{}",chooserTargetip);
        String targetSource = chooserTargetip + requestPath;
        try {
            return new URI(targetSource);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 获取IP规则
     */
    private String chooseTargetIp(List<ServiceInstance> serviceInstanceList) {
        //采取随机的获取一个
        Random random = new Random();
        int randomIndex = random.nextInt(serviceInstanceList.size());
        log.info("随机下标:{}",randomIndex);

        return serviceInstanceList.get(randomIndex).getUri().toString();
    }
}
