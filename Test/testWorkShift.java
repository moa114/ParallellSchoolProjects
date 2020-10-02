import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testWorkShift {

    @Test
    public void addWorkShift(){
        Admin admin = new Admin();
        admin.createNewDepartment("Kassa");
        admin.createWorkshift(admin.getDepartmentByName("Kassa"), 1155, 1855, 5);
        admin.createWorkshift(admin.getDepartmentByName("Kassa"), admin.getDepartmentByName("Kassa").getAllShifts().get(0) );
        assertEquals(2, admin.getDepartmentByName("Kassa").getAllShifts().size());
    }


    @Test
    public void removeWorkShift(){
        Admin admin = new Admin();
        admin.createNewDepartment("Kassa");
        admin.createWorkshift(admin.getDepartmentByName("Kassa"), 1155, 1855, 5);
        admin.createWorkshift(admin.getDepartmentByName("Kassa"), admin.getDepartmentByName("Kassa").getAllShifts().get(0) );
        admin.removeWorkshift(admin.getDepartmentByName("Kassa"), admin.getDepartmentByName("Kassa").getAllShifts().get(0) );
        assertEquals(1, admin.getDepartmentByName("Kassa").getAllShifts().size());
    }

    @Test
    public void deligateEmployee(){

    }

}
