import Model.Admin;
import Model.CertificateHandler;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class testCertificateHandler {
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
}
