package com.example.jbdl93rediscache.models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {

    private String id;

    private String name;

    private Integer age;

    private Gender gender;

    private Date createdOn;

    private Date updatedOn;

}
