import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testWorkShift {
    @Test
    public void testAddCertificateToWorkShift() {
        CertificateHandler ch = CertificateHandler.getInstance();
        Date d= new Date();
        List<Certificate> allcert = new ArrayList<>();
        ch.createNewCertificate("Frukt");
        allcert.add(ch.getCertificate("Frukt"));
        WorkShift ws = new WorkShift(d.getTime(),(d.getTime()+(1000 * 60 * 60 * 8)), allcert);
        ch.createNewCertificate("Kassa");
        ws.addCertificate(ch.getCertificate("Kassa"));
        assertTrue(ws.getAllCertificate().size() == 2);
    }


    @Test
    public void testRemoveCertificateFromWorkShift() {
        Date d= new Date();
        CertificateHandler ch = CertificateHandler.getInstance();
        ch.createNewCertificate("Kassa");
        List<Certificate> allcert = new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        WorkShift ws = new WorkShift(d.getTime(),(d.getTime()+(1000 * 60 * 60 * 8)), allcert);
        ws.removeCertificate(ch.getCertificate("Kassa"));
        assertTrue(ws.getAllCertificate().size() == 0);
    }

    @Test
    public void testRemoveWorkShift() {
        Admin a = Admin.getInstance();
        Date d = new Date();
        a.createNewDepartment("Kassa");
        a.createWorkshift(a.getDepartmentByName("Kassa"), d.getTime()+1111,d.getTime()+11111);
        a.createWorkshift(a.getDepartmentByName("Kassa"), d.getTime()+1111,d.getTime()+11111);
        a.removeWorkshift(a.getDepartmentByName("Kassa"), a.getDepartmentByName("Kassa").getAllShifts().get(1));
        assertTrue(a.getDepartmentByName("Kassa").getAllShifts().size() == 1);
    }

    @Test
    public void testEditEmployees() {
        Admin a = Admin.getInstance();
        Date d = new Date();
        a.createNewDepartment("Kassa");
        a.createWorkshift(a.getDepartmentByName("Kassa"), d.getTime()+1111,d.getTime()+11111);
        a.createNewEmployee("Cristian är kass", "133742042069");
        a.createWorkshift(a.getDepartmentByName("Kassa"), a.getWorkday(1).DATE+10, a.getWorkday(1).DATE+1100);
        a.getWorkday(1).occupiesEmployee(a.getDepartmentByName("Kassa").getAllShifts().get(0), a.getEmployeeByID("133742042069"));
        assertTrue(a.getDepartmentByName("Kassa").getAllShifts().get(0).getEmployee().getPersonalId().equals("133742042069"));
        a.createNewEmployee("Markus passar bättre här", "694201337420");
        a.getWorkday(1).reOccupieEmployee(a.getDepartmentByName("Kassa").getAllShifts().get(0), a.getEmployeeByID("694201337420"));
        assertTrue(a.getDepartmentByName("Kassa").getAllShifts().get(0).getEmployee().getPersonalId().equals("694201337420"));
    }
}
