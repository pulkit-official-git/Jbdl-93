package com.example.jbdl93doubts.D2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    @Bean("demo-1")
    public DemoService demoService() {
        return new DemoService();
    }

    @Bean("demo-2")
    public DemoService demoService2() {
        return new DemoService();
    }

}
