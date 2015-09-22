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
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public Office(String officeCode, AddressInterface address, Date startDate, String createdBy) {
        this.officeCode = officeCode;
        this.address = address;
        this.startDate = startDate;
        this.agreements = new HashMap<>();
        this.accounts = new HashMap<>();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Override
    public void setEndDate(Date endDate) {
        if(this.canCloseOffice()) {
            this.endDate = endDate;
        }
    }
    
    /**
     * @param agreement the agreement to add to agreements
     */
    @Override
    public void addAgreement(AgreementInterface agreement) {
        if(!agreements.containsKey(agreement.getAgreementRef())) {
            agreements.put(agreement.getAgreementRef(), agreement);
        }
    }
    
    /**
     * @param account the agreement to add to account
     */
    @Override
    public void addAccount(AccountInterface account) {
        if(accounts.containsKey(account.getAccRef())) {
            accounts.remove(account.getAccRef());
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
    public boolean getCurrent() {
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
    public String getCreatedBy() {
        return createdBy;
    }
    
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}