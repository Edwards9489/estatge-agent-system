
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
    private final ArrayList<ModifiedByInterface> modifiedBy;
    
    ///   CONSTRUCTORS ///
    
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
     * @param address the address to set
     */
    private void setAddress(AddressInterface address) {
        this.address = address;
    }
        
    /**
     * @param startDate the startDate to set
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
     * @param endDate the endDate to set
     * @param modifiedBy
     */
    @Override
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void updateAddress(AddressInterface address, Date startDate, ModifiedByInterface modifiedBy) {
        if(this.isCurrent()) {
            this.setAddress(address);
            this.setStartDate(startDate);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    public int getAddressUsageRef() {
        return this.addressUsageRef;
    }
    
    /**
     * @return a string representation of the address
     */
    @Override
    public String getAddressString() {
        return this.address.printAddress();
    }
    
    /**
     * @return the address
     */
    @Override
    public AddressInterface getAddress() {
        return this.address;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * @return the current
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
        return this.createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    @Override
    public String toString() {
        String temp = "\n\nAddress: " + this.getAddressString() + "\nStart Date: " + this.getStartDate() +
                "\nEnd Date: " + this.getEndDate() + "\nCreatedBy: " + this.getCreatedBy() + "\nCreated Date: " +
                this.getCreatedDate() + "\nLast Modified By: " + this.getLastModifiedBy() + "\nLast Modified Date: " +
                this.getLastModifiedDate() + "\nIs Current: " + isCurrent() +  "\nModified By\n" + this.getModifiedBy();
        return temp;
    }
}