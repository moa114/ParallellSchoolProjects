package Model;

import java.util.Date;

public class Certificate {
    public final String name;
    private static long IDSTART = 0;
    public final long ID;


    public Certificate(String name) {
        this.name = name;
        this.ID = IDSTART++;
    }
}
