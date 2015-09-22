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
    
    ///   VARIABLES   ///
    
    private final int appRef;
    private String appCorrName;
    private Date appStartDate;
    private String appStatusCode; //indicates the status of the app, e.g. NEW, INTR, HSED, CLSD, DTE (due to end)
    private final ArrayList<InvolvedPartyInterface> household;
    private final ArrayList<AddressUsageInterface> appAddresses = new ArrayList();
    private final ArrayList<Property> propertiesInterestedIn;
    private TenancyInterface tenancy;
    
    private final String createdBy;
    private final Date createdDate;
    
    
    
    /**
     * Constructor for objects of class Person
     * @param appRef
     * @param household
     * @param appStartDate
     * @param address
     * @param corrName
     * @param createdBy
     */
    public Application(int appRef, String corrName, Date appStartDate, ArrayList<InvolvedPartyInterface> household, AddressUsageInterface address, String createdBy) {
        this.appRef = appRef;
        appCorrName = corrName;
        this.appStartDate = appStartDate;
        appStatusCode = "NEW";
        this.household = household;
        setAppAddress(address);
        propertiesInterestedIn = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setCorrespondenceName(String name) {
        appCorrName = name;
    }
    
    private void setStartDate(Date startDate) {
        this.appStartDate = startDate;
    }
    
    @Override
    public void updateApplication(String name, Date startDate) {
        setCorrespondenceName(name);
        setStartDate(startDate);
    }
    
    public void setAppStatusCode(String code) {
        this.appStatusCode = code;
    }
    
    public void setAppAddress(AddressUsageInterface address) {
        if(!appAddresses.isEmpty()) {
            for(AddressUsageInterface temp : appAddresses) {
                if(temp.isCurrent()) {
                    temp.setEndDate(address.getStartDate());
                }
            }
        }
        appAddresses.add(address);
    }
    
    public void addInvolvedParty(InvolvedPartyInterface party) {
        if(!isHouseholdMember(party)) {
            getHousehold().add(getHousehold().size(), party);
        }
    }
    
    public void endInvolvedParty(InvolvedPartyInterface party, Date end, Element endReason) {
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
    
    
    
    /// ACCESSOR METHODS   ///
    
    private boolean isHouseholdMember(InvolvedPartyInterface party) {
        if(!household.isEmpty()) {
            for(InvolvedPartyInterface invParty : household) {
                if(invParty.isCurrent()) {
                    if(invParty.getPersonRef() == party.getPersonRef()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isInterestedProperty(Property property) {
        if(!propertiesInterestedIn.isEmpty()) {
            for(Property prop : propertiesInterestedIn) {
                if(prop.getPropRef() == property.getPropRef()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public int getApplicationRef() {
        return appRef;
    }

    /**
     * @return the appCorrName
     */
    @Override
    public String getAppCorrName() {
        return appCorrName;
    }

    /**
     * @return the appStatusCode
     */
    @Override
    public String getAppStatusCode() {
        return appStatusCode;
    }
    
    /**
     * @return the appStartDate
     */
    @Override
    public Date getAppStartDate() {
        return appStartDate;
    }
    
    /**
     * @return the appAddresses
     */
    @Override
    public List getApplicationAddressess() {
        return Collections.unmodifiableList(appAddresses);
    }

    /**
     * @return the appInterestedFlag
     */
    @Override
    public boolean isAppInterestedFlag() {
        return !propertiesInterestedIn.isEmpty();
    }

    /**
     * @return the household
     */
    @Override
    public List getHousehold() {
        return Collections.unmodifiableList(household);
    }

    /**
     * @return the propertiesIntrestedIn
     */
    @Override
    public List getPropertiesInterestedIn() {
        return Collections.unmodifiableList(propertiesInterestedIn);
    }

    /**
     * @return the tenancy
     */
    @Override
    public TenancyInterface getTenancy() {
        return tenancy;
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}