/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class PropertyElement{

    private final int propElementRef;
    private final ElementImpl element;
    private String stringValue;
    private double doubleValue;
    private Date startDate;
    private Date endDate;
    private final String createdBy;
    private final Date createdDate;
    private boolean charge;

    public PropertyElement(int propElementRef, ElementImpl element, boolean charge, String createdBy) {
        this.propElementRef = propElementRef;
        this. element = element;
        this.charge = charge;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    // MIGHT NOT NEED THIS CLASS AS CAN JUST HAVE A HASHMAP OF PROPERTY ELEMENTS IN SERVERIMPL FOR MASTER LISTS OF ALL PROPERTY ELEMENTS
    // AND THEN A HASHMAP OF (PROPERTY) ELEMENTS WITHIN EACH PROPERTY FOR THEIR OWN PERSONAL PROPERTY ELEMENTS
    
    // REASSESED AND THINK I WILL NEED A LIST OF PROPERTYELEMENT IN THE PROPERTY WHICH ALLOWS A VALUE TO BE ASSIGNED
    // TO THE PROPERTY ELEMENT WHICH IS ASSOCIATED WITH THE PROPERTY (I.E RENT, NUMBER OF BEDROOMS)
    // THESE ELEMENTS WILL ONLY BE SELECTED FROM THE HASHMAP OF PROPERTY ELEMENTS WITHIN SERVERIMPL

    /**
     * @return the propElementRef
     */
    public int getPropElementRef() {
        return propElementRef;
    }

    /**
     * @return the element
     */
    public ElementImpl getElement() {
        return element;
    }

    /**
     * @return the value
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * @param value the value to set
     */
    public void setStringValue(String value) {
        this.stringValue = value;
    }
    
    /**
     * @return the value
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * @param value the value to set
     */
    public void setDoubleValue(double value) {
        this.doubleValue = value;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the current
     */
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @return the charge
     */
    public boolean isCharge() {
        return charge;
    }

    /**
     * @param charge the charge to set
     */
    public void setCharge(boolean charge) {
        this.charge = charge;
    }
    
    public boolean isElementCode(String code) {
        return code.equals(element.getCode());
    }
}