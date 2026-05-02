package com.example.jbdl93doubts.D2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController2 {

    @Autowired
    @Qualifier("demo-2")
    private DemoService demoService;
}
