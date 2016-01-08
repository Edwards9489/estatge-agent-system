
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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class AddressUsage extends UnicastRemoteObject implements AddressUsageInterface {
    
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
     * @throws java.rmi.RemoteException 
     */
    public AddressUsage(int ref, AddressInterface address, Date startDate, Note note, String createdBy, Date createdDate) throws RemoteException {
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
    
    private void setComment(String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
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
     * @throws java.rmi.RemoteException
     */
    public void updateAddress(AddressInterface address, Date startDate, String comment, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getAddressUsageRef() throws RemoteException {
        return this.addressUsageRef;
    }
    
    /**
     * @return a string representation address
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getAddressString() throws RemoteException {
        return this.address.printAddress();
    }
    
    /**
     * @return address
     * @throws java.rmi.RemoteException
     */
    @Override
    public AddressInterface getAddress() throws RemoteException {
        return this.address;
    }

    /**
     * @return startDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getStartDate() throws RemoteException {
        return this.startDate;
    }

    /**
     * @return endDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getEndDate() throws RemoteException {
        return this.endDate;
    }

    /**
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(this.endDate == null) {
            return true;
        }
        else {
            return this.endDate.after(new Date());
        }
    }
    
    @Override
    public Note getNote() throws RemoteException {
        return note;
    }
    
    @Override
    public String getComment() throws RemoteException {
        return note.getNote();
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the user that last modified the AddressUsage
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
     * @return the last date the AddressUsage was modified
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
     * @return the list of modifiedBy objects for the AddressUsage
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the AddressUsage
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
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }
    
    /**
     * @return String representation of the AddressUsage
     */
    @Override
    public String toString() {
        try {
            String temp = "\n\nAddress: " + this.getAddressString() + "\nStart Date: " + this.getStartDate() +
                    "\nEnd Date: " + this.getEndDate() + "\nCreatedBy: " + this.getCreatedBy() + "\nCreated Date: " +
                    this.getCreatedDate() + "\nLast Modified By: " + this.getLastModifiedBy() + "\nLast Modified Date: " +
                    this.getLastModifiedDate() + "\nIs Current: " + isCurrent() +  "\nModified By\n" + this.getModifiedBy();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(AddressUsage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}