package com.example.spendingmanager.database.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "wallet")
public class Wallet {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private long balance;

    public Wallet(int id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @Ignore
    public Wallet(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
