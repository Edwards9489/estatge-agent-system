/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class DateConversion {

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        if(date != null) {
            cal.setTime(date);
            return cal;
        }
        return null;
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        return currentTime;
    }
    
    public static void addMonths(Date date, int numMonths){
    date.setMonth((date.getMonth() - 1 + numMonths) % 12 + 1);
    }
    
    public static java.sql.Date utilDateToSQLDate(Date utilDate) {
        java.sql.Date sqlDate = null;
        if(utilDate != null) {
            Calendar cal = DateConversion.dateToCalendar(utilDate);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);    
        
            sqlDate = new java.sql.Date(cal.getTime().getTime());
        }
        return sqlDate;
    }
}