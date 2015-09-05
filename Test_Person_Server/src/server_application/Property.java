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
    private Date acquired_date;
    private Date lease_end_date;
    private PropertyType prop_type;
    private PropertySubType prop_sub_type;
    private String prop_status; // Occupied, Void, New, End etc
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
        this.acquired_date = acquiredDate;
        this.prop_type = propType;
        this.prop_sub_type = propSubType;
        this.prop_status = "NEW";
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
     * @return the acquired_date
     */
    public Date getAcquired_date() {
        return acquired_date;
    }

    /**
     * @param acquired_date the acquired_date to set
     */
    public void setAcquired_date(Date acquired_date) {
        this.acquired_date = acquired_date;
    }

    /**
     * @return the lease_end_date
     */
    public Date getLease_end_date() {
        return lease_end_date;
    }

    /**
     * @param lease_end_date the lease_end_date to set
     */
    public void setLease_end_date(Date lease_end_date) {
        this.lease_end_date = lease_end_date;
    }

    /**
     * @return the prop_type
     */
    public PropertyType getProp_type() {
        return prop_type;
    }

    /**
     * @param prop_type the prop_type to set
     */
    public void setProp_type(PropertyType prop_type) {
        this.prop_type = prop_type;
    }

    /**
     * @return the prop_sub_type
     */
    public PropertySubType getProp_sub_type() {
        return prop_sub_type;
    }

    /**
     * @param prop_sub_type the prop_sub_type to set
     */
    public void setProp_sub_type(PropertySubType prop_sub_type) {
        this.prop_sub_type = prop_sub_type;
    }

    /**
     * @return the prop_status
     */
    public String getProp_status() {
        return prop_status;
    }

    /**
     * @param prop_status the prop_status to set
     */
    public void setProp_status(String prop_status) {
        this.prop_status = prop_status;
    }

    /**
     * @return the propertyElements
     */
    public HashMap<String, PropertyElement> getPropertyElements() {
        return propertyElements;
    }
}