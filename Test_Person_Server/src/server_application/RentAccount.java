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
public class RentAccount extends Account {
    private double rent;
    private Tenancy tenancy;
    
    

    public RentAccount(int rentAccRef, String name, Date rentAccStart, double rent, Tenancy tenancy, String createdBy) {
        super(rentAccRef, name, rentAccStart, createdBy);
        this.tenancy = tenancy;
        this.rent = rent;
        
        //create processTransactions which calculates the transactions due from start date
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
}