package com.isuru.bank;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    private String id;
    private double currentAmount;
    private Map<String, Double> amountByDate;

    public Customer(String id, double currentAmount) {
        this.id = id;
        this.currentAmount = currentAmount;
        this.amountByDate = new HashMap<>();
    }

    public Customer(String id) {
        this.id  = id;
        currentAmount = 0;
        this.amountByDate = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public Map<String, Double> getAmountByDate() {
        return amountByDate;
    }

    /*
    *  assuming transaction details are ordered by the date
    * */
    public void setAmount(String date, double amount, boolean isDebit) {
        if (isDebit) {
            currentAmount = currentAmount - amount; // use ternary operator
        }
        else {
            currentAmount = currentAmount + amount;
        }
        amountByDate.put(date.substring(0, 7), currentAmount); // need to return monthly balance, so store only the month details, date format - 2023-05-15
    }

    /*
    * If transaction details are not ordered by date
    * We can't use currentAMount
    * */
    public void setAmount_1(String date, double amount, boolean isDebit) {
        double balance = amountByDate.getOrDefault(date.substring(0, 7), 0.0);
        if (isDebit) {
            balance = balance - amount; // use ternary operator
        }
        else {
            balance= balance + amount;
        }
        amountByDate.put(date.substring(0, 7), balance); // need to return monthly balance, so store only the monthly details, date format - 2023-05-15
    }

    /*
    * If transaction details are not ordered by date
    * */
    public double getCumulativeBalance() {
        double amount  = 0;
        for (String month : amountByDate.keySet()) {
            amount += amountByDate.get(month);
        }
        return amount;
    }

    public double getMonthlyBalance(String month) {
        return amountByDate.get(month);
    }
}
