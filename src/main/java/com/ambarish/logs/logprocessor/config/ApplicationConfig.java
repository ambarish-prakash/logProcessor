package com.ambarish.logs.logprocessor.config;


import com.ambarish.logs.logprocessor.adapter.LogLineAdapter;
import com.ambarish.logs.logprocessor.service.LogInsertionService;
import com.ambarish.logs.logprocessor.service.StatusRetrivalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfig {

    @Value("${app.upload.batchSize}")
    int batchSize;

    @Value("${app.upload.threadPoolSize}")
    int threadPoolSize;

    @Bean
    StatusRetrivalService statusRetrivalService(){
        return new StatusRetrivalService();
    }

    @Bean
    LogInsertionService logInsertionService(ExecutorService executorService){
        return new LogInsertionService(executorService,batchSize);
    }

    @Bean
    LogLineAdapter logLineAdapter(){
        return new LogLineAdapter();
    }

    @Bean
    ExecutorService executorService(){
        return Executors.newFixedThreadPool(threadPoolSize);
    }

}
