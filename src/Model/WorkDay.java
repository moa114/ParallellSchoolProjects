package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class WorkDay {
    public final long date;
    private HashMap<Department, HashMap<WorkShift, List<Employee>>> departmentListHashMap;
    private static List<Department> departments = new ArrayList<>();

    public WorkDay(long date) {
        this.date = date;
        departmentListHashMap = new HashMap<>();
    }

    boolean allDepartmentsFilled() {
        for (Department d : departments) {
            for (WorkShift w : d.getAllShifts()) {
                if (w.requiredPersonnel > departmentListHashMap.get(d).get(w).size())
                    return false;
            }
        }
        return true;
    }

    List<Employee> getWorkingPersonnel(Department department) {
        return null;
    }

    List<Department> getAllDepartments() {
        return null;
    }

    public void ScheduleEmployee(Employee employee, Department department, WorkShift workShift) {
        departmentListHashMap.get(department).get(workShift).add(employee);
        employee.occupiedTimes.add(workShift);
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
}
