package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class TransactionService {


    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public String create(Long sender, Long receiver, Long amount) {

        Transaction transaction = Transaction.builder()
                .txnId(UUID.randomUUID().toString())
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        this.transactionRepository.save(transaction);

        JSONObject jsonObject = this.objectMapper.convertValue(transaction, JSONObject.class);
        this.kafkaTemplate.send("transaction-created93",jsonObject.toString());

        return transaction.getTxnId();

    }


    @KafkaListener(topics = "txn-update93",groupId = "txnUpdateGrp")
    public void updateTxn(String message){

        JSONObject jsonObject = (JSONObject) JSONValue.parse(message);

        String txnId = jsonObject.get("txnId").toString();
        String status = jsonObject.get("status").toString();

        Transaction txn = this.transactionRepository.findByTxnId(txnId);

        if(txn.getTransactionStatus() != TransactionStatus.PENDING){
            log.info("txn already completed");
            return;
        }

        if(status.equals("SUCCESS")){
            txn.setTransactionStatus(TransactionStatus.SUCCESS);
        }
        if(status.equals("FAILED")){
            txn.setTransactionStatus(TransactionStatus.FAILURE);
        }


        this.transactionRepository.save(txn);



    }
}
