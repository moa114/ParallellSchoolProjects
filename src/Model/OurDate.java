package Model;

import java.util.Collection;
import java.util.List;

public class OurDate {
    boolean allDepartmentsFilled(){return true;};
    List<Employee> getWorkingPersonel(Department department){return null;}
    List<Department> getAllDepartments(){return null;}
    public void ScheduleEmployee(Employee employee, Department department){}
    public void ScheduleEmployees(Collection<? extends Employee> employees, Department department){}
}
