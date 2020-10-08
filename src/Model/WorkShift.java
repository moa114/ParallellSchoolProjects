package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
     * @param start        The starting time for the Workshift
     * @param end          The ending time for the Workshift
     * @param certificate Required Certificates for the Workshift
     */
    public WorkShift(long start, long end, Certificate certificate, OccupiedTime breakTime, boolean repeat) {
        this.START = start;
        this.END = end;
        this.certificates.add(certificate);
        this.breakTime= breakTime;
        this.REPEAT = repeat;
    }

    /**
     * Creates a new workshift
     *
     * @param start        The starting time for the Workshift
     * @param end          The ending time for the Workshift
     * @param certificates A list of required Certificates
     */
    public WorkShift(long start, long end, List<Certificate> certificates, OccupiedTime breakTime, boolean repeat) {
        this.START = start;
        this.END = end;
        this.certificates.addAll(certificates);
        this.breakTime=breakTime;
        this.REPEAT = repeat;
    }

    /**
     * Creates a new Workshift
     *
     * @param start The starting time for the Workshift
     * @param end   The ending time for the Workshift
     */
    public WorkShift(long start, long end, OccupiedTime breakTime, boolean repeat) {
        this.START = start;
        this.END = end;
        this.breakTime=breakTime;
        this.REPEAT = repeat;
    }

    /**
     * Creates a copy of a previous Workshift without employee and occupation and moves it forward a week
     *
     * @param ws The Workshift you wish to copy
     */
    public WorkShift(WorkShift ws, long date) {

        Date wsStart = new Date(ws.START);
        this.START = date + wsStart.getHours()*60*60*1000 + wsStart.getMinutes()*60*1000;
        wsStart.setTime(ws.END);
        this.END = date + wsStart.getHours()*60*60*1000 + wsStart.getMinutes()*60*1000;
        this.certificates = ws.certificates;
        this.REPEAT = ws.REPEAT;
        this.breakTime = ws.breakTime;
    }

    public WorkShift(WorkShift ws, int date){
        this.START = ws.START+ date*24*60*60*1000;
        this.END = ws.END + date*24*60*60*1000;
        this.certificates = ws.certificates;
        this.REPEAT = ws.REPEAT;
        this.breakTime = ws.breakTime;
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

    public List<Certificate> getAllCertificate() {
        return certificates;
    }
    public OccupiedTime getBreakTime(){return breakTime;}

    /**
     * Removes a specified certificate from being required by the employees
     *
     * @param c the certificate that shall be removed
     */
    public void removeCertificate(Certificate c) {
        certificates.remove(c);
    }

    public Employee getEmployee() {return employee;}

    public OccupiedTime getOccupation() {
        return occupiedTime;
    }
}
