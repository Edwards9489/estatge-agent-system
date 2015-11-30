/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.rmi.RemoteException;
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
     * @throws java.rmi.RemoteException 
     */
    public RentAccount(int rentAccRef, TenancyInterface tenancy, String createdBy, Date createdDate) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getRent() throws RemoteException {
        return rent;
    }

    /**
     * @return tenancy
     * @throws java.rmi.RemoteException
     */
    @Override
    public TenancyInterface getTenancy() throws RemoteException {
        return tenancy;
    }
    
    /**
     * 
     * @return ref of tenancy
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getTenancyRef() throws RemoteException {
        return tenancy.getAgreementRef();
    }
}