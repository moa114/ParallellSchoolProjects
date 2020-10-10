package Model;

import java.time.DayOfWeek;
import java.util.*;


public class WeekHandler {

    private WeekHandler() {

    }

    public static long plusMinutes(int minutes) {
        return 1000 * 60 * minutes;
    }

    public static long plusHours(int hours) {
        return 1000 * 60 * 60 * hours;
    }

    public static long plusDays(int days) {
        return 1000 * 60 * 60 * 24 * days;
    }

    public static long plusHoursAndMinutes(int hours, int minutes) {
        return 1000 * 60 * 60 * hours + 1000 * 60 * minutes;
    }

    public static long plusDaysAndHoursAndMinutes(int days, int hours, int minutes) {
        return 1000 * 60 * 60 * hours + 1000 * 60 * minutes + 1000 * 60 * 60 * 24 * days;
    }


}
