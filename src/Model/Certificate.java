package Model;

public class Certificate {
    final String name;
    final java.util.Date expires;

    public Certificate(String name, java.util.Date expires) {
        this.name = name;
        this.expires = expires;
    }

    public Certificate(String name){
        this.name = name;
        this.expires = new java.util.Date();
        this.expires.setTime(Long.MAX_VALUE);
    }
}
