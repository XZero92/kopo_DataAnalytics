package java_22_99_ex3_datecalendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateCalendar {
    public static void main(String[] args) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        calendar.add(Calendar.DATE, 100);

        Date after100days = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String strAfter100days = sdf.format(after100days);
        System.out.println(strAfter100days);
    }
}
