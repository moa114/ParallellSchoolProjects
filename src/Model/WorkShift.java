package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkShift {
    private List<Certificate> certificates;
    private Employee employee;
    private OccupiedTime occupiedTime;
    private boolean occupied = false;
    final public long start, end;

    /**
     * Creates a new workshift
     * @param start The starting time for the Workshift
     * @param end The ending time for the Workshift
     * @param certificate Required Certificate for the Workshift
     */
    public WorkShift(long start, long end, Certificate certificate) {
        this.start = start;
        this.end = end;
        this.certificates.add(certificate);
    }

    /**
     * Creates a new workshift
     * @param start The starting time for the Workshift
     * @param end The ending time for the Workshift
     * @param certificates A list of required Certificates 
     */
    public WorkShift(long start, long end, List<Certificate> certificates) {
        this.start = start;
        this.end = end;
        this.certificates.addAll(certificates);
    }

    /**
     * Creates a new Workshift
     * @param start The starting time for the Workshift
     * @param end The ending time for the Workshift
     */
    public WorkShift(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a copy of a previous Workshift without employee and occupation
     * @param ws The Workshift you wish to copy
     */
    public WorkShift(WorkShift ws) {
        this.start = ws.start;
        this.end = ws.end;
        this.certificates = ws.certificates;
    }

    /**
     * Registers a new Employee to the Workshift
     * @param e The Employee
     * @param ot The Employees OccupiedTime
     */
    public void registerOccupation(Employee e, OccupiedTime ot){
        if (!occupied) {
            //TODO checka att employee har certificate via metod
            this.employee = e;
            this.occupiedTime = ot;
            occupied = true;
        }
    }

    /**
     * Checks if the Workshift is properly occupied
     * @return occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Clears the occupation for the Workshift
     */
    public void clearWorkShift(){
        employee.unRegisterOccupation(occupiedTime);
        occupied = false;
    }
}
