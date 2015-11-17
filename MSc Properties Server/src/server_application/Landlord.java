/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.LandlordInterface;
import interfaces.LeaseInterface;
import interfaces.ModifiedByInterface;
import interfaces.PersonInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Landlord implements LandlordInterface {
    
    ///   VARIABLES   ///
    
    private final int landlordRef;
    private final PersonInterface person;
    private final List<LeaseInterface> leases;
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
     */
    public Landlord(int landlordRef, PersonInterface person, String createdBy, Date createdDate) {
        this.landlordRef = landlordRef;
        this.person = person;
        this.leases = new ArrayList();
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
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return landlordRef
     */
    @Override
    public int getLandlordRef() {
        return landlordRef;
    }

    /**
     * @return person
     */
    @Override
    public PersonInterface getPerson() {
        return person;
    }
    
    /**
     * 
     * @return ref of person
     */
    @Override
    public int getPersonRef() {
        return person.getPersonRef();
    }
    
    /**
     * @return leases
     */
    @Override
    public List<LeaseInterface> getLeases() {
        return Collections.unmodifiableList(leases);
    }
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user that modified the Landlord
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
     * @return the last date a user modified the Landlord
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
     * @return the list of modifiedBy objects for the Landlord
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Landlord
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
        return createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}