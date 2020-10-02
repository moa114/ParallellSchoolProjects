package Model;

/**
 * Represents a time span when someone is not available, has a start and an end value
 */
public class OccupiedTime {
    long start, end;

    /**
     * Constructs an occupied time with a start and an end value
     * @param start The start value of the time span
     * @param end The end value of the time span
     */
    public OccupiedTime(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public OccupiedTime(WorkShift ws) {
        this.start = ws.start;
        this.end = ws.end;
    }

    /**
     * Checks if a start value and an end value is in between the occupied time
     * @param start The start value of the time span
     * @param end The end value of the time span
     * @return true if the time span is in between the occupied time span, false otherwise
     */
    public boolean inBetween(long start, long end) {
        return end >= this.start && this.end >= start;
    }
}
