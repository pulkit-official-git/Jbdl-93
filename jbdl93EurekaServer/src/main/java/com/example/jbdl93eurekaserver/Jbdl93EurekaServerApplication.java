package com.example.jbdl93eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Jbdl93EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jbdl93EurekaServerApplication.class, args);
    }

}
