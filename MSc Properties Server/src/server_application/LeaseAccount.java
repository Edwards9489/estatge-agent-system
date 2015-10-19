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
    
    public LeaseAccount(int leaseAccRef, LeaseInterface lease, String createdBy, Date createdDate) {
        super(leaseAccRef, lease.getAgreementName(), lease.getOfficeCode(), lease.getStartDate(), createdBy, createdDate);
        this.lease = lease;
        this.expenditure = lease.getExpenditure();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    @Override
    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the lease
     */
    @Override
    public LeaseInterface getLease() {
        return lease;
    }
    
    @Override
    public int getLeaseRef() {
        return lease.getAgreementRef();
    }
    
    @Override
    public double getExpenditure() {
        return this.expenditure;
    }
}