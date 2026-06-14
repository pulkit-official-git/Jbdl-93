package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,String> {
    Wallet findByUserId(Long userId);
}
