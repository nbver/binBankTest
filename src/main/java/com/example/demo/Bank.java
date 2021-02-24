package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "bins")
public class Bank {


    @Id
    private long bin;

    private String bank;

    public long getBin() {
        return bin;
    }

    public void setBin(long bin) {
        this.bin = bin;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Bank() {
    }

    public Bank(long bin, String bank) {
        this.bin = bin;
        this.bank = bank;
    }
}
