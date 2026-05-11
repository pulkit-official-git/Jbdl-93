package com.example.demojbdl93bms;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public void createBook(@Valid @RequestBody CreateBookRequest createBookRequest) throws SQLException {
        this.bookService.add(createBookRequest.toBook());
    }

    @GetMapping("/getAll")
    public List<Book> getAllBooks() throws SQLException {
        return this.bookService.getAll();
    }

    @GetMapping("/get")
    public Book getBookById(@RequestParam int id) throws SQLException {
        return this.bookService.getBook(id);
    }

    @DeleteMapping("/delete")
    public void deleteBookById(@RequestParam int id) throws SQLException {
        this.bookService.delete(id);
    }

    @PutMapping("/patch")
    public void update(@RequestBody UpdateRequestDto updateRequestDto,
    @RequestParam Integer id) throws SQLException {

        this.bookService.update(updateRequestDto.toBook(), id);
    }



}
