package io.github.lyrric.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class ThreadPoolInfoConsumer implements Consumer<ThreadPoolInfo> {


    private static final Logger log = LoggerFactory.getLogger(ThreadPoolInfoConsumer.class);


    @Override
    public void accept(ThreadPoolInfo threadPoolInfo) {
        log.info(threadPoolInfo.toString());
    }


}
