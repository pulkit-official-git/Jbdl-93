package com.example.jbdl93rediscache.services;

import com.example.jbdl93rediscache.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StringValueService {

    @Autowired
    RedisTemplate<String,Object>redisTemplate;

    private static final String PREFIX_KEY = "persons::";

    private static final Long EXPIRY = 10L;

    private String getKey(String id){
        return PREFIX_KEY + id;
    }


    public Person create(Person person) {
        String key = this.getKey(person.getId());
        redisTemplate.opsForValue().set(key,person,EXPIRY, TimeUnit.MINUTES);
        return person;
    }

    public Person get(String id) {
        return (Person) this.redisTemplate.opsForValue().get(getKey(id));
    }
}
