/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.LandlordInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Lease extends Agreement {
    private ArrayList<LandlordInterface> landlords;
    private final Property property;
    private final boolean fullManagement; // indicates if MSc Properties will manage all of the managerial affairs such as damage to prop, or just renting
    private final double expenditure; // expenditure to landlord(s)
    
    public Lease(int leaseRef, Date startDate, int length, String createdBy, Property property, boolean management, double expenditure) {
        super(leaseRef, startDate, length, createdBy);
        this.property = property;
        this.fullManagement = management;
        this.expenditure = expenditure;
    }

    /**
     * @return the landlords
     */
    public List getLandlords() {
        return Collections.unmodifiableList(landlords);
    }
    
    public void addLandlord(LandlordInterface landlord) {
        if(!isAlreadyLandlord(landlord)) {
            landlords.add(landlord);
            property.setLandlords(landlords);
        }
    }

    /**
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * @return the fullManagement
     */
    public boolean isFullManagement() {
        return fullManagement;
    }
    
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
    public double getExpenditure() {
        return expenditure;
    }
}