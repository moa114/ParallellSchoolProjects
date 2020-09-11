package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CertificateHandler {
    private static CertificateHandler single_instance = null;
    private List<Certificate> allCertificates;


    private CertificateHandler(){
        this.allCertificates = new ArrayList<>();
    }


    static CertificateHandler getInstance(){
        if (single_instance == null)
            single_instance = new CertificateHandler();
        return single_instance;
    }

    public Iterator<Certificate> getAllCertificates(){
        return allCertificates.iterator();
    }

    public Certificate getCertificate(String name) {
        for (Certificate c : allCertificates){
            if (c.name.equals(name))
                return c;
        }
        System.out.println("invalid name");
        return null;
    }

    public Certificate getCertificate(long ID) {
        for (Certificate c : allCertificates){
            if (c.ID == ID)
                return c;
        }
        System.out.println("invalid ID");
        return null;
    }


    public void createNewCertificate(String nameOfCertificate){
        this.allCertificates.add(new Certificate(nameOfCertificate));
    }

    public void assignCertificateToEmployees(Certificate certificate, List<Employee> employees){
        for (Employee e : employees){
            e.assignCertificate(certificate);
        }
    }
}
