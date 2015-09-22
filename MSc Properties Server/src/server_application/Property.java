/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.Element;
import interfaces.LandlordInterface;
import interfaces.PropertyInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Property implements PropertyInterface {
    
    ///   VARIABLES   ///
        
    private final int propRef;
    private AddressInterface address;
    private ArrayList<LandlordInterface> landlords;
    private Date acquiredDate;
    private Date leaseEndDate;
    private Element propType;
    private Element propSubType;
    private String propStatus; // Occupied, Void, New, End etc
    private final ArrayList<PropertyElement> propertyElements;
    
    ///   CONSTRUCTORS ///
    
    public Property(int propRef, AddressInterface address, boolean management, Date acquiredDate, Element propType, Element propSubType) {
        // initialise instance variables
        this.propRef = propRef;
        this.address = address;
        this.landlords = new ArrayList();
        this.acquiredDate = acquiredDate;
        this.propType = propType;
        this.propSubType = propSubType;
        this.propStatus = "NEW";
        propertyElements = new ArrayList();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param acquiredDate the acquiredDate to set
     */
    private void setAcquiredDate(Date acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    /**
     * @param propType the propType to set
     */
    private void setPropType(Element propType) {
        this.propType = propType;
    }

    /**
     * @param propSubType the propSubType to set
     */
    private void setPropSubType(Element propSubType) {
        this.propSubType = propSubType;
    }

    /**
     * @param landlords the landlords to set
     */
    @Override
    public void setLandlords(ArrayList<LandlordInterface> landlords) {
        this.landlords = landlords;
    }

    /**
     * @param leaseEndDate the leaseEndDate to set
     */
    @Override
    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    /**
     * @param propStatus the propStatus to set
     */
    @Override
    public void setPropStatus(String propStatus) {
        this.propStatus = propStatus;
    }
    
    @Override
    public void updateProperty(Date acquiredDate, Element propType, Element propSubType) {
        setAcquiredDate(acquiredDate);
        setPropType(propType);
        setPropSubType(propSubType);
    }

    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the propRef
     */
    @Override
    public int getPropRef() {
        return propRef;
    }

    /**
     * @return the address
     */
    @Override
    public AddressInterface getAddress() {
        return address;
    }
    
    /**
     * @return the landlord
     */
    @Override
    public List getLandlords() {
        return Collections.unmodifiableList(landlords);
    }

    /**
     * @return the acquiredDate
     */
    @Override
    public Date getAcquiredDate() {
        return acquiredDate;
    }

    /**
     * @return the leaseEndDate
     */
    @Override
    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    /**
     * @return the propType
     */
    @Override
    public Element getPropType() {
        return propType;
    }

    /**
     * @return the propSubType
     */
    @Override
    public Element getPropSubType() {
        return propSubType;
    }

    /**
     * @return the propStatus
     */
    @Override
    public String getPropStatus() {
        return propStatus;
    }

    /**
     * @return the propertyElements
     */
    @Override
    public List getPropertyElements() {
        return Collections.unmodifiableList(propertyElements);
    }
    
    @Override
    public boolean isCurrent() {
        if(leaseEndDate == null) {
            return true;
        }
        else {
            return leaseEndDate.after(new Date());
        }
    }
    
    @Override
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
    
    @Override
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