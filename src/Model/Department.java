package Model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a department with a specified name and a list for work shifts where the department can be manned. It also has a value of max persons who can be on break at the same time
 */
public class Department {

    private List<WorkShift> allShifts;
    private String name;
    private int maxPersonsOnBreak;
    private BreakHandler breakHandler;
    private Color paint=new Color(1, 1, 1, 1);

    /**
     * Constructs a department with a list for the work shifts where the department can be manned and a specified name and a max value of persons who can be on break at the same time
     *
     * @param name              the name of the department
     * @param maxPersonsOnBreak value of persons who can be on break at the same time
     */
    protected Department(String name, int maxPersonsOnBreak) {
        this.allShifts = new ArrayList<>();
        this.name = name;
        this.maxPersonsOnBreak = maxPersonsOnBreak;
        this.breakHandler = BreakHandler.getInstance();
    }

    public Color getColor(){
        return paint;
    }

    public void setColor(Color c){
        this.paint=c;
    }

    public String getName() {
        return name;
    }


    /**
     * Creates a break for a work shift
     *
     * @param startForTheWorkShift start time of the work shift
     * @param stopForTheWorkShift  stop time of the work shift
     * @return a calculated break, placed as close to the middle of the work shift as possible, in form of an OccupiedTime
     */
    private OccupiedTime createBreak(long startForTheWorkShift, long stopForTheWorkShift) {
        long breakLength = breakHandler.calculateLengthOfBreak(startForTheWorkShift, stopForTheWorkShift);
        long breakStart = (((stopForTheWorkShift - startForTheWorkShift) / 2) - (breakLength / 2));
        int count = 0;
        boolean created = false;
        if (maxPersonsOnBreak == 0) {
            return null;
        } //TODO exception?
        while (!created) {
            for (WorkShift s : allShifts) {
                if (s.getBreakTime().inBetween(breakStart, breakStart + breakLength)) {
                    count++;
                }
            }
            if (count < maxPersonsOnBreak) {
                created = true;
                return new OccupiedTime(breakStart, breakStart + breakLength);

            } else {
                breakStart = breakStart + 1000 * 60 * 5;
                count = 0;
            }
        }
        return null;
    }

    /**
     * Creates a work shift with a specified time span to the department where chosen certificates are required from the employee
     *
     * @param start        start time of the shift
     * @param stop         end time of the shift
     * @param certificates list of which certificates are required at the shift
     */
    protected void createShift(long start, long stop, List<Certificate> certificates, boolean[] repeat) {
        WorkShift ws = new WorkShift(start, stop, certificates, createBreak(start, stop), true);
        if (setRepeat(ws, repeat)) {
            allShifts.add(new WorkShift(start, stop, certificates, createBreak(start, stop), false));
        }
    }

    protected void createShift(long start, long stop, Certificate certificate, boolean[] repeat) {
        WorkShift ws = new WorkShift(start, stop, certificate, createBreak(start, stop), true);
        if (setRepeat(ws, repeat)) {
            allShifts.add(new WorkShift(start, stop, certificate, createBreak(start, stop), false));
        }
    }

    protected void createShift(long start, long stop, boolean[] repeat) {
        WorkShift ws = new WorkShift(start, stop, createBreak(start, stop), true);
        if (setRepeat(ws, repeat)) {
            allShifts.add(new WorkShift(start, stop, createBreak(start, stop), false));
        }
    }

    private boolean setRepeat(WorkShift ws, boolean[] repeat) {
        boolean single = true;
        int c = new Date(ws.START).getDay();
        for (int i = 0; i < 7; i++) {
            if (repeat[(i + c) % 7]) {
                createShift(ws, i);
                single = false;
            }
        }
        return single;
    }

    protected void createShift(WorkShift ws) {
        allShifts.add(new WorkShift(ws, 0));
    }

    protected void createShift(WorkShift ws, int i) {
        allShifts.add(new WorkShift(ws, i));
    }

    protected void removeShift(WorkShift ws) {
        allShifts.remove(ws);
    }

    public List<WorkShift> getAllShifts() {
        return allShifts;
    } //TODO one shift

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

    public void setMaxPersonsOnBreak(int maxPersonsOnBreak1) {
        maxPersonsOnBreak = maxPersonsOnBreak1;
    }

    public int getMaxPersonsOnBreak() {
        return maxPersonsOnBreak;
    }

    public BreakHandler getBreakHandler() {
        return breakHandler;
    }

    public void setName(String name) {
        this.name = name;
    }
}