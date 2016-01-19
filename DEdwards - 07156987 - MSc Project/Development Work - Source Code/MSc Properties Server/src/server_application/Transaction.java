/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Note;
import interfaces.TransactionInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class Transaction extends UnicastRemoteObject implements TransactionInterface {
    
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
     * @throws java.rmi.RemoteException 
     */
    public Transaction(int transactionRef, int accountRef, int fromRef, int toRef, double amount, boolean debit, Date transactionDate, Note note, String createdBy, Date createdDate) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getTransactionRef() throws RemoteException {
        return this.transactionRef;
    }
    
    /**
     * @return accountRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getAccountRef() throws RemoteException {
        return this.accountRef;
    }

    /**
     * @return fromRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getFromRef() throws RemoteException {
        return fromRef;
    }

    /**
     * @return toRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getToRef() throws RemoteException {
        return toRef;
    }

    /**
     * @return amount
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getAmount() throws RemoteException {
        return amount;
    }

    /**
     * @return transactionDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getTransactionDate() throws RemoteException {
        return transactionDate;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public Note getNote() throws RemoteException {
        return note;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String getComment() throws RemoteException {
        return note.getNote();
    }

    /**
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }

    /**
     * @return debit
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isDebit() throws RemoteException {
        return debit;
    }
    
    /**
     * 
     * @return String representation of the Transaction
     */
    @Override
    public String toString() {
        try {
            String temp = "\nAccount Ref: " + this.getAccountRef() + "\nFrom Ref: " + this.getFromRef() +
                    "\nTo Ref: " + this.getToRef() + "\nAmount: " + this.getAmount() + "\nTransaction Date: " +
                    this.getTransactionDate() + "\nCreated By: " + this.createdBy + "\nCreated Date: " + this.createdDate;
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}