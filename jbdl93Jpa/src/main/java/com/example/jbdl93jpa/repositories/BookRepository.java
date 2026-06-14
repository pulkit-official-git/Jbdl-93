package com.example.jbdl93jpa.repositories;

import com.example.jbdl93jpa.models.Book;
import com.example.jbdl93jpa.models.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(Genre genre);

    List<Book> findByGenreAndName(Genre genre, String name);

    List<Book> findByGenreOrName(Genre genre, String name);

    @Transactional
    @Modifying
    @Query("update Book b set b.genre = ?2 where b.id = ?1")
    void update(Integer id, Genre genre);

//    @Query("select b from Book b where b.genre = :genre")
//    List<Book> getByGenreJPQL(Genre genre);
//
//    @Query("select b from Book b where b.genre = ?1")
//    List<Book> getByGenreJPQL2(Genre genre);
//    
//    @Query(value = "select * from my_book where genre = :genre",nativeQuery = true)
//    List<Book> getByGenreNative(Genre genre);
//
//    @Query(value = "select * from my_book where genre = ?1",nativeQuery = true)
//    List<Book> getByGenreNative2(Genre genre);
}
//exception