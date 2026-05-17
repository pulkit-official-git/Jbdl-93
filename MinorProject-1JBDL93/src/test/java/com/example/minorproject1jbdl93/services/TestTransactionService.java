package com.example.minorproject1jbdl93.services;

import com.example.minorproject1jbdl93.dtos.GetStudentDetailsResponse;
import com.example.minorproject1jbdl93.models.*;
import com.example.minorproject1jbdl93.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.UUID;

//You should not write test cases only for happy cases, you also need to write test cases for failing or exceptions


@RunWith(MockitoJUnitRunner.class)
public class TestTransactionService {

//    @Test
//    public void testAdd(){
//
//        TransactionService transactionService = new TransactionService();
//
//        int result = transactionService.add(5,6);
//
//        int expectedOutput=12;
//
//        Assert.assertEquals(expectedOutput,result);
//
//    }

    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    BookService bookService;

    @Mock
    StudentService studentService;


    @Test
    public void testCalculateFine(){

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Maths")
                .build();

        Student student = Student.builder()
                .id(1L)
                .name("posty")
                .build();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1747483271000L))
                .build();

        Mockito.when(transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                Mockito.eq(student),
                Mockito.eq(book),
                Mockito.eq(TransactionType.ISSUANCE),
                Mockito.eq(TransactionStatus.SUCCESS)
                ))
                .thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentBookDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",100);

        int actualOutput = transactionService.calculateFine(book,student);
        int expectedOutput= 35000;
        Assert.assertEquals(actualOutput,expectedOutput);
    }

    @Test
    public void testCalculateFineWithNoFine(){

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Maths")
                .build();

        Student student = Student.builder()
                .id(1L)
                .name("posty")
                .build();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1779019271000L))
                .build();

        Mockito.when(transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                        Mockito.eq(student),
                        Mockito.eq(book),
                        Mockito.eq(TransactionType.ISSUANCE),
                        Mockito.eq(TransactionStatus.SUCCESS)
                ))
                .thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentBookDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",100);

        int actualOutput = transactionService.calculateFine(book,student);
        int expectedOutput= 0;
        Assert.assertEquals(actualOutput,expectedOutput);
    }


    @Test
    public void testInitiateReturn() throws Exception {



        Student student = Student.builder()
                .id(1L)
                .name("posty")
                .build();

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Maths")
                .student(student)
                .build();

        GetStudentDetailsResponse getStudentDetailsResponse = GetStudentDetailsResponse.builder()
                .student(student)
                .build();

        Mockito.when(studentService.getStudent(Mockito.eq(1L))).thenReturn(getStudentDetailsResponse);
        Mockito.when(bookService.get(Mockito.eq(1L))).thenReturn(book);

        String expectedOutput = UUID.randomUUID().toString();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .txnId(expectedOutput)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1779019271000L))
                .build();

        Mockito.when(transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                        Mockito.eq(student),
                        Mockito.eq(book),
                        Mockito.eq(TransactionType.ISSUANCE),
                        Mockito.eq(TransactionStatus.SUCCESS)
                ))
                .thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentBookDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",100);

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        Mockito.when(bookService.save(Mockito.any())).thenReturn(book);

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);


        String actualOutput = transactionService.initiateReturn(1L,1L);

        Assert.assertEquals(expectedOutput,actualOutput);



    }


    @Test(expected = Exception.class)
    public void testInitiateReturnWithException() throws Exception {



        Student student = Student.builder()
                .id(1L)
                .name("posty")
                .build();

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Maths")
                .build();

        GetStudentDetailsResponse getStudentDetailsResponse = GetStudentDetailsResponse.builder()
                .student(student)
                .build();

        Mockito.when(studentService.getStudent(Mockito.eq(1L))).thenReturn(getStudentDetailsResponse);
        Mockito.when(bookService.get(Mockito.eq(1L))).thenReturn(book);

        String actualOutput = transactionService.initiateReturn(1L,1L);


    }


}
