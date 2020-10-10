package Model;

/**
 * Represents a handler for the breaks of the work shifts, has information of how long a break should be
 */
public class BreakHandler {
    private long minBreakLength;
    private long midBreakLength;
    private long maxBreakLength;
    private static BreakHandler instance = null;


    private BreakHandler() {
    }

    public static BreakHandler getInstance() {
        if (instance == null)
            instance = new BreakHandler();
        return instance;
    }

    /**
     * Calculates length of the break according to chosen values of how long a break should be in various lengths of work shifts.
     *
     * @param start start value of the work shift
     * @param stop  stop value of the work shift
     * @return length of the break
     */
    public long calculateLengthOfBreak(long start, long stop) {
        if ((stop - start >= 1000 * 60 * 60 * 3) && (stop - start <= 1000 * 60 * 60 * 5)) {
            return minBreakLength;
        }
        if (stop - start <= 1000 * 60 * 60 * 8) {
            return midBreakLength;
        }
        if (stop - start > 1000 * 60 * 60 * 8) {
            return maxBreakLength;
        }

        return 0;
    }

    public void setMinBreakLength(long minBreakLength) {
        this.minBreakLength = minBreakLength;
    }

    public void setMidBreakLength(long midBreakLength) {
        this.midBreakLength = midBreakLength;
    }

    public void setMaxBreakLength(long maxBreakLength) {
        this.maxBreakLength = maxBreakLength;
    }

    public long getMinBreakLength() {
        return minBreakLength;
    }

    public long getMidBreakLength() {
        return midBreakLength;
    }

    public long getMaxBreakLength() {
        return maxBreakLength;
    }
}
