package com.example.minorproject1jbdl93.dtos;

import com.example.minorproject1jbdl93.models.Book;
import com.example.minorproject1jbdl93.models.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentDetailsResponse {

    Student student;

//    List<Book> books;
}
