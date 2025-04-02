package date_api;

import java.util.Date;
import java.util.Calendar;

public class CalendarEx {
    public static void main(String[] args) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);

        calendar.set(2023, 4, 2, 14, 2, 2);
        Date past1 = calendar.getTime();
        System.out.println(past1);

        calendar.set(Calendar.YEAR, 2024);
        Date past2 = calendar.getTime();
        System.out.println(past2);
    }
}
