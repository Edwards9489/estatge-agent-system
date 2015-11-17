/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.ContactInterface;
import interfaces.ModifiedByInterface;
import interfaces.OfficeInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Office implements OfficeInterface {
    
    ///   VARIABLES   ///
    
    private final String officeCode;
    private final AddressInterface address;
    private Date startDate;
    private Date endDate;
    private final List<ContactInterface> contacts;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Office
     * @param officeCode
     * @param address
     * @param startDate
     * @param createdBy
     * @param createdDate 
     */
    public Office(String officeCode, AddressInterface address, Date startDate, String createdBy, Date createdDate) {
        this.officeCode = officeCode;
        this.address = address;
        this.startDate = startDate;
        this.contacts = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
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
     * 
     * @param contact
     * @param modifiedBy 
     */
    public void createContact(ContactInterface contact, ModifiedByInterface modifiedBy) {
        this.contacts.add(contact);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param startDate
     * @param modifiedBy 
     */
    public void setStartDate(Date startDate, ModifiedByInterface modifiedBy) {
        this.startDate = startDate;
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param endDate
     * @param modifiedBy 
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if (endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return officeCode
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * @return address
     */
    @Override
    public AddressInterface getAddress() {
        return address;
    }
    
    /**
     * 
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }
    
    /**
     * 
     * @return endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return contacts
     */
    @Override
    public List<ContactInterface> getContacts() {
        return Collections.unmodifiableList(contacts);
    }
    
    public boolean hasContact(int contactRef) {
        if(contacts.isEmpty()) {
            return false;
        }
        for(ContactInterface contact : contacts) {
            if(contact.getContactRef() == contactRef) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     */
    @Override
    public boolean isCurrent() {
        if(this.endDate == null) {
            return true;
        }
        else {
            return this.endDate.after(new Date());
        }
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the date a user last modified the Office
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
     * @return the list of modifiedBy objects for the Office
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Office
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     * 
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * 
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}