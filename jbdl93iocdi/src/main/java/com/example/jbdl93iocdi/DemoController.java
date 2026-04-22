package com.example.jbdl93iocdi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    Person person;


//    @Autowired
//    Person person = new Person(); never do this as it will create 2 different objects 1 yours and 1 springs

//    field injection

//    public DemoController() {
//        this.person = new  Person();
//    }

    public DemoController() {
        logger.info("Inside DemoController constructor: this = {} and person = {}", this,this.person);
    }


    @GetMapping("/demo")
    public String demo() {
//        Person person = new Person(1,"fred");
        logger.info("Inside demo constructor person = {}", person);
        return "Hello World";
    }

    /*IOC -> Inversion of Control
    * 1. Spring create objects before starting the application
    * 2. If you want spring to create a object, you will have to tell spring
    * 3. if a class has an @Component annotation directly or indirectly that means spring is creating this object.
    * 4. if spring creates the object, spring will manage it.
    * 5. when spring creates an object, then the memory allocated to spring is called as IOC container
    * and programmatically its called as APPLICATION CONTEXT INTERFACE.
    * */

    /*DI -> Dependency Injection
    * 1. A phenomena in which a particular class is a part of ioc container and i want a reference of another class which
    * is also part of ioc container so we can the get the reference .
    * 2. we can use @Autowired for getting the reference of person class in democontroller
    * 3.getting the reference == injecting the dependency
    * 4. if object is not present in IOC Container , then there is no possibility of dependency injection
    * */


//    spring created Inside default constructor this = com.example.jbdl93iocdi.Person@b16e202
//    com.example.jbdl93iocdi.Person@18da4dd
//    com.example.jbdl93iocdi.Person@22c78700
//    com.example.jbdl93iocdi.Person@2fa7c460

//    Inside default constructor this = com.example.jbdl93iocdi.Person@7d04529c spring created
//Inside demo constructor person = com.example.jbdl93iocdi.Person@7d04529c


//    Inside default constructor this = com.example.jbdl93iocdi.Person@29a1505c

//    Inside demo constructor person = com.example.jbdl93iocdi.Person@75961f16

//    Inside demo constructor person = com.example.jbdl93iocdi.Person@2a2ef072
//    Inside demo constructor person = com.example.jbdl93iocdi.Person@c6c82aa
}
