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
import interfaces.Note;
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
    private final List<AgreementInterface> agreements;
    private final List<AccountInterface> accounts;
    private final List<ContactInterface> contacts;
    private final List<Note> notes;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Office
     * @param officeCode
     * @param address
     * @param startDate
     * @param createdBy
     * @param createdDate 
     */
    public Office(String officeCode, AddressInterface address, Date startDate, String createdBy, Date createdDate) {
        this.officeCode = officeCode;
        this.address = address;
        this.startDate = startDate;
        this.agreements = new ArrayList();
        this.accounts = new ArrayList();
        this.contacts = new ArrayList();
        this.notes = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * 
     * @param modifiedBy 
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * 
     * @param contact
     * @param modifiedBy 
     */
    public void createContact(ContactInterface contact, ModifiedByInterface modifiedBy) {
        if(!this.hasContact(contact.getContactRef())) {
            this.contacts.add(contact);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteContact(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasContact(ref)) {
            ContactInterface contact = this.getContact(ref);
            if(!contact.hasBeenModified()) {
                contacts.remove(contact);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * 
     * @param agreement
     * @param modifiedBy 
     */
    public void createAgreement(AgreementInterface agreement, ModifiedByInterface modifiedBy) {
        if(!this.hasAgreement(agreement.getAgreementRef())) {
            this.agreements.add(agreement);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteAgreement(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasAgreement(ref)) {
            AgreementInterface agreement = this.getAgreement(ref);
            if(!agreement.hasBeenModified()) {
                agreements.remove(agreement);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * 
     * @param account
     * @param modifiedBy 
     */
    public void createAccount(AccountInterface account, ModifiedByInterface modifiedBy) {
        if(!this.hasAccount(account.getAccRef())) {
            this.accounts.add(account);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteAccount(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasAccount(ref)) {
            AccountInterface account = this.getAccount(ref);
            if(!account.hasBeenModified()) {
                accounts.remove(account);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * 
     * @param startDate
     * @param modifiedBy 
     */
    public void setStartDate(Date startDate, ModifiedByInterface modifiedBy) {
        this.startDate = startDate;
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param endDate
     * @param modifiedBy 
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if (endDate == null || this.canCloseOffice() && endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return officeCode
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * @return address
     */
    @Override
    public AddressInterface getAddress() {
        return address;
    }
    
    /**
     * 
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }
    
    /**
     * 
     * @return endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }
    
    @Override
    public boolean hasNote(int ref) {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Note getNote(int ref) {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getRef() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Note> getNotes() {
        return Collections.unmodifiableList(this.notes);
    }
    
    @Override
    public boolean hasAgreement(int ref) {
        if(!agreements.isEmpty()) {
            for(AgreementInterface agreement : agreements) {
                if(agreement.getAgreementRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public AgreementInterface getAgreement(int ref) {
        if(this.hasAgreement(ref)) {
            for (AgreementInterface agreement : agreements) {
                if(agreement.getAgreementRef() == ref) {
                    return agreement;
                }
            }
        }
        return null;
    }

    /**
     * @return contacts
     */
    @Override
    public List<AgreementInterface> getAgreements() {
        return Collections.unmodifiableList(agreements);
    }
    
    @Override
    public boolean hasAccount(int ref) {
        if(!accounts.isEmpty()) {
            for(AccountInterface account : accounts) {
                if(account.getAccRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public AccountInterface getAccount(int ref) {
        if(this.hasAccount(ref)) {
            for (AccountInterface account : accounts) {
                if(account.getAccRef() == ref) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * @return contacts
     */
    @Override
    public List<AccountInterface> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
    
    @Override
    public ContactInterface getContact(int ref) {
        if(this.hasContact(ref)) {
            for(ContactInterface contact : contacts) {
                if(contact.getContactRef() == ref) {
                    return contact;
                }
            }
        }
        return null;
    }

    /**
     * @return contacts
     */
    @Override
    public List<ContactInterface> getContacts() {
        return Collections.unmodifiableList(contacts);
    }
    
    @Override
    public boolean hasContact(int contactRef) {
        if(contacts.isEmpty()) {
            return false;
        }
        for(ContactInterface contact : contacts) {
            if(contact.getContactRef() == contactRef) {
                return true;
            }
        }
        return false;
    }
    
    public boolean canCloseOffice() {
        if (agreements.isEmpty() && accounts.isEmpty()) {
            for (AgreementInterface agreement : agreements) {
                if(agreement.isCurrent()) {
                    return false;
                }
            }
            for (AccountInterface account : accounts) {
                if(account.isCurrent()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean hasAgreements() {
        return !agreements.isEmpty();
    }
    
    public boolean hasAccounts() {
        return !accounts.isEmpty();
    }
    
    /**
     * 
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
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the date a user last modified the Office
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the Office
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Office
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     * 
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * 
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}