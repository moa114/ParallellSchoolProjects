package Model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    List<OccupiedTime> occupiedTimes;
    String name;
    int personalId;
    List<Certificate> certificates;

    public Employee(String name, int personalId) {
        this.occupiedTimes = new ArrayList<>();
        this.name = name;
        this.personalId = personalId;
        this.certificates = new ArrayList<>();
    }

    public void assignCertificate(Certificate certificate){
        certificates.add(certificate);
    }

    public void assignCertificate(List<Certificate> certificates){
        this.certificates.addAll(certificates);
    }

    public boolean isOccupied(long start, long end){
        for (OccupiedTime occupiedTime : occupiedTimes){
            if (occupiedTime.inBetween(start,end))
                return true;
        }
        return false;
    }
}
