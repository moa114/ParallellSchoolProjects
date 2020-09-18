package Model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public List<OccupiedTime> occupiedTimes;
    public String name;
    public String email;
    public int personalId;
    public List<EmployeeCertificate> certificates;

    public Employee(String name, int personalId) {
        this.occupiedTimes = new ArrayList<>();
        this.name = name;
        this.personalId = personalId;
        this.certificates = new ArrayList<>();
    }

    public List<EmployeeCertificate> getAllCertificates() {
        return certificates;
    }

    public EmployeeCertificate getEmployeeCertificate(Certificate certificate){
        for (EmployeeCertificate c : certificates) {
            if (c.getCertificate() == certificate){
                return c;
            }
        }
        return null;
    }

    public void assignCertificate(EmployeeCertificate certificate) {
        this.certificates.add(certificate);

    }

    public void assignCertificate(List<EmployeeCertificate> certificates) {
        this.certificates.addAll(certificates);
    }

    public void unAssignCertificate(EmployeeCertificate certificate){
        certificates.remove(certificate);
    }

    public boolean isOccupied(long start, long end) {
        for (OccupiedTime occupiedTime : occupiedTimes) {
            if (occupiedTime.inBetween(start, end))
                return true;
        }
        return false;
    }
    public boolean isQualified(Department department){
        int count =0;
        for (Certificate certificate : department.getAllCertificate()){
            for(EmployeeCertificate certificate1: certificates){
                if (certificate1.getCertificate()==certificate){
                    count++;
                }
            }
        }
        if (count==department.getAllCertificate().size()){
            return true;
        }
        return false;
    }

    public int getPersonalId() {
        return personalId;
    }
    public String getName() {
        return name;
    }
}
