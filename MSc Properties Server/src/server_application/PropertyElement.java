/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.Note;
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
    private final Note note;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class PropertyElement
     * @param propElementRef
     * @param element
     * @param startDate
     * @param charge
     * @param stringValue
     * @param doubleValue
     * @param note
     * @param createdBy
     * @param createdDate 
     */
    public PropertyElement(int propElementRef, Element element, Date startDate, boolean charge, String stringValue, Double doubleValue, Note note, String createdBy, Date createdDate) {
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
        this.note = note;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///

    /**
     * @param value
     */
    private void setStringValue(String value) {
        this.stringValue = value;
    }

    /**
     * @param value
     */
    private void setDoubleValue(double value) {
        this.doubleValue = value;
    }
    
    /**
     * 
     * @param charge 
     */
    private void setCharge(boolean charge) {
        this.charge = charge;
    }

    /**
     * @param startDate
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
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
    
    private void setComment(String comment, ModifiedByInterface modifiedBy) {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param startDate
     * @param stringValue
     * @param doubleValue
     * @param charge
     * @param comment
     * @param modifiedBy 
     */
    public void updatePropertyElement(Date startDate, String stringValue, Double doubleValue, boolean charge, String comment, ModifiedByInterface modifiedBy) {
        if(charge) {
            this.setDoubleValue(doubleValue);
        }
        else if(!charge) {
            this.setStringValue(stringValue);
        }
        this.setCharge(charge);
        this.setStartDate(startDate);
        this.setComment(comment, modifiedBy);
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param endDate the endDate to set
     * @param modifiedBy
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate == null || endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * 
     * @return propertyElementRef
     */
    @Override
    public int getPropertyElementRef() {
        return this.propertyElementRef;
    }
    
    /**
     * @return element
     */
    @Override
    public Element getElement() {
        return element;
    }
    
    /**
     * 
     * @return code for element
     */
    @Override
    public String getElementCode() {
        return element.getCode();
    }

    /**
     * @return stringValue
     */
    @Override
    public String getStringValue() {
        return stringValue;
    }
    
    /**
     * @return doubleValue
     */
    @Override
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     */
    @Override
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.after(new Date());
        }
    }

    /**
     * @return charge
     */
    @Override
    public boolean isCharge() {
        return charge;
    }
    
    /**
     * 
     * @param code
     * @return true if this.code == code
     */
    @Override
    public boolean isElementCode(String code) {
        return code.equals(element.getCode());
    }
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user who modified the PropertyElement
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
     * @return the last date a user modified the PropertyElement
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
     * @return the list of modifiedBy objects for the PropertyElement
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last mdodifiedBy object for the PropertyElement
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    @Override
    public Note getNote() {
        return note;
    }
    
    @Override
    public String getComment() {
        return note.getNote();
    }

    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    @Override
    public String toString() {
        return "Property Element Ref: " + this.propertyElementRef + "\nstringValue: " + this.stringValue
                + "\ndoubleValue: " + this.doubleValue + "\nstartDate: " + startDate + "\nendDate: " + this.endDate
                + "\nCharge: " + this.charge;
    }
}