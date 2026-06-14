package com.example.jbdl93jpa.controllers;

import com.example.jbdl93jpa.dtos.CreateBookRequest;
import com.example.jbdl93jpa.models.Book;
import com.example.jbdl93jpa.models.Genre;
import com.example.jbdl93jpa.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/create")
    public Integer createBook(@RequestBody CreateBookRequest createBookRequest) {
        return this.bookService.create(createBookRequest.toBook());
    }

    @GetMapping("/get/{id}")
    public Book getBook(@PathVariable("id") Integer id) {
        return this.bookService.get(id);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return this.bookService.getAll();
    }

//    @GetMapping("/get/genre/{genre}")
//    public List<Book> getBooksByGenre(@PathVariable("genre") Genre genre) {
//        return this.bookService.getByGenre(genre);
//    }
//
//    @GetMapping("/get/genre/jpql/{genre}")
//    public List<Book> getBooksByGenreJPQL(@PathVariable("genre") Genre genre) {
//        return this.bookService.getByGenre(genre);
//    }

    @GetMapping("/get/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable("genre") Genre genre) {
        return this.bookService.getByGenre(genre);
    }


    @GetMapping("/get/genre/{genre}/name/{name}")
    public List<Book> getBooksByGenreAndName(@PathVariable("genre") Genre genre,
                                             @PathVariable("name") String name) {
        return this.bookService.getByGenreAndName(genre,name);
    }

    @PutMapping("/update/genre/{genre}")
    public void UpdateGenreById(@PathVariable("genre")Genre genre,
            @RequestParam Integer id) {
        this.bookService.update(id,genre);
    }

}
