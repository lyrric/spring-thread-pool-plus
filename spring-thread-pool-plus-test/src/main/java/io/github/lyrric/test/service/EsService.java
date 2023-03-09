package io.github.lyrric.test.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import io.github.lyrric.test.model.EsThreadPoolInfo;
import io.github.lyrric.threadplus.ThreadPoolInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class EsService {

    @Resource
    private ElasticsearchClient elasticsearchClient;


    public void add(ThreadPoolInfo poolInfo) throws IOException {
        IndexRequest<Object> indexRequest = new IndexRequest.Builder<>()
                .index("spring-thread-pool-plus")
                        .id(String.valueOf(System.currentTimeMillis()))
                                .document(poolInfo).build();
        elasticsearchClient.index(indexRequest);
    }
}
