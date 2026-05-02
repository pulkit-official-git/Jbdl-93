package com.example.jbdl93doubts.D2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class defaultConfig {

    @Bean
    protected ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
