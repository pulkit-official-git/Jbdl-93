package com.example.minorproject1jbdl93.services;

import com.example.minorproject1jbdl93.dtos.CreateBookResponse;
import com.example.minorproject1jbdl93.dtos.DummyResponse;
import com.example.minorproject1jbdl93.models.Author;
import com.example.minorproject1jbdl93.models.Book;
import com.example.minorproject1jbdl93.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;


    /*
    * 1. extract author
    * 2. save author in author service and repo
    * 3. get updated Author
    * 4. store updated author in book
    * 5. store book in db
    * 6. return desirable output
    * */
    public CreateBookResponse create(Book book) {

        Author author = book.getAuthor();
//        author.getBookList()
        author = this.authorService.createAuthor(author);
        book.setAuthor(author);
        book = this.bookRepository.save(book);

        return CreateBookResponse.builder()
                .bookId(book.getId())
                .createdOn(book.getCreatedOn())
                .build();
    }

    public Book get(Long id) {
        Book book =  this.bookRepository.findById(id).orElse(null);
        return book;

    }

    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }


    public List<Book> getBooksByStudentId(Long id) {

        return this.bookRepository.findByStudentId(id);
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }
}
