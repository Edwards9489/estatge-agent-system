/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.LandlordInterface;
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
    private final int landlordRef;
    private final PersonInterface person;
    private final ArrayList<Lease> leases;
    private final ArrayList<AddressUsage> addressUsages;
    private final String createdBy;
    private final Date createdDate;
    
    public Landlord(int landlordRef, PersonInterface person, String createdBy) {
        this.landlordRef = landlordRef;
        this.person = person;
        this.leases = new ArrayList();
        this.addressUsages = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }

    /**
     * @return the landlordRef
     */
    public int getLandlordRef() {
        return landlordRef;
    }

    /**
     * @return the person
     */
    public PersonInterface getPerson() {
        return person;
    }
    
    /**
     * @return the addressUsages
     */
    public List getLeases() {
        return Collections.unmodifiableList(leases);
    }
    
    public void createLease(Lease lease) {
        if (!leases.contains(lease)) {
            leases.add(lease);
        }
    }

    /**
     * @return the addressUsages
     */
    public List getAddressUsages() {
        return Collections.unmodifiableList(addressUsages);
    }
    
    public void createAddressUsage(AddressUsage addressUsage) {
        if(!addressUsages.isEmpty()) {
            AddressUsage temp = addressUsages.get(addressUsages.size()-1);
            temp.setEndDate(addressUsage.getStartDate());
        }
        addressUsages.add(addressUsage);
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
}
