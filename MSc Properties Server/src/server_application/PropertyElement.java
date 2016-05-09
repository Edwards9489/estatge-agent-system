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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class PropertyElement extends UnicastRemoteObject implements PropertyElementInterface {
    
    ///   VARIABLES   ///
    
    private final int propertyElementRef;
    private final int propRef;
    private final Element element;
    private String stringValue;
    private Double doubleValue;
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
     * @param propRef
     * @param element
     * @param startDate
     * @param charge
     * @param stringValue
     * @param doubleValue
     * @param note
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public PropertyElement(int propElementRef, int propRef, Element element, Date startDate, boolean charge, String stringValue, Double doubleValue, Note note, String createdBy, Date createdDate) throws RemoteException {
        this.propertyElementRef = propElementRef;
        this.propRef = propRef;
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
    private void setDoubleValue(Double value) {
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
    
    private void setComment(String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
    }
    
    /**
     * 
     * @param startDate
     * @param stringValue
     * @param doubleValue
     * @param charge
     * @param comment
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void updatePropertyElement(Date startDate, String stringValue, Double doubleValue, boolean charge, String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        if(charge) {
            this.setDoubleValue(doubleValue);
            this.setStringValue(null);
        }
        else if(!charge) {
            this.setStringValue(stringValue);
            this.setDoubleValue(null);
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPropertyElementRef() throws RemoteException {
        return this.propertyElementRef;
    }
    
    /**
     * 
     * @return propertyElementRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPropRef() throws RemoteException {
        return this.propRef;
    }
    
    /**
     * @return element
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getElement() throws RemoteException {
        return element;
    }
    
    /**
     * 
     * @return code for element
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getElementCode() throws RemoteException {
        return element.getCode();
    }

    /**
     * @return stringValue
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getStringValue() throws RemoteException {
        return stringValue;
    }
    
    /**
     * @return doubleValue
     * @throws java.rmi.RemoteException
     */
    @Override
    public Double getDoubleValue() throws RemoteException {
        return doubleValue;
    }

    /**
     * @return startDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getStartDate() throws RemoteException {
        return startDate;
    }

    /**
     * @return endDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getEndDate() throws RemoteException {
        return endDate;
    }

    /**
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.after(new Date());
        }
    }

    /**
     * @return charge
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCharge() throws RemoteException {
        return charge;
    }
    
    /**
     * 
     * @param code
     * @return true if this.code == code
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isElementCode(String code) throws RemoteException {
        return code.equals(element.getCode());
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user who modified the PropertyElement
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the last date a user modified the PropertyElement
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the PropertyElement
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last mdodifiedBy object for the PropertyElement
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public Note getNote() throws RemoteException {
        return note;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String getComment() throws RemoteException {
        return note.getNote();
    }

    /**
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }
    
    @Override
    public String toString() {
        return "Property Element Ref: " + this.propertyElementRef + "\nstringValue: " + this.stringValue
                + "\ndoubleValue: " + this.doubleValue + "\nstartDate: " + startDate + "\nendDate: " + this.endDate
                + "\nCharge: " + this.charge;
    }
}