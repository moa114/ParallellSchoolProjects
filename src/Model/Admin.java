package Model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents an static admin for the project with a list for all employees, a certificatehandler, a calendar and a employeesorter
 */

public class Admin implements Observable {
    private final List<Employee> employees;
    private final List<Department> departments;
    private final CertificateHandler certificateHandler;
    private final OurCalendar calendar;
    private final EmployeeSorter employeeSorter;
    public final Login loginHandler; //TODO private
    private List<Observer> observers, toBeAdded, toBeRemoved;
    private Exporter export;
    private static Admin instance = null;

    /**
     * Return the singleton object of Admin
     *
     * @return The instance of the Admin
     */
    public static Admin getInstance() {
        if (instance == null)
            instance = new Admin();
        return instance;
    }

    private Admin() {
        this.export = new Exporter();
        this.loginHandler = new Login();
        this.certificateHandler = CertificateHandler.getInstance();
        this.employees = new ArrayList<>();
        this.calendar = OurCalendar.getInstance();
        this.employeeSorter = new EmployeeSorter();
        this.departments = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.toBeAdded = new ArrayList<>();
        this.toBeRemoved = new ArrayList<>();
    }

    /*
     * creates an employee based on input from the keyboard
     *
    public void consoleCommandCreateEmployee() {
        Scanner sc = new Scanner(System.in);
        String name;
        String personalId;
        System.out.println("Information om den nya anställda");
        System.out.println("Namn: ");
        name = sc.nextLine();
        System.out.println("Personnummer: ");
        personalId = sc.next();
        //createNewEmployee(name, personalId);
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
    } */

    /**
     * Returns an employee at the specified index
     *
     * @param index the index of employee in the arraylist
     * @return An employee
     */
    public Employee getEmployee(int index) {
        return employees.get(index);

    }

    /**
     * Change the name of a specified employee
     *
     * @param employee the employee to change the name
     * @param name     the new name to give to the employee
     */
    public void changeEmployeeName(Employee employee, String name) {
        employee.newName(name);
        notifyObservers();
    }

    public void addObserver(Observer o) {
        toBeAdded.add(o);
    }

    public void removeObserver(Observer o) {
        toBeRemoved.add(o);
    }

    public void notifyObservers() {
        observers.removeAll(toBeRemoved);
        toBeRemoved.clear();
        observers.addAll(toBeAdded);
        toBeAdded.clear();
        observers.forEach(Observer::update);
    }

    /**
     * Returns the size of the Arraylist holding all employees
     *
     * @return an integer on how many employees there are
     */
    public int getEmployeeListSize() {
        return employees.size();
    }

    /**
     * Returns the size of the Arraylist holding all departments
     *
     * @return an integer on how many departments there are
     */
    public int getDepartmentListSize() {
        return departments.size();
    }

    /**
     * Returns the EmployeeSorter which sorts employees into WorkShifts
     *
     * @return the employeesorter
     */
    public EmployeeSorter getEmployeeSorter() {
        return employeeSorter;
    }

    /**
     * Returns an employee with the specified name if there is no duplicate names otherwise throws an exeption
     *
     * @param name The name of the employee
     * @return An employee with the specified name if there is no duplicate names
     */
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


