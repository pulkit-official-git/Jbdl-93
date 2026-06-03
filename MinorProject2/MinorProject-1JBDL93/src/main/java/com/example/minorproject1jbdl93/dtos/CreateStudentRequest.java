package com.example.minorproject1jbdl93.dtos;


import com.example.minorproject1jbdl93.models.Gender;
import com.example.minorproject1jbdl93.models.Student;
import com.example.minorproject1jbdl93.models.StudentStatus;
import com.example.minorproject1jbdl93.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {


    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Email
    private String email;


    private Gender gender;

    public Student toStudent(){
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .gender(this.gender)
                .status(StudentStatus.ACTIVE)
                .user(User.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build();
    }
}
