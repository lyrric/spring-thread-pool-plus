package io.github.lyrric.test.config;

import io.github.lyrric.test.model.EsThreadPoolInfo;
import io.github.lyrric.test.service.EsService;
import io.github.lyrric.threadplus.ThreadPoolInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Component
public class EsThreadPoolInfoConsumer implements Consumer<ThreadPoolInfo> {

    private final EsService esService;

    private static final Logger log = LoggerFactory.getLogger(EsThreadPoolInfoConsumer.class);

    public EsThreadPoolInfoConsumer(EsService esService) {
        this.esService = esService;
    }


    @Override
    public void accept(ThreadPoolInfo threadPoolInfo) {
        log.info(threadPoolInfo.toString());
        EsThreadPoolInfo data = new EsThreadPoolInfo();
        BeanUtils.copyProperties(threadPoolInfo, data);
        try {
            esService.add(threadPoolInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
