package com.ambarish.logs.logprocessor.config;


import com.ambarish.logs.logprocessor.service.StatusRetrivalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    StatusRetrivalService statusRetrivalService(){
        return new StatusRetrivalService();
    }

}
