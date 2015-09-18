/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Landlord {
    private final int landlordRef;
    private Person person;
    private EndReason endReason;
    private ArrayList<AddressUsage> addressUsages;
    private final String createdBy;
    private final Date createdDate;
    
    public Landlord(int landlordRef, Person person, String createdBy) {
        this.landlordRef = landlordRef;
        this.person = person;
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
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return the endReason
     */
    public EndReason getEndReason() {
        return endReason;
    }

    /**
     * @param endReason the endReason to set
     */
    public void setEndReason(EndReason endReason) {
        this.endReason = endReason;
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
