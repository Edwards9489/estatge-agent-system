/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
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
public class ElementImpl extends UnicastRemoteObject implements Element {
    
    ///   VARIABLES   ///
    
    private final String code;
    private String description;
    private boolean current;
    private final Note note;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class ElementImpl
     * @param code
     * @param description
     * @param note
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public ElementImpl(String code, String description, Note note, String createdBy, Date createdDate) throws RemoteException {
        this.code = code;
        this.description = description;
        this.note = note;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        current = true;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * 
     * @param description 
     */
    private void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 
     * @param current 
     */
    public void setCurrent(boolean current) {
        this.current = current;
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
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param description
     * @param current
     * @param comment
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void updateElement(String description, boolean current, String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        this.setDescription(description);
        this.setCurrent(current);
        this.setComment(comment, modifiedBy);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * 
     * @return code
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCode() throws RemoteException {
        return this.code;
    }
    
    /**
     * 
     * @return description
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getDescription() throws RemoteException {
        return this.description;
    }
    
    /**
     * 
     * @return current
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        return this.current;
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user to modify the ElementImpl
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        return this.getLastModification().getModifiedBy();
    }
    
    /**
     * 
     * @return the last date the ElementImpl was modified
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        return this.getLastModification().getModifiedDate();
    }
    
    /**
     * 
     * @return the last modifiedBy object for ElementImpl
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
     * @return the list of modifiedBy objects for ElementImpl
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
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
     * 
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }
    
    /**
     * 
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }
    
    /**
     * 
     * @return String representation of the ElementImpl
     */
    @Override
    public String toString() {
        try {
            String temp = "Code: " + this.code + "\nDescription: " + this.description + "\nCurrent: " + this.isCurrent()
                    + "\nCreated By: " + this.createdBy + "\nCreated Date: " + this.createdDate + "\nModifiedBy\n" + this.getModifiedBy();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(ElementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}