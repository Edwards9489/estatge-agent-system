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
     */
    public Lease(int leaseRef, Date startDate, int length, int accountRef, Property property, boolean management, double expenditure, String officeCode, String createdBy, Date createdDate) {
        super(leaseRef, property.getAddress().toString(), startDate, length, accountRef, createdBy, createdDate, officeCode);
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
     */
    public void addLandlord(LandlordInterface landlord, ModifiedByInterface modifiedBy) {
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
     */
    public void endLandlord(int landlordRef, ModifiedByInterface modifiedBy) {
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
     */
    @Override
    public List<LandlordInterface> getLandlords() {
        return Collections.unmodifiableList(landlords);
    }

    /**
     * @return property
     */
    @Override
    public PropertyInterface getProperty() {
        return property;
    }
    
    /**
     * @return ref of property
     */
    @Override
    public int getPropertyRef() {
        return property.getPropRef();
    }

    /**
     * @return fullManagement
     */
    @Override
    public boolean isFullManagement() {
        return fullManagement;
    }
    
    /**
     * 
     * @param landlordRef
     * @return true if landlords contain a landlord with ref == landlordRef
     */
    @Override
    public boolean isAlreadyLandlord(int landlordRef) {
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
     */
    @Override
    public double getExpenditure() {
        return expenditure;
    }
}