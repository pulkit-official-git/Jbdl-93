package com.example.jbdl93rediscache.services;

import com.example.jbdl93rediscache.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListValueService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private static final String KEY = "person_list";


    public Long lpush(Person person) {
        return this.redisTemplate.opsForList().leftPush(KEY,person);
    }

    public Long rpush(Person person) {
        return this.redisTemplate.opsForList().rightPush(KEY,person);
    }

    public List<Person> lpop(Integer count) {
        return this.redisTemplate
                .opsForList()
                .leftPop(KEY,count)
                .stream()
                .map(x->(Person)x)
                .collect(Collectors.toList());
    }

    public List<Person> rpop(Integer count) {

        return this.redisTemplate
                .opsForList()
                .rightPop(KEY,count)
                .stream()
                .map(x->(Person)x)
                .collect(Collectors.toList());
    }

    public List<Person> lrange(Integer start, Integer end) {

        return this.redisTemplate
                .opsForList()
                .range(KEY,start,end)
                .stream()
                .map(x->(Person)x)
                .collect(Collectors.toList());
    }
}
