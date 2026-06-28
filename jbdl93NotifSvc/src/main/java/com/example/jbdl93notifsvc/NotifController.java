package com.example.jbdl93notifsvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifController {

    Logger logger = LoggerFactory.getLogger(NotifController.class);


    @GetMapping("/notif")
    public void getNotif(){

        logger.info("inside notifffff 11111");
    }

}
