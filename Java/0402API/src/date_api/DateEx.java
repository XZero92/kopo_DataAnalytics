package date_api;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateEx {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now);
        System.out.println(now.getTime());

        Date past = new Date(1710756131614L);
        System.out.println(past);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String strNow = sdf.format(now);
        System.out.println(strNow);
    }
}
