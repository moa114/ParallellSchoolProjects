package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OurDate {
    public final long date;
    List<Department> departments;
    List<Employee> employees;

    public OurDate(long date) {
        this.date = date;
        this.departments = new ArrayList<>();
    }

    boolean allDepartmentsFilled(){
        for (Department d : departments){

        }
    }
    List<Employee> getWorkingPersonel(Department department){return null;}
    List<Department> getAllDepartments(){return null;}
    public void ScheduleEmployee(Employee employee, Department department){}
    public void ScheduleEmployees(Collection<? extends Employee> employees, Department department){}
}
