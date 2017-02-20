public class Converter {
    public static String convert(String number, int base1, int base2) {
        return Integer.toString(Integer.parseInt(number, base1), base2);
    }
}
