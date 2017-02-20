package Bank;

/**
 * Created by sun on 3/25/16.
 *
 * One-year Rate Account.
 */
public class OneyearRateAccount extends Bank {
    private double balance;

    public OneyearRateAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void count() {
        balance += balance * oneyearRate;
    }

    @Override
    public String show() {
        return String.format("%.2f", balance);
    }
}
