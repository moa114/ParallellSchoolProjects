package Model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSorter {
    public static List<Employee> getAvailablePersonnel(List<Employee> employees, List<String> certificates, long start, long stop) {
        List<Employee> newList = new ArrayList<>();
        for (Employee e : employees) {
            if (e.certificates.containsAll(certificates) && !e.isOccupied(start, stop))
                newList.add(e);
        }
        return newList;
    }

}
