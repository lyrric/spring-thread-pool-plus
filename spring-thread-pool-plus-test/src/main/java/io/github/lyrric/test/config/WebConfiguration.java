package io.github.lyrric.test.config;

import io.github.lyrric.threadplus.ThreadPoolExecutorManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public ThreadPoolExecutorManager threadPoolExecutorMonitor(EsThreadPoolInfoConsumer esThreadPoolInfoConsumer){
        return new ThreadPoolExecutorManager(esThreadPoolInfoConsumer);
    }

}
