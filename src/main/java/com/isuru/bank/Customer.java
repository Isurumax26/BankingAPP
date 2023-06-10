package com.isuru.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {

    private String id;
    private double currentAmount;
    private Map<String, Double> amountByDate;
    private List<String> transferDates;
    public static final Logger log = LogManager.getLogger(Customer.class);

    public Customer(String id, double currentAmount) {
        this.id = id;
        this.currentAmount = currentAmount;
        this.amountByDate = new HashMap<>();
    }

    public Customer(String id) {
        this.id  = id;
        currentAmount = 0;
        this.amountByDate = new HashMap<>();
        this.transferDates = new ArrayList<>();
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
        log.info("calculating the cumulative and monthly balance");
        if (isDebit) {
            currentAmount = currentAmount - amount; // use ternary operator
        }
        else {
            currentAmount = currentAmount + amount;
        }
        transferDates.add(date.substring(0, 7));
        amountByDate.put(date.substring(0, 7), currentAmount); // need to return monthly balance, so store only the month details, date format - 2023-05-15
    }
    
    public double getMonthlyBalance(String month) {
        log.info("fetching the monthly balance");
        if (amountByDate.containsKey(month)) {
            return amountByDate.get(month);
        }
        else {
            if (transferDates.size() == 0 || month.compareTo(transferDates.get(0)) < 0) {
                return 0;
            }
            else if (month.compareTo(transferDates.get(transferDates.size() - 1)) > 0) {
                return currentAmount;
            }
            else {
                for (int i = 0; i < transferDates.size(); i++) {
                    if (month.compareTo(transferDates.get(i)) < 0) {
                        return amountByDate.get(transferDates.get(i - 1));
                    }
                }
            }
        }
        return 0;
    }

    /*
     * If transaction details are not ordered by date
     * We can't use currentAMount
     *
     * Currently this method is not used for the logic
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
     * Currently this method is not used for the logic
     * */
    public double getCumulativeBalance() {
        double amount  = 0;
        for (String month : amountByDate.keySet()) {
            amount += amountByDate.get(month);
        }
        return amount;
    }

}
