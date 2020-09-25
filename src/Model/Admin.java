package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private List<Employee> employees;
    private List<Department> departments;
    private CertificateHandler certificateHandler;
    private OurCalendar calendar;
    private EmployeeSorter employeeSorter;

    public Admin() {
        this.certificateHandler = CertificateHandler.getInstance();
        this.employees = new ArrayList<>();
        this.calendar = OurCalendar.getInstance();
        this.employeeSorter = new EmployeeSorter();
        this.departments = new ArrayList<>();
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
    //Behöver vara public för att printa ut lista av alla anställda?
    public List<Employee> getEmployees() {
        return employees;
    }
    public int getEmployeeListSize(){return employees.size();}


    public EmployeeSorter getEmployeeSorter(){
        return employeeSorter;
    }

    public Employee getEmployeeByName(String name) {
        int count=0;
        Employee tmp= null;
        for (Employee e : employees){
            if (e.getName().equals(name)){
                count++;
                tmp=e;
            }
        }
        if(count==1){ return tmp; }
        System.out.println("invalid name");
        return null;//TODO exception?
    }


    public Employee getEmployeeByID(String ID){
        for (Employee e : employees)
            if (e.getPersonalId().equals(ID))
                return e;
        System.out.println("invalid name");
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

    public void createNewDepartment(String name) {
        departments.add(new Department(name));
    }

    public void createNewDepartment(String name, ArrayList<Certificate> cl) {
        departments.add(new Department(name, cl));
    }

    public void createWorkshift(Department d, long start, long end, int personell){
        d.createShift(start, end, personell);
    }

    public void createWorkshift(Department d, WorkShift ws){
        d.removeShift(ws);
    }

}
