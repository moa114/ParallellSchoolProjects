package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department with a specified name and a list for work shifts where the department can be manned. It also has a value of max persons who can be on break at the same time
 */
public class Department {
    private List<WorkShift> allShifts;
    private String name;
    private int maxPersonsOnBreak;
    private BreakHandler breakHandler;

    /**
     * Constructs a department with a list for the work shifts where the department can be manned and a specified name and a max value of persons who can be on break at the same time
     *
     * @param name the name of the department
     * @param maxPersonsOnBreak value of persons who can be on break at the same time
     */
    public Department(String name, int maxPersonsOnBreak) {
        this.allShifts = new ArrayList<>();
        this.name = name;
        this.maxPersonsOnBreak=maxPersonsOnBreak;
        this.breakHandler= BreakHandler.getInstance();
    }

    public String getName() {
        return name;
    }

    /**
     * Creates a work shift with a specified time span to the department where chosen certificates are required from the employee
     *
     * @param start        start time of the shift
     * @param stop         end time of the shift
     * @param certificates list of which certificates are required at the shift
     */
    public void createShift(long start, long stop, List<Certificate> certificates) {
        allShifts.add(new WorkShift(start, stop, certificates, createBreak(start,stop)));
    }
    public void createShift(long start, long stop) {
        allShifts.add(new WorkShift(start, stop,  createBreak(start,stop)));
    }

    /**
     * Creates a break for a work shift
     * @param startForTheWorkShift start time of the work shift
     * @param stopForTheWorkShift stop time of the work shift
     * @return a calculated break, placed as close to the middle of the work shift as possible, in form of an OccupiedTime
     */
    private OccupiedTime createBreak(long startForTheWorkShift, long stopForTheWorkShift){
        long breakLength = breakHandler.calculateLengthOfBreak(startForTheWorkShift,stopForTheWorkShift);
        long breakStart=(((stopForTheWorkShift-startForTheWorkShift)/2)-(breakLength/2));
        int count = 0;
        boolean created = false;
        if(maxPersonsOnBreak==0){return null;} //TODO exception?
        while(!created) {
            for (WorkShift s : allShifts) {
                if (s.getBreakTime().inBetween(breakStart, breakStart + breakLength)) {
                    count++;
                }
            }
            if (count < maxPersonsOnBreak) {
                created=true;
                return new OccupiedTime(breakStart, breakStart + breakLength);

            }
            else{
                breakStart=breakStart+1000 * 60 * 5;
                count=0;
            }
        }
    return null;}

    public void removeShift(WorkShift ws) {
        allShifts.remove(ws);
    }

    public List<WorkShift> getAllShifts() {
        return allShifts;
    }

    /**
     * Checks if all work shift of the department are manned
     *
     * @return true if all work shifts are manned, else false
     */
    public boolean isAllShiftsFilled() {
        for (WorkShift ws : allShifts) {
            if (!ws.isOccupied())
                return false;
        }
        return true;
    }
    private void setMaxPersonsOnBreak(int maxPersonsOnBreak1){
        maxPersonsOnBreak=maxPersonsOnBreak1;
    }
    private int getMaxPersonsOnBreak(){
        return maxPersonsOnBreak;
    }
    public BreakHandler getBreakHandler(){
        return breakHandler;
    }

}