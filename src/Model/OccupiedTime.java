package Model;

public class OccupiedTime {
    long start, end;

    public OccupiedTime(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public boolean inBetween(long start, long end) {
        return end >= this.start && this.end >= start;
    }
}
