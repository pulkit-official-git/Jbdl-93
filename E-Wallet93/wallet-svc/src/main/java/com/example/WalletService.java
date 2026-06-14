package com.example;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WalletService {

    @Value("${wallet.balance}")
    Long balance;

    @Autowired
    WalletRepository walletRepository;


    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;



    @KafkaListener(topics = "b93userCreated",groupId = "walletCreationGrp")
    public void createWallet(String message){

        JSONObject jsonObject = (JSONObject) JSONValue.parse(message);

        Long userId = (Long) jsonObject.get("id");

        Wallet wallet = this.walletRepository.findByUserId(userId);

        if(wallet!=null){
            log.info("wallet already created");
            return;
        }

        wallet = Wallet.builder()
                .id(UUID.randomUUID().toString())
                .status(WalletStatus.ACTIVE)
                .balance(this.balance)
                .userId(userId)
                .build();

        this.walletRepository.save(wallet);

        log.info("wallet created {} ",jsonObject.toString());

    }


    @KafkaListener(topics = "transaction-created93",groupId = "txngrp93")
    public void updateWallet(String message){

        JSONObject jsonObject =(JSONObject) JSONValue.parse(message);

        Long senderId = (Long) jsonObject.get("sender");
        Long receiverId = (Long) jsonObject.get("receiver");
        Long amount = (Long) jsonObject.get("amount");

        String txn = (String) jsonObject.get("txnId");

        Wallet senderWallet = this.walletRepository.findByUserId(senderId);
        Wallet receiverWallet = this.walletRepository.findByUserId(receiverId);

        if(senderWallet==null || receiverWallet==null || senderWallet.getBalance()<amount){

            JSONObject event = new JSONObject();
            event.put("status","FAILED");
            event.put("sender",senderId);
            event.put("receiver",receiverId);
            event.put("amount",amount);
            event.put("txnId",txn);

           this.kafkaTemplate.send("txn-update93",event.toString());

        }

        receiverWallet.setBalance(receiverWallet.getBalance()+amount);
        senderWallet.setBalance(senderWallet.getBalance()-amount);

        this.walletRepository.saveAll(List.of(senderWallet,receiverWallet));

        JSONObject event = new JSONObject();
        event.put("status","SUCCESS");
        event.put("sender",senderId);
        event.put("receiver",receiverId);
        event.put("amount",amount);
        event.put("txnId",txn);

        this.kafkaTemplate.send("txn-update93",event.toString());

    }
}
