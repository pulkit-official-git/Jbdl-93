package com.example.minorproject1jbdl93.repositories;

import com.example.minorproject1jbdl93.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByStudentId(Long id);
}
