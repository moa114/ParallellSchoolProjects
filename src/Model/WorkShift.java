package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkShift {
    final public int requiredPersonnel;
    private ArrayList<Employee> deligatedEmployees;
    private HashMap<Employee, OccupiedTime> occupiedLinks;
    final public long start, end;

    public WorkShift(long start, long end, int requiredPersonnel) {
        this.start = start;
        this.end = end;
        this.requiredPersonnel = requiredPersonnel;
        this.deligatedEmployees = new ArrayList<>();
    }

    public void registerOccupation(Employee e, OccupiedTime ot){
        deligatedEmployees.add(e);
        occupiedLinks.put(e, ot);
    }

    public void unRegisterOccupation(Employee e){
        deligatedEmployees.remove(e);
        e.unRegisterOccupation(occupiedLinks.get(e));
        occupiedLinks.remove(e);
    }

    public boolean isFilled() { //TODO en algoritm som testar hur många subworkshifts som finns
        return deligatedEmployees.size() >= requiredPersonnel;
    }

    public void clearWorkShift(){
        for (Employee e : deligatedEmployees){
            e.unRegisterOccupation(occupiedLinks.get(e));
        }
        deligatedEmployees.clear();
        occupiedLinks.clear();
    }
}
