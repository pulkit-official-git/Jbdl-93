package com.example.minorproject1jbdl93.dtos;


import com.example.minorproject1jbdl93.models.Author;
import com.example.minorproject1jbdl93.models.Book;
import com.example.minorproject1jbdl93.models.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String name;

    @NonNull
    private Genre genre;

    @NotBlank
    private String authorName;

    @Email
    private String email;

    public Book toBook(){

        return Book.builder()
                .name(name)
                .genre(genre)
                .author(Author.
                        builder()
                        .name(authorName)
                        .email(email)
                        .build())
                .build();

    }

}
