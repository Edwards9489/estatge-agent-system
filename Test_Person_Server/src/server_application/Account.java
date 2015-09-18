/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

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
    private ArrayList<Transaction> debitTransactions;
    private ArrayList<Transaction> creditTransactions;
    
    

    public Account(int accRef, String accName, Date startDate, String createdBy) {
        this.accRef = accRef;
        this.accName = accName;
        this.startDate = startDate;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        debitTransactions = new ArrayList<>();
        creditTransactions = new ArrayList<>();
        //create processTransactions which calculates the debitTransactions due from start date
    }

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
     * @param accountName the accountName to set
     */
    public void setAccountName(String accName) {
        this.accName = accName;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
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
    
    public void createTransaction(Transaction transaction) {
        if(transaction.isDebit()) {
            debitTransactions.add(transaction);
        }
        else {
            creditTransactions.add(transaction);
        }
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