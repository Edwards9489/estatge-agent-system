/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
}