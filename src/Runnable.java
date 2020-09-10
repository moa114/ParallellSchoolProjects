import Model.Admin;
import Model.OurCalendar;
import Model.OurDate;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Runnable {
    public static void main(String[] args) {
        OurCalendar calendar = new OurCalendar();
        System.out.println(System.currentTimeMillis());
        calendar.init();
        System.out.println(System.currentTimeMillis());
        for (OurDate d : calendar.getOurDates()){
            System.out.println(new Date(d.date));
        }
    }
}
