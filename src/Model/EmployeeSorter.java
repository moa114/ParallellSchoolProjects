package Model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSorter {


    public List<Employee> getAvailablePersons(long start, long end,List<Employee>employeeList) { //skickar in lista med anställda i parametern för att kunna göra denna och getQualifiedPersons i valfri ordning
        List<Employee> availableList = new ArrayList<>();
        for (Employee e : employeeList)
            if (!e.isOccupied(start, end))
                availableList.add(e);
        return availableList;
    }

    public List<Employee> getQualifiedPersons(Department department, List<Employee> employeeList) {
        List<Employee> qualifiedList = new ArrayList<>();
        for (Employee e : employeeList)
            if (e.isQualified(department))
                qualifiedList.add(e);
        return qualifiedList;
    }


    //här vill vi kolla om employee har workshit i occupied time för att kolla så de får tillräcklig ledighet mellan passen
}