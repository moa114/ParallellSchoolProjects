package Model;

public enum DayOfWeek {
    monday(0),
    tuesday(1),
    wednesday(2),
    thursday(3),
    friday(4),
    saturday(5),
    sunday(6);

    public final int offset;

    DayOfWeek(int offset) {
        this.offset = offset;
    }

    static DayOfWeek getDay(int calendarDay){
        switch (calendarDay){
            case 1: return monday;
            case 2: return tuesday;
            case 3: return wednesday;
            case 4: return thursday;
            case 5: return friday;
            case 6: return saturday;
            case 7: return sunday;
        }
        return null;
    }
}
