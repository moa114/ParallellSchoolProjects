package Model;

import java.util.Random;

public class Certificate {
    final String name;
    final long ID;
    private final static Random rn = new Random();

    public Certificate(String name){
        this.name = name;
        this.ID = rn.nextLong();
    }
}
