import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testOccupiedTime {

    @Test
    public void testVacation() { //Kanske funkar men inte helt säkra
        Admin a = Admin.getInstance();
        a.createNewEmployee("mark", "000000131324");
        a.createNewDepartment("Kassa", 1);
        boolean b[] = {true, true, true, true, true, true, true};
        Date today = new Date();
        today.setHours(0);

        a.createWorkshift(a.getDepartmentByName("Kassa"), (today.getTime() + 1000*60*60*24*2), (today.getTime() + 1000*60*60*24*2 + 1000*60*60*8), b);
        for (int i = today.getDate()+1 ; i - today.getDate() <= 14 ; i++){
            a.getWorkday(i).setWorkDay();
            a.getWorkday(i).occupiesEmployee(a.getWorkday(i).getWorkShifts(a.getDepartmentByName("Kassa")).get(0), a.getEmployeeByID("000000131324"));
        }
        assertEquals(14, a.getEmployeeByID("000000131324").getOccupiedTimes().size());
        assertEquals(a.getEmployeeByID("000000131324").getOccupiedTimes().get(2), a.getWorkday(today.getDate()+3).getWorkShifts(a.getDepartmentByName("Kassa")).get(0).getOccupation());

        a.setVacation(a.getEmployeeByID("000000131324"), (today.getTime() + 1000*60*60*24*2), (today.getTime() + 1000*60*60*24*6));
        assertEquals(4, 14-a.getEmployeeByID("000000131324").getOccupiedTimes().size());
    }

}
