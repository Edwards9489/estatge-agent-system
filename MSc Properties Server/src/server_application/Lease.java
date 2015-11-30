/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.LandlordInterface;
import interfaces.LeaseInterface;
import interfaces.ModifiedByInterface;
import interfaces.PropertyInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Lease extends Agreement implements LeaseInterface {
    
    ///   VARIABLES   ///
    
    private final List<LandlordInterface> landlords;
    private final PropertyInterface property;
    private final boolean fullManagement; // indicates if MSc Properties will manage all of the managerial affairs such as damage to prop, or just renting
    private final double expenditure; // money to landlord(s)
    
    ///   CONSTRUCTORS ///
    
    /**
     * 
     * @param leaseRef
     * @param startDate
     * @param length
     * @param accountRef
     * @param property
     * @param management
     * @param expenditure
     * @param officeCode
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public Lease(int leaseRef, Date startDate, int length, int accountRef, Property property, boolean management, double expenditure, String officeCode, String createdBy, Date createdDate) throws RemoteException {
        super(leaseRef, property.getAddress().printAddress(), startDate, length, accountRef, createdBy, createdDate, officeCode);
        this.landlords = new ArrayList();
        this.property = property;
        this.fullManagement = management;
        this.expenditure = expenditure;
    }
    
    
    
    ///   MUTATOR METHODS   ///

    /**
     * 
     * @param landlord
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void addLandlord(LandlordInterface landlord, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!isAlreadyLandlord(landlord.getLandlordRef())) {
            landlords.add(landlord);
            Property temp = (Property) this.getProperty();
            temp.setLandlords(landlords, modifiedBy);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * 
     * @param landlordRef
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void endLandlord(int landlordRef, ModifiedByInterface modifiedBy) throws RemoteException {
        if(isAlreadyLandlord(landlordRef)) {
            landlords.remove(landlordRef);
            Property temp = (Property) this.getProperty();
            temp.setLandlords(landlords, modifiedBy);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return landlords
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<LandlordInterface> getLandlords() throws RemoteException {
        return Collections.unmodifiableList(landlords);
    }

    /**
     * @return property
     * @throws java.rmi.RemoteException
     */
    @Override
    public PropertyInterface getProperty() throws RemoteException {
        return property;
    }
    
    /**
     * @return ref of property
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPropertyRef() throws RemoteException {
        return property.getPropRef();
    }

    /**
     * @return fullManagement
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isFullManagement() throws RemoteException {
        return fullManagement;
    }
    
    /**
     * 
     * @param landlordRef
     * @return true if landlords contain a landlord with ref == landlordRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isAlreadyLandlord(int landlordRef) throws RemoteException {
        if (!landlords.isEmpty()) {
            for (LandlordInterface temp : landlords) {
                if (temp.getLandlordRef() == landlordRef) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return expenditure
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getExpenditure() throws RemoteException {
        return expenditure;
    }
}