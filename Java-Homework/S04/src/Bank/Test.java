package Bank;

/**
 * Created by sun on 3/25/16.
 *
 * Unit text for Bank package.
 */
public class Test {
    public static void main(String[] args) {
        Bank oneyearRateAccount = new OneyearRateAccount(1000),
                oneyearNationaldebtAccount = new OneyearNationaldebtAccount(1000),
                interestAccount = new InterestAccount(1000);

        System.out.println("oneyearRateAccount: " + oneyearRateAccount.show());
        System.out.println("oneyearNationaldebtAccount: " + oneyearNationaldebtAccount.show());
        System.out.println("interestAccount: " + interestAccount.show());

        oneyearRateAccount.count();
        oneyearNationaldebtAccount.count();
        interestAccount.count();

        System.out.println("One year after.");
        System.out.println("oneyearRateAccount: " + oneyearRateAccount.show());
        System.out.println("oneyearNationaldebtAccount: " + oneyearNationaldebtAccount.show());
        System.out.println("interestAccount: " + interestAccount.show());
    }
}
