/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.Element;
import interfaces.LandlordInterface;
import interfaces.ModifiedByInterface;
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
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public Property(int propRef, AddressInterface address, Date acquiredDate, Element propType, Element propSubType, String createdBy, Date createdDate) {
        // initialise instance variables
        this.propRef = propRef;
        this.address = address;
        this.landlords = new ArrayList();
        this.acquiredDate = acquiredDate;
        this.propType = propType;
        this.propSubType = propSubType;
        this.propStatus = "NEW";
        propertyElements = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param acquiredDate the acquiredDate to set
     */
    private void setAddress(AddressInterface address) {
        this.address = address;
    }
    
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
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }

    /**
     * @param landlords the landlords to set
     * @param modifiedBy
     */
    @Override
    public void setLandlords(ArrayList<LandlordInterface> landlords, ModifiedByInterface modifiedBy) {
        this.landlords = landlords;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param leaseEndDate the leaseEndDate to set
     * @param modifiedBy
     */
    @Override
    public void setLeaseEndDate(Date leaseEndDate, ModifiedByInterface modifiedBy) {
        this.leaseEndDate = leaseEndDate;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param propStatus the propStatus to set
     * @param modifiedBy
     */
    @Override
    public void setPropStatus(String propStatus, ModifiedByInterface modifiedBy) {
        this.propStatus = propStatus;
        this.modifiedBy(modifiedBy);
    }
    
    @Override
    public void updateProperty(AddressInterface address, Date acquiredDate, Element propType, Element propSubType, ModifiedByInterface modifiedBy) {
        this.setAddress(address);
        this.setAcquiredDate(acquiredDate);
        this.setPropType(propType);
        this.setPropSubType(propSubType);
        this.modifiedBy(modifiedBy);
    }
    
    public void createPropertyElement(PropertyElement element, ModifiedByInterface modifiedBy) {
        if(!this.hasPropElement(element.getElementCode())) {
            this.propertyElements.add(element);
            this.modifiedBy(modifiedBy);
        }
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
    
    
    
    public boolean hasPropElement(String code) {
        if(!propertyElements.isEmpty()) {
            for(PropertyElement element : propertyElements) {
                if(element.isCurrent() && code.equals(element.getElementCode())) {
                    return true;
                }
            }
        }
        return false;
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
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
}