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

    public void createNewCertificate(String nameOfCertificate){
        this.allCertificates.add(new Certificate(nameOfCertificate));
    }

    public void assignCertificateToEmployees(Certificate certificate, List<Employee> employees){
        for (Employee e : employees){
            e.assignCertificate(certificate);
        }
    }
}
