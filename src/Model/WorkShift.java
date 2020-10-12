package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a WorkShift in which an employee can work in if the have the specified certificates required
 */
public class WorkShift {
    private List<Certificate> certificates = new ArrayList<>();
    private Employee employee;
    private OccupiedTime occupiedTime;
    private OccupiedTime breakTime;
    private boolean occupied = false;
    final public boolean REPEAT;
    final public long START, END;

    /**
     * Creates a new workshift
     *
     * @param start       The starting time for the Workshift
     * @param end         The ending time for the Workshift
     * @param certificate Required Certificates for the Workshift
     */
    protected WorkShift(long start, long end, Certificate certificate, OccupiedTime breakTime, boolean repeat) {
        this.START = start;
        this.END = end;
        this.certificates.add(certificate);
        this.breakTime = breakTime;
        this.REPEAT = repeat;
    }

    /**
     * Creates a new workshift
     *
     * @param start        The starting time for the Workshift
     * @param end          The ending time for the Workshift
     * @param certificates A list of required Certificates
     */
    protected WorkShift(long start, long end, List<Certificate> certificates, OccupiedTime breakTime, boolean repeat) {
        this.START = start;
        this.END = end;
        this.certificates.addAll(certificates);
        this.breakTime = breakTime;
        this.REPEAT = repeat;
    }

    /**
     * Creates a new Workshift
     *
     * @param start The starting time for the Workshift
     * @param end   The ending time for the Workshift
     */
    protected WorkShift(long start, long end, OccupiedTime breakTime, boolean repeat) {
        this.START = start;
        this.END = end;
        this.breakTime = breakTime;
        this.REPEAT = repeat;
    }

    /**
     * Creates a copy of a previous Workshift without employee and occupation and moves it forward a week
     *
     * @param workShift The Workshift you wish to copy and place on another day
     * @param date      The date in a long format for Java.Date
     */
    protected WorkShift(WorkShift workShift, long date) {

        Date wsStart = new Date(workShift.START);
        this.START = date + WeekHandler.plusHours(wsStart.getHours()) + WeekHandler.plusMinutes(wsStart.getMinutes());
        wsStart.setTime(workShift.END);
        this.END = date + WeekHandler.plusHours(wsStart.getHours()) + WeekHandler.plusMinutes(wsStart.getMinutes());
        this.certificates = workShift.certificates;
        this.REPEAT = workShift.REPEAT;
        this.breakTime = workShift.breakTime;
    }

    /**
     * Creates a copy of a previous Workshift without employee and occupation and moves it forward a week
     *
     * @param workShift The Workshift you wish to copy and place on another day
     * @param date      The amount of days to add to worshift
     */
    protected WorkShift(WorkShift workShift, int date) {
        this.START = workShift.START + WeekHandler.plusDays(date);
        this.END = workShift.END + WeekHandler.plusDays(date);
        this.certificates = workShift.certificates;
        this.REPEAT = workShift.REPEAT;
        this.breakTime = workShift.breakTime;
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
    public void clearWorkShiftOccupation() {
        employee.unRegisterOccupation(occupiedTime);
        occupied = false;
    }

    public void addCertificate(Certificate c) {
        certificates.add(c);
    }

    public Certificate getCertificate(int index) {
        return certificates.get(index);
    }

    public int getCertificatesSize() {
        return certificates.size();
    }

    public OccupiedTime getBreakTime() {
        return breakTime;
    }

    /**
     * Removes a specified certificate from being required by the employees
     *
     * @param c the certificate that shall be removed
     */
    public void removeCertificate(Certificate c) {
        certificates.remove(c);
    }

    public Employee getEmployee() {
        return employee;
    }

    public OccupiedTime getOccupation() {
        return occupiedTime;
    }
}
