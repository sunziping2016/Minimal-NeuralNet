package Lab13;

import java.io.*;

class MyException extends Exception {
    public MyException() { super(); }
    public MyException(String msg) { super(msg); }
}
public class Lab13 {
    public static BufferedReader getKeyboard()  {
        return new BufferedReader(new InputStreamReader(System.in));
    }
    public static String readKeyboardLine() {
        while (true) {
            try {
                BufferedReader kyboard = getKeyboard();
                String line = kyboard.readLine();
                return line;
            }
            catch (IOException error){
                System.err.println(error);
            }
        }
    }
    public static int readKeyboardInt() {
        while (true) {
            try {
                System.out.print("Input an integer: ");
                BufferedReader kyboard = getKeyboard();
                int number = Integer.parseInt(kyboard.readLine());
                return number;
            }
            catch (IOException error) {
                System.err.println(error);
            }
            catch (NumberFormatException error) {
                System.err.println(error);
            }
        }
    }
    public static double exp(double b, int c) throws Exception {
        if (c < 0)
            throw new Exception("c < 0");
        if (c == 0)
            return 1;
        if (c % 2 == 0)
            return exp(b * b, c / 2);
        return b * exp(b, c - 1);
    }
    public static int approach(double x) throws MyException {
        int i = 1;
        if (x < 0)
            throw new MyException("x < 0");
        if (x > 1)
            throw  new MyException("x > 1");
        if (x == 0.0)
            return 0;
        try {
            while (exp(x, i) >= 0.1)
                ++i;
        }
        catch (Exception error) {
            return 0;
        }
        return i;
    }
}
