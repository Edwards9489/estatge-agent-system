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
import interfaces.ModifiedByInterface;
import interfaces.PropertyInterface;
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
    private Date appEndDate;
    private String appStatusCode; //indicates the status of the app, e.g. NEW, INTR, HSED, CLSD, DTE (due to end)
    private final ArrayList<InvolvedParty> household;
    private final ArrayList<AddressUsageInterface> appAddresses = new ArrayList();
    private final ArrayList<PropertyInterface> propertiesInterestedIn;
    private final ArrayList<ModifiedByInterface> modifiedBy;
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
    public Application(int appRef, String corrName, Date appStartDate, ArrayList<InvolvedParty> household, AddressUsageInterface address, String createdBy) {
        this.appRef = appRef;
        this.appCorrName = corrName;
        this.appStartDate = appStartDate;
        this.appStatusCode = "NEW";
        this.household = household;
        this.appAddresses.add(address);
        this.propertiesInterestedIn = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setCorrespondenceName(String name) {
        this.appCorrName = name;
    }
    
    private void setStartDate(Date startDate) {
        this.appStartDate = startDate;
    }
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    private void clearInterestedProperties(ModifiedByInterface modifiedBy) {
        if(!this.propertiesInterestedIn.isEmpty()) {
            this.propertiesInterestedIn.clear();
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void updateApplication(String name, Date startDate, ModifiedByInterface modifiedBy) {
        if(isCurrent()) {
            this.setCorrespondenceName(name);
            this.setStartDate(startDate);
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        this.appEndDate = endDate;
        this.modifiedBy(modifiedBy);
    }
    
    public void setAppStatusCode(String code) {
        this.appStatusCode = code;
    }
    
    public void setAppAddress(AddressUsageInterface address, ModifiedByInterface modifiedBy) {
        if(!this.appAddresses.isEmpty()) {
            for(AddressUsageInterface temp : this.appAddresses) {
                if(temp.isCurrent()) {
                    temp.setEndDate(address.getStartDate(), modifiedBy);
                }
            }
        }
        this.appAddresses.add(address);
        this.modifiedBy(modifiedBy);
    }
    
    public void addInvolvedParty(InvolvedPartyInterface party, ModifiedByInterface modifiedBy) {
        if(!this.isHouseholdMember(party)) {
            this.household.add(this.getHousehold().size(), (InvolvedParty) party);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void changeMainApp(InvolvedPartyInterface party, Date end, Element endReason, ModifiedByInterface modifiedBy) {
        if(this.isHouseholdMember(party) && !party.isMainInd()) {
            if(party.isOver18()) {
                if(this.getMainApp() != null) {
                    party.updateInvolvedParty(party.isJointInd(), party.getStartDate(), this.getMainApp().getRelationship(), modifiedBy);
                    this.getMainApp().endInvolvedParty(end, endReason, modifiedBy);
                    InvolvedParty main = (InvolvedParty) this.getMainApp();
                    main.setMainInd();
                    InvolvedParty newMain = (InvolvedParty) party;
                    newMain.setMainInd();
                }
            }
        }
    }
    
    public void endInvolvedParty(InvolvedPartyInterface party, Date end, Element endReason, ModifiedByInterface modifiedBy) {
        if(this.isHouseholdMember(party)) {
            if(!party.isCurrent()) {
                if(!party.isMainInd()) {
                    party.endInvolvedParty(end, endReason, modifiedBy);
                    this.modifiedBy(modifiedBy);
                }
            }
        }
    }
    
    public void setTenancy(TenancyInterface tenancy, ModifiedByInterface modifiedBy) {
        this.tenancy = tenancy;
        this.clearInterestedProperties(modifiedBy);
        this.modifiedBy(modifiedBy);
    }
    
    public void addInterestedProperty(PropertyInterface property, ModifiedByInterface modifiedBy) {
        if(!this.isInterestedProperty(property)) {
            this.getPropertiesInterestedIn().add(this.getHousehold().size(), property);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void endInterestInProperty(PropertyInterface property, ModifiedByInterface modifiedBy) {
        if(this.isInterestedProperty(property)) {
            this.getPropertiesInterestedIn().remove(property);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    /// ACCESSOR METHODS   ///
    
    private boolean isHouseholdMember(InvolvedPartyInterface party) {
        if(!this.household.isEmpty()) {
            for(InvolvedPartyInterface invParty : this.household) {
                if(invParty.isCurrent()) {
                    if(invParty.getPersonRef() == party.getPersonRef()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isInterestedProperty(PropertyInterface property) {
        if(!this.propertiesInterestedIn.isEmpty()) {
            for(PropertyInterface prop : this.propertiesInterestedIn) {
                if(prop.getPropRef() == property.getPropRef()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private InvolvedPartyInterface getMainApp() {
        InvolvedPartyInterface main = null;
        for(InvolvedPartyInterface temp : household) {
            if(temp.getRelationship().getCode().equals("APPL")) {
                main = temp;
            }
        }
        return main;
    }
    
    private boolean isAppEndDateNull() {
        return this.appEndDate == null;
    }
    
    @Override
    public int getApplicationRef() {
        return this.appRef;
    }

    /**
     * @return the appCorrName
     */
    @Override
    public String getAppCorrName() {
        return this.appCorrName;
    }

    /**
     * @return the appStatusCode
     */
    @Override
    public String getAppStatusCode() {
        return this.appStatusCode;
    }
    
    /**
     * @return the appStartDate
     */
    @Override
    public Date getAppStartDate() {
        return this.appStartDate;
    }
    
    /**
     * @return the appStartDate
     */
    @Override
    public Date getAppEndDate() {
        return this.appEndDate;
    }
    
    @Override
    public String getCurrentApplicationAddressString() {
        if(!this.appAddresses.isEmpty()) {
            return this.appAddresses.get(this.appAddresses.size()-1).getAddressString();
        }
        return null;
    }
    
    /**
     * @return the appAddresses
     */
    @Override
    public List getApplicationAddressess() {
        return Collections.unmodifiableList(this.appAddresses);
    }

    /**
     * @return the appInterestedFlag
     */
    @Override
    public boolean isAppInterestedFlag() {
        return !this.propertiesInterestedIn.isEmpty();
    }

    /**
     * @return the household
     */
    @Override
    public List getHousehold() {
        return Collections.unmodifiableList(this.household);
    }

    /**
     * @return the propertiesIntrestedIn
     */
    @Override
    public List getPropertiesInterestedIn() {
        return Collections.unmodifiableList(this.propertiesInterestedIn);
    }

    /**
     * @return the tenancy
     */
    @Override
    public TenancyInterface getTenancy() {
        return this.tenancy;
    }
    
    @Override
    public boolean isCurrent() {
        if(this.isAppEndDateNull()) {
            return true;
        }
        else {
            return this.getAppEndDate().after(new Date());
        }
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1).getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1).getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    @Override
    public String toString() {
        String temp = "\nApplication Ref: " + this.getApplicationRef() + "\nApplication Correspondence Name: " + this.getAppCorrName() +
                "\nApplication Status Code: " + this.getAppStatusCode() + "\nApplication Address: " + this.getCurrentApplicationAddressString() +
                "\nApplication Addresses\n" + this.getApplicationAddressess() + "\nApplication Start Date: " + this.getAppStartDate() +
                "\nApplication End Date: " + this.getAppEndDate() + "\nIs Current: " + this.isCurrent() + "\nInterested in Properties: " +
                this.isAppInterestedFlag() + "\nHousehold\n" + this.getHousehold() + "\nProperties Interested In\n" + this.getPropertiesInterestedIn() +
                "\nTenancy\n" + this.getTenancy() + "\nCreated By: " + this.getCreatedBy() + "\nCreated Date: " + this.getCreatedDate() +
                "\nLast Modified By: " + this.getLastModifiedBy() + "\nLast Modified Date: " + this.getLastModifiedDate() + "\nModified By\n" + this.getModifiedBy();
        return temp;
    }
}