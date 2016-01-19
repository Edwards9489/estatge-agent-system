/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import java.util.Calendar;
import java.util.Date;
import classes.DateConversion;

/**
 *
 * @author Dwayne
 */
public class TestDateConversion {
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    
    public TestDateConversion() {
        //DateConversion.utilDateToSQLDate(date);
        //System.out.println(DateConversion.dateToCalendar(date));
        //System.out.println(DateConversion.dateToString(date));
        
        Calendar today = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();
        lastDay.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        
        System.out.println("TODAY: " + today.getTime());
        System.out.println("LAST DAY: " + lastDay.getTime());
    }
    
    public static void main(String[] args) {
        TestDateConversion test = new TestDateConversion();
    }
}