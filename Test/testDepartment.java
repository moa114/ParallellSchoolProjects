import Model.Admin;
import Model.Department;
import Model.WorkShift;
import Model.WeekHandler;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class testDepartment {
    @Test
    public void testCreateBreak() {
        Admin admin=Admin.getInstance();
        admin.createNewDepartment("Kassa", 2);
        Date date = (new Date());
        date.setTime(date.getTime()+(1000*60*60*24));
        boolean repeat[] = {false, false, false, false, true, false, false}; //TODO Markus fixar ordentlig repeat
        admin.getDepartmentByName("Kassa").getBreakHandler().setMinBreakLength(WeekHandler.plusMinutes(15));
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(WeekHandler.plusHours(1)),date.getTime()+(WeekHandler.plusHours(5)), repeat);
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(WeekHandler.plusHours(1)),date.getTime()+(WeekHandler.plusHours(5)), repeat);
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(WeekHandler.plusHours(1)),date.getTime()+(WeekHandler.plusHours(5)), repeat);
        admin.createWorkshift( admin.getDepartmentByName("Kassa"),date.getTime()+(WeekHandler.plusHours(1)),date.getTime()+(WeekHandler.plusHours(5)), repeat);

        System.out.println(admin.getDepartmentByName("Kassa").getShift(0).getBreakTime().START);
        System.out.println(admin.getDepartmentByName("Kassa").getShift(1).getBreakTime().START);
        System.out.println(admin.getDepartmentByName("Kassa").getShift(2).getBreakTime().START);
        System.out.println(admin.getDepartmentByName("Kassa").getShift(3).getBreakTime().START);


        System.out.println(new Date(admin.getDepartmentByName("Kassa").getShift(0).getBreakTime().START).getHours());
        System.out.println(new Date(admin.getDepartmentByName("Kassa").getShift(0).getBreakTime().START).getMinutes());


        System.out.println(new Date(admin.getDepartmentByName("Kassa").getShift(3).getBreakTime().START).getHours());
        System.out.println(new Date(admin.getDepartmentByName("Kassa").getShift(3).getBreakTime().START).getMinutes());
        assertTrue(new Date(admin.getDepartmentByName("Kassa").getShift(0).getBreakTime().START).getHours()+new Date(admin.getDepartmentByName("Kassa").getShift(0).getBreakTime().START).getMinutes()==new Date(admin.getDepartmentByName("Kassa").getShift(3).getBreakTime().START).getHours()+new Date(admin.getDepartmentByName("Kassa").getShift(3).getBreakTime().START).getMinutes());

    }
}
