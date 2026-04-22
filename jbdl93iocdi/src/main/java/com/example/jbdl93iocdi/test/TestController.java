package com.example.jbdl93iocdi.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {

//    @Autowired
    private final TestService testService;
    TestRepository testRepository;

//    @Value("${int.val}")
    private int id;

//    @Value("${string.val}")
    private String name;

//    @Value("${float.val}")
    private Float score;

//    constructor injection
//    @Autowired
    public TestController(TestService testService,
                          @Value("${int.val}") int id,
                          @Value("${string.val}") String name,
                          @Value("${float.val}") Float score) {
        this.testService = testService;

        this.id = id;
        this.name = name;
        this.score = score;
        System.out.println(score);
//        this.id=id;
        this.testService.doSomething();
    }

//    @Autowired
//    public TestController(TestRepository testRepository) {
//        this.testRepository = testRepository;
////        this.testService.doSomething();
//    }

    @GetMapping("/hello")
    public HashMap<String,Object> hello(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("score",score);
        return map;
    }
}

/*
* 1. @Autowired over constructor is to give priority and if u only have 1 constructor then its ommitable
* 2. if u want to inject a final property u neeed to use constructor injection
* 3.
* */

//what is a bean?
//anything which is a part ioc container is called as a bean