package SavingsAccount;

/**
 * Created by sun on 3/17/16.
 *
 * Test unit for SavingsAccount.java
 */
public class Test {
    public static void main(String[] args) {
        SavingsAccount saver1 = new SavingsAccount(2000.0), saver2 = new SavingsAccount(3000.0);
        System.out.println("saver1\tMonthly Interest: " + saver1.calculateMonthlyInterest() + "\tBalance: " + saver1.addMonthlyInterest());
        System.out.println("saver2\tMonthly Interest: " + saver2.calculateMonthlyInterest() + "\tBalance: " + saver2.addMonthlyInterest());
        SavingsAccount.modifyInterestRate(0.05);
        System.out.println("saver1\tMonthly Interest: " + saver1.calculateMonthlyInterest() + "\tBalance: " + saver1.addMonthlyInterest());
        System.out.println("saver2\tMonthly Interest: " + saver2.calculateMonthlyInterest() + "\tBalance: " + saver2.addMonthlyInterest());
    }
}
