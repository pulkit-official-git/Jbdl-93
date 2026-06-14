package com.example.jbdl93jpa.dtos;

import com.example.jbdl93jpa.models.Book;
import com.example.jbdl93jpa.models.Genre;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String name;
    private String author;
    @Email
    private String authorEmail;
    private Genre genre;

    public Book toBook() {
        return Book.builder()
                .name(name)
                .author(author)
                .authorEmail(authorEmail)
                .genre(genre)
                .build();
    }
}
