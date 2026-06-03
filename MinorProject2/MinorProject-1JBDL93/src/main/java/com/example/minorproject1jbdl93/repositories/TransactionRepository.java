package com.example.minorproject1jbdl93.repositories;

import com.example.minorproject1jbdl93.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(Student student, Book book, TransactionType transactionType, TransactionStatus transactionStatus);
}
