package Model;

import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Represents a department with a specified name and a list for work shifts where the department can be manned. It also has a minimum value of persons that has to work at the same time
 */
public class Department implements Observable{

    private List<WorkShift> allShifts;
    private List<WorkShift> addedShifts;
    private List<WorkShift> removedShifts;
    private List<Observer> observers;
    private String name;
    private int minPersonsOnShift;
    private BreakHandler breakHandler;
    private Color paint=new Color(1, 1, 1, 1);

    /**
     * Constructs a department with a list for the work shifts where the department can be manned and a specified name and a minimum value of persons that has to work at the same time
     *
     * @param name              the name of the department
     * @param minPersonsOnShift minimum value of persons who must work at the same time (not be on break)
     */
    protected Department(String name, int minPersonsOnShift) {
        this.allShifts = new ArrayList<>();
        this.name = name;
        this.minPersonsOnShift = minPersonsOnShift;
        this.breakHandler = BreakHandler.getInstance();
        this.observers = new ArrayList();
        this.addedShifts = new ArrayList();
        this.removedShifts = new ArrayList();
        for (int i = 0 ; i < OurCalendar.getInstance().getOurDateSize() ; i++){
            addObserver(OurCalendar.getInstance().getWorkday(i));
        }
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
        long breakStart = (((stopForTheWorkShift + startForTheWorkShift) / 2) - (breakLength / 2));
        int numberOfBreakTogether = 0;
        int numberOfWorkingPersonel=countPersonelOnDepartment(startForTheWorkShift, stopForTheWorkShift);
        if (minPersonsOnShift == 0) {
            return null;
        } //TODO exception?
        while (true) {
            for (WorkShift s : allShifts) {
                if (s.getBreakTime().inBetween(breakStart, breakStart + breakLength)) {
                    numberOfBreakTogether++;
                }
            }
            if (numberOfWorkingPersonel >= minPersonsOnShift){
                if ((numberOfWorkingPersonel==0)||((numberOfWorkingPersonel-numberOfBreakTogether)>=minPersonsOnShift)) {
                    return new OccupiedTime(breakStart, breakStart + breakLength);
                }
            }
            else{//mindre schemalagda än vad som krävs
                 return new OccupiedTime((breakStart+1+breakLength*numberOfWorkingPersonel),( (breakStart+1+breakLength*numberOfWorkingPersonel) + breakLength));
                }
            breakStart = breakStart + WeekHandler.plusMinutes(5);
            numberOfBreakTogether = 0;
        }
    }

    private int countPersonelOnDepartment(long startForTheWorkShift, long stopForTheWorkShift) {
        int count=0;
        for(WorkShift s : allShifts){
            if(s.END>= startForTheWorkShift && stopForTheWorkShift>=s.START)
                count++;
        }
        return count;
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
            WorkShift shift = new WorkShift(start, stop, certificates, createBreak(start, stop), false);
            allShifts.add(shift);
            addedShifts.add(shift);
        }
        notifyObservers();
    }

    protected void createShift(long start, long stop, Certificate certificate, boolean[] repeat) {
        WorkShift ws = new WorkShift(start, stop, certificate, createBreak(start, stop), true);
        if (setRepeat(ws, repeat)) {
            WorkShift shift = new WorkShift(start, stop, certificate, createBreak(start, stop), false);
            allShifts.add(shift);
            addedShifts.add(shift);
        }
        notifyObservers();
    }

    protected void createShift(long start, long stop, boolean[] repeat) {
        WorkShift ws = new WorkShift(start, stop, createBreak(start, stop), true);
        if (setRepeat(ws, repeat)) {
            WorkShift shift = new WorkShift(start, stop, createBreak(start, stop), false);
            allShifts.add(shift);
            addedShifts.add(shift);
        }
        notifyObservers();
    }

    protected void createShift(WorkShift ws) {
        allShifts.add(new WorkShift(ws, 0));
    }

    protected void createShift(WorkShift ws, int i) {
        WorkShift shift = new WorkShift(ws, createBreak(ws.START + WeekHandler.plusDays(i), ws.END + WeekHandler.plusDays(i)), i);
        allShifts.add(shift);
        addedShifts.add(shift);
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

    protected void removeShift(WorkShift ws) {
        allShifts.remove(ws);
        removedShifts.add(ws);
        notifyObservers();
    }

    public WorkShift getShift(int index){
        return allShifts.get(index);
    }

    public int getSizeAllShifts(){
        return allShifts.size();
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

    public void setMinPersonsOnShift(int minPersonsOnShift) {
        this.minPersonsOnShift = minPersonsOnShift;
    }

    public int getMinPersonsOnShift() {
        return minPersonsOnShift;
    }

    public BreakHandler getBreakHandler() {
        return breakHandler;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkShift getAddWorkShift(int index){
        return addedShifts.get(index);
    }

    public int getAddWorkShiftSize(){
        return addedShifts.size();
    }

    public WorkShift getRemoveWorkShift(int index) {
        return removedShifts.get(index);
    }

    public int getRemoveWorkShiftSize(){
        return removedShifts.size();
    }

    @Override
    public void addObserver(Observer observer) {
            observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update();
        }
        addedShifts.clear();
        removedShifts.clear();
    }


}
