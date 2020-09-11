package Model;

public class Certificate {
    final String name;
    private static long IDSTART = 0;
    public final long ID;

    public Certificate(String name){
        this.name = name;
        this.ID = IDSTART++;
    }
}
