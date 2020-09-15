package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private List<Employee> employees;
    private CertificateHandler certificateHandler;
    private OurCalendar calendar;

    public Admin() {
        this.certificateHandler = CertificateHandler.getInstance();
        this.employees = new ArrayList<>();
        this.calendar = new OurCalendar();
        this.calendar.init();
    }

    public List<Employee> getAvailablePersons(long start, long end) {
        List<Employee> sublist = new ArrayList<>();
        sublist.removeIf(p -> p.isOccupied(start, end)); //ta bort alla som är upptagna
        return sublist;
    }


    public void consoleCommandCreateEmployee() {
        Scanner sc = new Scanner(System.in);
        String name;
        int personalId;
        System.out.println("Information om den nya anställda");
        System.out.println("Namn: ");
        name = sc.nextLine();
        System.out.println("Personnummer: ");
        personalId = sc.nextInt();
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

    public List<Employee> getEmployees (){
        return employees;
    }

    public CertificateHandler getCertificatehandler (){
        return certificateHandler;
    }

    public void createNewEmployee(String name, int personalId) {
        employees.add(new Employee(name, personalId));
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

    public void removeEmployee(int personalId) {
        for (Employee e: employees) {
            if (e.getPersonalId() == personalId) {
                employees.remove(e);
            }
        }

    }

}
