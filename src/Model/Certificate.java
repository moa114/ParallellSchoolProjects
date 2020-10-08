package Model;

import java.util.Date;

/**
 * Represents a certificate with a specified name and a ID value
 */

public class Certificate {
    public final String NAME;
    private static long idStart = 0;
    public final long ID;


    /**
     * Constructs a certificate with a name and assigns a unique ID value to it
     *
     * @param name the name of the certificate
     */

    public Certificate(String name) {
        this.NAME = name;
        this.ID = idStart++;
    }

    public String getName() {
        return NAME;
    }

    public long getID() {
        return ID;
    }
}
