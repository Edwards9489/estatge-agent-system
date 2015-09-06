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
    private final int rent_acc_ref;
    private String account_name;
    private Date rent_acc_start;
    private Date rent_acc_end;
    private double net_rent;
    private double balance;
    private Tenancy tenancy;
    private boolean arrears_ind;
    private String createdBy;
    private Date createdDate;
    private HashMap<String, Transaction> transactions;
    
    

    public RentAccount(int rent_ref, String name, Date rentAccStart, Tenancy tenancy, String createdBy) {
        rent_acc_ref = rent_ref;
        arrears_ind = false;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        //create processTransactions which calculates the transactions due from start date
    }

    /**
     * @return the rent_acc_ref
     */
    public int getRent_acc_ref() {
        return rent_acc_ref;
    }

    /**
     * @return the account_name
     */
    public String getAccount_name() {
        return account_name;
    }

    /**
     * @param account_name the account_name to set
     */
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    /**
     * @return the rent_acc_start
     */
    public Date getRent_acc_start() {
        return rent_acc_start;
    }

    /**
     * @param rent_acc_start the rent_acc_start to set
     */
    public void setRent_acc_start(Date rent_acc_start) {
        this.rent_acc_start = rent_acc_start;
    }

    /**
     * @return the rent_acc_end
     */
    public Date getRent_acc_end() {
        return rent_acc_end;
    }

    /**
     * @param rent_acc_end the rent_acc_end to set
     */
    public void setRent_acc_end(Date rent_acc_end) {
        this.rent_acc_end = rent_acc_end;
    }

    /**
     * @return the net_rent
     */
    public double getNet_rent() {
        return net_rent;
    }

    /**
     * @param net_rent the net_rent to set
     */
    public void setNet_rent(double net_rent) {
        this.net_rent = net_rent;
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
     * @return the arrears_ind
     */
    public boolean isArrears_ind() {
        return arrears_ind;
    }

    /**
     * @param arrears_ind the arrears_ind to set
     */
    public void setArrears_ind(boolean arrears_ind) {
        this.arrears_ind = arrears_ind;
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