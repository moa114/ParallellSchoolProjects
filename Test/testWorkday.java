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
        CertificateHandler ch = CertificateHandler.getInstance();
        ch.createNewCertificate("Kassa");
        List<Certificate> allcert = new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        WorkShift w= new WorkShift(d.getTime(),(d.getTime()+(1000 * 60 * 60 * 8)),allcert,new OccupiedTime(2,2), true);
        WorkDay workday= new WorkDay(d.getTime());
        workday.setGuaranteedFreeTime(10);
        workday.occupiesEmployee(w,e);
        assertTrue(e.isOccupied((d.getTime()+(1000 * 60 * 60 * (17))),(d.getTime()+ (1000 * 60 * 60 * (8+23)))));
    }

    @Test
    public void testWeekly() { //yes, this is an insane test and I dont know what to do about it
        int dag = new Date().getDate() + 3;//the date we wanna set the workshifts at + however many days in the future you wanna set
        Admin a = Admin.getInstance();
        boolean repeat[] = {true, true, true, false, false, true, false}; //what days we wanna set workshifts at
        boolean repeat2[] = {true, false, true, false, false, false, false};
        a.createNewDepartment("Kassa", 1);
        a.createNewDepartment("Frukt", 1);
        a.createWorkshift(a.getDepartmentByName("Kassa"), a.getWorkday(dag).DATE+100, a.getWorkday(dag).DATE+1000*60*60, repeat);
        a.createWorkshift(a.getDepartmentByName("Kassa"), a.getWorkday(dag).DATE+100, a.getWorkday(dag).DATE+1000*60*60, repeat2);
        a.createWorkshift(a.getDepartmentByName("Frukt"), a.getWorkday(dag).DATE+100, a.getWorkday(dag).DATE+1000*60*60, repeat2);
        int countKassa[] = {0,0,0,0,0,0,0}; //counts all WorkShifts for Kassa, starts at "dag"s weekday
        int countFrukt[] = {0,0,0,0,0,0,0}; //counts all Workshifts for Frukt, starts at "dag"s weekday
        for (int i = 0 ; i < 7 ; i++) { //we set and check how many workshifts that actually have been set
            a.getWorkday(i+dag).setWorkDay();
            countKassa[i] = a.getWorkday(i+dag).getWorkShifts(a.getDepartmentByName("Kassa")).size();
            countFrukt[i] = a.getWorkday(i+dag).getWorkShifts(a.getDepartmentByName("Frukt")).size();
        }
        int offSet = new Date(a.getWorkday(dag).DATE).getDay(); //setting an offset so the order will match the order the days are set
        int countExpectedKassa[] = {2,1,2,0,0,1,0}; //expected value of Kassa in the correct order sun-sat
        int countExpectedFrukt[] = {1,0,1,0,0,0,0}; //expected value of Frukt in the correct order sun-sat
        assertTrue(testArray(countExpectedKassa, countKassa, offSet));
        assertTrue(testArray(countExpectedFrukt, countFrukt, offSet));

    }

    public boolean testArray(int[] expected, int[] acctual, int offSet){
        for (int i = 0 ; i < 7 ; i++) {
            if(expected[(i+offSet)%7] != acctual[i]){
                System.out.print(expected[i] + " " + acctual[i] +" "+ i);
                return false;
            }
        }
        return true;
    }


    @Test
    public void testOccupieAnOccupiedEmployee() {
        Employee e=new Employee("moa", "000211444444");
        Date d= new Date();
        d.setTime(d.getTime()+(24*60*60*1000));
        CertificateHandler ch = CertificateHandler.getInstance();
        ch.createNewCertificate("Kassa");
        List<Certificate> allcert = new ArrayList<>();
        allcert.add(ch.getCertificate("Kassa"));
        WorkShift w= new WorkShift(d.getTime(),(d.getTime()+(1000 * 60 * 60 * 8)),allcert,new OccupiedTime(2,2), false);
        WorkShift w2= new WorkShift(d.getTime()+1000,(d.getTime()+1000+(1000 * 60 * 60 * 8)),allcert,new OccupiedTime(2,2), false);
        WorkShift w3= new WorkShift(d.getTime()-1000,(d.getTime()-1000+(1000 * 60 * 60 * 8)),allcert,new OccupiedTime(2,2), false);
        WorkDay workday= new WorkDay(d.getTime());
        workday.setGuaranteedFreeTime(10);
        workday.occupiesEmployee(w,e);
        workday.occupiesEmployee(w2,e);
        workday.occupiesEmployee(w3,e);
        assertTrue(e.getOccupiedTimes().size() == 1);
    }




}
