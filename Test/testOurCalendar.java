import Model.OurCalendar;
import Model.WorkDay;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class testOurCalendar {
    @Test
    public void testInit() {
        OurCalendar calendar = OurCalendar.getInstance();
        List<WorkDay> list = calendar.getOurDates().subList(0, 365);
        assertEquals(365, list.size());  // kollar om kalendern skapar ett helt år
    }
}
