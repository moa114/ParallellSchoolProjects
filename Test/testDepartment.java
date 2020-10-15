import Model.Admin;
import Model.Department;
import Model.WorkShift;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class testDepartment {
    @Test
    public void testCreateBreak() {
        Admin admin=Admin.getInstance();
        admin.createNewDepartment("Kassa", 2);
        Date date = (new Date());
        date.setTime(date.getTime()+(1000*60*60*24));
        boolean repeat[] = {false, false, false, false, true, false, false}; //TODO Markus fixar ordentlig repeat
        admin.getDepartmentByName("Kassa").getBreakHandler().setMinBreakLength(1000*60*15);
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);

        System.out.println(admin.getDepartmentByName("Kassa").getAllShifts().get(0).getBreakTime().start);
        System.out.println(admin.getDepartmentByName("Kassa").getAllShifts().get(1).getBreakTime().start);
        System.out.println(admin.getDepartmentByName("Kassa").getAllShifts().get(2).getBreakTime().start);
        System.out.println(admin.getDepartmentByName("Kassa").getAllShifts().get(3).getBreakTime().start);


        System.out.println(new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(0).getBreakTime().start).getHours());
        System.out.println(new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(0).getBreakTime().start).getMinutes());


        System.out.println(new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(3).getBreakTime().start).getHours());
        System.out.println(new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(3).getBreakTime().start).getMinutes());
        assertTrue(new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(0).getBreakTime().start).getHours()+new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(0).getBreakTime().start).getMinutes()==new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(3).getBreakTime().start).getHours()+new Date(admin.getDepartmentByName("Kassa").getAllShifts().get(3).getBreakTime().start).getMinutes());

    }
}
