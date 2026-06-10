package com.example.jbdl93rediscache.controllers;

import com.example.jbdl93rediscache.dtos.CreatePersonRequest;
import com.example.jbdl93rediscache.models.Person;
import com.example.jbdl93rediscache.services.StringValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/string")
public class StringValueController {

    @Autowired
    StringValueService stringValueService;


    @PostMapping("/create")
    public Person create(@RequestBody CreatePersonRequest createPersonRequest){
        return this.stringValueService.create(createPersonRequest.toPerson());
    }

    @GetMapping("/get")
    public Person get(@RequestParam String id){
        return this.stringValueService.get(id);
    }




}
