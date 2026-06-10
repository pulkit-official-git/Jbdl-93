package com.example.jbdl93rediscache.controllers;

import com.example.jbdl93rediscache.dtos.CreatePersonRequest;
import com.example.jbdl93rediscache.models.Person;
import com.example.jbdl93rediscache.services.ListValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListValueController {

    @Autowired
    ListValueService listValueService;


    @PostMapping("/lpush")
    public Long lpush(@RequestBody CreatePersonRequest createPersonRequest){
       return this.listValueService.lpush(createPersonRequest.toPerson());
    }

    @PostMapping("/rpush")
    public Long rpush(@RequestBody CreatePersonRequest createPersonRequest){
        return this.listValueService.rpush(createPersonRequest.toPerson());
    }

    @PostMapping("/lpop")
    public List<Person> lpop(@RequestParam(name="count",required = false,defaultValue = "1") Integer count){
        return this.listValueService.lpop(count);
    }

    @PostMapping("/rpop")
    public List<Person> rpop(@RequestParam(name="count",required = false,defaultValue = "1") Integer count){
        return this.listValueService.rpop(count);
    }


    @GetMapping("/lrange")
    public List<Person> lrange(@RequestParam(name="start",required = false,defaultValue = "0") Integer start,
                               @RequestParam(name="end",required = false,defaultValue = "-1") Integer end){
        return this.listValueService.lrange(start,end);
    }

}
