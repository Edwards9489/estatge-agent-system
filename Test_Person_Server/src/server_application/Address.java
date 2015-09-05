/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Address
{
    // instance variables - replace the example below with your own
    private final int addressRef; // unique refrence for address
    private int buildingNumber = -1;
    private String buildingName;
    private int subStreetNumber = -1;
    private String subStreet;
    private int streetNumber = -1;
    private String street;
    private String area;
    private String town;
    private String country;
    private String postcode;
    private final String createdBy;
    private final Date createdDate;
    private ArrayList<String> modifiedBy;
    private ArrayList<Date> modifiedDate;
    
    

    /**
     * Constructor for objects of class Address
     * @param street
     * @param town
     * @param postcode
     */
    public Address(int addressRef, String street, String town, String postcode, String createdBy) {
        // initialise instance variables
        this.addressRef = addressRef;
        this.street = street;
        this.town = town;
        this.postcode = postcode;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param number
     */
    public void setBuildingNumber(int number)
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
    public void setBuildingName(String name)
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
    public void setSubStreetNumber(int number)
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
    public void setSubStreet(String street)
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
    public void setStreetNumber(int number)
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
    public void setStreet(String street)
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
    public void setArea(String area)
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
    public void setTown(String town)
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
    public void setCountry(String country)
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
    public void setPostcode(String postcode)
    {
        // put your code here
        this.postcode = postcode;
    }
    
    public boolean isBuildingNumberNull()
    {
        return getBuildingNumber() == -1;
    }
    
    public boolean isBuildingNameNull()
    {
        return getBuildingName() == null;
    }
    
    public boolean isSubStreetNumberNull()
    {
        return getSubStreetNumber() == -1;
    }
    
    public boolean isSubStreetNull()
    {
        return getSubStreet() == null;
    }
    
    public boolean isStreetNumberNull()
    {
        return getStreetNumber() == -1;
    }
    
    public boolean isStreetNull()
    {
        return getStreet() == null;
    }
    
    public boolean isAreaNull()
    {
        return getArea() == null;
    }
    
    public boolean isTownNull()
    {
        return getTown() == null;
    }
    
    public boolean isCountryNull()
    {
        return getCountry() == null;
    }
    
    public boolean isPostcodeNull()
    {
        return getPostcode() == null;
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
            temp = temp + getBuildingNumber();
        }
        if(!isBuildingNameNull())
        {
            temp = temp + " " + getBuildingName() + ",";
        }
        if(!isSubStreetNumberNull())
        {
            temp = temp + " " + getSubStreetNumber();
        }
        if(!isSubStreetNull())
        {
            temp = temp + " " + getSubStreet() + ",";
        }
        if(!isStreetNumberNull())
        {
            if(temp != null)
            {
                temp = temp + " ";
            }
            
            temp = temp + getStreetNumber();
        }
        
        temp = temp + " " + getStreet() + ", ";
        
        if(!isAreaNull())
        {
            temp = temp + getArea() + ", ";
        }
        
        temp = temp + getTown() + ", ";
        
        if(!isCountryNull())
        {
            temp = temp + getCountry() + ", " + getPostcode();
        }
        else
        {
            temp = temp + getPostcode();
        }
        
        System.out.println(temp);
    }
    
    
    /**
     * @return the addressRef
     */
    public int getAddressRef() {
        return addressRef;
    }

    /**
     * @return the buildingNumber
     */
    public int getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * @return the subStreetNumber
     */
    public int getSubStreetNumber() {
        return subStreetNumber;
    }

    /**
     * @return the subStreet
     */
    public String getSubStreet() {
        return subStreet;
    }

    /**
     * @return the streetNumber
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
}