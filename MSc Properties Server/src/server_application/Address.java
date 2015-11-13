
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
    private final List<ModifiedByInterface> modifiedBy;
    
    

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
     * @param createdDate
     */
    public Address(int addressRef, String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, String createdBy, Date createdDate) {
        
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
        this.createdDate = createdDate;
        this.modifiedBy = new ArrayList();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param number
     */
    private void setBuildingNumber(String number) {
        buildingNumber = number;
    }
    
    /**
     * @param name
     */
    private void setBuildingName(String name) {
        buildingName = name;
    }
    
    /**
     * @param number
     */
    private void setSubStreetNumber(String number) {
        subStreetNumber = number;
    }
    
    /**
     * @param street
     */
    private void setSubStreet(String street) {
        subStreet = street;
    }
    
    /**
     * @param number
     */
    private void setStreetNumber(String number) {
        streetNumber = number;
    }
    
    /**
     * @param street
     */
    private void setStreet(String street) {
        this.street = street;
    }
    
    /**
     * @param area
     */
    private void setArea(String area) {
        this.area = area;
    }
    
    /**
     * @param town
     */
    private void setTown(String town) {
        this.town = town;
    }
    
    /**
     * @param country
     */
    private void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * @param postcode
     */
    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    /**
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * @param buildingNumber
     * @param buildingName
     * @param subStreetNumber
     * @param subStreet
     * @param streetNumber
     * @param street
     * @param area
     * @param town
     * @param country
     * @param postcode
     * @param modifiedBy
     */
    public void updateAddress(String buildingNumber, String buildingName, String subStreetNumber,
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
    
    /**
     * @return buildingNumber.isEmpty()
     */
    private boolean isBuildingNumberNull() {
        return buildingNumber.isEmpty();
    }
    
    /**
     * @return buildingName.isEmpty()
     */
    private boolean isBuildingNameNull() {
        return buildingName.isEmpty();
    }
    
    /**
     * @return subStreetNumber.isEmpty()
     */
    private boolean isSubStreetNumberNull() {
        return subStreetNumber.isEmpty();
    }
    
    /**
     * @return subStreet.isEmpty()
     */
    private boolean isSubStreetNull() {
        return subStreet.isEmpty();
    }
    
    /**
     * @return streetNumber.isEmpty()
     */
    private boolean isStreetNumberNull() {
        return streetNumber.isEmpty();
    }

    /**
     * @return street.isEmpty()
     */
    private boolean isStreetNull() {
        return street.isEmpty();
    }

    /**
     * @return area.isEmpty()
     */
    private boolean isAreaNull() {
        return area.isEmpty();
    }

    /**
     * @return town.isEmpty()
     */
    private boolean isTownNull() {
        return town.isEmpty();
    }

    /**
     * @return country.isEmpty()
     */
    private boolean isCountryNull() {
        return country.isEmpty();
    }

    /**
     * @return addressRef
     */
    @Override
    public int getAddressRef() {
        return addressRef;
    }

    /**
     * @return buildingNumber
     */
    @Override
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * @return buildingName
     */
    @Override
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * @return subStreetNumber
     */
    @Override
    public String getSubStreetNumber() {
        return subStreetNumber;
    }

    /**
     * @return subStreet
     */
    @Override
    public String getSubStreet() {
        return subStreet;
    }

    /**
     * @return streetNumber
     */
    @Override
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * @return street
     */
    @Override
    public String getStreet() {
        return street;
    }

    /**
     * @return area
     */
    @Override
    public String getArea() {
        return area;
    }

    /**
     * @return town
     */
    @Override
    public String getTown() {
        return town;
    }

    /**
     * @return country
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * @return postcode
     */
    @Override
    public String getPostcode() {
        return postcode;
    }
    
    /**
     * @return the name of the last user to modify the Address
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the date the last time the Address was modified
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return a list of modifiedBy objects for the Address
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return get the last modifiedBy object for the Address
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     * @return String representation of the Address as one line
     */
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
    
    /**
     * @return String representation of the Address
     */
    @Override
    public String toString() {
        String temp = "\n\nAddress: " + this.printAddress() + "\nCreatedBy: " + this.getCreatedBy() +
                "\nCreated Date: " + this.getCreatedDate() + "Last ModifiedBy: " + this.getLastModifiedBy() +
                "Last Modified Date: " + this.getLastModifiedDate() + "\nModifiedBy\n" + this.getModifiedBy();
        return temp;
    }
}