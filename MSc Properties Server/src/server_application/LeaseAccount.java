/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class LeaseAccount extends Account implements LeaseAccountInterface {
    
    ///   VARIABLES   ///
    
    private final LeaseInterface lease;
    private double expenditure;
    
    ///   CONSTRUCTORS ///
    
    /**
     * 
     * @param leaseAccRef
     * @param lease
     * @param createdBy
     * @param createdDate 
     */
    public LeaseAccount(int leaseAccRef, LeaseInterface lease, String createdBy, Date createdDate) {
        super(leaseAccRef, lease.getAgreementName(), lease.getOfficeCode(), lease.getStartDate(), createdBy, createdDate);
        this.lease = lease;
        this.expenditure = lease.getExpenditure();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * 
     * @param expenditure 
     */
    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return lease
     */
    @Override
    public LeaseInterface getLease() {
        return lease;
    }
    
    /**
     * 
     * @return ref of lease
     */
    @Override
    public int getLeaseRef() {
        return lease.getAgreementRef();
    }
    
    /**
     * 
     * @return expenditure
     */
    @Override
    public double getExpenditure() {
        return this.expenditure;
    }
}