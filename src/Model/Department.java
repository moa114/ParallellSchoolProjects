package Model;

public enum Department {
    warehouse(4),
    dairy(2);
    int nRequiredPersons;

    Department(int nRequiredPersons) {
        this.nRequiredPersons = nRequiredPersons;
    }
}
