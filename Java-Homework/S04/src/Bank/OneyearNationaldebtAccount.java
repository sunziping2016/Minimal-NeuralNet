package Bank;

/**
 * Created by sun on 3/25/16.
 *
 * One-year national debt account class.
 */
public class OneyearNationaldebtAccount extends Bank {
    private double balance;

    public OneyearNationaldebtAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void count() {
        balance += balance * oneyearNationaldebt;
    }

    @Override
    public String show() {
        return String.format("%.2f", balance);
    }
}
