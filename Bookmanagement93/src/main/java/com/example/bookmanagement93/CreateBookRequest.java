package com.example.bookmanagement93;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateBookRequest {

    @NotBlank
    private String name;
    private String author;
    @Email
    private String authorEmail;
    private double price;
    private Genre genre;

    public Book toBook() {
        return Book.builder()
                .name(name)
                .author(author)
                .authorEmail(authorEmail)
                .price(price)
                .genre(genre)
                .build();
    }
}
