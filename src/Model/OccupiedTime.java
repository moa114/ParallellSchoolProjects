package Model;

public class OccupiedTime {
    long start, end;
    public boolean inBetween(long start, long end){
        if (end<this.start)
            return false;
        return start <= this.end;
    }
}
