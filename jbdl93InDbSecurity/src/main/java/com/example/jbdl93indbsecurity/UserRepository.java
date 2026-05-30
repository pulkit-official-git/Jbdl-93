package com.example.jbdl93indbsecurity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DemoUser,Integer> {

    DemoUser findByUsername(String username);
}
