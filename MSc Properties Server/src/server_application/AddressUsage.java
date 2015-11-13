
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
import interfaces.ModifiedByInterface;
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
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class AddressUsage
     * @param ref
     * @param address
     * @param startDate
     * @param createdBy
     * @param createdDate 
     */
    public AddressUsage(int ref, AddressInterface address, Date startDate, String createdBy, Date createdDate) {
        this.addressUsageRef = ref;
        this.address = address;
        this.startDate = startDate;
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
        if(endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    /**
     * @param address
     * @param startDate
     * @param modifiedBy
     */
    public void updateAddress(AddressInterface address, Date startDate, ModifiedByInterface modifiedBy) {
        if(this.isCurrent()) {
            this.setAddress(address);
            this.setStartDate(startDate);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return addressUsageRef
     */
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