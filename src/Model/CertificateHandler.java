package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class CertificateHandler {
    private static CertificateHandler single_instance = null;
    private List<Certificate> allCertificates;
    private HashMap<Certificate, List<Employee>> employeeLinkCertificate;

    public void linkEmployeeToCertificate(Certificate c, Employee e){
        employeeLinkCertificate.get(c).add(e);
    }

    public void unlinkEmployeeToCertificate(Certificate c, Employee e){
        employeeLinkCertificate.get(c).remove(e);
    }

    //TODO Immutable
    public List<Employee> getEmployeeWithCertificate(Certificate c){
        return employeeLinkCertificate.get(c);
    }

    private CertificateHandler(){
        this.allCertificates = new ArrayList<>();
        this.employeeLinkCertificate = new HashMap<>();
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
        Certificate tmp = new Certificate(nameOfCertificate);
        this.allCertificates.add(tmp);
        employeeLinkCertificate.put(tmp, new ArrayList<Employee>());
    }
    
    public void deleteCertificate(Certificate certificate){
        for (Employee e:employeeLinkCertificate.get(certificate)) {
            e.unAssignCertificate(e.getEmployeeCertificate(certificate));
        }
        this.allCertificates.remove(certificate);
    }

    public void deleteCertificate(int ID){
        this.allCertificates.remove(ID);
    }

    public void deleteCertificate(String name){
        for (Certificate c: allCertificates) {
            if (c.name.equalsIgnoreCase(name)){
                deleteCertificate(c);
                break;
            }
        }
    }

    public void assignCertificateToEmployees(EmployeeCertificate certificate, List<Employee> employees){
        for (Employee e : employees){
            e.assignCertificate(certificate);
        }
    }
}
