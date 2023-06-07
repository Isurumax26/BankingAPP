package com.isuru.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BankingAppTest {

    @Mock
    private  BankingAppController bankingAppController;

    private BankingApp bankingApp;

    @BeforeEach
    void setUp() {
        Transaction transaction_1 = new Transaction("1", "1", "2", "2020-05-25", 1000.00);
        Transaction transaction_2 = new Transaction("2", "1", "2", "2020-02-25", 2000.00);
        Transaction transaction_3 = new Transaction("3", "2", "1", "2020-01-12", 500.00);
        Transaction transaction_4 = new Transaction("4", "1", "2", "2020-01-25", 200.00);


        List<Transaction> transactions = Arrays.asList(transaction_3, transaction_2, transaction_1, transaction_4); //try List.of()

        when(bankingAppController.getTransactionDetailsFromAPI()).thenReturn(transactions);
        bankingApp = new BankingApp(bankingAppController);
    }

    @Test
    void getCumulativeBalance() {
        bankingApp.setUp();
        double value = bankingApp.getCumulativeBalance("2");
        Assertions.assertEquals(2700.00, value);
    }

    @Test
    void getMonthlyBalance() {
        bankingApp.setUp();
        double value = bankingApp.getMonthlyBalance("1", "01", "2020");
        Assertions.assertEquals(300.00, value);
    }



}
