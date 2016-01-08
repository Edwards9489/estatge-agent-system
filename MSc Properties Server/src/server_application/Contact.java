/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ContactInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class Contact extends UnicastRemoteObject implements ContactInterface {
    
    ///   VARIABLES   ///
    
    private final int contactRef;
    private Element contactType;
    private String contactValue;
    private Date startDate;
    private Date endDate;
    private final Note note;
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Contact
     * @param ref
     * @param type
     * @param value
     * @param date
     * @param note
     * @param createdBy
     * @param createdDate
     * @throws java.rmi.RemoteException
     */
    public Contact(int ref, Element type, String value, Date date, Note note, String createdBy, Date createdDate) throws RemoteException {
        this.contactRef = ref;
        contactType = type;
        contactValue = value;
        startDate = date;
        this.note = note;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = new ArrayList();
    }
    
    
    
    /// MUTATOR METHODS   ///
    
    /**
     * @param contactType the contactType to set
     */
    private void setContactType(Element contactType) {
        this.contactType = contactType;
    }
    
    /**
     * @param contactValue the contactValue to set
     */
    private void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    /**
     * @param startDate the startDate to set
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
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
    
    private void setComment(String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
    }
    
    /**
     * @param contactType
     * @param contactValue
     * @param startDate
     * @param comment
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void updateContact(Element contactType, String contactValue, Date startDate, String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        if (this.isCurrent()) {
            this.setContactType(contactType);
            this.setContactValue(contactValue);
            this.setStartDate(startDate);
            this.setComment(comment, modifiedBy);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return contactRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getContactRef() throws RemoteException {
        return this.contactRef;
    }
    
    /**
     * @return contactType
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getContactType() throws RemoteException {
        return contactType;
    }
    
    /**
     * @return contactType description
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getContactTypeDescription() throws RemoteException {
        return contactType.getDescription();
    }

    /**
     * @return contactValue
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getContactValue() throws RemoteException {
        return contactValue;
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
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the Contact
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
     * @return the last date the Contact was modified
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
     * @return the list of modifiedBy objects for the Contact
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the Contact
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    @Override
    public Note getNote() throws RemoteException {
        return note;
    }
    
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
    /**
     * 
     * @return String representation of Contact
     */
    @Override
    public String toString() {
        return "AMEND toString()";
    }
}