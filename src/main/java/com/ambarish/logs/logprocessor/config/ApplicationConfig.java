package com.ambarish.logs.logprocessor.config;


import com.ambarish.logs.logprocessor.adapter.LogLineAdapter;
import com.ambarish.logs.logprocessor.service.LogInsertionService;
import com.ambarish.logs.logprocessor.service.StatusRetrivalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    StatusRetrivalService statusRetrivalService(){
        return new StatusRetrivalService();
    }

    @Bean
    LogInsertionService logInsertionService(){
        return new LogInsertionService();
    }

    @Bean
    LogLineAdapter logLineAdapter(){
        return new LogLineAdapter();
    }

}
