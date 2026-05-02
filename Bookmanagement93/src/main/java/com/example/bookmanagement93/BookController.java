package com.example.bookmanagement93;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public int  addBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        return this.bookService.create(createBookRequest.toBook());
    }
}
