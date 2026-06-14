package com.example.bookmanagement93;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateBookRequest {

    private String name;
    private String author;
    private String authorEmail;
    private double price;
    private Genre genre;

    public Book toBook(){
        return Book.builder()
                .name(name)
                .author(author)
                .authorEmail(authorEmail)
                .price(price)
                .genre(genre)
                .updatedOn(new Date())
                .build();

    }
}
