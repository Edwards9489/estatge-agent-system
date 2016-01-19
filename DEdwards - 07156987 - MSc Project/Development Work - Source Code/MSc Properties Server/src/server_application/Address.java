
package server_application;

import interfaces.AddressInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class Address extends UnicastRemoteObject implements AddressInterface {
    
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
    private final Note note;
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
     * @param note
     * @param createdDate
     * @throws java.rmi.RemoteException
     */
    public Address(int addressRef, String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area, String town,
            String country, String postcode, Note note, String createdBy, Date createdDate) throws RemoteException {
        
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
        this.note = note;
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
    
    private void setComment(String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
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
     * @param comment
     * @param country
     * @param postcode
     * @param modifiedBy
     */
    public void updateAddress(String buildingNumber, String buildingName, String subStreetNumber,
            String subStreet, String streetNumber, String street, String area,
            String town, String country, String postcode, String comment, ModifiedByInterface modifiedBy) throws RemoteException {
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
        this.setComment(comment, modifiedBy);
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getAddressRef() throws RemoteException {
        return addressRef;
    }

    /**
     * @return buildingNumber
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getBuildingNumber() throws RemoteException {
        return buildingNumber;
    }

    /**
     * @return buildingName
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getBuildingName() throws RemoteException {
        return buildingName;
    }

    /**
     * @return subStreetNumber
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getSubStreetNumber() throws RemoteException {
        return subStreetNumber;
    }

    /**
     * @return subStreet
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getSubStreet() throws RemoteException {
        return subStreet;
    }

    /**
     * @return streetNumber
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getStreetNumber() throws RemoteException {
        return streetNumber;
    }

    /**
     * @return street
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getStreet() throws RemoteException {
        return street;
    }

    /**
     * @return area
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getArea() throws RemoteException {
        return area;
    }

    /**
     * @return town
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getTown() throws RemoteException {
        return town;
    }

    /**
     * @return country
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCountry() throws RemoteException {
        return country;
    }

    /**
     * @return postcode
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getPostcode() throws RemoteException {
        return postcode;
    }
    
    @Override
    public Note getNote() throws RemoteException {
        return note;
    }
    
    @Override
    public String getComment() throws RemoteException {
        return note.getNote();
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the Address
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the date the last time the Address was modified
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return a list of modifiedBy objects for the Address
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return get the last modifiedBy object for the Address
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }
    
    /**
     * @return String representation of the Address as one line
     * @throws java.rmi.RemoteException
     */
    @Override
    public String printAddress() throws RemoteException {
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
        try {
            String temp = "\n\nAddress: " + this.printAddress() + "\nCreatedBy: " + this.getCreatedBy() +
                    "\nCreated Date: " + this.getCreatedDate() + "Last ModifiedBy: " + this.getLastModifiedBy() +
                    "Last Modified Date: " + this.getLastModifiedDate() + "\nModifiedBy\n" + this.getModifiedBy();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(Address.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}