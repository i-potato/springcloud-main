# springcloud-main
## Submission 
- Email: 454479748@qq.com
- Name: 杨于枫
- Type of application code：java

## 框架使用
- 服务注册与发现：eureka
- 配置中心：config
- 服务网关：gatway
- 负载均衡：ribbon
- 服务间调用：openFeign
- ORM: mybatis

## 开发环境
- jdk: 8.0
- springcloud: Hoxton.SR1  
- springboot: 2.2.2.RELEASE
- ORM: mybatis1.3.2
- 数据库: mysql5.6
- 开发工具: sts3.8(spring版eclipse)、navicat premium、git、postman、sublime text
- 编码: utf-8

## 项目架构

|文件目录|说明|启动端口|访问样例|
|:-:|:-:|:-:|:-:|
| springcloud-commons| 项目公共jar包|无|无|
| springcloud-eureka   |服务注册与发现|8000|http://localhost:8000/ |
| springcloud-config-center|配置中心|10000|http://localhost:10000/application-dev.yml |
| springcloud-gateway|网关|80|http://localhost/user/lb |
| springcloud-user-service8081|简单的用户模块demo，服务端|8081|http://localhost:8081/user/get/1 |
| springcloud-user-service8082|与8081为镜像，用于测试负载均衡|8082|http://localhost:8082/user/get/1|
| springcloud-user-client|简单的用户客户端|8080|http://localhost:8080/user/get/1|
| springcloud-news|简单的新闻客户端|8083|http://localhost:8083/news/list |
|sql|sql文件存放地址|无|无|

## 启动顺序
- 1.springcloud-eureka 
- 2.springcloud-config-center
- 3.springcloud-user-service8081、springcloud-user-service8082
- 4.springcloud-user-client、springcloud-news
- 5.springcloud-gateway

## 说明
- 配置中心git地址：https://github.com/i-potato/springcloud-config 
- 数据库sql文件在sql目录下,需提前创建yyf-springcloud数据库
- 80网关仅配置了两个路径：http://localhost/user/lb  、 http://localhost/user/get/1
- 因使用了JWT验证，部分接口需要登录获取Authorization
- 因时间原因尚未完成web层html页面、因环境原因未使用redis缓存，请见谅

