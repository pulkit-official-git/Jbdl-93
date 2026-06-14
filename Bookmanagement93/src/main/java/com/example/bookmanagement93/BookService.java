package com.example.bookmanagement93;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void create(Book book) throws SQLException {
        this.bookRepository.createBook(book);
    }

    public List<Book> getAll() throws SQLException {
        return this.bookRepository.getAll();
    }

    public Book getById(int id) throws SQLException {
        return this.bookRepository.getById(id);
    }

    public void delete(int id) throws SQLException {
        this.bookRepository.delete(id);
    }

    public void update(int id, Book book) throws SQLException {
        Book existingBook = this.getById(id);

        HashMap<String, Object> existingMap = this.objectMapper.convertValue(existingBook, HashMap.class);
        HashMap<String, Object> incomingMap = this.objectMapper.convertValue(book, HashMap.class);

        for( String key : incomingMap.keySet() ){
            if(incomingMap.get(key)!=null){
                existingMap.put(key,incomingMap.get(key));
            }
        }

        existingBook = this.objectMapper.convertValue(existingMap, Book.class);

        this.bookRepository.update(existingBook,id);





    }
}
