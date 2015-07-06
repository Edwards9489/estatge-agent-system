/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

/**
 *
 * @author Dwayne
 */
public class PropertySubType
{
    // instance variables - replace the example below with your own
    private String propertySubTypeCode;
    private String propertySubTypeDescription;
    private boolean isCurrent;

    /**
     * Constructor for objects of class PropertySubType
     */
    public PropertySubType(String code, String description)
    {
        // initialise instance variables
        propertySubTypeCode = code;
        propertySubTypeDescription = description;
        isCurrent = true;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updatePropertySubTypeDescription(String description)
    {
        // put your code here
        propertySubTypeDescription = description;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getPropertySubTypeCode()
    {
        // put your code here
        return propertySubTypeCode;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getPropertySubTypeDescription()
    {
        // put your code here
        return propertySubTypeDescription;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void makeCurrent()
    {
        // put your code here
        if(isCurrent == false)
        {
            isCurrent = true;
        }
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void makeNonCurrent()
    {
        // put your code here
        if(isCurrent == true)
        {
            isCurrent = false;
        }
    }
}