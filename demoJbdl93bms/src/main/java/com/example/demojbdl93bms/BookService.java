package com.example.demojbdl93bms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public void add(Book book) throws SQLException {
        this.bookRepository.createBook(book);
    }

    public List<Book> getAll() throws SQLException {
        return this.bookRepository.getAll();
    }

    public Book getBook(int id) throws SQLException {
        return this.bookRepository.getBook(id);
    }

    public void delete(int id) throws SQLException {
        this.bookRepository.delete(id);
    }

    public void update(Book book, Integer id) throws SQLException {
        Book existingBook = this.getBook(id);

        HashMap<String, Object> existing = this.objectMapper.convertValue(existingBook,HashMap.class);
        HashMap<String,Object> incoming = this.objectMapper.convertValue(book,HashMap.class);

        for(String key : incoming.keySet()) {
            if(incoming.get(key) != null) {
                existing.put(key,incoming.get(key));
            }
        }

        existingBook = this.objectMapper.convertValue(existing,Book.class);

        this.bookRepository.update(existingBook,id);



    }
}
