package Model;

import java.time.DayOfWeek;
import java.util.*;


public class WeekHandler {

    private HashMap<DayOfWeek, ArrayList<WorkShift>> weekDays;

    public WeekHandler() {
        this.weekDays = new HashMap<>();
    }

    public void setWeeklyWorkshift(DayOfWeek dow, WorkShift ws) {
        weekDays.computeIfAbsent(dow, k -> new ArrayList<WorkShift>());
        weekDays.get(dow).add(ws);
    }

    public void setWeeklyWorkshift(ArrayList<DayOfWeek> dows, WorkShift ws) {
        for (DayOfWeek dow : dows) {
            weekDays.computeIfAbsent(dow, k -> new ArrayList<WorkShift>());
            weekDays.get(dow).add(ws);
        }
    }

    public void setWorkDay(WorkDay wd) {
        wd.setWorkShifts(weekDays.get(DayOfWeek.of(new Date(wd.DATE).getDay())));
    }

}
