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
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class AddressUsage implements AddressUsageInterface {
    
    ///   VARIABLES   ///
    
    private AddressInterface address;
    private Date startDate;
    private Date endDate;
    private final String createdBy;
    private final Date createdDate;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    
    ///   CONSTRUCTORS ///
    
    public AddressUsage(AddressInterface address, Date startDate, String createdBy) {
        this.address = address;
        this.startDate = startDate;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        modifiedBy = new ArrayList();
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
    
    /**
     * @param endDate the endDate to set
     */
    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public void updateAddress(AddressInterface address, Date startDate) {
        setAddress(address);
        setStartDate(startDate);
    }
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return a string representation of the address
     */
    @Override
    public String getAddressString() {
        return address.toString();
    }
    
    /**
     * @return the address
     */
    @Override
    public AddressInterface getAddress() {
        return address;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the current
     */
    @Override
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
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