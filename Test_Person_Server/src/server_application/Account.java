
package server_application;

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
public class Account {
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
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @param balance the balance to set
     */
    private void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param accName
     */
    public void setAccountName(String accName) {
        this.accName = accName;
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
    
    
    ///   ACCESSOR METHODS   ///
     
     /**
     * @return the accRef
     */
    public int getAccRef() {
        return accRef;
    }

    /**
     * @return the accountName
     */
    public String getAccName() {
        return accName;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @return the positiveInd
     */
    public boolean isPositiveInd() {
        return balance > 0;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @return the debitTransactions
     */
    public List getDebitTransactions() {
        return Collections.unmodifiableList(debitTransactions);
    }
    
    /**
     * @return the creditTransactions
     */
    public List getCreditTransactions() {
        return Collections.unmodifiableList(creditTransactions);
    }
}