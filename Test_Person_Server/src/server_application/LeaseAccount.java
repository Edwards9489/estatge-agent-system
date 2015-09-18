/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class LeaseAccount extends Account{
    private Lease lease;
    
    public LeaseAccount(int leaseAccRef, String name, Date leaseAccStart, Lease lease, String createdBy) {
        super(leaseAccRef, name, leaseAccStart, createdBy);
        this.lease = lease;
    }

    /**
     * @return the lease
     */
    public Lease getLease() {
        return lease;
    }

    /**
     * @param lease the lease to set
     */
    public void setLease(Lease lease) {
        this.lease = lease;
    }
}