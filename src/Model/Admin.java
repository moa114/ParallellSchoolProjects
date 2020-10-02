package Model;

import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents an static admin for the project with a list for all employees, a certificatehandler, a calendar and a employeesorter
 */

public class Admin implements Observable {
    private List<Employee> employees;
    private List<Department> departments;
    private CertificateHandler certificateHandler;
    private OurCalendar calendar;
    private EmployeeSorter employeeSorter;
    private List<Observer> observers, toBeAdded, toBeRemoved;
    private Exporter export;
    private static Admin instance = null;

    public static Admin getInstance() {
        if (instance == null)
            instance = new Admin();
        return instance;
    }

    private Admin() {
        this.export = new Exporter();
        this.certificateHandler = CertificateHandler.getInstance();
        this.employees = new ArrayList<>();
        this.calendar = OurCalendar.getInstance();
        this.employeeSorter = new EmployeeSorter();
        this.departments = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.toBeAdded = new ArrayList<>();
        this.toBeRemoved = new ArrayList<>();
        this.departments = new ArrayList<>();
    }

    /**
     * creates an employee based on input from the keyboard
     */
    public void consoleCommandCreateEmployee() {
        Scanner sc = new Scanner(System.in);
        String name;
        String personalId;
        System.out.println("Information om den nya anställda");
        System.out.println("Namn: ");
        name = sc.nextLine();
        System.out.println("Personnummer: ");
        personalId = sc.next();
        createNewEmployee(name, personalId);
        sc.nextLine();
        System.out.println("Do you want to give this person A Certificate? (y/n)");
        if (sc.nextLine().contains("y")) {
            boolean running = true;
            while (running) {
                System.out.println("Vad heter certifikatet?");
                String tmp = sc.nextLine();
                certificateHandler.createNewCertificate(tmp);
                System.out.println("Vill du lägga till ett till certifikat? (y/n)");
                if (sc.nextLine().contains("n"))
                    running = false;
            }
        }
        for (Employee e : employees) {
            System.out.println("____________________");
            System.out.println(e.getName());
            System.out.println(e.PERSONAL_ID);
            System.out.println(e.getAllCertificates());
        }
    }

    //Behöver vara public för att printa ut lista av alla anställda?
    public List<Employee> getEmployees() {
        return employees;

    }

    public void changeEmployeeName(Employee employee, String name) {
        employee.newName(name);
        notifyObservers();
    }

    /*hej*/

    public void addObserver(Observer o) {
        toBeAdded.add(o);
    }

    public void removeObserver(Observer o) {
        toBeRemoved.add(o);
    }

    public void notifyObservers() {
        observers.removeAll(toBeRemoved);
        toBeRemoved.clear();
        observers.forEach(Observer::update);
        observers.addAll(toBeAdded);
        toBeAdded.clear();
    }

    public int getEmployeeListSize() {
        return employees.size();
    }


    public EmployeeSorter getEmployeeSorter() {
        return employeeSorter;
    }

    public Employee getEmployeeByName(String name) {
        int count = 0;
        Employee tmp = null;
        for (Employee e : employees) {
            if (e.getName().equals(name)) {
                count++;
                tmp = e;
            }
        }
        if (count == 1) {
            return tmp;
        }
        System.out.println("invalid name");
        return null;//TODO exception?
    }


    public Employee getEmployeeByID(String ID) {
        for (Employee e : employees)
            if (e.getPersonalId().equals(ID))
                return e;
        System.out.println("invalid name");
        return null;
    }

    public CertificateHandler getCertificatehandler() {
        return certificateHandler;
    }

    /**
     * creates an employee with a specific name and a specific personal ID
     *
     * @param name       name of the employee
     * @param personalId personal ID of the employee
     */
    public void createNewEmployee(String name, String personalId) {
        if (checkLengthEmployeeId(personalId) && checkIfExistsEmployeeId(personalId)) {
            employees.add(new Employee(name, personalId));
        }
    }

    /**
     * checks if an chosen personal ID belongs to an employee
     *
     * @param PersonalId personal ID that shall be checked
     * @return true if the ID doesn't match an employee's and false if it does
     */
    private boolean checkIfExistsEmployeeId(String PersonalId) {
        for (Employee e : employees) {
            if (e.getPersonalId().equals(PersonalId)) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks so a chosen personal ID is 12 characters long
     *
     * @param PersonalId the personal ID
     * @return true if it's 12 characters long and false if it is not
     */
    private boolean checkLengthEmployeeId(String PersonalId) {
        if (PersonalId.length() == 12) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * creates an employeecertificate with a chosen expire date to a chosen employee
     *
     * @param certificate the certificate that should be assigned to the employee
     * @param e           the employee who shall get a certificate
     * @param expiryDate  the expire date of the employeecertificate
     */
    public void createEmployeeCertificate(Certificate certificate, Employee e, Date expiryDate) {
        e.assignCertificate(new EmployeeCertificate(certificate, expiryDate));
        certificateHandler.linkEmployeeToCertificate(certificate, e);
    }

    /**
     * Removes a chosen certificate from a chosen employee
     *
     * @param certificate the certificate that should be removed
     * @param e           the employee who's chosen certificate shall be removed
     */
    public void removeEmployeeCertificate(Certificate certificate, Employee e) {
        e.unAssignCertificate(e.getEmployeeCertificate(certificate));
        certificateHandler.unlinkEmployeeToCertificate(certificate, e);
    }

    /**
     * removes an chosen employee from the admins list of employees
     *
     * @param e the employee that shall be removed
     */
    public void removeEmployee(Employee e) {
        employees.remove(e);
    }

    /**
     * Removes an chosen employee based on its personal ID from the admins list of employees
     *
     * @param personalId the personal ID that belongs to the employee that shall be removed
     */
    public void removeEmployee(String personalId) {
        for (Employee e : employees) {
            if (e.getPersonalId().equals(personalId)) {
                employees.remove(e);
                break;
            }
        }

    }

    public void createNewDepartment(String name) {
        departments.add(new Department(name));
    }

    public void createWorkshift(Department d, long start, long end, List<Certificate> certificates) {
        d.createShift(start, end, certificates);
    }

    public void removeWorkshift(Department d, WorkShift ws) {
        d.removeShift(ws);
    }

    public Department getDepartmentByName(String name) {
        for (Department d : departments) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        System.out.println("invalid name");
        return null;//TODO exception?
    }

    private boolean validateTimeSpan(long start, long end) {
        return start < end;
    }

}
