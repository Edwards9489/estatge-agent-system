/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.PropertyElementInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class PropertyElement implements PropertyElementInterface {
    
    ///   VARIABLES   ///
    
    private final int propertyElementRef;
    private final Element element;
    private String stringValue;
    private double doubleValue;
    private Date startDate;
    private Date endDate;
    private boolean charge;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    
    ///   CONSTRUCTORS ///
    
    public PropertyElement(int propElementRef, Element element, Date startDate, boolean charge, String stringValue, double doubleValue, String createdBy, Date createdDate) {
        this.propertyElementRef = propElementRef;
        this. element = element;
        this.startDate = startDate;
        this.charge = charge;
        if(charge) {
            setDoubleValue(doubleValue);
        }
        else if(!charge) {
            setStringValue(stringValue);
        }
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///

    /**
     * @param value the value to set
     */
    private void setStringValue(String value) {
        this.stringValue = value;
    }

    /**
     * @param value the value to set
     */
    private void setDoubleValue(double value) {
        this.doubleValue = value;
    }
    
    private void setCharge(boolean charge) {
        this.charge = charge;
    }

    /**
     * @param startDate the startDate to set
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    @Override
    public void updatePropertyElement(Date startDate, String stringValue, double doubleValue, boolean charge, ModifiedByInterface modifiedBy) {
        if(charge) {
            setDoubleValue(doubleValue);
        }
        else if(!charge) {
            setStringValue(stringValue);
        }
        setCharge(charge);
        setStartDate(startDate);
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param endDate the endDate to set
     * @param modifiedBy
     */
    @Override
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    public int getPropertyElementRef() {
        return this.propertyElementRef;
    }
    /**
     * @return the element
     */
    @Override
    public Element getElement() {
        return element;
    }
    
    @Override
    public String getElementCode() {
        return element.getCode();
    }

    /**
     * @return the value
     */
    @Override
    public String getStringValue() {
        return stringValue;
    }
    
    /**
     * @return the value
     */
    @Override
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the current
     */
    @Override
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }

    /**
     * @return the charge
     */
    @Override
    public boolean isCharge() {
        return charge;
    }
    
    @Override
    public boolean isElementCode(String code) {
        return code.equals(element.getCode());
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
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}