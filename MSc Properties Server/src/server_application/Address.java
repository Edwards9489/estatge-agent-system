
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
        this.setBuildingNumber(buildingNumber);
        this.setBuildingName(buildingName);
        this.setSubStreetNumber(subStreetNumber);
        this.setSubStreet(subStreet);
        this.setStreetNumber(streetNumber);
        this.setStreet(street);
        this.setArea(area);
        this.setTown(town);
        this.setCountry(country);
        this.setPostcode(postcode);
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
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    @Override
    public void setAddress(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area,
            String town, String country, String postcode, ModifiedByInterface modifiedBy) {
        this.setBuildingNumber(buildingNumber);
        this.setBuildingName(buildingName);
        this.setSubStreetNumber(subStreetNumber);
        this.setSubStreet(subStreet);
        this.setStreetNumber(streetNumber);
        if(!street.isEmpty()) {
            this.setStreet(street);
        }
        this.setArea(area);
        if(!town.isEmpty()) {
            this.setTown(town);
        }
        if(!country.isEmpty()) {
            this.setCountry(country);
        }
        if(!postcode.isEmpty()) {
            this.setPostcode(postcode);
        }
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    private boolean isBuildingNumberNull() {
        return buildingNumber.isEmpty();
    }

    private boolean isBuildingNameNull() {
        return buildingName.isEmpty();
    }

    private boolean isSubStreetNumberNull() {
        return subStreetNumber.isEmpty();
    }

    private boolean isSubStreetNull() {
        return subStreet.isEmpty();
    }

    private boolean isStreetNumberNull() {
        return streetNumber.isEmpty();
    }

    private boolean isStreetNull() {
        return street.isEmpty();
    }

    private boolean isAreaNull() {
        return area.isEmpty();
    }

    private boolean isTownNull() {
        return town.isEmpty();
    }

    private boolean isCountryNull() {
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
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1).getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1).getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
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
    public String printAddress()
    {
        String temp = "";
        
        if (!isBuildingNumberNull()) {
            temp = temp + getBuildingNumber() + " " + getBuildingName() + ", ";
        } else if (isBuildingNumberNull() && !isBuildingNameNull()) {
            temp = temp + getBuildingName() + ", ";
        }
        
        if (!isSubStreetNumberNull()) {
            temp = temp + getSubStreetNumber() + " " + getSubStreet() + ", ";
        } else if (isSubStreetNumberNull() && !isSubStreetNull()) {
            temp = temp + getSubStreet() + ", ";
        }

        if (!isStreetNumberNull()) {
            temp = temp + getStreetNumber() + " " + getStreet() + ", ";
        } else if (isStreetNumberNull() && !isStreetNull()) {
            temp = temp + getStreet() + ", ";
        }

        if (!isAreaNull()) {
            temp = temp + getArea() + ", ";
        }

        if (!isTownNull()) {
            temp = temp + getTown() + ", ";
        }

        if (!isCountryNull()) {
            temp = temp + getCountry() + ", ";
        }
        
        temp = temp + getPostcode();

        return temp;
    }
    
    @Override
    public String toString() {
        String temp = "\n\nAddress: " + this.printAddress() + "\nCreatedBy: " + this.getCreatedBy() +
                "\nCreated Date: " + this.getCreatedDate() + "Last ModifiedBy: " + this.getLastModifiedBy() +
                "Last Modified Date: " + this.getLastModifiedDate() + "\nModifiedBy\n" + this.getModifiedBy();
        return temp;
    }
}