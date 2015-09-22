/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.PropertyElementInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class PropertyElement implements PropertyElementInterface {
    
    ///   VARIABLES   ///
    
    private final Element element;
    private String stringValue;
    private double doubleValue;
    private Date startDate;
    private Date endDate;
    private final String createdBy;
    private final Date createdDate;
    private boolean charge;
    
    ///   CONSTRUCTORS ///
    
    public PropertyElement(Element element, Date startDate, boolean charge, String stringValue, double doubleValue, String createdBy) {
        this. element = element;
        updatePropertyElement(startDate, stringValue, doubleValue, charge);
        this.createdBy = createdBy;
        this.createdDate = new Date();
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
    
    @Override
    public void updatePropertyElement(Date startDate, String stringValue, double doubleValue, boolean charge) {
        if(charge) {
            setDoubleValue(doubleValue);
        }
        else if(!charge) {
            setStringValue(stringValue);
        }
        setCharge(charge);
        setStartDate(startDate);
    }

    /**
     * @param endDate the endDate to set
     */
    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
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