package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkShift {
    private List<Certificate> certificates = new ArrayList<>();
    private Employee employee;
    private OccupiedTime occupiedTime;
    private boolean occupied = false;
    final public long START, END;

    /**
     * Creates a new workshift
     *
     * @param start        The starting time for the Workshift
     * @param end          The ending time for the Workshift
     * @param certificates Required Certificates for the Workshift
     */
    public WorkShift(long start, long end, Certificate certificate) {
        this.START = start;
        this.END = end;
        this.certificates.add(certificate);
    }

    /**
     * Creates a new workshift
     *
     * @param start        The starting time for the Workshift
     * @param end          The ending time for the Workshift
     * @param certificates A list of required Certificates
     */
    public WorkShift(long start, long end, List<Certificate> certificates) {
        this.START = start;
        this.END = end;
        this.certificates.addAll(certificates);
    }

    /**
     * Creates a new Workshift
     *
     * @param start The starting time for the Workshift
     * @param end   The ending time for the Workshift
     */
    public WorkShift(long start, long end) {
        this.START = start;
        this.END = end;
    }

    /**
     * Creates a copy of a previous Workshift without employee and occupation
     *
     * @param ws The Workshift you wish to copy
     */
    public WorkShift(WorkShift ws) {
        this.START = ws.START;
        this.END = ws.END;
        this.certificates = ws.certificates;
    }

    /**
     * Registers a new Employee to the Workshift
     *
     * @param e  The Employee
     * @param ot The Employees OccupiedTime
     */
    public void registerOccupation(Employee e, OccupiedTime ot) {
        if (!occupied) {
            //TODO checka att employee har certificate via metod
            this.employee = e;
            this.occupiedTime = ot;
            occupied = true;
        }
    }

    /**
     * Checks if the Workshift is properly occupied
     *
     * @return occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Clears the occupation for the Workshift
     */
    public void clearWorkShift() {
        employee.unRegisterOccupation(occupiedTime);
        occupied = false;
    }

    public void addCertificate(Certificate c) {
        certificates.add(c);
    }

    public List<Certificate> getAllCertificate() {
        return certificates;
    }

    /**
     * Removes a specified certificate from being required by the employees
     *
     * @param c the certificate that shall be removed
     */
    public void removeCertificate(Certificate c) {
        certificates.remove(c);
    }
}
