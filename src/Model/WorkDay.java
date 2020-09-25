package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a work day with a specified date, a hash map(with departments, work shifts and employees),and a list of departments
 */
public class WorkDay {
    public final long date;
    private HashMap<Department, HashMap<WorkShift, List<Employee>>> departmentListHashMap;
    private static List<Department> departments = new ArrayList<>();

    /**
     * Constructs a work day with a specified date and with hash map
     * @param date Date of the work day
     */
    public WorkDay(long date) {
        this.date = date;
        departmentListHashMap = new HashMap<>();
    }

    /**
     * Checks if all departments are filled
     * @return true if all the departments are filled and otherwise false
     */
    boolean allDepartmentsFilled() {
        for (Department d : departments) {
            for (WorkShift w : d.getAllShifts()) {
                if (w.requiredPersonnel > departmentListHashMap.get(d).get(w).size())
                    return false;
            }
        }
        return true;
    }

    /**
     * Gets the employees that are working in a specified department
     * @param department A department
     * @return the list of employees that are working in the department
     */
    List<Employee> getWorkingPersonnel(Department department) {
        return null;
    }

    /**
     * Gets all the departments
     * @return a list of all the departments
     */
    List<Department> getAllDepartments() {
        return null;
    }

    /**
     * A method that schedules an employee on a work shift in a department
     * @param employee The employee that will be scheduled
     * @param department The department where the employee will be scheduled
     * @param workShift The work shift the employee will be scheduled on
     */
    public void scheduleEmployee(Employee employee, Department department, WorkShift workShift) {
        departmentListHashMap.computeIfAbsent(department, k -> new HashMap<>());
        departmentListHashMap.get(department).computeIfAbsent(workShift, k -> new ArrayList<>());
        departmentListHashMap.get(department).get(workShift).add(employee);
        employee.occupiedTimes.add(workShift);
    }

    /**
     * A method that schedules employees on a work shift in a department
     * @param employees The list of employees that will be scheduled
     * @param department The department where the employees will be scheduled
     * @param workShift The work shift the employees will be scheduled on
     */
    public void scheduleEmployees(List<Employee> employees, Department department, WorkShift workShift) {
        departmentListHashMap.computeIfAbsent(department, k -> new HashMap<>());
        departmentListHashMap.get(department).computeIfAbsent(workShift, k -> new ArrayList<>());
        departmentListHashMap.get(department).get(workShift).addAll(employees);
        for (Employee employee : employees)
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
