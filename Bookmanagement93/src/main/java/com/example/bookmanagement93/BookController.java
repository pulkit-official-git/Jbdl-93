package com.example.bookmanagement93;

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
    public void  addBook(@Valid @RequestBody CreateBookRequest createBookRequest) throws SQLException {
        this.bookService.create(createBookRequest.toBook());
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() throws SQLException {
        return this.bookService.getAll();
    }

    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable("id") int id) throws SQLException {
        return this.bookService.getById(id);
    }

    @DeleteMapping("/delete/id")
    public void deleteBookById(@RequestParam int id) throws SQLException {
        this.bookService.delete(id);
    }

    @PutMapping("/update")
    public void update(@RequestParam int id, @RequestBody UpdateBookRequest updateBookRequest) throws SQLException {
        this.bookService.update(id,updateBookRequest.toBook());
    }
}
