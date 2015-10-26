
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
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * @param startDate the startDate to set
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
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * @param endDate the endDate to set
     * @param modifiedBy
     */
    @Override
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
        
    }
    
    @Override
    public void updateAccount(Date startDate, String accName, ModifiedByInterface modifiedBy) {
        if (this.isCurrent()) {
            this.setStartDate(startDate);
            this.setAccountName(accName);
            this.modifiedBy(modifiedBy);
        }

    }

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
     * @return the accRef
     */
    @Override
    public int getAccRef() {
        return this.accRef;
    }

    /**
     * @return the accountName
     */
    @Override
    public String getAccName() {
        return this.accName;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * @return the balance
     */
    @Override
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * @return the officeCode
     */
    @Override
    public String getOfficeCode() {
        return this.officeCode;
    }
    
    @Override
    public boolean isNegativeInd() {
        return this.balance < 0;
    }
    
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

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * @return the debitTransactions
     */
    @Override
    public List<TransactionInterface> getDebitTransactions() {
        return Collections.unmodifiableList(this.debitTransactions);
    }
    
    /**
     * @return the creditTransactions
     */
    @Override
    public List<TransactionInterface> getCreditTransactions() {
        return Collections.unmodifiableList(this.creditTransactions);
    }
    
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