    /**
     * Returns the employee with the specified ID
     *
     * @param ID ID of the employee
     * @return The employee with the specified ID
     */
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
    public void createNewEmployee(String name, String personalId, String email) {
        if (checkLengthEmployeeId(personalId) && checkIfExistsEmployeeId(personalId)) {
            employees.add(new Employee(name, personalId, email));
            notifyObservers();
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
        return PersonalId.length() == 12;
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
        notifyObservers();
    }

    /**
     * calls the certificatehandler and notifies the observers
     *
     * @param name The name of the new certificate
     */
    public void createCertificate(String name) {
        certificateHandler.createNewCertificate(name);
        notifyObservers();
    }

    /**
     * calls the certificatehandler and notifies the observers
     *
     * @param certificate The certificate that will be removed
     */
    public void deleteCertificate(Certificate certificate) {
        certificateHandler.deleteCertificate(certificate);
        notifyObservers();
    }

    public void createEmployeeCertificate(Certificate certificate, Employee e) {
        e.assignCertificate(new EmployeeCertificate(certificate));
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
        notifyObservers();
    }

    /**
     * removes an chosen employee from the admins list of employees
     *
     * @param e the employee that shall be removed
     */
    public void removeEmployee(Employee e) {
        employees.remove(e);
        notifyObservers();
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

    /**
     * Creates a new WorkShift for a Department with multible required Certificates
     *
     * @param d            a Department
     * @param start        a starting time
     * @param end          an ending time
     * @param certificates A list of Certificates
     */
    public void createWorkshift(Department d, long start, long end, List<Certificate> certificates, boolean[] repeat) {
        if ((repeat.length == 7) && (validateTimeSpan(start, end) && validateStartTime(start))) {
            d.createShift(start, end, certificates, repeat); //TODO weekly booleans and not just true
        } else {
            //TODO exception
        }
        notifyObservers();
    }
    /**
     * Creates a new department and adds it to  workday
     * @param name Name of the department
     * @param minPersonsOnShift
     * @param color
     */
    public void createNewDepartment(String name, int minPersonsOnShift, Color color) {
        Department d = new Department(name,minPersonsOnShift);
        d.setColor(color);
        WorkDay.addDepartment(d);
        departments.add(d);
        notifyObservers();
    }

    /**
     * Creates a new department and adds it to  workday
     * @param name Name of the department
     * @param minPersonsOnShift Max amount of people aloud to have a break at the same time
     */
    public void createNewDepartment(String name, int minPersonsOnShift) {
        Department d = new Department(name, minPersonsOnShift);
        WorkDay.addDepartment(d);
        departments.add(d);
    }

    /**
     * Removes the specified department
     * @param department the department to remove
     */
    public void removeDepartment(Department department) {
        WorkDay.removeDepartment(department);
        departments.remove(department);
    }

    /**
     * Creates a new WorkShift for a Department with a required Certificate
     *
     * @param d           a Department
     * @param start       a starting time
     * @param end         an ending time
     * @param certificate a Certificate
     */
    public void createWorkshift(Department d, long start, long end, Certificate certificate, boolean[] repeat) {
        if ((repeat.length == 7) && (validateTimeSpan(start, end) && validateStartTime(start))) {
            d.createShift(start, end, certificate, repeat); //TODO weekly booleans and not just true
        } else {
            //TODO exception
        }
        notifyObservers();
    }

    /**
     * Creates a new WorkShift for a Department
     *
     * @param d     a Department
     * @param start a starting time
     * @param end   an ending time
     */
    public void createWorkshift(Department d, long start, long end, boolean[] repeat) {
        if ((repeat.length == 7) && (validateTimeSpan(start, end) && validateStartTime(start))) {
            d.createShift(start, end, repeat); //TODO weekly booleans and not just true
        } else {
            //TODO exception
        }
        notifyObservers();
    }

    /**
     * Creates a copy of an existing WorkShift for an Department
     *
     * @param d  a Department
     * @param ws the WorkShift
     */
    public void createWorkshift(Department d, WorkShift ws) {
        d.createShift(ws);
        notifyObservers();
    }

    /**
     * Removes a WorkShift
     *
     * @param d  the Department where the WorkShift is
     * @param ws the WorkShift
     */
    public void removeWorkshift(Department d, WorkShift ws) {
        d.removeShift(ws);
        notifyObservers();
    }

    /**
     * Get a Department based on its name if there aren´t multiple
     *
     * @param name The name of the department
     * @return The department that matches name
     */
    public Department getDepartmentByName(String name) {
        for (Department d : departments) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        System.out.println("invalid name");
        return null;//TODO exception?
    }

    /**
     * Checks if the end time is after the start time
     *
     * @param start Starting time
     * @param end   Ending time
     * @return Vailid or invalid
     */
    private boolean validateTimeSpan(long start, long end) {
        return start < end;
    }

    /**
     * Checks if the start date is a valid date
     *
     * @param start WorkShift starting time
     * @return Valid or invalid
     */
    private boolean validateStartTime(long start) {
        return new Date().getTime() <= start;
    }

    public List<Department> getDepartments(){
        return departments;
    }

    public void changeDepartmentName(Department department, String name){
        department.setName(name);
    }

    /**
     * Creates a vacation for the specified employee so he cannot be offered a job during the specified time
     *
     * @param employee The employee to get a veacation
     * @param start    start of the vacation
     * @param end      end of the vacation
     */
    public void setVacation(Employee employee, long start, long end) {
        Date startDate = new Date(start);
        Date endDate = new Date(end);
        int stop = calendar.getDateIndex(endDate);
        employee.registerOccupation(start, end);
        for (int i = calendar.getDateIndex(startDate); i <= stop + 4; i++) {
            calendar.getWorkday(i).unRegisterOccupations(employee, start, end);
        }
    }
}
