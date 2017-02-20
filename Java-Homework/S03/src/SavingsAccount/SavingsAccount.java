package SavingsAccount;

/**
 * Created by sun on 3/16/16.
 *
 * Class represent saving accounts.
 */
public class SavingsAccount {
    public SavingsAccount(double init) {
        savinsBalance = init;
    }
    public double calculateMonthlyInterest() {
        return savinsBalance * annualInterestRate / 12;
    }
    public double addMonthlyInterest() {
        savinsBalance += calculateMonthlyInterest();
        return savinsBalance;
    }

    public static void modifyInterestRate(double value) {
        annualInterestRate = value;
    }

    private static double annualInterestRate = 0.04;
    private double savinsBalance;
}
