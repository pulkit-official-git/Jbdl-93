package com.example.minorproject1jbdl93.repositories;

import com.example.minorproject1jbdl93.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
