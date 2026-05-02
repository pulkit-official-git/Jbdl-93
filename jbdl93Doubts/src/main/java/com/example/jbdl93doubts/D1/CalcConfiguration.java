package com.example.jbdl93doubts.D1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalcConfiguration {

    @Bean("us-bean")
     Calculator calculator() {
        return new USCalculator();
    }

    @Bean("uk-bean")
    public Calculator calculator2() {
        return new UKCalculator();
    }
}
