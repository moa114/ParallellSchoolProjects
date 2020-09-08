package Model;


import java.util.ArrayList;
import java.util.List;

public class Admin {
    List<Employee> employees;

    public List<Employee> getAvailablePersons(long start, long end, List<Certification> certificationRequirements){
        List<Employee> sublist = new ArrayList<>();
        for (Employee p : employees){
            if (p.licenses.containsAll(certificationRequirements))
                sublist.add(p);
        } //Hitta alla personer med de rätta kvalifikationer
        sublist.removeIf(p -> p.isOccupied(start, end)); //ta bort alla som är upptagna
        return sublist;
    }

}
