package io.github.lyrric.test.controller;

import io.github.lyrric.test.TestTask;
import io.github.lyrric.threadplus.decorator.ThreadPoolExecutorPlus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {



    ExecutorService threadPoolExecutor = ThreadPoolExecutorPlus.builder()
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


    @GetMapping(value = "/test/{count}")
    @ResponseBody
    public String test(@PathVariable final Integer count) {
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.submit(new TestTask());
        }
        return "success";
    }

}
