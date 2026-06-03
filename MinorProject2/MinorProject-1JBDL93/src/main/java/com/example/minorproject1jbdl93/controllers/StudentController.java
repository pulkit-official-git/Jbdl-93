package com.example.minorproject1jbdl93.controllers;

import com.example.minorproject1jbdl93.dtos.CreateStudentRequest;
import com.example.minorproject1jbdl93.dtos.GetStudentDetailsResponse;
import com.example.minorproject1jbdl93.dtos.UpdateStudentRequest;
import com.example.minorproject1jbdl93.models.Student;
import com.example.minorproject1jbdl93.models.User;
import com.example.minorproject1jbdl93.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//if frontend says true return list of books issued by the student else don't return

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


//    @GetMapping("/get")
//    public GetStudentDetailsResponse getStudent(@RequestParam("id") Long id,
//                                                @RequestParam(value = "require-book-list",required = false,defaultValue = "false") boolean requireBookList){
//        return this.studentService.getStudent(id,requireBookList);
//    }


    @PostMapping("/create")
    public GetStudentDetailsResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        return this.studentService.create(createStudentRequest.toStudent());
    }

    @GetMapping("/admin/get")
    public GetStudentDetailsResponse getStudentByAdmin(@RequestParam("id") Long id){
        return this.studentService.getStudent(id);
    }

    @GetMapping("/get")
    public GetStudentDetailsResponse getStudent(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User)authentication.getPrincipal();
        Long id = user.getStudent().getId();
        return this.studentService.getStudent(id);
    }

    @PutMapping("/update")
    public GetStudentDetailsResponse updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User)authentication.getPrincipal();
        Long id = user.getStudent().getId();
        return this.studentService.update(id,updateStudentRequest.toStudent());
    }


    @DeleteMapping("/delete")
    public void deleteStudent(){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User)authentication.getPrincipal();
        Long id = user.getStudent().getId();
        this.studentService.deActivate(id);
    }



}
