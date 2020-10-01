import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.*;
import org.junit.Test;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testWorkday {

    @Test
    public void testoccupiesEmployee(){
        Employee e=new Employee("moa", "000211444444");
        Date d= new Date();
        WorkShift w= new WorkShift(d.getTime(),(d.getTime()+(1000 * 60 * 60 * 8)),1);
        WorkDay workday= new WorkDay(d.getTime());
        workday.setGuaranteedFreeTime(10);
        workday.occupiesEmployee(w,e);
        assertTrue(e.isOccupied((d.getTime()+(1000 * 60 * 60 * (17))),(d.getTime()+ (1000 * 60 * 60 * (8+23)))));
    }

}
