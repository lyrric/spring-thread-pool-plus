package io.github.lyrric.controller;

import io.github.lyrric.Application;
import io.github.lyrric.core.ThreadPoolExecutorDecorator;
import io.github.lyrric.task.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    ExecutorService threadPoolExecutor = ThreadPoolExecutorDecorator.builder()
            .corePoolSize(5)
            .maximumPoolSize(20)
            .key("test")
            .name("kafka")
            .queueWarningRatio(0.6d)
            .execTimeout(3000)
            .waitTimeout(5000)
            .keepAliveTime(30)
            .unit(TimeUnit.SECONDS)
            .workQueue(new ArrayBlockingQueue<>(100))
            .build();


    @GetMapping(value = "/test/{count}/{sleepTime}")
    @ResponseBody
    public String test(@PathVariable final Integer count, @PathVariable final long sleepTime) {
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.submit(new MyTask());
        }
        return "success";
    }
}
