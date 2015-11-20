
package server_application;

import interfaces.AccountInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.TransactionInterface;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Account implements AccountInterface {
    
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
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Account
     * @param accRef
     * @param accName
     * @param officeCode
     * @param createdBy
     * @param startDate
     * @param createdDate
     */
    public Account(int accRef, String accName, String officeCode, Date startDate, String createdBy, Date createdDate) {
        this.accRef = accRef;
        this.accName = accName;
        this.startDate = startDate;
        this.officeCode = officeCode;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.transactions = new ArrayList();
        this.notes = new ArrayList();
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
     */
    public void updateAccount(Date startDate, String accName, ModifiedByInterface modifiedBy) {
        if (this.isCurrent()) {
            this.setStartDate(startDate);
            this.setAccountName(accName);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param transaction
     * @param modifiedBy
     */
    public void createTransaction(TransactionInterface transaction, ModifiedByInterface modifiedBy) {
        if (this.isCurrent()) {
            transactions.add(transaction);
            this.setBalance(this.balance + transaction.getAmount());
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param ref
     * @param modifiedBy
     */
    public void deleteTransaction(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasTransaction(ref)) {
            TransactionInterface transaction = this.getTransaction(ref);
            this.transactions.remove(transaction);
            this.setBalance(this.balance - transaction.getAmount());
            this.modifiedBy(modifiedBy);
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
    
    
    
    ///   ACCESSOR METHODS   ///
     
     /**
     * @return accRef
     */
    @Override
    public int getAccRef() {
        return this.accRef;
    }

    /**
     * @return accName
     */
    @Override
    public String getAccName() {
        return this.accName;
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
     * @return balance
     */
    @Override
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * @return officeCode
     */
    @Override
    public String getOfficeCode() {
        return this.officeCode;
    }
    
    /**
     * @return true if the account balance < 0
     */
    @Override
    public boolean isNegativeInd() {
        return this.balance < 0;
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
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    @Override
    public boolean hasTransaction(int ref) {
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
    public TransactionInterface getTransaction(int ref) {
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
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the last date the account was modified
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return the list of modifiedBy objects
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the account
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
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
     * @return creditTransactions
     */
    @Override
    public List<TransactionInterface> getTransactions() {
        return Collections.unmodifiableList(this.transactions);
    }
    
    /**
     * @return String representation of Account
     */
    @Override
    public String toString() {
        String temp = "\n\nAccount Ref: " + this.getAccRef() + "\nAccount Name: " + this.getAccName() +
                "\nAccount Balance: " + this.getBalance() + "\nStart Date: " + this.getStartDate() +
                "\nEnd Date: " + this.getEndDate() + "\nCreated By: " + this.createdBy + "\nCreated Date: " +
                this.createdDate + "\nIs Account Current: " + isCurrent() + "\nIs Account in Arrears: " +
                isNegativeInd() + "\n\nModifed By\n" + this.getModifiedBy() + "\n\nTransactions\n" +
                this.getTransactions();
        return temp;
    }
}