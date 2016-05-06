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
        
        
//        System.out.println("TODAY: " + today.getTime());
//        System.out.println("LAST DAY: " + lastDay.getTime());
        
        Calendar newDate = Calendar.getInstance();
        newDate.set(2016, 04, 04);
        
        System.out.println("Date 1: " + today.getTime());
        System.out.println("Date 2: " + newDate.getTime());
        System.out.println();
        System.out.println("Date Equal: " + DateConversion.dateEqual(today.getTime(), newDate.getTime()));
        System.out.println("Date 1 Before Date 2: " + DateConversion.firstDateBeforeSecondDate(today.getTime(), newDate.getTime()));
        System.out.println("Date 1 After Date 2: " + DateConversion.firstDateAfterSecondDate(today.getTime(), newDate.getTime()));
        
        System.out.println();
        System.out.println("Date Equal (int): " + DateConversion.dateEqual2(today.getTime(), newDate.getTime()));
        System.out.println("Date 1 Before Date 2 (int): " + DateConversion.firstDateBeforeSecondDate2(today.getTime(), newDate.getTime()));
        System.out.println("Date 1 After Date 2 (int): " + DateConversion.firstDateAfterSecondDate2(today.getTime(), newDate.getTime()));
    }
    
    public static void main(String[] args) {
        TestDateConversion test = new TestDateConversion();
    }
}