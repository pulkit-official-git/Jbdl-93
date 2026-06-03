package com.example.minorproject1jbdl93.controllers;

import com.example.minorproject1jbdl93.models.TransactionType;
import com.example.minorproject1jbdl93.models.User;
import com.example.minorproject1jbdl93.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initiate")
    private String initiateTxn(
            @RequestParam Long bookId,
            @RequestParam TransactionType transactionType
    ) throws Exception {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User)authentication.getPrincipal();
        Long studentId = user.getStudent().getId();

        return this.transactionService.create(studentId,bookId,transactionType);

    }
}
