package com.example.jbdl93inmemorysecurity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String adminHello(){
        return "hello Admin";
    }

    @GetMapping("/user/hello")
    public String userHello(){
        return "hello User";
    }

    @GetMapping("/user/admin/hello")
    public String userAdminHello(){
        return "hello User";
    }


}
