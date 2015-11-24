
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class AddressUsage implements AddressUsageInterface {
    
    ///   VARIABLES   ///
    
    private final int addressUsageRef;
    private AddressInterface address;
    private Date startDate;
    private Date endDate;
    private final Note note;
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class AddressUsage
     * @param ref
     * @param address
     * @param startDate
     * @param note
     * @param createdBy
     * @param createdDate 
     */
    public AddressUsage(int ref, AddressInterface address, Date startDate, Note note, String createdBy, Date createdDate) {
        this.addressUsageRef = ref;
        this.address = address;
        this.startDate = startDate;
        this.note = note;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = new ArrayList();
    }
    
    
    
    ///   MUTATOR METHODS   ///    
    

    /**
     * @param address
     */
    private void setAddress(AddressInterface address) {
        this.address = address;
    }
        
    /**
     * @param startDate
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    private void setComment(String comment, ModifiedByInterface modifiedBy) {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
        this.modifiedBy(modifiedBy);
    }
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * @param endDate
     * @param modifiedBy
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate == null || endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    /**
     * @param address
     * @param startDate
     * @param comment
     * @param modifiedBy
     */
    public void updateAddress(AddressInterface address, Date startDate, String comment, ModifiedByInterface modifiedBy) {
        if(this.isCurrent()) {
            this.setAddress(address);
            this.setComment(comment, modifiedBy);
            this.setStartDate(startDate);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return addressUsageRef
     */
    @Override
    public int getAddressUsageRef() {
        return this.addressUsageRef;
    }
    
    /**
     * @return a string representation address
     */
    @Override
    public String getAddressString() {
        return this.address.printAddress();
    }
    
    /**
     * @return address
     */
    @Override
    public AddressInterface getAddress() {
        return this.address;
    }

    /**
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * @return endDate
     */
    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    /**
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
    public Note getNote() {
        return note;
    }
    
    @Override
    public String getComment() {
        return note.getNote();
    }
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the user that last modified the AddressUsage
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the last date the AddressUsage was modified
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return the list of modifiedBy objects for the AddressUsage
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the AddressUsage
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
    
    /**
     * @return String representation of the AddressUsage
     */
    @Override
    public String toString() {
        String temp = "\n\nAddress: " + this.getAddressString() + "\nStart Date: " + this.getStartDate() +
                "\nEnd Date: " + this.getEndDate() + "\nCreatedBy: " + this.getCreatedBy() + "\nCreated Date: " +
                this.getCreatedDate() + "\nLast Modified By: " + this.getLastModifiedBy() + "\nLast Modified Date: " +
                this.getLastModifiedDate() + "\nIs Current: " + isCurrent() +  "\nModified By\n" + this.getModifiedBy();
        return temp;
    }
}