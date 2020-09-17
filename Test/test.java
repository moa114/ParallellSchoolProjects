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
            int count=0;
            for (WorkDay d : calendar.getOurDates()) {
               count++;
            }
            assertTrue(count==365);  // kollar om kalendern skapar ett helt år
        }


        @Test
        public void testCreateNewEmployee(){ //kollar så createNewEmployee lägger till i listan
            Admin admin= new Admin();
            admin.createNewEmployee("moa", 1);
            assertTrue(admin.getEmployees().size()==1);
        }

        @Test
     public void testAddCertificate(){
            Admin admin = new Admin();
            CertificateHandler ch= admin.getCertificatehandler();
            List<Certificate> allcert= new ArrayList<>();
            ch.createNewCertificate("Frukt");
            allcert.add(ch.getCertificate("Frukt"));
            Department dep=new Department("TestAvdelning", allcert);
            ch.createNewCertificate("Kassa");
            dep.addCertificate(ch.getCertificate("Kassa"));
            assertTrue(dep.getAllCertificate().size()==2);
        }

    @Test
    public void testRemoveCertificate(){
        Admin admin = new Admin();
        CertificateHandler ch= admin.getCertificatehandler();
        ch.createNewCertificate("Kassa");
        List<Certificate> allcert= new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        Department dep=new Department("TestAvdelning", allcert);
        dep.removeCertificate(ch.getCertificate("Kassa"));
        assertTrue(dep.getAllCertificate().size()==0);
    }

    @Test
    public void testQualifiedEmployee(){
            Admin admin = new Admin();
            Date date = new Date();
            CertificateHandler ch= admin.getCertificatehandler();
            admin.createNewEmployee("Moa",23);
            admin.getCertificatehandler().createNewCertificate("Kassa");
             List<Certificate> allcert= new ArrayList<>();
             allcert.add(ch.getCertificate("Kassa"));
            Department department = new Department("TestAvdelning",allcert);
            admin.createEmployeeCertificate(ch.getCertificate("Kassa"),admin.getEmployee("Moa"),(date));

        assertTrue(admin.getQualifiedPersons(department,admin.getEmployees()).size()==1);}
    }
