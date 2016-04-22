/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Note;
import interfaces.ModifiedByInterface;
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
public class NoteImpl extends UnicastRemoteObject implements Note {
    private final int reference;
    private String note;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    public NoteImpl(int ref, String note, String createdBy, Date createdDate) throws RemoteException {
        this.reference = ref;
        this.note = note;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    public void modifiedBy(ModifiedByInterface modified) {
        if(modified != null) {
            this.modifiedBy.add(modified);
        }
    }

    /**
     * @param note the note to set
     * @param modified
     */
    public void setNote(String note, ModifiedByInterface modified) {
        this.note = note;
        this.modifiedBy(modified);
    }
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the account
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
     * @return the last date the account was modified
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
     * @return the list of modifiedBy objects
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the account
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
     * @return the reference
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getReference() throws RemoteException {
        return reference;
    }

    /**
     * @return the note
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getNote() throws RemoteException {
        return note;
    }

    /**
     * @return the createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return createdBy;
    }

    /**
     * @return the createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }
}