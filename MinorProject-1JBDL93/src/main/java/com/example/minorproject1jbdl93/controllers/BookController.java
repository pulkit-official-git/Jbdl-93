package com.example.minorproject1jbdl93.controllers;

import com.example.minorproject1jbdl93.dtos.CreateBookRequest;
import com.example.minorproject1jbdl93.dtos.CreateBookResponse;
import com.example.minorproject1jbdl93.models.Book;
import com.example.minorproject1jbdl93.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/create")
    public CreateBookResponse createBook(@RequestBody CreateBookRequest createBookRequest){
        return this.bookService.create(createBookRequest.toBook());
    }

    @GetMapping("/get")
    public Book getBook(@RequestParam Long id){
        return this.bookService.get(id);
    }
}
