package top.openadexchange.tencentcloud.cos;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "openadexchange.tencent.cos")
public class CosConfigurationProperties {

    private String secretId;
    private String secretKey;
    private String region;
    private String bucket;
    private String domain;
    private boolean enable = true;
    private boolean https = true;
}
