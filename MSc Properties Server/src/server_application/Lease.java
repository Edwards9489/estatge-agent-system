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
    
    private ArrayList<LandlordInterface> landlords;
    private final PropertyInterface property;
    private final boolean fullManagement; // indicates if MSc Properties will manage all of the managerial affairs such as damage to prop, or just renting
    private final double expenditure; // expenditure to landlord(s)
    
    ///   CONSTRUCTORS ///
    
    public Lease(int leaseRef, Date startDate, int length, String createdBy, Property property,
            boolean management, double expenditure, String officeCode) {
        super(leaseRef, property.getAddress().toString(), startDate, length, createdBy, officeCode);
        this.property = property;
        this.fullManagement = management;
        this.expenditure = expenditure;
    }
    
    
    
    ///   MUTATOR METHODS   ///

    @Override
    public void addLandlord(LandlordInterface landlord, ModifiedByInterface modifiedBy) {
        if(!isAlreadyLandlord(landlord)) {
            landlords.add(landlord);
            property.setLandlords(landlords, modifiedBy);
        }
    }
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return the landlords
     */
    @Override
    public List getLandlords() {
        return Collections.unmodifiableList(landlords);
    }

    /**
     * @return the property
     */
    @Override
    public PropertyInterface getProperty() {
        return property;
    }
    
    /**
     * @return the property ref
     */
    @Override
    public int getPropertyRef() {
        return property.getPropRef();
    }

    /**
     * @return the fullManagement
     */
    @Override
    public boolean isFullManagement() {
        return fullManagement;
    }
    
    @Override
    public boolean isAlreadyLandlord(LandlordInterface landlord) {
        boolean answer = false;
        if (!landlords.isEmpty()) {
            for (LandlordInterface temp : landlords) {
                if (temp.getLandlordRef() == landlord.getLandlordRef()) {
                    answer = true;
                }
            }
        }
        return answer;
    }

    /**
     * @return the expenditure
     */
    @Override
    public double getExpenditure() {
        return expenditure;
    }
}