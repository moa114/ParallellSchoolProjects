package Model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private List<OccupiedTime> occupiedTimes;
    private String name;
    private int personalId;

    public Employee(String name, int personalId) {
        this.occupiedTimes = new ArrayList<>();
        this.name = name;
        this.personalId = personalId;
    }

    public boolean isOccupied(long start, long end){
        for (OccupiedTime occupiedTime : occupiedTimes){
            if (occupiedTime.inBetween(start,end))
                return true;
        }
        return false;
    }

    public String getname() {
        return name;
    }

    public int getpersonalId() {
        return personalId;
    }
}
