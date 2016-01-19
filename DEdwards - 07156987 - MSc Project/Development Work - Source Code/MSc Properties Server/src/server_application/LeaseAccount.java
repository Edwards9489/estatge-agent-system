/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class LeaseAccount extends Account implements LeaseAccountInterface {
    
    ///   VARIABLES   ///
    
    private final LeaseInterface lease;
    private final double expenditure;
    
    ///   CONSTRUCTORS ///
    
    /**
     * 
     * @param leaseAccRef
     * @param lease
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public LeaseAccount(int leaseAccRef, LeaseInterface lease, String createdBy, Date createdDate) throws RemoteException {
        super(leaseAccRef, lease.getAgreementName(), lease.getOfficeCode(), lease.getStartDate(), createdBy, createdDate);
        this.lease = lease;
        this.expenditure = lease.getExpenditure();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return lease
     * @throws java.rmi.RemoteException
     */
    @Override
    public LeaseInterface getLease() throws RemoteException {
        return lease;
    }
    
    /**
     * 
     * @return ref of lease
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getLeaseRef() throws RemoteException {
        return lease.getAgreementRef();
    }
    
    /**
     * 
     * @return expenditure
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getExpenditure() throws RemoteException {
        return this.expenditure;
    }
}