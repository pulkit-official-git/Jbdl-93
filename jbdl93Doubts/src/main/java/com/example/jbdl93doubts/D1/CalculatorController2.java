package com.example.jbdl93doubts.D1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;

@RestController
public class CalculatorController2 {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    @Qualifier("us-bean")
    private Calculator calculator;

    @GetMapping("/calculate2")
    public HashMap<String,Integer> calculate2(){
        int add =  calculator.add(1,2);
        int  sub =  calculator.sub(1,2);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("add",add);
        map.put("sub",sub);
        return map;
    }
}
