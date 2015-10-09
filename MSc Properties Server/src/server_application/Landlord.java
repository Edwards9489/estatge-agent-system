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
    private final ArrayList<LeaseInterface> leases;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public Landlord(int landlordRef, PersonInterface person, String createdBy) {
        this.landlordRef = landlordRef;
        this.person = person;
        this.leases = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    public void createLease(LeaseInterface lease, ModifiedByInterface modifiedBy) {
        if (!leases.contains(lease)) {
            leases.add(lease);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the landlordRef
     */
    @Override
    public int getLandlordRef() {
        return landlordRef;
    }

    /**
     * @return the person
     */
    @Override
    public PersonInterface getPerson() {
        return person;
    }
    
    @Override
    public int getPersonRef() {
        return person.getPersonRef();
    }
    
    /**
     * @return the addressUsages
     */
    @Override
    public List getLeases() {
        return Collections.unmodifiableList(leases);
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}