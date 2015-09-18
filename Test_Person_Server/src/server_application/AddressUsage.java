/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class AddressUsage {
    private Address address;
    private Date startDate;
    private Date endDate;
    
    public AddressUsage(Address address, Date startDate) {
        this.address = address;
        this.startDate = startDate;
    }

    /**
     * @return a string representation of the address
     */
    public String getAddressString() {
        return address.toString();
    }
    
    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the current
     */
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }
}