package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository extends JpaRepository<Bank, Long> {
    Bank getBankByBin(long bin);

    boolean existsByBin(long bin);

    Bank save(Bank bank);
}
