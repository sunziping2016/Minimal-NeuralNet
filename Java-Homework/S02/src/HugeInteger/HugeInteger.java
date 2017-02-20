package HugeInteger;

import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * Created by sun on 3/10/16.
 *
 * Class represents integer with a high but limited precision of 40.
 */
public class HugeInteger implements Comparable<HugeInteger> {
    public HugeInteger()
    {
        Arrays.fill(digits, (byte)0);
        positive = true;
    }
    public HugeInteger(String str) {
        inputHugeInteger(str);
    }
    public void inputHugeInteger(String str) {
        if (str.matches("[+-]?\\s*\\d+")) {
            str = str.trim();
            if (str.matches("[+-]?\\s*\\d+")) {
                boolean notZero = false;
                Arrays.fill(digits, (byte)0);
                for (int i = str.length() - 1, j = 0; i >= 0 && j < MAX_DIGITS && Character.isDigit(str.charAt(i)); --i) {
                    digits[j++] = (byte) (str.charAt(i) - '0');
                    if (str.charAt(i) != '0')
                        notZero = true;
                }
                if (str.charAt(0) == '-' && notZero)
                    positive = false;
                else
                    positive = true;
            }
        }
        else {
            Arrays.fill(digits, (byte)0);
            positive = true;
        }

    }
    public String outputHugeInteger() {
        return this.toString();
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (!positive)
            str.append('-');
        int i = MAX_DIGITS - 1;
        while (i > 0 && digits[i] == 0)
            --i;
        while(i >= 0) {
            str.append((char) (digits[i] + '0'));
            --i;
        }
        return str.toString();
    }
    @Override
    public int compareTo(HugeInteger other) {
        if (positive != other.positive)
            return positive ? 1 : -1;
        int res = compareDigits(digits, other.digits);
        if (res == 0)
            return 0;
        else if (res > 0)
            return positive ? 1 : -1;
        else
            return positive ? -1 : 1;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HugeInteger)
            return this.compareTo((HugeInteger)obj) == 0;
        else
            return false;
    }

    public HugeInteger addHugeIntegers(HugeInteger other) {
        HugeInteger res = new HugeInteger();
        if (positive == other.positive) {
            addDigits(this.digits, other.digits, res.digits);
            res.positive = positive;
        }
        else {
            int ret = compareDigits(digits, other.digits);
            if (ret > 0) {
                subDigits(digits, other.digits, res.digits);
                res.positive = positive;
            }
            else if(ret < 0) {
                subDigits(other.digits, digits, res.digits);
                res.positive = other.positive;
            }
            // if ret == 0, res is 0
        }
        return res;
    }
    public HugeInteger subHugeIntegers(HugeInteger other) {
        HugeInteger res = new HugeInteger();
        if (positive != other.positive) {
            addDigits(this.digits, other.digits, res.digits);
            res.positive = positive;
        }
        else {
            int ret = compareDigits(digits, other.digits);
            if (ret > 0) {
                subDigits(digits, other.digits, res.digits);
                res.positive = positive;
            }
            else if(ret < 0) {
                subDigits(other.digits, digits, res.digits);
                res.positive = !other.positive;
            }
            // if ret == 0, res is 0
        }
        return res;
    }
    public boolean isEqualTo(HugeInteger other) {
        return compareTo(other) == 0;
    }
    public boolean isNotEqualTo(HugeInteger other) {
        return compareTo(other) != 0;
    }
    public boolean isGreaterThan(HugeInteger other) {
        return compareTo(other) > 0;
    }
    public boolean isLessThan(HugeInteger other) {
        return compareTo(other) < 0;
    }
    public boolean isGreaterThanOrEqualTO(HugeInteger other) {
        return compareTo(other) >= 0;
    }
    public boolean isLessThanOrEqualTO(HugeInteger other) {
        return compareTo(other) <= 0;
    }

    protected static void addDigits(byte[] a, byte[] b, byte[] res) {
        byte c = 0;
        for (int i = 0; i < MAX_DIGITS; ++i) {
            c = (byte)(a[i] + b[i] + c);
            res[i] = (byte)(c % 10);
            c /= 10;
        }
    }
    protected static void subDigits(byte[] a, byte[] b, byte[] res) {
        byte c = 0, temp;
        for (int i = 0; i < MAX_DIGITS; ++i) {
            c = (byte) (a[i] - b[i] - c);
            if (c >= 0) {
                res[i] = c;
                c = 0;
            }
            else {
                res[i] = (byte)(10 + c);
                c = 1;
            }
        }
    }
    protected static int compareDigits(byte[] a, byte[] b) {
        int i = MAX_DIGITS - 1;
        while (i >= 0 && a[i] == b[i])
            --i;
        if (i < 0)
            return 0;
        return a[i] - b[i] > 0 ? 1 : -1;
    }

    static final int MAX_DIGITS = 40;
    private final byte[] digits = new byte[MAX_DIGITS];
    private boolean positive; // Assume HugeInteger("0").positive equals true;

}
