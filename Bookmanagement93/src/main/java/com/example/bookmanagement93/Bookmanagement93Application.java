package com.example.bookmanagement93;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class Bookmanagement93Application {

    public static void main(String[] args) {
        SpringApplication.run(Bookmanagement93Application.class, args);


//        book = book.name("");
//        book.getId();

//        Book book3 = new Book().Name("");

        Book book = Book.builder()
                .id(1)
                .name("Intro to Physics")
                .author("HC Verma")
                .authorEmail("verma@")
                .genre(Genre.PHYSICS)
                .build();
        System.out.println(book);

        Book.BookBuilder builder= Book.builder().id(2);

//        dkafjdsnfjks



        System.out.println(builder.build());

        builder.name("Intro to Chemistry");

        System.out.println(builder.build());




//        Book book2 = new Book(1,"Welcome to maths","CV Jawahar","jawahar@gmail",2456.20,Genre.MATHS,new Date(),new Date());

    }

}
