package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Phaser;

public class WorkDay {
    public final long date;
    private static List<Department> departments = new ArrayList<>();
    private HashMap<Department, List<WorkShift>>  departmentLinks;

    public WorkDay(long date) {
        this.date = date;
        this.departmentLinks = new HashMap<>();
    }

    /*boolean allDepartmentsFilled() {
        for (Department d : departments) {
            for (WorkShift w : d.getAllShifts()) {
                if (w.requiredPersonnel > departmentListHashMap.get(d).get(w).size())
                    return false;
            }
        }
        return true;
    }*/

    List<Employee> getWorkingPersonnel(Department department) {
        return null;
    }

    List<Department> getAllDepartments() {
        return null;
    }

    private long plusHours(int hours) {
        return date + 1000 * 60 * 60 * hours;
    }

    private long plusMinutes(int minutes) {
        return date + 1000 * 60 * minutes;
    }

    private long plusHoursAndMinutes(int hours, int minutes) {
        return date + 1000 * 60 * 60 * hours + 1000 * 60 * minutes;
    }

    private void ScheduleEmployees(Collection<? extends Employee> employees, Department department) {
    }

    public void setWorkShifts(ArrayList<WorkShift> wss){
        for (Department d : departments){
            for (WorkShift ws1 : d.getAllShifts()){
                for (WorkShift ws2 : wss){
                    if (ws1 == ws2) {
                        departmentLinks.computeIfAbsent(d, k -> new ArrayList<WorkShift>());
                        departmentLinks.get(d).add(new WorkShift(ws2));
                    }
                }
            }
        }
    }
}
