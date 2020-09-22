import Model.Admin;
import Model.Certificate;
import Model.CertificateHandler;
import Model.Department;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class testDepartment {
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
    public void testDepartmentFilled() { //TODO  ?
        Admin admin = new Admin();

    }
}
