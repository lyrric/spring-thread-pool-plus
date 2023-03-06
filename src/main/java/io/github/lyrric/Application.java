package io.github.lyrric;

import io.github.lyrric.core.ThreadPoolExecutorMonitor;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public ThreadPoolExecutorMonitor threadPoolExecutorMonitor(){
        return new ThreadPoolExecutorMonitor(t->{
            LoggerFactory.getLogger(ThreadPoolExecutorMonitor.class).info(t.toString());
        });
    }


}
