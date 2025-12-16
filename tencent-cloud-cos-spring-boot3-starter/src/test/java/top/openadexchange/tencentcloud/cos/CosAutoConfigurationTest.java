package top.openadexchange.tencentcloud.cos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.qcloud.cos.COSClient;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CosAutoConfiguration 单元测试类
 */
class CosAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner().withConfiguration(AutoConfigurations.of(CosAutoConfiguration.class));

    @Test
    void cosClientShouldBeCreatedWhenPropertiesAreSet() {
        this.contextRunner.withPropertyValues("openadexchange.tencent.cos.enable=true",
                "openadexchange.tencent.cos.secret-id=test-secret-id",
                "openadexchange.tencent.cos.secret-key=test-secret-key",
                "openadexchange.tencent.cos.region=ap-beijing").run(context -> {
            assertThat(context).hasSingleBean(COSClient.class);
            assertThat(context.getBean(COSClient.class)).isNotNull();
        });
    }

    @Test
    void cosClientShouldNotBeCreatedWhenEnableIsFalse() {
        this.contextRunner.withPropertyValues("openadexchange.tencent.cos.enable=false",
                "openadexchange.tencent.cos.secret-id=test-secret-id",
                "openadexchange.tencent.cos.secret-key=test-secret-key",
                "openadexchange.tencent.cos.region=ap-beijing").run(context -> {
            assertThat(context).doesNotHaveBean(COSClient.class);
        });
    }

    @Test
    void cosClientShouldUseDefaultEnableValue() {
        this.contextRunner.withPropertyValues("openadexchange.tencent.cos.secret-id=test-secret-id",
                "openadexchange.tencent.cos.secret-key=test-secret-key",
                "openadexchange.tencent.cos.region=ap-beijing").run(context -> {
            assertThat(context).hasSingleBean(COSClient.class);
        });
    }

    @Test
    void cosClientShouldNotBeCreatedWhenPropertiesAreMissing() {
        this.contextRunner.run(context -> {
            assertThrows(IllegalStateException.class, () -> {
                context.getBean(COSClient.class);
            });
        });
    }

    @Test
    void cosClientShouldRespectHttpsSettingWhenTrue() {
        this.contextRunner.withPropertyValues("openadexchange.tencent.cos.enable=true",
                "openadexchange.tencent.cos.secret-id=test-secret-id",
                "openadexchange.tencent.cos.secret-key=test-secret-key",
                "openadexchange.tencent.cos.region=ap-beijing",
                "openadexchange.tencent.cos.https=true").run(context -> {
            assertThat(context).hasSingleBean(COSClient.class);
        });
    }

    @Test
    void cosClientShouldRespectHttpsSettingWhenFalse() {
        this.contextRunner.withPropertyValues("openadexchange.tencent.cos.enable=true",
                "openadexchange.tencent.cos.secret-id=test-secret-id",
                "openadexchange.tencent.cos.secret-key=test-secret-key",
                "openadexchange.tencent.cos.region=ap-beijing",
                "openadexchange.tencent.cos.https=false").run(context -> {
            assertThat(context).hasSingleBean(COSClient.class);
        });
    }
}