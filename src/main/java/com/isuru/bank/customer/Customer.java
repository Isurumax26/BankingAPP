package com.isuru.bank.customer;

public interface Customer {

    void setAmount(String date, double amount, boolean isDebit);

    double getMonthlyBalance(String month);

    double getCurrentAmount();
}
