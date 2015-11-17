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
import interfaces.PropertyElementInterface;
import interfaces.PropertyInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dwayne
 */
public class Property implements PropertyInterface {
    
    ///   VARIABLES   ///
        
    private final int propRef;
    private AddressInterface address;
    private List<LandlordInterface> landlords;
    private Date acquiredDate;
    private Date leaseEndDate;
    private Element propType;
    private Element propSubType;
    private String propStatus; // Occupied, Void, New, End etc
    private final Map<Integer, PropertyElementInterface> propertyElements;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Property
     * @param propRef
     * @param address
     * @param acquiredDate
     * @param propType
     * @param propSubType
     * @param createdBy
     * @param createdDate 
     */
    public Property(int propRef, AddressInterface address, Date acquiredDate, Element propType, Element propSubType, String createdBy, Date createdDate) {
        // initialise instance variables
        this.propRef = propRef;
        this.address = address;
        this.landlords = new ArrayList();
        this.acquiredDate = acquiredDate;
        this.propType = propType;
        this.propSubType = propSubType;
        this.propStatus = "NEW";
        propertyElements = new HashMap<>();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param address
     */
    private void setAddress(AddressInterface address) {
        this.address = address;
    }
    
    /**
     * @param acquiredDate
     */
    private void setAcquiredDate(Date acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    /**
     * @param propType
     */
    private void setPropType(Element propType) {
        this.propType = propType;
    }

    /**
     * @param propSubType=
     */
    private void setPropSubType(Element propSubType) {
        this.propSubType = propSubType;
    }
    
    /**
     * 
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }

    /**
     * @param landlords
     * @param modifiedBy
     */
    public void setLandlords(List<LandlordInterface> landlords, ModifiedByInterface modifiedBy) {
        this.landlords = landlords;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param leaseEndDate
     * @param modifiedBy
     */
    public void setLeaseEndDate(Date leaseEndDate, ModifiedByInterface modifiedBy) {
        this.leaseEndDate = leaseEndDate;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param propStatus
     * @param modifiedBy
     */
    public void setPropStatus(String propStatus, ModifiedByInterface modifiedBy) {
        this.propStatus = propStatus;
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param address
     * @param acquiredDate
     * @param propType
     * @param propSubType
     * @param modifiedBy 
     */
    public void updateProperty(AddressInterface address, Date acquiredDate, Element propType, Element propSubType, ModifiedByInterface modifiedBy) {
        this.setAddress(address);
        this.setAcquiredDate(acquiredDate);
        this.setPropType(propType);
        this.setPropSubType(propSubType);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param element
     * @param modifiedBy 
     */
    public void createPropertyElement(PropertyElement element, ModifiedByInterface modifiedBy) {
        if(!this.hasCurrentPropElement(element.getElementCode())) {
            this.propertyElements.put(element.getPropertyElementRef(), element);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void endPropertyElement(int ref, ModifiedByInterface modifiedBy) {
        
    }

    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return propRef
     */
    @Override
    public int getPropRef() {
        return propRef;
    }

    /**
     * @return address
     */
    @Override
    public AddressInterface getAddress() {
        return address;
    }
    
    /**
     * @return landlords
     */
    @Override
    public List<LandlordInterface> getLandlords() {
        return Collections.unmodifiableList(landlords);
    }

    /**
     * @return acquiredDate
     */
    @Override
    public Date getAcquiredDate() {
        return acquiredDate;
    }

    /**
     * @return leaseEndDate
     */
    @Override
    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    /**
     * @return propType
     */
    @Override
    public Element getPropType() {
        return propType;
    }

    /**
     * @return propSubType
     */
    @Override
    public Element getPropSubType() {
        return propSubType;
    }

    /**
     * @return propStatus
     */
    @Override
    public String getPropStatus() {
        return propStatus;
    }

    /**
     * @return propertyElements
     */
    @Override
    public List<PropertyElementInterface> getPropertyElements() {
        return Collections.unmodifiableList(new ArrayList(propertyElements.values()));
    }
    
    /**
     * 
     * @param code
     * @return true if propertyElements contains PropertyElement with code == code and current
     */
    public boolean hasCurrentPropElement(String code) {
        if(!propertyElements.isEmpty()) {
            for(PropertyElementInterface element : propertyElements.values()) {
                if(element.isCurrent() && code.equals(element.getElementCode())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @param ref
     * @return true if propertyElements contains PropertyElement with ref == ref
     */
    public boolean hasPropElement(int ref) {
        return propertyElements.containsKey(ref);
    }
    
    public PropertyElementInterface getPropElement(int ref) {
        if(this.hasPropElement(ref)) {
            return this.propertyElements.get(ref);
        }
        return null;
    }
    
    /**
     * 
     * @return true if leaseEndDate == null || (leaseEndDate != null && leaseEndDate > TODAY)
     */
    @Override
    public boolean isCurrent() {
        if(leaseEndDate == null) {
            return true;
        }
        else {
            return leaseEndDate.after(new Date());
        }
    }
    
    /**
     * 
     * @return value assigned to RENT element from propertyElements
     */
    @Override
    public double getRent() {
        double rent = -1;
        if (!propertyElements.isEmpty()) {
            for (PropertyElementInterface temp : propertyElements.values()) {
                if (temp.isCurrent() && temp.isElementCode("RENT")) {
                    rent = temp.getDoubleValue();
                }
            }
        }
        return rent;
    }
    
    /**
     * 
     * @return sum of values assigned to charge elements from propertyElements
     */
    @Override
    public double getCharges() {
        double charges = -1;
        if(!propertyElements.isEmpty()) {
            for (PropertyElementInterface temp : propertyElements.values()) {
                if(temp.isCurrent() && temp.isCharge() && !temp.isElementCode("RENT")) {
                    charges = temp.getDoubleValue();
                }
            }
        }
        return charges;
    }
    
    /**
     * 
     * @return the name of the last user who modified the Property
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the last date a user modified the Property
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the Property
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifedBy object for the Property
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
        return this.createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
}