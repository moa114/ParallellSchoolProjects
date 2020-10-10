import Model.Admin;
import Model.Department;
import Model.WeekHandler;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class testDepartment {
    @Test
    public void testCreateBreak() {
        Admin admin = Admin.getInstance();
        admin.createNewDepartment("Kassa", 3);
        Date date = new Date();
        boolean repeat[] = {true, true, true, true, true, true, true};
        admin.getDepartmentByName("Kassa").getBreakHandler().setMinBreakLength(WeekHandler.plusMinutes(15));
        admin.createWorkshift(admin.getDepartmentByName("Kassa"), date.getTime() + (WeekHandler.plusHours(1)), date.getTime() + (WeekHandler.plusHours(5)), repeat);
        admin.createWorkshift(admin.getDepartmentByName("Kassa"), date.getTime() + (WeekHandler.plusHours(1)), date.getTime() + (WeekHandler.plusHours(5)), repeat);
        admin.createWorkshift(admin.getDepartmentByName("Kassa"), date.getTime() + (WeekHandler.plusHours(1)), date.getTime() + (WeekHandler.plusHours(5)), repeat);

        assertTrue(admin.getDepartmentByName("Kassa").getShift(0).getBreakTime().inBetween(admin.getDepartmentByName("Kassa").getShift(2).getBreakTime().START, admin.getDepartmentByName("Kassa").getShift(2).getBreakTime().END));
    }
}
