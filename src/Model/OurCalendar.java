package Model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class OurCalendar {
    private Calendar calendar;
    private static boolean initialized = false;
    private List<OurDate> ourDates;

    public void init(){
        if(!initialized){
            calendar = Calendar.getInstance();
            ourDates = new ArrayList<>();
            generateDates();
            initialized = true;
        }


    }

    /**
     * Generates Date objects for the calender and places it in a sorted list
     */
    private void generateDates(){
        Date throwAwayDate = new Date();
        int startingMonth = throwAwayDate.getMonth();
        for (int month = 0; month<12; month++) { //how many months you want to generate in the future
            for (int day = 1; day <= YearMonth.of(throwAwayDate.getYear(), throwAwayDate.getMonth()+1).lengthOfMonth(); day++){
                throwAwayDate.setDate(day);
                throwAwayDate.setMonth((startingMonth + month)%12);
                resetThrowable(throwAwayDate);
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

    private void resetThrowable( Date throwAwayDate){
        throwAwayDate.setSeconds(0);
        throwAwayDate.setMinutes(0);
        throwAwayDate.setHours(0);
    }

    OurDate getDate(long date){
        return null;
    }
}
