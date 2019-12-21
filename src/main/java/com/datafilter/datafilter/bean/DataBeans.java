package com.datafilter.datafilter.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataBeans {

    @Bean
    public RestTemplate getRestTamplate(){
        return new RestTemplate();
    }
}
