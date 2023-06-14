package com.isuru.bank.tranactions;

public class Transaction {

    private String id;
    private String debitCustomerId;
    private String creditCustomerId;
    private String date;
    private double amount;

    public Transaction(String id, String debitCustomerId, String creditCustomerId, String date, double amount) {
        this.id = id;
        this.debitCustomerId = debitCustomerId;
        this.creditCustomerId = creditCustomerId;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDebitCustomerId() {
        return debitCustomerId;
    }

    public void setDebitCustomerId(String debitCustomerId) {
        this.debitCustomerId = debitCustomerId;
    }

    public String getCreditCustomerId() {
        return creditCustomerId;
    }

    public void setCreditCustomerId(String creditCustomerId) {
        this.creditCustomerId = creditCustomerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
