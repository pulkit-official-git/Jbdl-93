package com.example.jbdl93iocdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController2 {

    Logger logger = LoggerFactory.getLogger(DemoController2.class);

    @Autowired
    Person person ;

    @GetMapping("/demo2")
    public String demo() {
//        Person person = new Person(1,"fred");
        logger.info("Inside demo constructor person = {}", person);
        return "Hello World";
    }
}
