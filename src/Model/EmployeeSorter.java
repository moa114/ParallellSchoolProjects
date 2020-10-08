package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles the sortation of employees
 */
public class EmployeeSorter {

    /**
     * Gets a list of employees that are both available at a given time span and are qualified with certain certificates
     *
     * @param employees    A list with employees that the method selects from
     * @param certificates A list with certificates
     * @param start        Start time of the time span
     * @param stop         Stop time of the time span
     * @return A list with employees that are available and qualified
     */
    public static List<Employee> getAvailableQualifiedPersonnel(List<Employee> employees, List<String> certificates, long start, long stop) {
        List<Employee> newList = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getAllCertificates().containsAll(certificates) && !e.isOccupied(start, stop))
                newList.add(e);
        }
        return newList;
    }

    /**
     * Gets a list of employees that are available at a given time span
     *
     * @param start        Start time of the time span
     * @param end          Stop time of the time span
     * @param employeeList A list with employees that the method selects from
     * @return A list with employees that are available
     */
    public List<Employee> getAvailablePersons(long start, long end, List<Employee> employeeList) { //skickar in lista med anställda i parametern för att kunna göra denna och getQualifiedPersons i valfri ordning
        List<Employee> availableList = new ArrayList<>();
        for (Employee e : employeeList)
            if (!e.isOccupied(start, end))
                availableList.add(e);
        return availableList;
    }

    /**
     * Gets a list of employees that are qualified for a certain workshift
     *
     * @param ws           A workshift
     * @param employeeList A list with employees that the method selects from
     * @return A list with employees that are qualified for the workshift
     */
    public List<Employee> getQualifiedPersons(WorkShift ws, List<Employee> employeeList) {
        List<Employee> qualifiedList = new ArrayList<>();
        for (Employee e : employeeList)
            if (e.isQualified(ws))
                qualifiedList.add(e);
        return qualifiedList;
    }


    //här vill vi kolla om employee har workshift i occupied time för att kolla så de får tillräcklig ledighet mellan passen
}