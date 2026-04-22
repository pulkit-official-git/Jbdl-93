package com.example.jbdl93iocdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
//@Scope("prototype")
public class Person {

    Logger log = LoggerFactory.getLogger(this.getClass());

    int id;
    String name;

    public Person() {
        log.info("Inside default constructor this = {}", this);
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        log.info("Inside parameterised constructor this = {}", this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
