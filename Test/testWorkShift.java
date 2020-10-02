import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testWorkShift {
    @Test
    public void testAddCertificateToDepartment() {
        CertificateHandler ch = CertificateHandler.getInstance();
        Date d= new Date();
        List<Certificate> allcert = new ArrayList<>();
        ch.createNewCertificate("Frukt");
        allcert.add(ch.getCertificate("Frukt"));
        WorkShift ws = new Department(d.getTime(),(d.getTime()+(1000 * 60 * 60 * 8)), allcert);
        ch.createNewCertificate("Kassa");
        ws.addCertificate(ch.getCertificate("Kassa"));
        assertTrue(ws.getAllCertificate().size() == 2);
    }


    @Test
    public void testRemoveCertificateFromDepartment() {
        Date d= new Date();
        CertificateHandler ch = CertificateHandler.getInstance();
        ch.createNewCertificate("Kassa");
        List<Certificate> allcert = new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        WorkShift ws = new Department(d.getTime(),(d.getTime()+(1000 * 60 * 60 * 8)), allcert);
        ws.removeCertificate(ch.getCertificate("Kassa"));
        assertTrue(ws.getAllCertificate().size() == 0);
    }
}
