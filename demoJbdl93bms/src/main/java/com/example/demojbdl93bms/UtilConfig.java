package com.example.demojbdl93bms;

import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class UtilConfig {

    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
