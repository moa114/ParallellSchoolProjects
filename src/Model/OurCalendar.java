package Model;

import java.util.List;

public class OurCalendar {
    java.util.Calendar calendar;
    boolean initialized = false;
    List<OurDate> ourDates;
    public void init(){
        calendar = java.util.Calendar.getInstance();
    }

    OurDate getDate(long date){
        return null;
    }
}
