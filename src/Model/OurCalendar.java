package Model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Represents a calendar filled with work days
 */
public class OurCalendar {
    private Calendar calendar;
    private List<WorkDay> workDays;
    private static OurCalendar instance = null;

    /**
     * Initalizes the class with a Java calender as singleton
     */
    public static OurCalendar getInstance() {
        if (instance == null)
            instance = new OurCalendar();
        return instance;
    }

    private OurCalendar() {
        this.calendar = java.util.Calendar.getInstance();
        this.workDays = new ArrayList<>();
        this.generateDates();
    }

    /**
     * Generates Date objects for the calender and places it in a sorted list
     */
    private void generateDates() {
        Date throwAwayDate = new Date();
        int startingMonth = throwAwayDate.getMonth();
        for (int month = 0; month < 12; month++) { //how many months you want to generate in the future
            for (int day = 1; day <= YearMonth.of(throwAwayDate.getYear(), throwAwayDate.getMonth() + 1).lengthOfMonth(); day++) {
                throwAwayDate.setDate(day);
                throwAwayDate.setMonth((startingMonth + month) % 12);
                resetThrowable(throwAwayDate);
                workDays.add(new WorkDay(throwAwayDate.getTime()));
            }
            if (throwAwayDate.getMonth() == Calendar.DECEMBER) {
                throwAwayDate.setYear(throwAwayDate.getYear() + 1);
                throwAwayDate.setMonth(Calendar.JANUARY);
            }
        }
    }


    public WorkDay getWorkday(int index) {
        if (index < 0) index = -index;
        index = index % 365;
        return workDays.get(index);
    }

    public int getOurDateSize() {
        return workDays.size();
    }

    /**
     * As we only want to get the day, month and year We reset seconds, minutes and hours
     *
     * @param throwAwayDate The Date to reset
     */
    private void resetThrowable(Date throwAwayDate) {
        throwAwayDate.setSeconds(0);
        throwAwayDate.setMinutes(0);
        throwAwayDate.setHours(0);
    }

    /**
     * Gets the work day of the specified date
     *
     * @param date The date
     * @return the work day of the date
     */
    public WorkDay getDate(Date date) {
        for (WorkDay wd : workDays) {
            Date date2 = new Date(wd.DATE);
            if (date.getDate() == date2.getDate() && date.getMonth() == date2.getMonth() && date.getYear() == date2.getYear()) {
                return wd;
            }
        }
        return null; //TODO exception
    }

    public int getDateIndex(Date date) {
        for (int i = 0; i < workDays.size(); i++) {
            Date date2 = new Date(workDays.get(i).DATE);
            if (date.getDate() == date2.getDate() && date.getMonth() == date2.getMonth() && date.getYear() == date2.getYear()) {
                return i;
            }
        }
        return -1; //TODO exception
    }
}
