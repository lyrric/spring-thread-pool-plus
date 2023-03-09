package io.github.lyrric.threadplus;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@RefreshScope
@EnableConfigurationProperties(value = ThreadPoolPlusProperties.class)
@EnableScheduling
public class ThreadPoolPlusConfiguration {


}
