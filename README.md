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