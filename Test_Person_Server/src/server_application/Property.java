/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class Property
{
    // instance variables - replace the example below with your own
    private final int propRef;
    private Address address;
    private Landlord landlord;
    private boolean fullManagement; // indicates if MSc Properties will manage all of the managerial affairs such as damage to prop, or just renting
    private Date acquiredDate;
    private Date leaseEndDate;
    private boolean current;
    private PropertyType propType;
    private PropertySubType propSubType;
    private String propStatus; // Occupied, Void, New, End etc
    private HashMap<String, PropertyElement> propertyElements = new HashMap<>();
    
    /**
     * Constructor for objects of class Property
     */
    public Property(int propRef, Address address, Landlord landlord, boolean management, Date acquiredDate, PropertyType propType, PropertySubType propSubType) {
        // initialise instance variables
        this.propRef = propRef;
        this.address = address;
        this.landlord = landlord;
        this.fullManagement = management;
        this.acquiredDate = acquiredDate;
        this.propType = propType;
        this.propSubType = propSubType;
        this.propStatus = "NEW";
        current = true;
    }

    /**
     * @return the propRef
     */
    public int getPropRef() {
        return propRef;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the landlord
     */
    public Landlord getLandlord() {
        return landlord;
    }

    /**
     * @param landlord the landlord to set
     */
    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    /**
     * @return the fullManagement
     */
    public boolean isFullManagement() {
        return fullManagement;
    }

    /**
     * @param fullManagement the fullManagement to set
     */
    public void setFullManagement(boolean fullManagement) {
        this.fullManagement = fullManagement;
    }

    /**
     * @return the acquiredDate
     */
    public Date getAcquiredDate() {
        return acquiredDate;
    }

    /**
     * @param acquiredDate the acquiredDate to set
     */
    public void setAcquiredDate(Date acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    /**
     * @return the leaseEndDate
     */
    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    /**
     * @param leaseEndDate the leaseEndDate to set
     */
    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    /**
     * @return the propType
     */
    public PropertyType getPropType() {
        return propType;
    }

    /**
     * @param propType the propType to set
     */
    public void setPropType(PropertyType propType) {
        this.propType = propType;
    }

    /**
     * @return the propSubType
     */
    public PropertySubType getPropSubType() {
        return propSubType;
    }

    /**
     * @param propSubType the propSubType to set
     */
    public void setPropSubType(PropertySubType propSubType) {
        this.propSubType = propSubType;
    }

    /**
     * @return the propStatus
     */
    public String getPropStatus() {
        return propStatus;
    }

    /**
     * @param propStatus the propStatus to set
     */
    public void setPropStatus(String propStatus) {
        this.propStatus = propStatus;
    }

    /**
     * @return the propertyElements
     */
    public HashMap<String, PropertyElement> getPropertyElements() {
        return propertyElements;
    }
}