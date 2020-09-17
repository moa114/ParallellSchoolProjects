import Model.*;

import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;


public class Runnable {

    public static void main(String[] args) {
        Admin admin = new Admin();
        CertificateHandler certificateHandler = admin.getCertificatehandler();
        OurCalendar calendar = OurCalendar.getInstance();
        calendar.init();
        int index = askQuestionInt("How many persons?");
        for (int i = 0; i<index; i++)
            admin.createNewEmployee(askQuestionString("Name " + (1+i)), askQuestionInt("Personal ID"));
        for (int i = askQuestionInt("How many certificates?"); i>0; i--)
            certificateHandler.createNewCertificate(askQuestionString("Name of certificate?"));
        Iterator<Certificate> iterator = certificateHandler.getAllCertificates();
        while (iterator.hasNext())
            System.out.println(iterator.next().name);
        for (Employee e : admin.getEmployees())
            System.out.println(e.name);
        admin.getEmployees().get(askQuestionInt("which employee(0, 1, 2, 3...)")).assignCertificate(new EmployeeCertificate(certificateHandler.getCertificate(askQuestionString("name of certificate")), new Date()));

    }

    public static int askQuestionInt(String question){
        System.out.println(question);
        return new Scanner(System.in).nextInt();
    }

    public static String askQuestionString(String question){
        System.out.println(question);
        return new Scanner(System.in).next();
    }
}
