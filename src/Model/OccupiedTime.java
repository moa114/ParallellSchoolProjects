package Model;

public class OccupiedTime {
    long start, end;
    public boolean inBetween(long start, long end){
        return end >= this.start && this.end >= start;
    }
}
