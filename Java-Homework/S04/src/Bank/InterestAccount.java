package Bank;

/**
 * Created by sun on 3/25/16.
 *
 * Interest account class.
 */
public class InterestAccount extends Bank {
    private double balance;

    public InterestAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void count() {
        balance += balance * interestRate;
    }

    @Override
    public String show() {
        return String.format("%.2f", balance);
    }
}