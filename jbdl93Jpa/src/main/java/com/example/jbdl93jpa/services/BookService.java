package com.example.jbdl93jpa.services;

import com.example.jbdl93jpa.models.Book;
import com.example.jbdl93jpa.models.Genre;
import com.example.jbdl93jpa.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//    @Autowired
//    private SimpleJpaRepository<Book,Integer> bookRepository;


    public Integer create(Book book) {
        return this.bookRepository.save(book).getId();
    }

    public Book get(Integer id) {
        return this.bookRepository.findById(id).get();
    }

    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    public List<Book> getByGenre(Genre genre) {
        return this.bookRepository.findByGenre(genre);
    }

    public List<Book> getByGenreAndName(Genre genre, String name) {
        return this.bookRepository.findByGenreOrName(genre,name);
    }

    public void update(Integer id, Genre genre) {
        this.bookRepository.update(id,genre);
    }

//    public List<Book> getByGenre(Genre genre) {
//        return this.bookRepository.getByGenreNative(genre);
//    }
//    public List<Book> getByGenreJPQL(Genre genre) {
//        return this.bookRepository.getByGenreJPQL(genre);
//    }

}
