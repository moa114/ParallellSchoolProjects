package Model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class OurCalendar {
    java.util.Calendar calendar;
    boolean initialized = false;
    private List<OurDate> ourDates;
    Date throwAwayDate;
    public void init(){
        calendar = java.util.Calendar.getInstance();
        throwAwayDate = new Date();
        ourDates = new ArrayList<>();
        generateDates();
    }

    private void generateDates(){
        int startingMonth = throwAwayDate.getMonth();
        for (int month = 0; month<12; month++) { //variabeln month är hur många månader fram man vill generera kalendern
            for (int day = 1; day <= YearMonth.of(throwAwayDate.getYear(), throwAwayDate.getMonth()+1).lengthOfMonth(); day++){
                throwAwayDate.setDate(day);
                throwAwayDate.setMonth((startingMonth + month)%12);
                resetThrowable();
                ourDates.add(new OurDate(throwAwayDate.getTime()));
            }
            if (throwAwayDate.getMonth()==Calendar.DECEMBER) {
                throwAwayDate.setYear(throwAwayDate.getYear() + 1);
                throwAwayDate.setMonth(Calendar.JANUARY);
            }
        }
    }

    public List<OurDate> getOurDates() {
        return ourDates;
    }

    private void resetThrowable(){
        throwAwayDate.setSeconds(0);
        throwAwayDate.setMinutes(0);
        throwAwayDate.setHours(0);
    }

    OurDate getDate(long date){
        return null;
    }
}
