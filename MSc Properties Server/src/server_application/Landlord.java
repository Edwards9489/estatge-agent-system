/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.LandlordInterface;
import interfaces.LeaseInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PersonInterface;
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
public class Landlord extends UnicastRemoteObject implements LandlordInterface {
    
    ///   VARIABLES   ///
    
    private final int landlordRef;
    private final PersonInterface person;
    private final List<LeaseInterface> leases;
    private final List<Note> notes;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Landlord
     * @param landlordRef
     * @param person
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public Landlord(int landlordRef, PersonInterface person, String createdBy, Date createdDate) throws RemoteException {
        this.landlordRef = landlordRef;
        this.person = person;
        this.leases = new ArrayList();
        this.notes = new ArrayList();
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
     * @param lease
     * @param modifiedBy 
     */
    public void createLease(LeaseInterface lease, ModifiedByInterface modifiedBy) {
        if (!leases.contains(lease)) {
            leases.add(lease);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteLease(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasLease(ref)) {
            LeaseInterface lease = this.getLease(ref);
            if(lease.hasBeenModified()) {
                leases.remove(lease);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return landlordRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getLandlordRef() throws RemoteException {
        return landlordRef;
    }

    /**
     * @return person
     * @throws java.rmi.RemoteException
     */
    @Override
    public PersonInterface getPerson() throws RemoteException {
        return person;
    }
    
    /**
     * 
     * @return ref of person
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPersonRef() throws RemoteException {
        return person.getPersonRef();
    }
    
    @Override
    public boolean hasLease(int ref) throws RemoteException {
        if(!leases.isEmpty()) {
            for(LeaseInterface lease : leases) {
                if(lease.getAccountRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public LeaseInterface getLease(int ref) throws RemoteException {
        if(!leases.isEmpty()) {
            for(LeaseInterface lease : leases) {
                if(lease.getAccountRef() == ref) {
                    return lease;
                }
            }
        }
        return null;
    }
    
    /**
     * @return leases
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<LeaseInterface> getLeases() throws RemoteException {
        return Collections.unmodifiableList(leases);
    }
    
    @Override
    public boolean hasNote(int ref) throws RemoteException {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getReference() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Note getNote(int ref) throws RemoteException {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getReference() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user that modified the Landlord
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
     * @return the last date a user modified the Landlord
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
     * @return the list of modifiedBy objects for the Landlord
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Landlord
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
}