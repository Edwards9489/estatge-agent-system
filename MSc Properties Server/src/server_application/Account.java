
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
    private final String createdBy;
    private final Date createdDate;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final ArrayList<TransactionInterface> debitTransactions;
    private final ArrayList<TransactionInterface> creditTransactions;
    
    ///   CONSTRUCTORS ///

    public Account(int accRef, String accName, Date startDate, String createdBy) {
        this.accRef = accRef;
        this.accName = accName;
        this.startDate = startDate;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
        debitTransactions = new ArrayList<>();
        creditTransactions = new ArrayList<>();
    }
    
    
    
    ///   MUTATOR METHODS   ///

    /**
     * @param balance the balance to set
     */
    private void setBalance(double balance) {
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
    
    /**
     * @param endDate the endDate to set
     */
    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public void updateAccount(Date startDate, String accName) {
        setStartDate(startDate);
        setAccountName(accName);
    }
    
    public void createTransaction(TransactionInterface transaction) {
        if(transaction.isDebit()) {
            debitTransactions.add(transaction);
            setBalance(balance + transaction.getAmount());
        }
        else {
            creditTransactions.add(transaction);
            setBalance(balance - transaction.getAmount());
        }
    }
    
    public void modifiedBy(ModifiedBy modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
     
     /**
     * @return the accRef
     */
    @Override
    public int getAccRef() {
        return accRef;
    }

    /**
     * @return the accountName
     */
    @Override
    public String getAccName() {
        return accName;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the balance
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * @return the positiveInd
     */
    @Override
    public boolean isPositiveInd() {
        return balance > 0;
    }
    
    @Override
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @return the debitTransactions
     */
    @Override
    public List getDebitTransactions() {
        return Collections.unmodifiableList(debitTransactions);
    }
    
    /**
     * @return the creditTransactions
     */
    @Override
    public List getCreditTransactions() {
        return Collections.unmodifiableList(creditTransactions);
    }
}