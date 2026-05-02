package com.example.jbdl93doubts.D1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class CalculatorController {

    @Autowired
    @Qualifier("uk-bean")
    private Calculator calculator;

    @GetMapping("/calculate")
    public HashMap<String,Integer> calculate(){
        int add =  calculator.add(1,2);
        int  sub =  calculator.sub(1,2);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("add",add);
        map.put("sub",sub);
        return map;
    }
}
