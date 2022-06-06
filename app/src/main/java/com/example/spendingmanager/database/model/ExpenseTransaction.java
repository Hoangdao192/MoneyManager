package com.example.spendingmanager.database.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "expense_transaction",
        foreignKeys = {
                @ForeignKey(
                        entity = Wallet.class,
                        parentColumns = "id",
                        childColumns = "walletId",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = TransactionCategory.class,
                        parentColumns = "id",
                        childColumns = "transactionCategoryId",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                )
        }
)
public class ExpenseTransaction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long timestamp;
    private long amount;
    private String note = "";
    private int transactionCategoryId;
    private int walletId;

    public ExpenseTransaction(int id, long timestamp, long amount, String note, int transactionCategoryId, int walletId) {
        this.id = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.note = note;
        this.transactionCategoryId = transactionCategoryId;
        this.walletId = walletId;
    }

    @Ignore
    public ExpenseTransaction(long timestamp, long amount, String note, int transactionCategoryId, int walletId) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.note = note;
        this.transactionCategoryId = transactionCategoryId;
        this.walletId = walletId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public void setTransactionCategoryId(int transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }
}
