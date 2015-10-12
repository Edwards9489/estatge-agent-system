/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import java.util.Calendar;
import java.util.Date;
import server_application.DateConversion;

/**
 *
 * @author Dwayne
 */
public class TestDateConversion {
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    
    public TestDateConversion() {
        //DateConversion.utilDateToSQLDate(date);
        System.out.println(DateConversion.dateToCalendar(date));
    }
    
    public static void main(String[] args) {
        TestDateConversion test = new TestDateConversion();
    }
}