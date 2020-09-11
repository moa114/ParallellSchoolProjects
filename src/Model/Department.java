package Model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    int requiredPersonel;
    String name;
    List<Certificate> certificates;

    public Department(int requiredPersonel, String name) {
        this.requiredPersonel = requiredPersonel;
        this.name = name;
        this.certificates = new ArrayList<>();
    }

    public Department(int requiredPersonel, String name, List<Certificate> certificates) {
        this.requiredPersonel = requiredPersonel;
        this.name = name;
        this.certificates = certificates;
    }
}
