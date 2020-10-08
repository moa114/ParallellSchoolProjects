import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testAdmin {


    @Test
    public void testCreateNewEmployee() {  //kollar så createNewEmployee lägger till i listan och att man inte kan lägga till om personnummret inte är 12 långt samt om det redan finns
        Admin admin = Admin.getInstance();
        admin.createNewEmployee("moa", "1234789123");
        assertTrue(admin.getEmployeeListSize() == 0);
        admin.createNewEmployee("moa", "123456789123");
        assertTrue(admin.getEmployeeListSize() == 1);
        admin.createNewEmployee("moa", "123456789123");
        assertTrue(admin.getEmployeeListSize() == 1);
        admin.createNewEmployee("moa", "123456089123");
        assertTrue(admin.getEmployeeListSize() == 2);
    }

    @Test
    public void testDeleteEmployee() {
        Admin admin = Admin.getInstance();
        admin.createNewEmployee("moa", "123456789123");
        admin.createNewEmployee("markus", "213456789123");
        admin.removeEmployee(admin.getEmployeeByName("moa"));
        assertTrue(admin.getEmployeeListSize() == 1);
        admin.createNewEmployee("Crille", "312456789123");
        admin.removeEmployee("312456789123");
        assertTrue(admin.getEmployeeListSize() == 1);
    }



    @Test
    public void testRemoveEmployeeCertificate() {
        Admin admin = Admin.getInstance();
        admin.createNewEmployee("moa", "123456789231"); //TODO det ska inte finnas dubletter av personnummer samt 10 siffror långt
        admin.createNewEmployee("moa", "123456789235");
        admin.createNewEmployee("crilllle", "123456789239");
        CertificateHandler ch = admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("123456789231"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("123456789235"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployeeByID("123456789235"), new Date());
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).size() == 2);
        admin.removeEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("123456789235"));
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).size() == 1);
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Frukt")).size() == 1);
    }
    @Test
    public void testCreateWorkshift() {
        Admin admin=Admin.getInstance();
        Department department = new Department("kassa", 3);
        Date date = new Date();
        boolean repeat[] = {true, false, false, false, false, false, false};
        department.getBreakHandler().setMinBreakLength(1000*60*15);
        admin.createWorkshift(department,date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);
        admin.createWorkshift(department,date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);
        admin.createWorkshift(department,date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);

        assertTrue(department.getAllShifts().size()==3);}
}
