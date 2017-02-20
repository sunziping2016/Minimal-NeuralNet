package Date;

/**
 * Created by sun on 3/16/16.
 *
 * Date class with overloaded constructors.
 */
public class Date {
    public Date(String month, String day, String year) {
        this.month = Integer.parseInt(month) - 1;
        this.day = Integer.parseInt(day);
        this.year = Integer.parseInt(year);
    }
    public Date(String month, int day, int year) {
        this.month = monthNameToNum(month);
        this.day = day;
        this.year = year;
    }
    public Date(int year, int month, int day) {
        this.month = month - 1;
        this.day = day;
        this.year = year;
    }
    public String format(int n) {
        switch (n)
        {
            case 0:
                return String.format("%2d/%2d/%4d", month + 1, day, year);
            case 1:
                return String.format("%s %d, %d", monthNumToName(month), day, year);
            default:
                return String.format("%d 年 %d 月 %d 日", year, month + 1, day);
        }
    }
    public static int monthNameToNum(String name) {
        for (int i = 0; i < 12; ++i)
            if (monthName[i].startsWith(name))
                return i;
        return 0;
    }
    public static String monthNumToName(int num) {
        return monthName[num];
    }
    public static final String[] monthName = new String[] {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    private int month, day, year;
}
