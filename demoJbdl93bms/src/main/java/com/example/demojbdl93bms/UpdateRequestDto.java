package com.example.demojbdl93bms;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestDto {

    private String name;
    private String author;
    private String authorEmail;
    private Double price;
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
