package Model;

import java.util.List;

public class Calendar {
    java.util.Calendar calendar;
    boolean initialized = false;
    List<Date> dates;
    public void init(){
        calendar = java.util.Calendar.getInstance();
    }

    Date getDate(long date){
        return null;
    }
}
