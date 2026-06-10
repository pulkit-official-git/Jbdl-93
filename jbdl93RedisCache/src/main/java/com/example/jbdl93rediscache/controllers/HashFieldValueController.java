package com.example.jbdl93rediscache.controllers;

import com.example.jbdl93rediscache.dtos.CreatePersonRequest;
import com.example.jbdl93rediscache.models.Person;
import com.example.jbdl93rediscache.services.HashFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hash")
public class HashFieldValueController {

    @Autowired
    HashFieldValueService hashFieldValueService;

    @PostMapping("/create")
    public Person create(@RequestBody CreatePersonRequest createPersonRequest){
        return this.hashFieldValueService.create(createPersonRequest.toPerson());
    }

    @GetMapping("/get")
    public Object get(@RequestParam String id,
                      @RequestParam String field){
        return this.hashFieldValueService.getF(id,field);
    }

    @GetMapping("/getAll")
    public Person getAll(@RequestParam String id){
        return this.hashFieldValueService.getAll(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id,
    @RequestParam String field ){
        this.hashFieldValueService.delete(id,field);
    }
}
