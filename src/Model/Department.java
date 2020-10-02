package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department with a specified name and a list for work shifts where the department can be manned
 */
public class Department {
    private List<WorkShift> allShifts;
    private String name;

    /**
     * Constructs a department with a list for the work shifts where the department can be manned and a specified name
     * @param name the name of the department
     */
    public Department(String name) {
        this.allShifts = new ArrayList<>();
        this.name = name;
    }

    public String getName(){
        return name;
    }

    /**
     * Creates a work shift with a specified time span to the department where a chosen amount certificates are required from the employee
     * @param start start time of the shift
     * @param stop end time of the shift
     * @param certificates list of which certificates are required at the shift
     */
    public void createShift(long start, long stop, List<Certificate> certificates ) {
        allShifts.add(new WorkShift(start, stop, certificates));
    }

    public void removeShift(WorkShift ws){
        allShifts.remove(ws);
    }

    public List<WorkShift> getAllShifts() {
        return allShifts;
    }

    /**
     * Checks if all work shift of the department are manned
     * @return true if all work shifts are manned, else false
     */
    public boolean isAllShiftsFilled(){
        for(WorkShift ws: allShifts){
            if(!ws.isOccupied())
                return false;
        }
        return true;
    }
    }