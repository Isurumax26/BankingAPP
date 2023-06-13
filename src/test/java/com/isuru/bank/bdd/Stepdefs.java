package com.isuru.bank.bdd;

import com.isuru.bank.BankingApp;
import com.isuru.bank.BankingAppController;
import com.isuru.bank.exceptions.CustomerNotFoundException;
import com.isuru.bank.tranactions.Transaction;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Stepdefs {
    @Mock
    private BankingAppController bankingAppController;

    private BankingApp bankingApp;
    private double value;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("All the transaction")
    public void inject_transactions() {

        // Add more test data
        Transaction transaction_1 = new Transaction("1", "1", "2", "2020-05-25", 1000.00);
        Transaction transaction_2 = new Transaction("2", "1", "2", "2020-02-25", 2000.00);
        Transaction transaction_3 = new Transaction("3", "2", "1", "2020-01-12", 500.00);
        Transaction transaction_4 = new Transaction("4", "1", "2", "2020-01-25", 200.00);
        Transaction transaction_5 = new Transaction("5", "2", "3", "2022-04-25", 500.00);
        Transaction transaction_6 = new Transaction("6", "1", "3", "2021-03-25", 100.00);
        Transaction transaction_7 = new Transaction("7", "3", "4", "2022-02-25", 200.00);
        Transaction transaction_8 = new Transaction("8", "2", "5", "2021-02-25", 1000.00);
        Transaction transaction_9 = new Transaction("9", "3", "5", "2021-01-25", 500.00);
        Transaction transaction_10 = new Transaction("10", "4", "5", "2022-03-25", 1000.00);


        List<Transaction> transactions = Arrays.asList(transaction_1, transaction_2, transaction_3, transaction_4);

        when(bankingAppController.getTransactionDetailsFromAPI()).thenReturn(transactions);
        bankingApp = new BankingApp(bankingAppController);
    }


    @When("Customer {string} request cumulative balance")
    public void customer_request_balance(String id) throws CustomerNotFoundException {
        bankingApp.setUp();
        value = bankingApp.getCumulativeBalance(id);
    }

    @When("Customer {string} request monthly balance on {string}")
    public void customer_request_balance(String id, String date) throws CustomerNotFoundException {
        bankingApp.setUp();
        String[] monthYear = date.split("-");
        value = bankingApp.getMonthlyBalance(id, monthYear[1], monthYear[0]);
    }

    @Then("Application should return {double}")
    public void app_should_return(double expectedValue) {
        Assertions.assertEquals(expectedValue, value);
    }
}
