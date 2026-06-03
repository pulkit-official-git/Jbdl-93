package com.example.minorproject1jbdl93.services;

import com.example.minorproject1jbdl93.dtos.CreateBookResponse;
import com.example.minorproject1jbdl93.dtos.GetStudentDetailsResponse;
import com.example.minorproject1jbdl93.models.*;
import com.example.minorproject1jbdl93.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TestTransactionService {


//    @Test
//    public void testAdd(){
//        TransactionService transactionService = new TransactionService();
//
//        int result = transactionService.add(5,6);
//
//        Assert.assertEquals(12,result);
//    }

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    @Mock
    BookService bookService;

    @Mock
    StudentService studentService;

    @Test
    public void testCalculateFine(){

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Music")
                .build();

        Student student = Student.builder()
                .id(1L)
                .name("Ram")
                .build();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1747474954000L))
                .build();

        Mockito.when(this.transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                Mockito.eq(student),
                Mockito.eq(book),
                Mockito.eq(TransactionType.ISSUANCE),
                Mockito.eq(TransactionStatus.SUCCESS)

        )).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentBookDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",100);


        int actualFine = 35000;
        int expectedFine = this.transactionService.calculateFine(book,student);

        Assert.assertEquals(actualFine,expectedFine);

    }


    @Test
    public void testCalculateFineWithNoFine(){

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Music")
                .build();

        Student student = Student.builder()
                .id(1L)
                .name("Ram")
                .build();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1779011548000L))
                .build();

        Mockito.when(this.transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                Mockito.eq(student),
                Mockito.eq(book),
                Mockito.eq(TransactionType.ISSUANCE),
                Mockito.eq(TransactionStatus.SUCCESS)

        )).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentBookDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",100);


        int actualFine = 0;
        int expectedFine = this.transactionService.calculateFine(book,student);

        Assert.assertEquals(actualFine,expectedFine);

    }

    @Test
    public void TestInitiateReturn() throws Exception {



        Student student = Student.builder()
                .id(1L)
                .name("Ram")
                .build();

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Music")
                .student(student)
                .build();

        GetStudentDetailsResponse getStudentDetailsResponse = GetStudentDetailsResponse.builder()
                .student(student)
                .build();

        Mockito.when(bookService.get(Mockito.eq(1L))).thenReturn(book);
        Mockito.when(studentService.getStudent(Mockito.eq(1L))).thenReturn(getStudentDetailsResponse);


        String expectedTxnId = UUID.randomUUID().toString();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .txnId(expectedTxnId)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1779011548000L))
                .build();

        Mockito.when(this.transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                Mockito.eq(student),
                Mockito.eq(book),
                Mockito.eq(TransactionType.ISSUANCE),
                Mockito.eq(TransactionStatus.SUCCESS)

        )).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService,"studentBookDaysLimit",15);
        ReflectionTestUtils.setField(transactionService,"finePerDay",100);

        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
        Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(book);

        String actualTxnId = transactionService.initiateReturn(1L,1L);

        Assert.assertEquals(actualTxnId,expectedTxnId);


    }

    @Test(expected = Exception.class)
    public void TestInitiateReturnWithException() throws Exception {



        Student student = Student.builder()
                .id(1L)
                .name("Ram")
                .build();

        Book book = Book.builder()
                .id(1L)
                .name("Intro to Music")
                .build();

        GetStudentDetailsResponse getStudentDetailsResponse = GetStudentDetailsResponse.builder()
                .student(student)
                .build();

        Mockito.when(bookService.get(Mockito.eq(1L))).thenReturn(book);
        Mockito.when(studentService.getStudent(Mockito.eq(1L))).thenReturn(getStudentDetailsResponse);


        String expectedTxnId = UUID.randomUUID().toString();


        String actualTxnId = transactionService.initiateReturn(1L,1L);

    }

}
