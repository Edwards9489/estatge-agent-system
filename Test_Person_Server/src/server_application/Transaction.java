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
    
    private String accountRef;
    private String fromRef;
    private String toRef;
    private double amount;
    private Date transactionDate;
    
    public Transaction(String accountRef, String fromRef, String toRef, double amount) {
        this.accountRef = accountRef;
        this.fromRef = fromRef;
        this.toRef = toRef;
        this.amount = amount;
        this.transactionDate = new Date();
    }

    /**
     * @return the accountRef
     */
    public String getAccountRef() {
        return accountRef;
    }

    /**
     * @param accountRef the accountRef to set
     */
    public void setAccountRef(String accountRef) {
        this.accountRef = accountRef;
    }

    /**
     * @return the fromRef
     */
    public String getFromRef() {
        return fromRef;
    }

    /**
     * @param fromRef the fromRef to set
     */
    public void setFromRef(String fromRef) {
        this.fromRef = fromRef;
    }

    /**
     * @return the toRef
     */
    public String getToRef() {
        return toRef;
    }

    /**
     * @param toRef the toRef to set
     */
    public void setToRef(String toRef) {
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
}