package com.isuru.bank;

import com.isuru.bank.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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

    @Nested
    class Transactions_01 {
        @BeforeEach
        void setUp() {
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


            List<Transaction> transactions = Arrays.asList(transaction_1, transaction_2, transaction_3, transaction_4); //try List.of()

            when(bankingAppController.getTransactionDetailsFromAPI()).thenReturn(transactions);
            bankingApp = new BankingApp(bankingAppController);
        }

        @Test
        void getCumulativeBalance_test_01() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getCumulativeBalance("2");
            Assertions.assertEquals(2700.00, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_01() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("1", "01", "2020");
            Assertions.assertEquals(300.00, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_02() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("1", "03", "2020");
            Assertions.assertEquals(-1700.00, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_03() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("1", "03", "2019");
            Assertions.assertEquals(0.0, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_04() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("2", "03", "2023");
            Assertions.assertEquals(2700.00, value);
        }


        @Test
        void getCumulativeBalance_invalidCustomer_test() {
            bankingApp.setUp();
            Assertions.assertThrows(CustomerNotFoundException.class, () -> bankingApp.getCumulativeBalance("7"));
        }



    }

    @Nested
    class Transactions_02 {
        @BeforeEach
        void setUp() {
            // Add more test data
            Transaction transaction_1 = new Transaction("1", "1", "2", "2020-05-25", 1000.00);
            Transaction transaction_2 = new Transaction("2", "1", "2", "2020-02-25", 2000.00);
            Transaction transaction_3 = new Transaction("3", "2", "1", "2020-01-12", 500.00);
            Transaction transaction_4 = new Transaction("4", "", "2", "2020-01-25", 200.00);
            Transaction transaction_5 = new Transaction("5", "2", "3", "2022-04-25", 500.00);
            Transaction transaction_6 = new Transaction("6", "1", "3", "2021-03-25", 100.00);
            Transaction transaction_7 = new Transaction("7", "3", "4", "2022-02-25", 200.00);
            Transaction transaction_8 = new Transaction("8", "2", "5", "2021-02-25", 1000.00);
            Transaction transaction_9 = new Transaction("9", "3", "5", "2021-01-25", 500.00);
            Transaction transaction_10 = new Transaction("10", "4", "5", "2022-03-25", 1000.00);


            List<Transaction> transactions = Arrays.asList(transaction_1, transaction_2, transaction_3, transaction_4); //try List.of()

            when(bankingAppController.getTransactionDetailsFromAPI()).thenReturn(transactions);
            bankingApp = new BankingApp(bankingAppController);
        }

        @Test
        void getCumulativeBalance_test_01() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getCumulativeBalance("2");
            Assertions.assertEquals(2700.00, value);
        }

        @Test
        void getCumulativeBalance_test_02() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getCumulativeBalance("1");
            Assertions.assertEquals(-2500.00, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_01() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("1", "01", "2020");
            Assertions.assertEquals(500.00, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_02() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("1", "03", "2020");
            Assertions.assertEquals(-1500.00, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_03() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("1", "03", "2019");
            Assertions.assertEquals(0.0, value);
        }

        @Test
        void getMonthlyBalance_test_scenario_04() throws CustomerNotFoundException {
            bankingApp.setUp();
            double value = bankingApp.getMonthlyBalance("2", "03", "2023");
            Assertions.assertEquals(2700.00, value);
        }


        @Test
        void getCumulativeBalance_invalidCustomer_test() {
            bankingApp.setUp();
            Assertions.assertThrows(CustomerNotFoundException.class, () -> bankingApp.getCumulativeBalance("7"));
        }



    }




}
