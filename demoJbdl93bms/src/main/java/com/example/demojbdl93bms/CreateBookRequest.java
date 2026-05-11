package com.example.demojbdl93bms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

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

    private Double price;
    private Genre genre;

    public Book toBook() {
        return Book.builder()
                .name(name)
                .author(author)
                .authorEmail(authorEmail)
                .price(price)
                .genre(genre)
                .createdOn(new Date())
                .updatedOn(new Date())
                .build();
    }
}
