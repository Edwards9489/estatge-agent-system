/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class RentAccount extends Account implements RentAccountInterface {
    
    ///   VARIABLES   ///
    
    private double rent;
    private final TenancyInterface tenancy;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class RentAccount
     * @param rentAccRef
     * @param tenancy
     * @param createdBy
     * @param createdDate 
     */
    public RentAccount(int rentAccRef, TenancyInterface tenancy, String createdBy, Date createdDate) {
        super(rentAccRef, tenancy.getAgreementName(), tenancy.getOfficeCode(), tenancy.getStartDate(), createdBy, createdDate);
        this.tenancy = tenancy;
        this.rent = tenancy.getRent() + tenancy.getCharges();
        //create processTransactions which calculates the transactions due from start date
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param rent
     */
    public void setRent(double rent) {
        this.rent = rent;
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return rent
     */
    @Override
    public double getRent() {
        return rent;
    }

    /**
     * @return tenancy
     */
    @Override
    public TenancyInterface getTenancy() {
        return tenancy;
    }
    
    /**
     * 
     * @return ref of tenancy
     */
    @Override
    public int getTenancyRef() {
        return tenancy.getAgreementRef();
    }
}