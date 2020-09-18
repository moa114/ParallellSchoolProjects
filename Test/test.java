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
        OurCalendar calendar = new OurCalendar();
        calendar.init();         // Call method to test
        assertTrue(calendar.getOurDates().size() == 365);  // kollar om kalendern skapar ett helt år
    }

    @Test
    public void testCreateNewEmployee() { //kollar så createNewEmployee lägger till i listan
        Admin admin = new Admin();
        admin.createNewEmployee("moa", 1);
        assertTrue(admin.getEmployees().size() == 1);
    }

    @Test
    public void testDeleteEmployee() {
        Admin admin = new Admin();
        admin.createNewEmployee("moa", 1);
        admin.createNewEmployee("markus", 2);
        admin.removeEmployee(admin.getEmployees().get(0));
        assertTrue(admin.getEmployees().size() == 1);
        admin.createNewEmployee("Crille", 3);
        admin.removeEmployee(3);
        assertTrue(admin.getEmployees().size() == 1);
    }

    @Test
    public void testAddCertificateToDepartment() {
        Admin admin = new Admin();
        CertificateHandler ch = admin.getCertificatehandler();
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
        Admin admin = new Admin();
        CertificateHandler ch = admin.getCertificatehandler();
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
        admin.createNewEmployee("moa", 1);
        CertificateHandler ch = admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(0), new Date());
        Employee e = admin.getEmployees().get(0);
        Certificate kassa = ch.getCertificate("Kassa");
        EmployeeCertificate ec = e.getEmployeeCertificate(kassa);
        assertEquals(ec.getCertificateName() , "Kassa");
    }

    @Test
    public void testWhoHasCertificate() {
        Admin admin = new Admin();
        admin.createNewEmployee("moa", 1); //TODO det ska inte finnas dubletter av personnummer samt 10 siffror långt
        admin.createNewEmployee("moa", 2);
        admin.createNewEmployee("crilllle", 3);
        CertificateHandler ch = admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(0), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(1), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployees().get(1), new Date());
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).size() == 2);
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Frukt")).size() == 1);
        assertTrue(!ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).contains(admin.getEmployees().get(2)));
    }

    @Test
    public void testRemoveGlobalCertificate() {
        Admin admin = new Admin();
        admin.createNewEmployee("moa", 1);
        admin.createNewEmployee("moa", 2);
        admin.createNewEmployee("crilllle", 3);
        CertificateHandler ch = admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(0), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(1), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployees().get(1), new Date());
        ch.deleteCertificate("Kassa");
        assertTrue(admin.getEmployees().get(1).certificates.size()==1);
    }

    @Test
    public void testRemoveEmployeeCertificate() {
        Admin admin = new Admin();
        admin.createNewEmployee("moa", 1); //TODO det ska inte finnas dubletter av personnummer samt 10 siffror långt
        admin.createNewEmployee("moa", 2);
        admin.createNewEmployee("crilllle", 3);
        CertificateHandler ch = admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(0), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(1), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployees().get(1), new Date());
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).size() == 2);
        admin.removeEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(1));
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Kassa")).size() == 1);
        assertTrue(ch.getEmployeeWithCertificate(ch.getCertificate("Frukt")).size() == 1);
    }
    @Test
    public void testGetQualifiedPersons(){
        Admin admin = new Admin();
        CertificateHandler ch = admin.getCertificatehandler();
        admin.createNewEmployee("moa", 1);
        admin.createNewEmployee("Victor", 2);
        ch.createNewCertificate("Kassa");
        ch.createNewCertificate("Frukt");
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(0), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployees().get(0), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Kassa"), admin.getEmployees().get(1), new Date());
        admin.createEmployeeCertificate(ch.getCertificate("Frukt"), admin.getEmployees().get(1), new Date());
        List<Certificate> allcert = new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        allcert.add(ch.getCertificate("Frukt"));
        Department department = new Department("TestAvdelning",allcert);
        assertTrue(admin.getQualifiedPersons(department,admin.getEmployees()).size()==2);
    }

}
