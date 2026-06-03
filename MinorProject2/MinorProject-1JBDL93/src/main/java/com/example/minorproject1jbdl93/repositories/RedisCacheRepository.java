package com.example.minorproject1jbdl93.repositories;

import com.example.minorproject1jbdl93.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisCacheRepository {

    private static final String KEY_PREFIX = "std::";
    private static final Long KEY_EXPIRY = 3600L;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private String getKey(Long studentId){
        return KEY_PREFIX+studentId;
    }

    public Student get(Long id) {
        return (Student) this.redisTemplate.opsForValue().get(getKey(id));
    }

    public void create(Student student) {
        this.redisTemplate.opsForValue().set(getKey(student.getId()),student,KEY_EXPIRY, TimeUnit.SECONDS);
    }
}
