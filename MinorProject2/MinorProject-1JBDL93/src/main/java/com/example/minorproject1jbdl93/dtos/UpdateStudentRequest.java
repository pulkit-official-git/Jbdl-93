package com.example.minorproject1jbdl93.dtos;

import com.example.minorproject1jbdl93.models.Gender;
import com.example.minorproject1jbdl93.models.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStudentRequest {

    private String name;

    private String email;

    private Gender gender;

    public Student toStudent(){
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .gender(this.gender)
                .build();
    }
}
