package com.msbffshop.bffshop.config.resttemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigure {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
