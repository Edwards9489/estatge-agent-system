/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Address implements AddressInterface {
    private final int addressRef; // unique refrence for address
    private String buildingNumber;
    private String buildingName;
    private String subStreetNumber;
    private String subStreet;
    private String streetNumber;
    private String street;
    private String area;
    private String town;
    private String country;
    private String postcode;
    private final String createdBy;
    private final Date createdDate;
    private ArrayList<ModifiedBy> modifiedBy;
    
    

    /**
     * Constructor for objects of class Address
     * @param street
     * @param town
     * @param postcode
     */
    public Address(int addressRef, String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town, String postcode, String createdBy) {
        
        initializeValues(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, postcode);
        this.addressRef = addressRef;
        this.postcode = postcode;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        this.modifiedBy = new ArrayList();
    }
    
    public Address(int addressRef, String postcode, String createdBy) {
        this.addressRef = addressRef;
        this.postcode = postcode;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        this.modifiedBy = new ArrayList();
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param number
     */
    public void setBuildingNumber(String number) {
        buildingNumber = number;
    }
    
    public void setBuildingName(String name) {
        buildingName = name;
    }
    
    public void setSubStreetNumber(String number) {
        subStreetNumber = number;
    }
    
    public void setSubStreet(String street) {
        subStreet = street;
    }
    
    public void setStreetNumber(String number) {
        streetNumber = number;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public void setAddress(String buildingNumber, String buildingName, String subStreetNumber, String subStreet,
            String streetNumber, String street, String area, String town, String postcode, String modifiedBy) {
        
        initializeValues(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, postcode);
        modifiedBy(modifiedBy, "amended address");
    }
    
    public boolean isBuildingNumberNull()
    {
        return getBuildingNumber() == null;
    }
    
    public boolean isBuildingNameNull()
    {
        return getBuildingName() == null;
    }
    
    public boolean isSubStreetNumberNull()
    {
        return getSubStreetNumber() == null;
    }
    
    public boolean isSubStreetNull()
    {
        return getSubStreet() == null;
    }
    
    public boolean isStreetNumberNull()
    {
        return getStreetNumber() == null;
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
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toString()
    {
        String temp = "";

        if (!isBuildingNumberNull()) {
            temp = temp + getBuildingNumber();
        }

        if (!isBuildingNameNull()) {
            if (temp.length() != 0) {
                temp = temp + " ";
            }
            temp = temp + getBuildingName();
        }

        if (!isSubStreetNumberNull()) {
            if (temp.length() != 0) {
                temp = temp + ", ";
            }
            temp = temp + getSubStreetNumber();
        }

        if (!isSubStreetNull()) {
            if (temp.length() != 0) {
                temp = temp + " ";
            }
            temp = temp + getSubStreet();
        }

        if (!isStreetNumberNull()) {
            if (temp.length() != 0) {
                temp = temp + ", ";
            }
            temp = temp + getStreetNumber();
        }

        if (!isStreetNull()) {
            if (temp.length() != 0) {
                temp = temp + " ";
            }
            temp = temp + getStreet();
        }

        if (!isAreaNull()) {
            if (temp.length() != 0) {
                temp = temp + ", ";
            }
            temp = temp + getArea();
        }

        if (!isTownNull()) {
            if (temp.length() != 0) {
                temp = temp + ", ";
            }
            temp = temp + getTown();
        }

        if (!isCountryNull()) {
            if (temp.length() != 0) {
                temp = temp + ", ";
            }
            temp = temp + getCountry();
        }

        if (temp.length() != 0) {
            temp = temp + ", ";
        }
        
        temp = temp + getPostcode();

        return temp;
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
    public String getBuildingNumber() {
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
    public String getSubStreetNumber() {
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
    public String getStreetNumber() {
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
    
    public void modifiedBy(String modifiedBy, String description) {
        ModifiedBy temp = new ModifiedBy(modifiedBy, description);
        
        this.modifiedBy.add(temp);
    }
    
    public List getmodifiedBy() {
        return Collections.unmodifiableList(modifiedBy);
    }
    
    private void initializeValues(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town, String postcode) {
        if(buildingNumber != null) {
            setBuildingNumber(buildingNumber);
        }
        if(buildingName != null) {
            setBuildingName(buildingName);
        }
        if(subStreetNumber != null) {
            setSubStreetNumber(subStreetNumber);
        }
        if(subStreet != null) {
            setSubStreet(subStreet);
        }
        if(streetNumber != null) {
            setStreetNumber(streetNumber);
        }
        if(street != null) {
            setStreet(street);
        }
        if(area != null) {
            setArea(area);
        }
        if(town != null) {
            setTown(town);
        }
        if(postcode != null) {
            setPostcode(postcode);
        }
    }
}