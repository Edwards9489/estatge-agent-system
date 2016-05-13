/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.math.BigDecimal;

/**
 *
 * @author Dwayne
 */
public class Utils {
    public static String getFileExtension(String name) {
        int pointIndex = name.lastIndexOf(".");
        
        if(pointIndex == -1) {
            return null;
            // returns null if "." is not found in String
        }
        
        if(pointIndex == name.length()-1) {
            return null;
            // returns null if "." is last charecter in String
        }
        
        return name.substring(pointIndex+1, name.length());
        // returns the String from the "." onwards
    }
    
    public static String getFileName(String name) {
        int pointIndex = name.lastIndexOf(".");
        
        if(pointIndex == -1) {
            return null;
            // returns null if "." is not found in String
        }
        
        if(pointIndex == name.length()-1) {
            return null;
            // returns null if "." is last charecter in String
        }
        
        return name.substring(0, pointIndex);
    }
    
    public static String getFileNameWithoutVersion(String name) {
        int pointIndex = name.lastIndexOf("v");
        
        if(pointIndex == -1) {
            return null;
            // returns null if "." is not found in String
        }
        
        if(pointIndex == name.length()-1) {
            return null;
            // returns null if "." is last charecter in String
        }
        
        return name.substring(0, pointIndex+1);
    }
    
    public static BigDecimal convertDouble(double value) {
        BigDecimal original = new BigDecimal(value);
        BigDecimal scaled = original.setScale(2, BigDecimal.ROUND_HALF_UP);
        return scaled;
    }
    
    public static boolean compareStrings(String firstString, String secondString) {
        if (firstString != null && secondString != null) {
            firstString = firstString.toUpperCase();
            secondString = secondString.toUpperCase();
            return firstString.equals(secondString);
        } else {
            return firstString == null && secondString == null;
        }
    }
    
    public static String trimToUpperCase(String text) {
        if (text != null) {
            text = text.trim();
            text = text.toUpperCase();
            return text;
        }
        return null;
    }
}