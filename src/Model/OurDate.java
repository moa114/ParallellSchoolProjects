package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class OurDate {
    public final long date;
    HashMap<Department, List<Employee>> departmentListHashMap;
    static List<Department> departments = new ArrayList<>();

    public OurDate(long date) {
        this.date = date;
        departmentListHashMap = new HashMap<>();
    }

    boolean allDepartmentsFilled(){
        for (Department d : departments) {
            if (d.requiredPersonnel < departmentListHashMap.get(d).size())
                return false;
        }
        return true;
    }

    public int getNMissingPersonnel(Department department){
        return department.requiredPersonnel-departmentListHashMap.get(department).size();
    }

    List<Employee> getWorkingPersonel(Department department){return null;}
    List<Department> getAllDepartments(){return null;}
    public void ScheduleEmployee(Employee employee, Department department){
        departmentListHashMap.get(department).add(employee);
    }
    public void ScheduleEmployees(Collection<? extends Employee> employees, Department department){}
}
