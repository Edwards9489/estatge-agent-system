
package server_application;

import interfaces.AddressInterface;
import interfaces.ModifiedByInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Address implements AddressInterface {
    
    ///   VARIABLES   ///
    
    private final int addressRef;
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
    private final ArrayList<ModifiedByInterface> modifiedBy;
    
    

    /**
     * Constructor for objects of class Address
     * @param addressRef
     * @param buildingNumber
     * @param street
     * @param buildingName
     * @param subStreet
     * @param streetNumber
     * @param subStreetNumber
     * @param town
     * @param area
     * @param country
     * @param postcode
     * @param createdBy
     */
    public Address(int addressRef, String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy) {
        
        this.addressRef = addressRef;
        setAddress(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode);
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
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setBuildingNumber(String number) {
        buildingNumber = number;
    }
    
    private void setBuildingName(String name) {
        buildingName = name;
    }
    
    private void setSubStreetNumber(String number) {
        subStreetNumber = number;
    }
    
    private void setSubStreet(String street) {
        subStreet = street;
    }
    
    private void setStreetNumber(String number) {
        streetNumber = number;
    }
    
    private void setStreet(String street) {
        this.street = street;
    }
    
    private void setArea(String area) {
        this.area = area;
    }
    
    private void setTown(String town) {
        this.town = town;
    }
    
    private void setCountry(String country) {
        this.country = country;
    }
    
    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    @Override
    public void setAddress(String buildingNumber, String buildingName,
            String subStreetNumber, String subStreet, String streetNumber,
            String street, String area, String town, String country, String postcode) {
        
        setBuildingNumber(buildingNumber);
        setBuildingName(buildingName);
        setSubStreetNumber(subStreetNumber);
        setSubStreet(subStreet);
        setStreetNumber(streetNumber);
        setStreet(street);
        setArea(area);
        setTown(town);
        setCountry(country);
        setPostcode(postcode);
    }    
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    private boolean isBuildingNumberNull()
    {
        return buildingNumber.isEmpty();
    }
    
    private boolean isBuildingNameNull()
    {
        return buildingName.isEmpty();
    }
    
    private boolean isSubStreetNumberNull()
    {
        return subStreetNumber.isEmpty();
    }
    
    private boolean isSubStreetNull()
    {
        return subStreet.isEmpty();
    }
    
    private boolean isStreetNumberNull()
    {
        return streetNumber.isEmpty();
    }
    
    private boolean isStreetNull()
    {
        return street.isEmpty();
    }
    
    private boolean isAreaNull()
    {
        return area.isEmpty();
    }
    
    private boolean isTownNull()
    {
        return town.isEmpty();
    }
    
    private boolean isCountryNull()
    {
        return country.isEmpty();
    }
    
    /**
     * @return the addressRef
     */
    @Override
    public int getAddressRef() {
        return addressRef;
    }

    /**
     * @return the buildingNumber
     */
    @Override
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * @return the buildingName
     */
    @Override
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * @return the subStreetNumber
     */
    @Override
    public String getSubStreetNumber() {
        return subStreetNumber;
    }

    /**
     * @return the subStreet
     */
    @Override
    public String getSubStreet() {
        return subStreet;
    }

    /**
     * @return the streetNumber
     */
    @Override
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * @return the street
     */
    @Override
    public String getStreet() {
        return street;
    }

    /**
     * @return the area
     */
    @Override
    public String getArea() {
        return area;
    }

    /**
     * @return the town
     */
    @Override
    public String getTown() {
        return town;
    }

    /**
     * @return the country
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * @return the postcode
     */
    @Override
    public String getPostcode() {
        return postcode;
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    @Override
    public List getModifiedByList() {
        return Collections.unmodifiableList(modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getModifiedBy() {
        return modifiedBy.get(modifiedBy.size()-1);
    }
    
    @Override
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
}