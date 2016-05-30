/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTimeComparator;


/**
 *
 * @author Dwayne
 */
public class DateConversion {
    
    /**
     * 
     * @param date
     * @return 
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        if(date != null) {
            cal.setTime(date);
            return cal;
        }
        return null;
    }
    
    /**
     * 
     * @param date
     * @return 
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        String currentTime = sdf.format(date);
        return currentTime;
    }
    
    /**
     * 
     * @param utilDate
     * @return 
     */
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
    
    public static boolean dateEqual(Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null) {
            return DateTimeComparator.getDateOnlyInstance().compare(firstDate, secondDate) == 0;
        }
        return false;
    }
    
    public static boolean firstDateBeforeSecondDate(Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null) {
            return DateTimeComparator.getDateOnlyInstance().compare(firstDate, secondDate) < 0;
        }
        return false;
    }
    
    public static boolean firstDateAfterSecondDate(Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null) {
            return DateTimeComparator.getDateOnlyInstance().compare(firstDate, secondDate) > 0;
        }
        return false;
    }
    
    public static Integer dateEqual2(Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null) {
            DateTimeComparator.getDateOnlyInstance().compare(firstDate, secondDate);
        }
        return null;
    }
    
    public static Integer firstDateBeforeSecondDate2(Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null) {
            return DateTimeComparator.getDateOnlyInstance().compare(firstDate, secondDate);
        }
        return null;
    }
    
    public static Integer firstDateAfterSecondDate2(Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null) {
            return DateTimeComparator.getDateOnlyInstance().compare(firstDate, secondDate);
        }
        return null;
    }
    
    public static boolean dateBetweenDates(Date date, Date fromDate, Date toDate) {
        if (date != null && fromDate != null && toDate != null) {
            return ((firstDateAfterSecondDate(date, fromDate) && firstDateBeforeSecondDate(date, toDate)) || (dateEqual(date, fromDate) || dateEqual(date, toDate)));
        }
        return false;
    }
}