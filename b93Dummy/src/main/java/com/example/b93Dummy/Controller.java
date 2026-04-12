package com.example.b93Dummy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }



//    crud

/*Post create unsafe
//Get read safe
//Put update unsafe
//Patch update unsafe
//Delete delete unsafe
//Options  safe
//Head safe
* */

}
