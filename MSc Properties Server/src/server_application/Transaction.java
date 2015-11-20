/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Note;
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
    private final Note note;
    private final Date transactionDate;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Transaction
     * @param transactionRef
     * @param accountRef
     * @param fromRef
     * @param toRef
     * @param amount
     * @param debit
     * @param transactionDate
     * @param note
     * @param createdBy
     * @param createdDate 
     */
    public Transaction(int transactionRef, int accountRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, Note note, String createdBy, Date createdDate) {
        this.transactionRef = transactionRef;
        this.accountRef = accountRef;
        this.fromRef = fromRef;
        this.toRef = toRef;
        this.amount = amount;
        this.debit = debit;
        this.transactionDate = transactionDate;
        this.note = note;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return transactionRef
     */
    @Override
    public int getTransactionRef() {
        return this.transactionRef;
    }
    
    /**
     * @return accountRef
     */
    @Override
    public int getAccountRef() {
        return this.accountRef;
    }

    /**
     * @return fromRef
     */
    @Override
    public int getFromRef() {
        return fromRef;
    }

    /**
     * @return toRef
     */
    @Override
    public int getToRef() {
        return toRef;
    }

    /**
     * @return amount
     */
    @Override
    public double getAmount() {
        return amount;
    }

    /**
     * @return transactionDate
     */
    @Override
    public Date getTransactionDate() {
        return transactionDate;
    }
    
    @Override
    public Note getNote() {
        return note;
    }
    
    @Override
    public String getComment() {
        return note.getNote();
    }

    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @return debit
     */
    @Override
    public boolean isDebit() {
        return debit;
    }
    
    /**
     * 
     * @return String representation of the Transaction
     */
    @Override
    public String toString() {
        String temp = "\nAccount Ref: " + this.getAccountRef() + "\nFrom Ref: " + this.getFromRef() +
                "\nTo Ref: " + this.getToRef() + "\nAmount: " + this.getAmount() + "\nTransaction Date: " +
                this.getTransactionDate() + "\nCreated By: " + this.createdBy + "\nCreated Date: " + this.createdDate;
        return temp;
    }
}