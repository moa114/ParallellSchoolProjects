import Model.Admin;
import Model.Department;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class testDepartment {
    @Test
    public void testCreateBreak() {
        Admin admin=Admin.getInstance();
        Department department = new Department("kassa", 3);
        Date date = new Date();
        boolean repeat[] = {true, true, true, true, true, true, true};
        department.getBreakHandler().setMinBreakLength(1000*60*15);
        admin.createWorkshift(department,date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);
        admin.createWorkshift(department,date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);
        admin.createWorkshift(department,date.getTime()+(1000 * 60 * 60 * 1),date.getTime()+(1000 * 60 * 60 * 5), repeat);

        assertTrue(department.getAllShifts().get(0).getBreakTime().inBetween(department.getAllShifts().get(2).getBreakTime().start,department.getAllShifts().get(2).getBreakTime().end));}
}
