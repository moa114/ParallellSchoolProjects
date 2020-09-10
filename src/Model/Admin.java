package Model;


import jdk.jshell.execution.StreamingExecutionControl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Admin {
    List<Employee> employees;
    CertificateHandler certificateHandler;
    Date throwAwayDate;
    OurCalendar calendar;

    public Admin() {
        this.certificateHandler = CertificateHandler.getInstance();
        this.employees = new ArrayList<>();
        this.throwAwayDate = new Date();
        this.calendar = new OurCalendar();
        this.calendar.init();
    }

    public List<Employee> getAvailablePersons(long start, long end){
        List<Employee> sublist = new ArrayList<>();
        sublist.removeIf(p -> p.isOccupied(start, end)); //ta bort alla som är upptagna
        return sublist;
    }





    public void consoleCommandCreateEmployee(){
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
        while (running){
            System.out.println("Vad heter certifikatet?");
            String tmp =sc.nextLine();
            certificateHandler.createNewCertificate(tmp);
            certificateHandler.assignCertificateToEmployee(tmp, employees.get(employees.size()-1));
            System.out.println("Vill du lägga till ett till certifikat? (y/n)");
            if (sc.nextLine().contains("n"))
                running = false;
            }
        }
        for (Employee e : employees){
            System.out.println("____________________");
            System.out.println(e.name);
            System.out.println(e.personalId);
            System.out.println(certificateHandler.getCertificates(e));
        }
    }

    public void createNewEmployee(String name, int personalId){
        employees.add(new Employee(name, personalId));
    }

}
