/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.AddressUsageInterface;
import interfaces.ApplicationInterface;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.TenancyInterface;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class Application implements ApplicationInterface {
    private final int appRef;
    private String appCorrName;
    private String appStatusCode; //indicates the status of the app, e.g. NEW, INTR, HSED, CLSD, DTE (due to end)
    private ArrayList<InvolvedPartyInterface> household = new ArrayList<>();
    private AddressUsageInterface appAddress;
    private ArrayList<Property> propertiesInterestedIn;
    private TenancyInterface tenancy;
    private final String createdBy;
    private final Date createdDate;
    
    
    
    /**
     * Constructor for objects of class Person
     */
    public Application(int appRef, ArrayList<InvolvedPartyInterface> household, AddressUsageInterface address, String corrName, String createdBy) {
        this.appRef = appRef;
        appCorrName = corrName;
        appStatusCode = "NEW";
        this.household = household;
        appAddress = address;
        propertiesInterestedIn = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    public int getApplicationRef() {
        return appRef;
    }

    /**
     * @return the appCorrName
     */
    public String getAppCorrName() {
        return appCorrName;
    }

    /**
     * @return the appStatusCode
     */
    public String getAppStatusCode() {
        return appStatusCode;
    }
    
    /**
     * @return the appInterestedFlag
     */
    public boolean isAppInterestedFlag() {
        return !propertiesInterestedIn.isEmpty();
    }
    
    private boolean isHouseholdMember(InvolvedPartyInterface party) {
        if(!household.isEmpty()) {
            for(InvolvedPartyInterface invParty : household) {
                if(invParty.equals(party)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isInterestedProperty(Property property) {
        if(!propertiesInterestedIn.isEmpty()) {
            for(Property prop : propertiesInterestedIn) {
                if(prop.equals(property)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the household
     */
    public List getHousehold() {
        return Collections.unmodifiableList(household);
    }

    /**
     * @return the propertiesIntrestedIn
     */
    public List getPropertiesInterestedIn() {
        return Collections.unmodifiableList(propertiesInterestedIn);
    }

    /**
     * @return the tenancy
     */
    public TenancyInterface getTenancy() {
        return tenancy;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCorrespondenceName(String name) {
        appCorrName = name;
    }
    
    public void addInvolvedParty(InvolvedParty party) {
        if(!isHouseholdMember(party)) {
            getHousehold().add(getHousehold().size(), party);
        }
    }
    
    public void endInvolvedParty(InvolvedParty party, Date end, Element endReason) {
        if(isHouseholdMember(party)) {
            if(!party.isCurrent()) {
                party.endInvolvedParty(end, endReason);
            }
        }
    }
    
    public void setTenancy(TenancyInterface tenancy) {
        this.tenancy = tenancy;
    }
    
    public void addInterestedProperty(Property property) {
        if(!isInterestedProperty(property)) {
            getPropertiesInterestedIn().add(getHousehold().size(), property);
        }
    }
    
    public void endInterestInProperty(Property property) {
        if(isInterestedProperty(property)) {
            getPropertiesInterestedIn().remove(property);
        }
    }
    
    public void clearInterestedProperties() {
        if(!propertiesInterestedIn.isEmpty()) {
            propertiesInterestedIn.clear();
        }
    }
}