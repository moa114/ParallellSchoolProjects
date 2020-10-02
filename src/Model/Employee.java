package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an employee with a specified name, email, personal ID, certificates and time span where the employee is not available for work
 */
public class Employee {
    private List<OccupiedTime> occupiedTimes;
    private String name;
    private String email;
    public final String PERSONAL_ID;
    private List<EmployeeCertificate> certificates;

    /**
     * constructs an employee with a list for time span where the employee is not available for work, a specified name, specified personal ID and a list for provided certificates
     *
     * @param name       the employee's name
     * @param personalId the employee's personal ID
     */
    public Employee(String name, String personalId) {
        this.occupiedTimes = new ArrayList<>();
        this.name = name;
        this.PERSONAL_ID = personalId;
        this.certificates = new ArrayList<>();
    }

    public List<EmployeeCertificate> getAllCertificates() {
        return certificates;
    }

    public EmployeeCertificate getEmployeeCertificate(Certificate certificate) {
        for (EmployeeCertificate c : certificates) {
            if (c.getCertificate() == certificate) {
                return c;
            }
        }
        return null;
    }

    /**
     * Assigns a specified certificate to the employee
     *
     * @param certificate the certificate that should be assigned
     */
    public void assignCertificate(EmployeeCertificate certificate) {
        this.certificates.add(certificate);

    }

    /**
     * Assigns several specified certificates to the employee
     *
     * @param certificates list with all the certificates that should be assigned
     */
    public void assignCertificate(List<EmployeeCertificate> certificates) {
        this.certificates.addAll(certificates);
    }

    /**
     * Removes a specified certificate from the employee
     *
     * @param certificate the certificate that should be taken from the employee
     */
    public void unAssignCertificate(EmployeeCertificate certificate) {
        certificates.remove(certificate);
    }

    /**
     * checks if the employee is not available for work at an chosen time span
     *
     * @param start when the time span starts
     * @param end   when the time span stops
     * @return true if the employee is not available and false if the employee is available
     */
    public boolean isOccupied(long start, long end) {
        for (OccupiedTime occupiedTime : occupiedTimes) {
            if (occupiedTime.inBetween(start, end))
                return true;
        }
        return false;
    }

    /**
     * checks if the employee is qualified, has the required certificates, for a chosen workshift or not
     *
     * @param ws the chosen workshift
     * @return true if the employee has the required certificates for the workshift and false if not
     */
    public boolean isQualified(WorkShift ws) {
        int count = 0;
        for (Certificate certificate : ws.getAllCertificate()) {
            for (EmployeeCertificate certificate1 : certificates) {
                if (certificate1.getCertificate() == certificate) {
                    count++;
                }
            }
        }
        if (count == ws.getAllCertificate().size()) {
            return true;
        }
        return false;
    }

    public String getPersonalId() {
        return PERSONAL_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void unRegisterOccupation(OccupiedTime ot) {
        occupiedTimes.remove(ot);
    }

    public List<OccupiedTime> getOccupiedTimes() {
        return occupiedTimes;
    }

    public void registerOccupation() {

    }

    public void newName(String name) {
        this.name = name;
    }
}
