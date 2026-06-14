package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String,String>kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void createUser(User user) {
        this.userRepository.save(user);

        JSONObject jsonObject = this.objectMapper.convertValue(user, JSONObject.class);
        this.kafkaTemplate.send("b93userCreated",jsonObject.toString());
    }
}
