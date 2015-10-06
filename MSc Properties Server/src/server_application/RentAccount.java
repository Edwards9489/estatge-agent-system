/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class RentAccount extends Account implements RentAccountInterface {
    
    ///   VARIABLES   ///
    
    private double rent;
    private final TenancyInterface tenancy;
    
    ///   CONSTRUCTORS ///
    
    public RentAccount(int rentAccRef, String name, Date rentAccStart, double rent, TenancyInterface tenancy, String createdBy) {
        super(rentAccRef, name, rentAccStart, createdBy);
        this.tenancy = tenancy;
        this.rent = rent;
        
        //create processTransactions which calculates the transactions due from start date
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param rent the rent to set
     */
    @Override
    public void setRent(double rent) {
        this.rent = rent;
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the rent
     */
    @Override
    public double getRent() {
        return rent;
    }

    /**
     * @return the tenancy
     */
    @Override
    public TenancyInterface getTenancy() {
        return tenancy;
    }
    
    @Override
    public int getTenancyRef() {
        return tenancy.getAgreementRef();
    }
}