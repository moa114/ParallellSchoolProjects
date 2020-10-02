package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department with a specified name, a list for certificates that is required for the employee to provide to be able to work at the department and a list for work shifts where the department can be manned
 */
public class Department {
    private List<WorkShift> allShifts;
    private String name;
    private List<Certificate> certificates;

    /**
     * Constructs a department with a list for the work shifts where the department can be manned, a specified name and assigns chosen certificates for the department to require
     * @param name the name of the department
     * @param certificates list of all certificates the department shall require from the employee
     */
    public Department(String name, List<Certificate> certificates) {
        this.allShifts = new ArrayList<>();
        this.name = name;
        this.certificates = certificates;
    }

    public Department(String name) {
        this.allShifts = new ArrayList<>();
        this.name = name;
        this.certificates = new ArrayList<>();
    }

    /**
     * Creates a work shift with a specified time span to the department where a chosen amount of employees can be scheduled
     * @param start start time of the shift
     * @param stop end time of the shift
     * @param nEmployees number of how many employees that can work at the work shift
     */
    public void createShift(long start, long stop, int nEmployees) {
        allShifts.add(new WorkShift(start, stop, nEmployees));
    }

    public void createShift(WorkShift ws) {
        allShifts.add(new WorkShift(ws));
    }

    public void removeShift(WorkShift ws){
        allShifts.remove(ws);
    }

    public List<WorkShift> getAllShifts() {
        return allShifts;
    }

    /**
     * Adds a specified certificate to the department to require from the employees
      * @param c certificate that shall be added
     */
    public void addCertificate(Certificate c) {
        certificates.add(c);
    }

    public List<Certificate> getAllCertificate() {
        return certificates;
    }

    /**
     * Removes a specified certificate from being required by the employees
     * @param c the certificate that shall be removed
     */
    public void removeCertificate(Certificate c) {
        certificates.remove(c);
    }

    public String getName(){
        return name;
    }
}