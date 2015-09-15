/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class RentAccount {
    // instance variables - replace the example below with your own
    private final int rentAccRef;
    private String accountName;
    private Date rentAccStart;
    private Date rentAccEnd;
    private double rent;
    private double balance;
    private Tenancy tenancy;
    private boolean arrearsInd;
    private String createdBy;
    private Date createdDate;
    private HashMap<String, Transaction> transactions;
    
    

    public RentAccount(int rent_ref, String name, Date rentAccStart, Tenancy tenancy, String createdBy) {
        rentAccRef = rent_ref;
        arrearsInd = false;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        //create processTransactions which calculates the transactions due from start date
    }

    /**
     * @return the rentAccRef
     */
    public int getRentAccRef() {
        return rentAccRef;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the rentAccStart
     */
    public Date getRent_acc_start() {
        return rentAccStart;
    }

    /**
     * @param rent_acc_start the rentAccStart to set
     */
    public void setRent_acc_start(Date rent_acc_start) {
        this.rentAccStart = rent_acc_start;
    }

    /**
     * @return the rentAccEnd
     */
    public Date getRentAccEnd() {
        return rentAccEnd;
    }

    /**
     * @param rentAccEnd the rentAccEnd to set
     */
    public void setRentAccEnd(Date rentAccEnd) {
        this.rentAccEnd = rentAccEnd;
    }

    /**
     * @return the rent
     */
    public double getRent() {
        return rent;
    }

    /**
     * @param rent the rent to set
     */
    public void setRent(double rent) {
        this.rent = rent;
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
     * @return the tenancy
     */
    public Tenancy getTenancy() {
        return tenancy;
    }

    /**
     * @param tenancy the tenancy to set
     */
    public void setTenancy(Tenancy tenancy) {
        this.tenancy = tenancy;
    }

    /**
     * @return the arrearsInd
     */
    public boolean isArrearsInd() {
        return arrearsInd;
    }

    /**
     * @param arrearsInd the arrearsInd to set
     */
    public void setArrearsInd(boolean arrearsInd) {
        this.arrearsInd = arrearsInd;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the transactions
     */
    public HashMap<String, Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(HashMap<String, Transaction> transactions) {
        this.transactions = transactions;
    }
}