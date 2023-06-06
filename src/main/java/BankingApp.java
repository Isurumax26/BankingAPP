import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingApp {

    Map<String, Customer> customerIds = new HashMap<>(); // in-memory map

    public void setUp() {
        List<Transaction> transactions = getTransactionDetailsFromAPI();

        for (Transaction transaction : transactions) {
            String debitCustomerId = transaction.getDebitCustomerId();
            String creditCustomerId = transaction.getCreditCustomerId();
            double amount  = transaction.getAmount();
            String date = transaction.getDate();

            Customer debitCustomer = customerIds.computeIfAbsent(debitCustomerId, Customer::new);
            debitCustomer.setAmount(date, amount, true);
            customerIds.put(debitCustomerId, debitCustomer); // do this by one line

            Customer creditCustomer = customerIds.computeIfAbsent(creditCustomerId, Customer::new);
            creditCustomer.setAmount(date, amount, false);
            customerIds.put(creditCustomerId, creditCustomer);

        }
    }

    public List<Transaction> getTransactionDetailsFromAPI() {
        return new ArrayList<>();
    }

    public double getMonthlyBalance(String customerId, String month, String year) {
        Customer customer = customerIds.get(customerId);
        return customer.getMonthlyBalance(year + "-" + month);
    }

    public double getCumulativeBalance(String customerId) {
        Customer customer = customerIds.get(customerId);
        return customer.getCurrentAmount();
    }

}
