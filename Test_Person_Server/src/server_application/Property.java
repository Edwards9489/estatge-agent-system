/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Property
{
    // instance variables - replace the example below with your own
    private final int propRef;
    private Address address;
    private ArrayList<Landlord> landlords;
    private Date acquiredDate;
    private Date leaseEndDate;
    private PropertyType propType;
    private PropertySubType propSubType;
    private String propStatus; // Occupied, Void, New, End etc
    private ArrayList<PropertyElement> propertyElements;
    
    /**
     * Constructor for objects of class Property
     */
    public Property(int propRef, Address address, ArrayList<Landlord> landlords, boolean management, Date acquiredDate, PropertyType propType, PropertySubType propSubType) {
        // initialise instance variables
        this.propRef = propRef;
        this.address = address;
        this.landlords = landlords;
        this.acquiredDate = acquiredDate;
        this.propType = propType;
        this.propSubType = propSubType;
        this.propStatus = "NEW";
        propertyElements = new ArrayList();
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
    public List getLandlords() {
        return Collections.unmodifiableList(landlords);
    }

    /**
     * @param landlords the landlords to set
     */
    public void setLandlords(ArrayList<Landlord> landlords) {
        this.landlords = landlords;
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
    public List getPropertyElements() {
        return Collections.unmodifiableList(propertyElements);
    }
    
    public boolean isCurrent() {
        if(leaseEndDate == null) {
            return true;
        }
        else {
            return leaseEndDate.after(new Date());
        }
    }
    
    public double getRent() {
        double rent = -1;
        if (!propertyElements.isEmpty()) {
            for (PropertyElement temp : propertyElements) {
                if (temp.isCurrent() && temp.isElementCode("RENT")) {
                    rent = temp.getDoubleValue();
                }
            }
        }
        return rent;
    }
    
    public double getCharges() {
        double charges = -1;
        if(!propertyElements.isEmpty()) {
            for (PropertyElement temp : propertyElements) {
                if(temp.isCurrent() && temp.isCharge()) {
                    charges = temp.getDoubleValue();
                }
            }
        }
        return charges;
    }
}