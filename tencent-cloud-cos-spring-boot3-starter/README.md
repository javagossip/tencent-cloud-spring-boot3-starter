# Tencent Cloud COS Spring Boot Starter

Tencent Cloud COS (Cloud Object Storage) Spring Boot Starter 是一个用于简化在 Spring Boot 应用中集成腾讯云对象存储服务的启动器。

## 功能特性

- 自动配置 COS 客户端
- 支持通过配置文件灵活配置 COS 参数
- 支持 HTTPS 协议
- 支持启用/禁用自动配置

## 快速开始

### 添加依赖

在 Maven 项目的 `pom.xml` 中添加以下依赖：

```xml
<dependency>
    <groupId>top.openadexchange</groupId>
    <artifactId>tencent-cloud-cos-spring-boot3-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 配置参数

在 `application.yml` 或 `application.properties` 中添加以下配置：

```yaml
openadexchange:
  tencent:
    cos:
      enable: true              # 是否启用 COS 自动配置，默认为 true
      secret-id: your-secret-id # 腾讯云访问密钥 ID
      secret-key: your-secret-key # 腾讯云访问密钥 Key
      region: ap-beijing        # 存储桶所在地域
      bucket: your-bucket-name  # 存储桶名称
      domain: your-domain       # 自定义域名（可选）
      https: true               # 是否使用 HTTPS 协议，默认为 true
```

### 使用 COS 客户端

在你的 Spring Bean 中直接注入 COSClient：

```java
@Service
public class YourService {
    
    @Autowired
    private COSClient cosClient;
    
    public void uploadFile() {
        // 使用 cosClient 进行文件上传操作
        // 具体使用方法参考腾讯云 COS SDK 文档
    }
}
```

## 配置项说明

| 配置项 | 说明 | 默认值 |
|--------|------|--------|
| openadexchange.tencent.cos.enable | 是否启用 COS 自动配置 | true |
| openadexchange.tencent.cos.secret-id | 腾讯云访问密钥 ID | 无 |
| openadexchange.tencent.cos.secret-key | 腾讯云访问密钥 Key | 无 |
| openadexchange.tencent.cos.region | 存储桶所在地域 | 无 |
| openadexchange.tencent.cos.bucket | 存储桶名称 | 无 |
| openadexchange.tencent.cos.domain | 自定义域名 | 无 |
| openadexchange.tencent.cos.https | 是否使用 HTTPS 协议 | true |

## 自定义 COSClient

如果默认的自动配置不能满足需求，你可以自己定义 COSClient Bean，Starter 会自动让步使用你定义的 Bean：

```java
@Configuration
public class CosConfig {
    
    @Bean
    public COSClient customCOSClient() {
        // 创建并配置自己的 COSClient 实例
        return new COSClient(/* 参数 */);
    }
}
```

## 测试

运行单元测试确保功能正常：

```bash
mvn test
```

## 许可证

Apache License 2.0