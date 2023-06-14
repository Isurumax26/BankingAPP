package com.isuru.bank.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerImpl implements Customer {

    private String id;
    private double currentAmount;
    private Map<String, Double> amountByDate;
    private List<String> transferDates;
    public static final Logger log = LogManager.getLogger(CustomerImpl.class);

    public CustomerImpl(String id, double currentAmount) {
        this.id = id;
        this.currentAmount = currentAmount;
        this.amountByDate = new HashMap<>();
    }

    public CustomerImpl(String id) {
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
    *  calculate the cumulative balance and monthly balance
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
                int index  = getClosestTransactionDate(month);
                return amountByDate.get(transferDates.get(index));
            }
        }
    }

    /*
    * doing binary search to find the closest transaction month for the given date
    * */
    public int getClosestTransactionDate(String month) {

        int left  = 0;
        int right = transferDates.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (transferDates.get(mid).compareTo(month) > 0) {
                right  = mid - 1;
            }
            else if (transferDates.get(mid).compareTo(month) < 0) {
                left  = mid + 1;
            }
        }
        return right;
    }
}
