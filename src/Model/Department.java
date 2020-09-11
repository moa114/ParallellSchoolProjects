package Model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    int requiredPersonnel;
    String name;
    List<Certificate> certificates;

    public Department(int requiredPersonnel, String name) {
        this.requiredPersonnel = requiredPersonnel;
        this.name = name;
        this.certificates = new ArrayList<>();
    }

    public Department(int requiredPersonnel, String name, List<Certificate> certificates) {
        this.requiredPersonnel = requiredPersonnel;
        this.name = name;
        this.certificates = certificates;
    }
}
