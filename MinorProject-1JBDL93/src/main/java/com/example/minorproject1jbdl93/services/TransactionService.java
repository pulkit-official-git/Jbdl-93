package com.example.minorproject1jbdl93.services;

import com.example.minorproject1jbdl93.models.*;
import com.example.minorproject1jbdl93.repositories.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Value("${issuePerStudentLimit}")
    private int issuePerStudentLimit;


    @Value("${book.limit}")
    private int studentBookDaysLimit;

    @Value("${finePerDay}")
    private int finePerDay;

    public String create(Long studentId, Long bookId, TransactionType transactionType) throws Exception {

        switch (transactionType){

            case ISSUANCE :
                return initiateIssuance(studentId,bookId);
            case RETURN:
                return initiateReturn(studentId,bookId);
            default:
                throw new Exception("invalid Transaction Type");

        }

    }

/*
* Issuance Logic
*
* 1. Data Retrieval
*   a. Book
*   b. Student
*
* 2. Validations
*   a. if student is null or book is null
*   b. if book is assigned to someone else
*   c. if student limit exceeds
*   d.(if already fine is there)(later phase)
*
* 3. Create a txn with status as pending
* 4. Allot book to the student
* 5. make transaction success
* 6. If transaction got failed so make it failed  and handle accordingly (rollback)
*
* */

    public String initiateIssuance(Long studentId, Long bookId) throws Exception {

//        Data Retrieval
        Student student = this.studentService.getStudent(studentId).getStudent();
        Book book = this.bookService.get(bookId);

//        make a package of exceptions(explore controller advice)
//        Validations
        if (student == null || student.getStatus()== StudentStatus.INACTIVE) {
            throw new Exception("invalid student");
        }
        if(book == null || book.getStudent()!=null){
            throw new Exception("invalid book or book is assigned to someone else");
        }

        List<Book>issuedBooks = student.getBookList();
        if(issuedBooks.size() >= issuePerStudentLimit){
            throw new Exception("book issue limit reached");
        }

        Transaction transaction = Transaction.builder()
                .student(student)
                .book(book)
                .txnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.PENDING)
                .fine(0)
                .build();

        Transaction savedTransaction = this.transactionRepository.save(transaction);


        try{

            book.setStudent(student);
            this.bookService.save(book);

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            this.transactionRepository.save(transaction);


        }catch (Exception ex){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);

            if(book.getStudent()!=null){
                book.setStudent(null);
                this.bookService.save(book);
            }
        }

        return savedTransaction.getTxnId();

    }

    public String initiateReturn(Long studentId, Long bookId) throws Exception {

//        Data Retrieval
        Student student = this.studentService.getStudent(studentId).getStudent();
        Book book = this.bookService.get(bookId);


//        Validations
        if (student == null || student.getStatus()== StudentStatus.INACTIVE) {
            throw new Exception("invalid student");
        }

        if(book == null || book.getStudent()==null || book.getStudent().getId() !=studentId){
            throw new Exception("invalid book or book is assigned to someone else");
        }

        Transaction transaction = Transaction.builder()
                .student(student)
                .book(book)
                .txnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .transactionStatus(TransactionStatus.PENDING)
                .fine(0)
                .build();


        int fine = this.calculateFine(book,student);
        transaction.setFine(fine);

        Transaction savedTransaction = this.transactionRepository.save(transaction);


        try{

            book.setStudent(null);
            this.bookService.save(book);
//            int temp = 1/0;
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            this.transactionRepository.save(transaction);


        }catch (Exception ex){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);

            if(book.getStudent()==null){
                book.setStudent(student);
                this.bookService.save(book);
            }
        }

        return savedTransaction.getTxnId();

    }

    public int calculateFine(Book book, Student student){

        Transaction issuedTxn = this.transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc
                (student,book,TransactionType.ISSUANCE,TransactionStatus.SUCCESS);

        Long issuedTxnTime = issuedTxn.getUpdatedOn().getTime();
        Long timeDiffInMillis = System.currentTimeMillis()-issuedTxnTime;

        Long timeDiffInDays = TimeUnit.DAYS.convert(timeDiffInMillis, TimeUnit.MILLISECONDS);

        if(timeDiffInDays > studentBookDaysLimit){
            return (timeDiffInDays.intValue() - studentBookDaysLimit)*finePerDay;
        }
        return 0;

    }

    public int add(int a , int b){
        return a+b+1;
    }
}
