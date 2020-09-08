package Model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Engine {
    List<Person> employees;

    public List<Person> getAvailablePersons(long start,long end, List<Certification> certificationRequirements){
        List<Person> sublist = new ArrayList<>();
        for (Person p : employees){
            if (p.licenses.containsAll(certificationRequirements))
                sublist.add(p);
        } //Hitta alla personer med de rätta kvalifikationer
        sublist.removeIf(p -> p.isOccupied(start, end)); //ta bort alla som är upptagna
        return sublist;
    }

}
