package Model;

public class WorkShift extends OccupiedTime {
    final public int requiredPersonnel;

    public WorkShift(long start, long end, int requiredPersonnel) {
        super(start, end);
        this.requiredPersonnel = requiredPersonnel;
    }
}
