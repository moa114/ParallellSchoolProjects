package Model;

import java.util.List;

public class Employee {
    List<OccupiedTime> occupiedTimes;
    String name;

    public boolean isOccupied(long start, long end){
        for (OccupiedTime occupiedTime : occupiedTimes){
            if (occupiedTime.inBetween(start,end))
                return true;
        }
        return false;
    }
}
