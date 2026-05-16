package com.example.minorproject1jbdl93.services;

import com.example.minorproject1jbdl93.dtos.GetStudentDetailsResponse;
import com.example.minorproject1jbdl93.models.Book;
import com.example.minorproject1jbdl93.models.Student;
import com.example.minorproject1jbdl93.models.StudentStatus;
import com.example.minorproject1jbdl93.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public GetStudentDetailsResponse create(Student student) {
         Student savedStudent = this.studentRepository.save(student);
         return GetStudentDetailsResponse.builder()
                 .student(savedStudent)
                 .build();
    }

    public GetStudentDetailsResponse getStudent(Long id) {
        Student student = this.studentRepository.findById(id).get();
        return GetStudentDetailsResponse.builder()
                 .student(student)
                 .build();
    }

    public GetStudentDetailsResponse update(Long id, Student student) {

        Student existingStudent = this.studentRepository.findById(id).get();
        if(existingStudent == null){
            return null;
        }
        Student mergedStudent = this.merge(student,existingStudent);
        mergedStudent = this.studentRepository.save(mergedStudent);

        return GetStudentDetailsResponse.builder()
                 .student(mergedStudent)
                 .build();
    }

    private Student merge(Student incoming, Student existing){

        HashMap<String,Object> incomingMap = this.objectMapper.convertValue(incoming, HashMap.class);
        HashMap<String,Object> existingMap = this.objectMapper.convertValue(existing, HashMap.class);

        for(String obj : incomingMap.keySet()){
            if(incomingMap.get(obj) != null){
                existingMap.put(obj,incomingMap.get(obj));
            }
        }

        Student mergedStudent = this.objectMapper.convertValue(existingMap, Student.class);
        return mergedStudent;
    }

    public void deActivate(Long id) {
        Student existingStudent = this.studentRepository.findById(id).get();
        if(existingStudent != null) {
            this.studentRepository.deactivate(id, StudentStatus.INACTIVE);
        }

    }


//    public GetStudentDetailsResponse getStudent(Long id,boolean requireBookList) {
//
//        Student student = this.studentRepository.findById(id).get();
//
//        List<Book>bookList=null;
//        if (requireBookList) {
//            bookList = this.bookService.getBooksByStudentId(student.getId());
//        }
//
//        return GetStudentDetailsResponse.builder()
//                .student(student)
//                .books(bookList)
//                .build();
//    }
}
