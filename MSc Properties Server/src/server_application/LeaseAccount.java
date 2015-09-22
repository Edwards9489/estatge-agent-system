/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;

/**
 *
 * @author Dwayne
 */
public class LeaseAccount extends Account implements LeaseAccountInterface {
    
    ///   VARIABLES   ///
    
    private final LeaseInterface lease;
    
    ///   CONSTRUCTORS ///
    
    public LeaseAccount(int leaseAccRef, LeaseInterface lease, String createdBy) {
        super(leaseAccRef, lease.getAgreementName(), lease.getStartDate(), createdBy);
        this.lease = lease;
    }    
    
    
    
    ///   MUTATOR METHODS   ///
    
        
    
    
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
}