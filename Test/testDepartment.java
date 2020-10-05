import Model.Department;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class testDepartment {
    @Test
    public void testCreateBreak() {
        Department department = new Department("kassa", 3);
        Date date = new Date();
        department.getBreakHandler().setMinBreakLength(1000*60*15);
        department.createShift(date.getTime(),date.getTime()+(1000 * 60 * 60 * 4));
        department.createShift(date.getTime(),date.getTime()+(1000 * 60 * 60 * 4));
        department.createShift(date.getTime(),date.getTime()+(1000 * 60 * 60 * 4));

    assertTrue(department.getAllShifts().get(0).getBreakTime().inBetween(department.getAllShifts().get(2).getBreakTime().start,department.getAllShifts().get(2).getBreakTime().end));}
}
