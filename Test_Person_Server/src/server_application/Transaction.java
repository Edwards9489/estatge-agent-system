/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Transaction {
    
    private final int accountRef;
    private int fromRef;
    private int toRef;
    private double amount;
    private final boolean debit;
    private Date transactionDate;
    private final String createdBy;
    private final Date createdDate;
    
    public Transaction(int accountRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String createdBy) {
        this.accountRef = accountRef;
        this.fromRef = fromRef;
        this.toRef = toRef;
        this.amount = amount;
        this.debit = debit;
        this.transactionDate = transactionDate;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }

    /**
     * @return the accountRef
     */
    public int getAccountRef() {
        return accountRef;
    }

    /**
     * @return the fromRef
     */
    public int getFromRef() {
        return fromRef;
    }

    /**
     * @param fromRef the fromRef to set
     */
    public void setFromRef(int fromRef) {
        this.fromRef = fromRef;
    }

    /**
     * @return the toRef
     */
    public int getToRef() {
        return toRef;
    }

    /**
     * @param toRef the toRef to set
     */
    public void setToRef(int toRef) {
        this.toRef = toRef;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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
     * @return the debit
     */
    public boolean isDebit() {
        return debit;
    }
}