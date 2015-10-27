/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AddressInterface;
import interfaces.ContactInterface;
import interfaces.ModifiedByInterface;
import interfaces.OfficeInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Office implements OfficeInterface {
    
    ///   VARIABLES   ///
    
    private final String officeCode;
    private final AddressInterface address;
    private Date startDate;
    private Date endDate;
    private List<ContactInterface> contacts;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public Office(String officeCode, AddressInterface address, Date startDate, String createdBy, Date createdDate) {
        this.officeCode = officeCode;
        this.address = address;
        this.startDate = startDate;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    @Override
    public void setStartDate(Date startDate, ModifiedByInterface modifiedBy) {
        this.startDate = startDate;
        this.modifiedBy(modifiedBy);
    }
    
    @Override
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(this.canCloseOffice()) {
            if(endDate.after(this.startDate)) {
                this.endDate = endDate;
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return the officeCode
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * @return the address
     */
    @Override
    public AddressInterface getAddress() {
        return address;
    }
    
    @Override
    public Date getStartDate() {
        return startDate;
    }
    
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the contacts
     */
    @Override
    public List<ContactInterface> getContacts() {
        return Collections.unmodifiableList(contacts);
    }
    
    @Override
    public boolean isCurrent() {
        if(endDate != null) {
            return endDate.before(new Date());
        }
        return false;
    }
    
    public boolean hasContact(int contactRef) {
        for(ContactInterface contact : contacts) {
            if(contact.getContactRef() == contactRef) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean canCloseOffice() {
//        if(agreements.isEmpty()) {
//            return true;
//        }
//        else if(!agreements.isEmpty()) {
//            for(AgreementInterface temp : agreements.values()) {
//                if(temp.isCurrent()) {
//                    return false;
//                }
//            }
//        }
//        
//        if(accounts.isEmpty()) {
//            return false;
//        }
//        else if(!accounts.isEmpty()) {
//            for(AccountInterface temp : accounts.values()) {
//                if(temp.isCurrent()) {
//                    return false;
//                }
//            }
//        }
        return true;
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
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    @Override
    public String getCreatedBy() {
        return createdBy;
    }
    
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}