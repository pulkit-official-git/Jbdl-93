package com.example.jbdl93rediscache.services;


import com.example.jbdl93rediscache.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HashFieldValueService {

    @Autowired
    public RedisTemplate<String,Object>redisTemplate;

    @Autowired
    public ObjectMapper objectMapper;

    private static final String HASH_KEY_PREFIX = "person_hash::";

    private String getKey(String id){
        return HASH_KEY_PREFIX+id;
    }

    public Person create(Person person) {
        Map<String,Object>mp = this.objectMapper.convertValue(person,Map.class);
        this.redisTemplate.opsForHash().putAll(getKey(person.getId()),mp);
        return person;
    }

    public Object getF(String id, String field) {
        return this.redisTemplate.opsForHash().get(getKey(id),field);
    }

    public Person getAll(String id) {
        Map mp = this.redisTemplate.opsForHash().entries(getKey(id));
        return this.objectMapper.convertValue(mp,Person.class);
    }

    public void delete(String id,String field) {
        this.redisTemplate.opsForHash().delete(getKey(id),field);
    }
}
