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
public class PropertyTypeValue
{
    // instance variables - replace the example below with your own
    private String propertyTypeValueCode;
    private PropertyType propertyType;
    private PropertySubType propertySubType;
    private boolean isCurrent;

    /**
     * Constructor for objects of class PropertyTypeValue
     */
    public PropertyTypeValue(Element type, Element subType)
    {
        // initialise instance variables
        propertyTypeValueCode = type.getCode() + subType.getCode();
        propertyType = (PropertyType) type;
        propertySubType = (PropertySubType) subType;
        isCurrent = true;
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