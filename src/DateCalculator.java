import java.time.LocalDateTime;

public class DateCalculator {

    public static int days(LocalDateTime time1, LocalDateTime time2) {
        int N1 = 1461 * f(time1.getYear(), time1.getMonthValue()) / 4 + 153 * g(time1.getMonthValue()) / 5 + time1.getDayOfMonth();
        int N2 = 1461 * f(time2.getYear(), time2.getMonthValue()) / 4 + 153 * g(time2.getMonthValue()) / 5 + time2.getDayOfMonth();

        return Math.abs(N1 - N2);

    }

    public static int  weekDay(LocalDateTime time) {
        return ((1461 * f(time.getYear(), time.getMonthValue()) / 4 + 153 * g(time.getMonthValue()) / 5 + time.getDayOfMonth()) - 621049) % 7;

    }

    private static int f(int year, int month) {
        if (month <= 2) {
            return year-1;
        } else {
            return year;
        }
    }

    private static int g(int month) {
        if (month <= 2) {
            return month + 13;
        } else {
            return month + 1;
        }
    }


    public static void main(String[] args) {
        LocalDateTime time1 = LocalDateTime.of(2004, 8, 8, 0, 0, 0);

        LocalDateTime time2 = LocalDateTime.of(2020, 5, 1, 0, 0, 0);

        System.out.println(weekDay(time1));
        System.out.println(weekDay(time2));

        System.out.println("\n\b");
    }

}
