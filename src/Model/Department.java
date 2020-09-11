package Model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    List<WorkShift> allShifts;
    String name;
    List<Certificate> certificates;

    public Department(String name, List<Certificate> certificates) {
        this.allShifts = new ArrayList<>();
        this.name = name;
        this.certificates = certificates;
    }

    public void CreateShift(long start, long stop, int nEmployees){
        allShifts.add(new WorkShift(start, stop, nEmployees));
    }

    public List<WorkShift> getAllShifts() {
        return allShifts;
    }
}
