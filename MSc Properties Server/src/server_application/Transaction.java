/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.TransactionInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Transaction implements TransactionInterface {
    
    ///   VARIABLES   ///
    
    private final int transactionRef;
    private final int accountRef;
    private final int fromRef;
    private final int toRef;
    private final double amount;
    private final boolean debit;
    private final Date transactionDate;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public Transaction(int transactionRef, int accountRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, String createdBy, Date createdDate) {
        this.transactionRef = transactionRef;
        this.accountRef = accountRef;
        this.fromRef = fromRef;
        this.toRef = toRef;
        this.amount = amount;
        this.debit = debit;
        this.transactionDate = transactionDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the transactionRef
     */
    @Override
    public int getTransactionRef() {
        return this.transactionRef;
    }
    
    /**
     * @return the accountRef
     */
    @Override
    public int getAccountRef() {
        return this.accountRef;
    }

    /**
     * @return the fromRef
     */
    @Override
    public int getFromRef() {
        return fromRef;
    }

    /**
     * @return the toRef
     */
    @Override
    public int getToRef() {
        return toRef;
    }

    /**
     * @return the amount
     */
    @Override
    public double getAmount() {
        return amount;
    }

    /**
     * @return the transactionDate
     */
    @Override
    public Date getTransactionDate() {
        return transactionDate;
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
     * @return the debit
     */
    @Override
    public boolean isDebit() {
        return debit;
    }
    
    @Override
    public String toString() {
        String temp = "\nAccount Ref: " + this.getAccountRef() + "\nFrom Ref: " + this.getFromRef() +
                "\nTo Ref: " + this.getToRef() + "\nAmount: " + this.getAmount() + "\nTransaction Date: " +
                this.getTransactionDate() + "\nCreated By: " + this.createdBy + "\nCreated Date: " + this.createdDate;
        return temp;
    }
}