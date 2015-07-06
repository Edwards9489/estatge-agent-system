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
public class PropertyType
{
    // instance variables - replace the example below with your own
    private String propertyTypeCode;
    private String propertyTypeDescription;
    private boolean isCurrent;

    /**
     * Constructor for objects of class PropertyType
     */
    public PropertyType(String code, String description)
    {
        // initialise instance variables
        propertyTypeCode = code;
        propertyTypeDescription = description;
        isCurrent = true;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updatePropertyTypeDescription(String description)
    {
        // put your code here
        propertyTypeDescription = description;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getPropertyTypeCode()
    {
        // put your code here
        return propertyTypeCode;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getPropertyTypeDescription()
    {
        // put your code here
        return propertyTypeDescription;
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
