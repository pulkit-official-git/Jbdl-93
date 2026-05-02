package com.example.bookmanagement93;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {


    private int id;
    private String name;
    private String author;
    private String authorEmail;
    private double price;
    private Genre genre;
    private Date createdOn;
    private Date updatedOn;

}
