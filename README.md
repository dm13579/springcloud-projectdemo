## 项目技术栈
```java
注册中心/配置中心：Nacos
网关：SpringCloud Gateway
安全框架：Spring Security,Spring Cloud Oauth2
限流组件：Sentinel
Rpc远程调用：Ribbon,OpenFeign
持久层层：MySQL,Redis,MyBatis,PageHelper
数据库连接池：Alibaba Druid
日志管理：Logback,Logstash
```

## 目录结构
```java
├─springcloud-projectdemo----------------------------父项目，公共依赖
│  │
│  ├─projectdemo-common
│  │  │
│  │  ├─projectdemo-common-api-----------------------公共feign api基础包
│  │  │
│  │  ├─projectdemo-common-core----------------------公共核心基础包
│  │  │
│  │  ├─projectdemo-common-log-----------------------微服务核心依赖包
│  │  │
│  │  ├─projectdemo-common-mysql---------------------mysql配置与基础
│  │  │
│  │  ├─projectdemo-common-redis---------------------redis配置与基础包
│  │  │
│  │  └─projectdemo-common-service-------------------微服务核心依赖包
│  │  │
│  │  └─projectdemo-common-swagger-------------------swagger配置与基础包
│  │
│  ├─paascloud-feign-api
│  │  │
│  │  ├─paascloud-feign-api-user---------------------用户服务中心API
│  │
│  ├─projectdemo-gateway-----------------------------微服务网关中心
│  │
│  ├─projectdemo-service
│  │  │
│  │  ├─projectdemo-springcloudstream----------------springcloudstream服务中心
│  │  │
│  │  ├─projectdemo-user-----------------------------用户服务中心
│  │  │
│  │  ├─projectdemo-usercenter-----------------------用户中心服务中心
```

## swagger
swagger网关路径：{gateway:ip}:{gateway:port}/doc.html
### 注意点
1. **网关断言id需和服务名保持一致**（swagger会根据断言id构造api-doc路径）

## 自动装配
* projectdemo-common-mysql  --  MyBatisConfig
* projectdemo-common-swagger  --  SwaggerConfig