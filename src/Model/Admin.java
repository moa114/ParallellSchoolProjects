package Model;

import java.util.*;

public class Admin implements Observable{
    private static Admin instance = null;
    private List<Employee> employees;
    private CertificateHandler certificateHandler;
    private OurCalendar calendar;
    private EmployeeSorter employeeSorter;
    private List<Observer> observers;

    public static Admin getInstance() {
        if (instance == null)
            instance = new Admin();;
        return instance;
    }

    private Admin() {
        this.certificateHandler = CertificateHandler.getInstance();
        this.employees = new ArrayList<>();
        this.calendar = OurCalendar.getInstance();
        this.employeeSorter = new EmployeeSorter();
        this.observers = new ArrayList<>();
    }

    public List<Employee> getAvailablePersons(long start, long end, List<Employee> employeeList) { //skickar in lista med anställda i parametern för att kunna göra denna och getQualifiedPersons i valfri ordning
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
            System.out.println(e.name);
            System.out.println(e.personalId);
            System.out.println(e.certificates);
        }
    }
    public void addObserver(Observer o){
        observers.add(o);
    }
    public void removeObserver(Observer o){
        observers.remove(o);
    }
    public void notifyObservers(){
        observers.forEach(Observer::update);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public EmployeeSorter getEmployeeSorter(){
        return employeeSorter;
    }

    public Employee getEmployee(String name) {
        for (Employee e : employees)
            if (e.getName().equals(name))
                return e;
        return null;
    }

    public CertificateHandler getCertificatehandler() {
        return certificateHandler;
    }

    public void createNewEmployee(String name, String personalId) {
        if (checkLengthEmployeeId(personalId) && checkIfExistsEmployeeId(personalId)) {
            employees.add(new Employee(name, personalId));
        }
    }

    private boolean checkIfExistsEmployeeId(String PersonalId) {
        for (Employee e : employees) {
            if (e.getPersonalId().equals(PersonalId)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLengthEmployeeId(String PersonalId) {
        if (PersonalId.length() == 12) {
            return true;
        } else {
            return false;
        }
    }

    public void createEmployeeCertificate(Certificate certificate, Employee e, Date expiryDate) {
        e.assignCertificate(new EmployeeCertificate(certificate, expiryDate));
        certificateHandler.linkEmployeeToCertificate(certificate, e);
    }

    public void removeEmployeeCertificate(Certificate certificate, Employee e) {
        e.unAssignCertificate(e.getEmployeeCertificate(certificate));
        certificateHandler.unlinkEmployeeToCertificate(certificate, e);
    }

    public void removeEmployee(Employee e) {
        employees.remove(e);
    }

    public void removeEmployee(String personalId) {
        for (Employee e : employees) {
            if (e.getPersonalId().equals(personalId)) {
                employees.remove(e);
                break;
            }
        }

    }

}
