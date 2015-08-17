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
public class Address
{
    // instance variables - replace the example below with your own
    private final int addressRef;
    private Integer buildingNumber;
    private String buildingName;
    private Integer subStreetNumber;
    private String subStreet;
    private Integer streetNumber;
    private String street;
    private String area;
    private String town;
    private String country;
    private String postcode;
    

    /**
     * Constructor for objects of class Address
     * @param street
     * @param town
     * @param postcode
     */
    public Address(int addressRef, String street, String town, String postcode)
    {
        // initialise instance variables
        this.addressRef = addressRef;
        this.street = street;
        this.town = town;
        this.postcode = postcode;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param number
     */
    public void updateBuildingNumber(int number)
    {
        // put your code here
        buildingNumber = number;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateBuildingName(String name)
    {
        // put your code here
        buildingName = name;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateSubStreetNumber(int number)
    {
        // put your code here
        subStreetNumber = number;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateSubStreet(String street)
    {
        // put your code here
        subStreet = street;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateStreetNumber(int number)
    {
        // put your code here
        streetNumber = number;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateStreet(String street)
    {
        // put your code here
        this.street = street;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateArea(String area)
    {
        // put your code here
        this.area = area;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateTown(String town)
    {
        // put your code here
        this.town = town;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateCountry(String country)
    {
        // put your code here
        this.country = country;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updatePostcode(String postcode)
    {
        // put your code here
        this.postcode = postcode;
    }
    
    public boolean isBuildingNumberNull()
    {
        return buildingNumber == null;
    }
    
    public boolean isBuildingNameNull()
    {
        return buildingName == null;
    }
    
    public boolean isSubStreetNumberNull()
    {
        return subStreetNumber == null;
    }
    
    public boolean isSubStreetNull()
    {
        return subStreet == null;
    }
    
    public boolean isStreetNumberNull()
    {
        return streetNumber == null;
    }
    
    public boolean isStreetNull()
    {
        return street == null;
    }
    
    public boolean isAreaNull()
    {
        return area == null;
    }
    
    public boolean isTownNull()
    {
        return town == null;
    }
    
    public boolean isCountryNull()
    {
        return country == null;
    }
    
    public boolean isPostcodeNull()
    {
        return postcode == null;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void displayAddress()
    {
        // put your code here
        String temp = "";
        if(!isBuildingNumberNull())
        {
            temp = temp + buildingNumber;
        }
        if(!isBuildingNameNull())
        {
            temp = temp + " " + buildingName + ",";
        }
        if(!isSubStreetNumberNull())
        {
            temp = temp + " " + subStreetNumber;
        }
        if(!isSubStreetNull())
        {
            temp = temp + " " + subStreet + ",";
        }
        if(!isStreetNumberNull())
        {
            if(temp != null)
            {
                temp = temp + " ";
            }
            
            temp = temp + streetNumber;
        }
        
        temp = temp + " " + street + ", ";
        
        if(!isAreaNull())
        {
            temp = temp + area + ", ";
        }
        
        temp = temp + town + ", ";
        
        if(!isCountryNull())
        {
            temp = temp + country + ", " + postcode;
        }
        else
        {
            temp = temp + postcode;
        }
        
        System.out.println(temp);
    }
}