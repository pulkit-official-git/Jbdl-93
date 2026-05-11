package com.example.demojbdl93bms;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private Integer id;
    private String name;
    private String author;
    private String authorEmail;
    private Double price;
    private Genre genre;
    private Date createdOn;
    private Date updatedOn;

}
