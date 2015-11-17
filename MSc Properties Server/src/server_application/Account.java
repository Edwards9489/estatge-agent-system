
package server_application;

import interfaces.AccountInterface;
import interfaces.ModifiedByInterface;
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
    private final List<TransactionInterface> debitTransactions;
    private final List<TransactionInterface> creditTransactions;
    
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
        this.debitTransactions = new ArrayList<>();
        this.creditTransactions = new ArrayList<>();
    }
    
    
    
    ///   MUTATOR METHODS   ///

    /**
     * @param balance
     */
    public void setBalance(double balance) {
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
            if (transaction.isDebit()) {
                this.debitTransactions.add(transaction);
                this.setBalance(this.balance + transaction.getAmount());
            } else {
                this.creditTransactions.add(transaction);
                this.setBalance(this.balance - transaction.getAmount());
            }
            this.modifiedBy(modifiedBy);
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
     * @return debitTransactions
     */
    @Override
    public List<TransactionInterface> getDebitTransactions() {
        return Collections.unmodifiableList(this.debitTransactions);
    }
    
    /**
     * @return creditTransactions
     */
    @Override
    public List<TransactionInterface> getCreditTransactions() {
        return Collections.unmodifiableList(this.creditTransactions);
    }
    
    /**
     * @return a list of date ordered transactions
     */
    @Override
    public List<TransactionInterface> getDateOrderedTransactions() {
        // Code to return an ordered List of Transactions
        List<TransactionInterface> transactions = new ArrayList();
        List<TransactionInterface> debits = this.getDebitTransactions();
        List<TransactionInterface> credits = this.getDebitTransactions();
        int i = 0;
        int ind = 0;
        
        if (!debits.isEmpty() && !credits.isEmpty()) {
            while (i < debits.size()) {
                TransactionInterface debit = debits.get(i);
                while (i < debits.size()) {
                    TransactionInterface credit = credits.get(ind);
                    if (debit.getTransactionDate().before(credit.getTransactionDate())) {
                        transactions.add(debit);
                        i++;
                    } else {
                        transactions.add(credit);
                        ind++;
                    }
                }
            }
            return transactions;
        } else if(!debits.isEmpty() && credits.isEmpty()) {
            transactions = debits;
            return transactions;
        } else if(debits.isEmpty() && !credits.isEmpty()) {
            transactions = credits;
            return transactions;
        }
        return transactions;
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
                isNegativeInd() + "\n\nModifed By\n" + this.getModifiedBy() + "\n\nCredit Transactions\n" +
                this.getCreditTransactions() + "\n\nDebit Transactions\n" + this.getDebitTransactions();
        return temp;
    }
}