package com.example.minorproject1jbdl93.services;

import com.example.minorproject1jbdl93.models.Author;
import com.example.minorproject1jbdl93.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }
}
