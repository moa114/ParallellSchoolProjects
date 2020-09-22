import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {

    @Test
    public void testInit() {
        OurCalendar calendar = OurCalendar.getInstance();
        List<WorkDay> list = calendar.getOurDates().subList(0, 365);
        assertEquals(365, list.size());  // kollar om kalendern skapar ett helt år
    }

    @Test
    public void testCreateNewEmployee() {  //kollar så createNewEmployee lägger till i listan och att man inte kan lägga till om personnummret inte är 12 långt samt om det redan finns
        Admin admin = new Admin();
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
        Admin admin = new Admin();
        admin.createNewEmployee("moa", "123456789123");
        admin.createNewEmployee("markus", "213456789123");
        admin.removeEmployee(admin.getEmployeeByName("moa"));
        assertTrue(admin.getEmployeeListSize() == 1);
        admin.createNewEmployee("Crille", "312456789123");
        admin.removeEmployee("312456789123");
        assertTrue(admin.getEmployeeListSize() == 1);
    }

    @Test
    public void testAddCertificateToDepartment() {
        CertificateHandler ch = CertificateHandler.getInstance();
        List<Certificate> allcert = new ArrayList<>();
        ch.createNewCertificate("Frukt");
        allcert.add(ch.getCertificate("Frukt"));
        Department dep = new Department("TestAvdelning", allcert);
        ch.createNewCertificate("Kassa");
        dep.addCertificate(ch.getCertificate("Kassa"));
        assertTrue(dep.getAllCertificate().size() == 2);
    }

    @Test
    public void testRemoveCertificateFromDepartment() {
        CertificateHandler ch = CertificateHandler.getInstance();
        ch.createNewCertificate("Kassa");
        List<Certificate> allcert = new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        Department dep = new Department("TestAvdelning", allcert);
        dep.removeCertificate(ch.getCertificate("Kassa"));
        assertTrue(dep.getAllCertificate().size() == 0);
    }

    @Test
    public void testDeligateCertificate() {
        Admin admin = new Admin();
        admin.createNewEmployee("moa", "123456789123");
        CertificateHandler ch = CertificateHandler.getInstance();
        ch.createNewCertificate("Kassa");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("123456789123"), new Date());
        Employee e = admin.getEmployeeByName("moa");
        Certificate kassa = ch.getCertificate("Kassa");
        EmployeeCertificate ec = e.getEmployeeCertificate(kassa);
        assertEquals(ec.getCertificateName(), "Kassa");
    }

    @Test
    public void testWhoHasCertificate() {
        Admin admin = new Admin();
        admin.createNewEmployee("moa", "123456789231"); //TODO det ska inte finnas dubletter av personnummer samt 10 siffror långt
        admin.createNewEmployee("moa", "213456789123");
        admin.createNewEmployee("crilllle", "312123456789");
        CertificateHandler ch = admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("123456789231"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("213456789123"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployeeByID("213456789123"), new Date());
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).size() == 2);
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Frukt")).size() == 1);
        assertTrue(!ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).contains(admin.getEmployeeByID("312123456789")));
    }

    @Test
    public void testRemoveGlobalCertificate() {
        Admin admin = new Admin();
        admin.createNewEmployee("moa", "123456789231");
        admin.createNewEmployee("moa", "123456789232");
        admin.createNewEmployee("crilllle", "1234567892315");
        CertificateHandler ch = admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("123456789231"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByID("123456789232"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployeeByID("123456789232"), new Date());
        ch.deleteCertificate("Kassa");
        assertTrue(admin.getEmployeeByID("123456789232").certificates.size() == 1);
    }

    @Test
    public void testRemoveEmployeeCertificate() {
        Admin admin = new Admin();
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
    public void testGetQualifiedPersons() {
        Admin admin = new Admin();
        CertificateHandler ch = admin.getCertificatehandler();
        admin.createNewEmployee("moa", "123456789231");
        admin.createNewEmployee("Victor", "123456789234");;
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByName("moa"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployeeByName("moa"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployeeByName("Victor"), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployeeByName("Victor"), new Date());
        List<Certificate> allcert = new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        allcert.add(ch.getCertificate("Frukt"));
        Department department = new Department("TestAvdelning", allcert);
        assertTrue(admin.getEmployeeSorter().getQualifiedPersons(department, admin.getEmployees()).size() == 2);
    }

    @Test
    public void testDepartmentFilled() {
        Admin admin = new Admin();

    }
}
