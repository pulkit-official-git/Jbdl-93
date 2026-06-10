package com.example.jbdl93rediscache.dtos;


import com.example.jbdl93rediscache.models.Gender;
import com.example.jbdl93rediscache.models.Person;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePersonRequest {


    private String name;

    private Integer age;

    private Gender gender;


    public Person toPerson(){
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .age(age)
                .gender(gender)
                .createdOn(new Date())
                .updatedOn(new Date())
                .build();
    }
}
