/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ApplicationInterface;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.ModifiedByInterface;
import interfaces.PropertyInterface;
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
    private final ArrayList<AddressUsage> appAddresses = new ArrayList();
    private final ArrayList<PropertyInterface> propertiesInterestedIn;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private int tenancyRef;
    
    private final String createdBy;
    private final Date createdDate;
    
    
    
    /**
     * Constructor for objects of class Person
     * @param appRef
     * @param appStartDate
     * @param address
     * @param mainApp
     * @param corrName
     * @param createdBy
     */
    public Application(int appRef, String corrName, Date appStartDate, InvolvedParty mainApp, AddressUsage address, String createdBy, Date createdDate) {
        this.appRef = appRef;
        this.appCorrName = corrName;
        this.appStartDate = appStartDate;
        this.appStatusCode = "NEW";
        this.household = new ArrayList();
        this.household.add(mainApp);
        this.appAddresses.add(address);
        this.propertiesInterestedIn = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
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
        if(endDate.after(this.appStartDate)) {
            this.appEndDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void setAppStatusCode(String code) {
        this.appStatusCode = code;
    }
    
    private void setHouseholdAddress(AddressUsage address, ModifiedByInterface modifiedBy) {
        if(!household.isEmpty()) {
            for(InvolvedParty invParty : household) {
                if(invParty.isCurrent()) {
                    invParty.getPerson().createAddress(address, modifiedBy);
                }
            }
        }
    }
    
    public void setAppAddress(AddressUsage address, ModifiedByInterface modifiedBy) {
        if(!this.appAddresses.isEmpty()) {
            for(AddressUsage temp : this.appAddresses) {
                if(temp.isCurrent()) {
                    temp.setEndDate(address.getStartDate(), modifiedBy);
                }
            }
        }
        this.appAddresses.add(address);
        this.setHouseholdAddress(address, modifiedBy);
        this.modifiedBy(modifiedBy);
    }
    
    public void addInvolvedParty(InvolvedParty party, ModifiedByInterface modifiedBy) {
        if(!this.isHouseholdMember(party.getInvolvedPartyRef())) {
            this.household.add(this.getHousehold().size(), party);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void changeMainApp(int invPartyRef, Date end, Element endReason, ModifiedByInterface modifiedBy) {
        if(this.isHouseholdMember(invPartyRef) && !this.getInvolvedParty(invPartyRef).isMainInd()) {
            if(this.getInvolvedParty(invPartyRef).isOver18()) {
                if(this.getMainApp() != null) {
                    InvolvedParty party = this.getInvolvedParty(invPartyRef);
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
    
    public void endInvolvedParty(int invPartyRef, Date end, Element endReason, ModifiedByInterface modifiedBy) {
        if(this.isHouseholdMember(invPartyRef)) {
            InvolvedParty party = this.getInvolvedParty(invPartyRef);
            if(!party.isCurrent()) {
                if(!party.isMainInd()) {
                    party.endInvolvedParty(end, endReason, modifiedBy);
                    this.modifiedBy(modifiedBy);
                }
            }
        }
    }
    
    public void setTenancy(int tenancyRef, ModifiedByInterface modifiedBy) {
        this.tenancyRef = tenancyRef;
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
    
    public boolean isHouseholdMember(int invPartyRef) {
        if(!this.household.isEmpty()) {
            for(InvolvedPartyInterface invParty : this.household) {
                if(invParty.isCurrent()) {
                    if(invParty.getPersonRef() == invPartyRef) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public InvolvedParty getInvolvedParty(int invPartyRef) {
        if(!this.household.isEmpty() && this.isHouseholdMember(invPartyRef)) {
            for(InvolvedParty party : this.household) {
                if(party.getInvolvedPartyRef() == invPartyRef) {
                    return party;
                }
            }
        }
        return null;
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
    public int getTenancyRef() {
        return this.tenancyRef;
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
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
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
                "\nTenancy Ref\n" + this.getTenancyRef() + "\nCreated By: " + this.getCreatedBy() + "\nCreated Date: " + this.getCreatedDate() +
                "\nLast Modified By: " + this.getLastModifiedBy() + "\nLast Modified Date: " + this.getLastModifiedDate() + "\nModified By\n" + this.getModifiedBy();
        return temp;
    }
}