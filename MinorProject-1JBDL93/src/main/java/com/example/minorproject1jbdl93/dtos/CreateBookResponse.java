package com.example.minorproject1jbdl93.dtos;

import com.example.minorproject1jbdl93.models.Book;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookResponse {

    private Long bookId;

    private Date createdOn;
}
