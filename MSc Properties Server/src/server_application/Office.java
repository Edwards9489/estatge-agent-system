/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AccountInterface;
import interfaces.AddressInterface;
import interfaces.AgreementInterface;
import interfaces.ContactInterface;
import interfaces.ModifiedByInterface;
import interfaces.OfficeInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
    private ArrayList<ContactInterface> contacts;
    private final HashMap<Integer, AgreementInterface> agreements;
    private final HashMap<Integer, AccountInterface> accounts;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public Office(String officeCode, AddressInterface address, Date startDate, String createdBy) {
        this.officeCode = officeCode;
        this.address = address;
        this.startDate = startDate;
        this.agreements = new HashMap<>();
        this.accounts = new HashMap<>();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
        
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    @Override
    public void setStartDate(Date startDate, ModifiedByInterface modifiedBy) {
        this.startDate = startDate;
        this.modifiedBy(modifiedBy);
    }
    
    @Override
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(this.canCloseOffice()) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param agreement the agreement to add to agreements
     */
    @Override
    public void addAgreement(AgreementInterface agreement, ModifiedByInterface modifiedBy) {
        if(!agreements.containsKey(agreement.getAgreementRef())) {
            agreements.put(agreement.getAgreementRef(), agreement);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param account the agreement to add to account
     */
    @Override
    public void addAccount(AccountInterface account, ModifiedByInterface modifiedBy) {
        if(accounts.containsKey(account.getAccRef())) {
            accounts.remove(account.getAccRef());
            this.modifiedBy(modifiedBy);
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
    public List getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    /**
     * @return the agreements
     */
    @Override
    public List getAgreements() {
        return Collections.unmodifiableList((List) agreements.values());
    }

    /**
     * @return the accounts
     */
    @Override
    public List getAccounts() {
        return Collections.unmodifiableList((List) accounts.values());
    }
    
    @Override
    public boolean isCurrent() {
        if(endDate != null) {
            return endDate.before(new Date());
        }
        return false;
    }
    
    @Override
    public boolean canCloseOffice() {
        if(agreements.isEmpty()) {
            return true;
        }
        else if(!agreements.isEmpty()) {
            for(AgreementInterface temp : agreements.values()) {
                if(temp.isCurrent()) {
                    return false;
                }
            }
        }
        
        if(accounts.isEmpty()) {
            return false;
        }
        else if(!accounts.isEmpty()) {
            for(AccountInterface temp : accounts.values()) {
                if(temp.isCurrent()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1).getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1).getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
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