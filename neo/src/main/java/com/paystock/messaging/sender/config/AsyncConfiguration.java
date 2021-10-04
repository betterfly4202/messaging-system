package com.paystock.messaging.sender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
public class AsyncConfiguration implements AsyncConfigurer {
    private final int DEFAULT_THREAD_SIZE = 1;
    private final int QUEUE_CAPACITY_SIZE = 100;

    @Bean
    public Executor asyncExecutor(){
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;

        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(DEFAULT_THREAD_SIZE);
        threadPool.setQueueCapacity(QUEUE_CAPACITY_SIZE);
        threadPool.setMaxPoolSize(corePoolSize);
        threadPool.initialize();

        return threadPool;
    }
}
