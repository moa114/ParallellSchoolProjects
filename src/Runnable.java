import Model.OurCalendar;
import Model.WorkDay;


import java.util.Date;

public class Runnable {
    public static void main(String[] args) {
        OurCalendar calendar = new OurCalendar();
        System.out.println(System.currentTimeMillis());
        calendar.init();
        System.out.println(System.currentTimeMillis());
        for (WorkDay d : calendar.getOurDates()) {
            System.out.println(new Date(d.date));
        }
    }
}
