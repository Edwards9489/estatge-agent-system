/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import classes.Utils;
import interfaces.AccountInterface;
import interfaces.AddressInterface;
import interfaces.AgreementInterface;
import interfaces.ContactInterface;
import interfaces.ContractInterface;
import interfaces.Document;
import interfaces.EmployeeAccountInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Office extends UnicastRemoteObject implements OfficeInterface {
    
    ///   VARIABLES   ///
    
    private final String officeCode;
    private final AddressInterface address;
    private final double addrLong;
    private final double addrLat;
    private Date startDate;
    private Date endDate;
    private final List<AgreementInterface> agreements;
    private final List<AccountInterface> accounts;
    private final List<ContactInterface> contacts;
    private final List<Note> notes;
    private final List<ModifiedByInterface> modifiedBy;
    private final List<Document> documents;
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
     * @throws java.rmi.RemoteException 
     */
    public Office(String officeCode, AddressInterface address, double addrLong, double addrLat, Date startDate, String createdBy, Date createdDate) throws RemoteException {
        this.officeCode = officeCode;
        this.address = address;
        this.addrLong = addrLong;
        this.addrLat = addrLat;
        this.startDate = startDate;
        this.agreements = new ArrayList();
        this.accounts = new ArrayList();
        this.contacts = new ArrayList();
        this.notes = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.documents = new ArrayList();
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
     * @throws java.rmi.RemoteException 
     */
    public void createContact(ContactInterface contact, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasContact(contact.getContactRef())) {
            this.contacts.add(contact);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteContact(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasContact(ref)) {
            ContactInterface contact = this.getContact(ref);
            if(!contact.hasBeenModified()) {
                contacts.remove(contact);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     *
     * @param note
     * @param modifiedBy
     */
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(!note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * 
     * @param agreement
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void createAgreement(AgreementInterface agreement, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasAgreement(agreement.getAgreementRef())) {
            this.agreements.add(agreement);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteAgreement(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException 
     */
    public void createAccount(AccountInterface account, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasAccount(account.getAccRef())) {
            this.accounts.add(account);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteAccount(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException 
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) throws RemoteException {
        if (endDate == null || this.canCloseOffice() && endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param document
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void createDocument(Document document, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasDocument(document.getDocumentRef())) {
            documents.add(document);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void deleteDocument(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasDocument(ref)) {
            documents.remove(this.getDocument(ref));
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return officeCode
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getOfficeCode() throws RemoteException {
        return officeCode;
    }

    /**
     * @return address
     * @throws java.rmi.RemoteException
     */
    @Override
    public AddressInterface getAddress() throws RemoteException {
        return address;
    }

    /**
     * @return addrLong
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getAddrLong() throws RemoteException {
        return addrLong;
    }

    /**
     * @return addrLat
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getAddrLat() throws RemoteException {
        return addrLat;
    }
    
    /**
     * 
     * @return startDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getStartDate() throws RemoteException {
        return startDate;
    }
    
    /**
     * 
     * @return endDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getEndDate() throws RemoteException {
        return endDate;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasNote(int ref) throws RemoteException {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getReference() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public Note getNote(int ref) throws RemoteException {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getReference() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasAgreement(int ref) throws RemoteException {
        if(!agreements.isEmpty()) {
            for(AgreementInterface agreement : agreements) {
                if(agreement.getAgreementRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public AgreementInterface getAgreement(int ref) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<AgreementInterface> getAgreements() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList(agreements));
    }
    
    /**
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<TenancyInterface> getTenancies() throws RemoteException {
        List<TenancyInterface> tenancies = new ArrayList();
        for (AgreementInterface temp : agreements) {
            if (temp instanceof TenancyInterface) {
                TenancyInterface tenancy = (TenancyInterface) temp;
                tenancies.add(tenancy);
            }
        }
        return tenancies;
    }
    
    /**
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<LeaseInterface> getLeases() throws RemoteException {
        List<LeaseInterface> leases = new ArrayList();
        for (AgreementInterface temp : agreements) {
            if (temp instanceof LeaseInterface) {
                LeaseInterface lease = (LeaseInterface) temp;
                leases.add(lease);
            }
        }
        return leases;
    }
    
    /**
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ContractInterface> getContracts() throws RemoteException {
        List<ContractInterface> contracts = new ArrayList();
        for (AgreementInterface temp : agreements) {
            if (temp instanceof ContractInterface) {
                ContractInterface contract = (ContractInterface) temp;
                contracts.add(contract);
            }
        }
        return contracts;
    }
    
    @Override
    public boolean hasAccount(int ref) throws RemoteException {
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
    public AccountInterface getAccount(int ref) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<AccountInterface> getAccounts() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList(accounts));
    }
    
    /**
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<RentAccountInterface> getRentAccounts() throws RemoteException {
        List<RentAccountInterface> rentAccs = new ArrayList();
        for (AccountInterface temp : accounts) {
            if (temp instanceof RentAccountInterface) {
                RentAccountInterface rentAcc = (RentAccountInterface) temp;
                rentAccs.add(rentAcc);
            }
        }
        return rentAccs;
    }
    
    /**
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<LeaseAccountInterface> getLeaseAccounts() throws RemoteException {
        List<LeaseAccountInterface> leaseAccs = new ArrayList();
        for (AccountInterface temp : accounts) {
            if (temp instanceof LeaseAccountInterface) {
                LeaseAccountInterface leaseAcc = (LeaseAccountInterface) temp;
                leaseAccs.add(leaseAcc);
            }
        }
        return leaseAccs;
    }
    
    /**
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<EmployeeAccountInterface> getEmployeeAccounts() throws RemoteException {
        List<EmployeeAccountInterface> empAccs = new ArrayList();
        for (AccountInterface temp : accounts) {
            if (temp instanceof EmployeeAccountInterface) {
                EmployeeAccountInterface empAccount = (EmployeeAccountInterface) temp;
                empAccs.add(empAccount);
            }
        }
        return empAccs;
    }
    
    @Override
    public ContactInterface getContact(int ref) throws RemoteException {
        if(this.hasContact(ref)) {
            for(ContactInterface contact : contacts) {
                if(contact.getContactRef() == ref) {
                    return contact;
                }
            }
        }
        return null;
    }
    
    public ContactInterface getCurrentContact(String contactTypeCode) throws RemoteException {
        if(this.hasCurrentContact(contactTypeCode)) {
            for (ContactInterface temp : contacts) {
                if (temp.isCurrent() && contactTypeCode.equals(temp.getContactType().getCode())) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * @return contacts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ContactInterface> getContacts() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList(contacts));
    }
    
    @Override
    public boolean hasContact(int contactRef) throws RemoteException {
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
    
    public boolean hasCurrentContact(String contactTypeCode) throws RemoteException {
        if(contacts.isEmpty()) {
            return false;
        } else {
            for (ContactInterface contact : contacts) {
                if (contact.isCurrent() && contactTypeCode.equals(contact.getContactType().getCode())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private boolean hasCurrentAgreement() throws RemoteException {
        if(!this.agreements.isEmpty()) {
            for (AgreementInterface agreement : this.agreements) {
                if(agreement.isCurrent()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean hasCurrentAccount() throws RemoteException {
        if(!this.accounts.isEmpty()) {
            for (AccountInterface account : this.accounts) {
                if(account.isCurrent()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean canCloseOffice() throws RemoteException {
        return !(this.hasCurrentAccount() || this.hasCurrentAgreement());
    }
    
    @Override
    public boolean hasAgreements() {
        return !agreements.isEmpty();
    }
    
    @Override
    public boolean hasAccounts() {
        return !accounts.isEmpty();
    }
    
    /**
     * 
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(this.endDate == null) {
            return true;
        }
        else {
            return this.endDate.after(new Date());
        }
    }
    
    @Override
    public boolean hasDocument(int ref) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean hasDocument(String fileName) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(Utils.compareStrings(fileName, document.getCurrentDocumentName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Document getDocument(int ref) throws RemoteException {
        if(this.hasDocument(ref)) {
            for (Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return document;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Document> getDocuments() throws RemoteException {
        return Collections.unmodifiableList(this.documents);
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the date a user last modified the Office
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the Office
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Office
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     * 
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return createdBy;
    }
    
    /**
     * 
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }
}