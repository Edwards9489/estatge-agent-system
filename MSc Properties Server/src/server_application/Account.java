
package server_application;

import interfaces.AccountInterface;
import interfaces.Document;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.TransactionInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class Account extends UnicastRemoteObject implements AccountInterface {
    
    ///   VARIABLES   ///
    
    private final int accRef;
    private String accName;
    private Date startDate;
    private Date endDate;
    private double balance;
    private final String officeCode;
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;
    private final List<TransactionInterface> transactions;
    private final List<Note> notes;
    private final ArrayList<Document> documents;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Account
     * @param accRef
     * @param accName
     * @param officeCode
     * @param createdBy
     * @param startDate
     * @param createdDate
     * @throws java.rmi.RemoteException
     */
    public Account(int accRef, String accName, String officeCode, Date startDate, String createdBy, Date createdDate) throws RemoteException {
        this.accRef = accRef;
        this.accName = accName;
        this.startDate = startDate;
        this.officeCode = officeCode;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.transactions = new ArrayList();
        this.notes = new ArrayList();
        this.documents = new ArrayList();
    }
    
    
    
    ///   MUTATOR METHODS   ///

    /**
     * @param balance
     */
    private void setBalance(double balance) {
        this.balance = balance;
    }
    
    /**
     * @param startDate
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param accName
     */
    private void setAccountName(String accName) {
        this.accName = accName;
    }
    
    /**
     * @param modifiedBy
     */
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
        if(endDate == null || endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param startDate
     * @param accName
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void updateAccount(Date startDate, String accName, ModifiedByInterface modifiedBy) throws RemoteException {
        if (this.isCurrent()) {
            this.setStartDate(startDate);
            this.setAccountName(accName);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param transaction
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void createTransaction(TransactionInterface transaction, ModifiedByInterface modifiedBy) throws RemoteException {
        if (this.isCurrent()) {
            transactions.add(transaction);
            this.setBalance(this.balance + transaction.getAmount());
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param ref
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void deleteTransaction(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasTransaction(ref)) {
            TransactionInterface transaction = this.getTransaction(ref);
            this.transactions.remove(transaction);
            this.setBalance(this.balance - transaction.getAmount());
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(note.getRef())) {
            notes.add(note);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    public void createDocument(Document document, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasDocument(document.getDocumentRef())) {
            documents.add(document);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteDocument(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasDocument(ref)) {
            documents.remove(this.getDocument(ref));
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
     
     /**
     * @return accRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getAccRef() throws RemoteException {
        return this.accRef;
    }

    /**
     * @return accName
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getAccName() throws RemoteException {
        return this.accName;
    }

    /**
     * @return startDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getStartDate() throws RemoteException {
        return this.startDate;
    }

    /**
     * @return endDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getEndDate() throws RemoteException {
        return this.endDate;
    }

    /**
     * @return balance
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getBalance() throws RemoteException {
        return this.balance;
    }
    
    /**
     * @return officeCode
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getOfficeCode() throws RemoteException {
        return this.officeCode;
    }
    
    /**
     * @return true if the account balance < 0
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isNegativeInd() throws RemoteException {
        return this.balance < 0;
    }
    
    /**
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
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    @Override
    public boolean hasTransaction(int ref) throws RemoteException {
        if(!transactions.isEmpty()) {
            for(TransactionInterface transaction : transactions) {
                if(transaction.getTransactionRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean hasNote(int ref) throws RemoteException {
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
    public Note getNote(int ref) throws RemoteException {
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
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
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
                if(fileName.equals(document.getDocumentName())) {
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
    public TransactionInterface getTransaction(int ref) throws RemoteException {
        if(this.hasTransaction(ref)) {
            for(TransactionInterface transaction : transactions) {
                if(transaction.getTransactionRef() == ref) {
                    return transaction;
                }
            }
        }
        return null;
    }
    
    /**
     * @return the name of the last user to modify the account
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the last date the account was modified
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
     * @return the list of modifiedBy objects
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the account
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
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }
    
    /**
     * @return creditTransactions
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<TransactionInterface> getTransactions() throws RemoteException {
        return Collections.unmodifiableList(this.transactions);
    }
    
    /**
     * @return String representation of Account
     */
    @Override
    public String toString() {
        try {
            String temp = "\n\nAccount Ref: " + this.getAccRef() + "\nAccount Name: " + this.getAccName() +
                    "\nAccount Balance: " + this.getBalance() + "\nStart Date: " + this.getStartDate() +
                    "\nEnd Date: " + this.getEndDate() + "\nCreated By: " + this.createdBy + "\nCreated Date: " +
                    this.createdDate + "\nIs Account Current: " + isCurrent() + "\nIs Account in Arrears: " +
                    isNegativeInd() + "\n\nModifed By\n" + this.getModifiedBy() + "\n\nTransactions\n" +
                    this.getTransactions();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
}