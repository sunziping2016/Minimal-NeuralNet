package Bank;

/**
 * Created by sun on 3/25/16.
 *
 * Bank base class.
 */
abstract public class Bank {
    static final double oneyearRate = 0.0178, oneyearNationaldebt = 0.0198, interestRate = 0.0078;

    abstract public void count();
    abstract public String show();
}
