package io.github.lyrric.test.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.github.lyrric.threadplus.ThreadPoolExecutorManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfiguration {

    @Resource
    private ObjectMapper objectMapper;

    @Bean
    public ThreadPoolExecutorManager threadPoolExecutorMonitor(EsThreadPoolInfoConsumer esThreadPoolInfoConsumer){
        return new ThreadPoolExecutorManager(esThreadPoolInfoConsumer);

    }
    @PostConstruct
    public void initMapper(){
        // 默认 LocalDateTime 格式,主要是要注入这个JavaTimeModule
        JavaTimeModule timeModule = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        objectMapper.registerModule(timeModule);
    }

}
