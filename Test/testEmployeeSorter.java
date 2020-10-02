import Model.Admin;
import Model.Certificate;
import Model.CertificateHandler;
import Model.Department;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class testEmployeeSorter {
    @Test
    public void testGetQualifiedPersons() {
        Admin admin = Admin.getInstance();
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
}
