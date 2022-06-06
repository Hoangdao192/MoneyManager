package com.example.spendingmanager.database.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_category")
/**
 * Define the TransactionCategory property and transaction_category table's structure in Database
 */
public class TransactionCategory {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String type;

    @Ignore
    public static String TYPE_DEFAULT = "default";
    @Ignore
    public static String TYPE_CUSTOM = "custom";

    public TransactionCategory(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Ignore
    public TransactionCategory(String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
