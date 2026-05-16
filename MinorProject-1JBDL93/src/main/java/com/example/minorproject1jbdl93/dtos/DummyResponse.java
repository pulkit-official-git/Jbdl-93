package com.example.minorproject1jbdl93.dtos;

import com.example.minorproject1jbdl93.models.Author;
import com.example.minorproject1jbdl93.models.Book;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DummyResponse {

    List<Book> books;
    Author author;
}
