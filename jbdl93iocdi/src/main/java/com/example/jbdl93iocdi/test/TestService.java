package com.example.jbdl93iocdi.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    ObjectMapper objectMapper;

    public void doSomething(){
        System.out.println("doSomething");
    }
}
