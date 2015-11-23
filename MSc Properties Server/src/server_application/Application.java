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
import interfaces.Note;
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
    private final List<InvolvedPartyInterface> household;
    private final List<AddressUsageInterface> appAddresses;
    private final List<PropertyInterface> propertiesInterestedIn;
    private final List<ModifiedByInterface> modifiedBy;
    private final List<Note> notes;
    private Integer tenancyRef;
    
    private final String createdBy;
    private final Date createdDate;
    
    
    /**
     * Constructor for objects of class Application
     * @param appRef
     * @param corrName
     * @param appStartDate
     * @param statusCode
     * @param createdBy
     * @param createdDate 
     */
    public Application(int appRef, String corrName, Date appStartDate, String statusCode, String createdBy, Date createdDate) {
        this.appRef = appRef;
        this.appCorrName = corrName;
        this.appStartDate = appStartDate;
        this.appStatusCode = statusCode;
        this.household = new ArrayList();
        this.appAddresses = new ArrayList();
        this.tenancyRef = null;
        this.propertiesInterestedIn = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.notes = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    /**
     * Constructor for objects of class Application
     * @param appRef
     * @param appStartDate
     * @param address
     * @param mainApp
     * @param corrName
     * @param createdBy
     * @param createdDate
     */
    public Application(int appRef, String corrName, Date appStartDate, InvolvedParty mainApp, AddressUsage address, String createdBy, Date createdDate) {
        this(appRef, corrName, appStartDate, "NEW", createdBy, createdDate);
        this.household.add(mainApp);
        this.appAddresses.add(address);
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param name
     */
    private void setCorrespondenceName(String name) {
        this.appCorrName = name;
    }
    
    /**
     * @param startDate
     */
    private void setStartDate(Date startDate) {
        this.appStartDate = startDate;
    }
    
    /**
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * @param modifiedBy
     */
    private void clearInterestedProperties(ModifiedByInterface modifiedBy) {
        if(!this.propertiesInterestedIn.isEmpty()) {
            this.propertiesInterestedIn.clear();
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param name
     * @param startDate
     * @param modifiedBy
     */
    public void updateApplication(String name, Date startDate, ModifiedByInterface modifiedBy) {
        if(isCurrent()) {
            this.setCorrespondenceName(name);
            this.setStartDate(startDate);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param endDate
     * @param modifiedBy
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate == null || endDate.after(this.appStartDate)) {
            this.appEndDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param code
     */
    public void setAppStatusCode(String code) {
        this.appStatusCode = code;
    }
    
    /**
     * @param address
     * @param modifiedBy
     */
    private void setHouseholdAddress(AddressUsage address, ModifiedByInterface modifiedBy) {
        if(!household.isEmpty()) {
            for(InvolvedPartyInterface invParty : household) {
                if(invParty.isCurrent()) {
                    Person temp = (Person) invParty.getPerson();
                    temp.createAddress(address, modifiedBy);
                }
            }
        }
    }
    
    private void deleteHouseholdAddress(AddressUsageInterface address, ModifiedByInterface modifiedBy) {
        if(!household.isEmpty()) {
            for(InvolvedPartyInterface invParty : household) {
                if(invParty.isCurrent()) {
                    Person temp = (Person) invParty.getPerson();
                    temp.deleteAddress(address, modifiedBy);
                }
            }
        }
    }
    
    /**
     * @param address
     * @param modifiedBy
     */
    public void setAppAddress(AddressUsage address, ModifiedByInterface modifiedBy) {
        if(!this.appAddresses.isEmpty()) {
            for(AddressUsageInterface addressUsage : this.appAddresses) {
                if(addressUsage.isCurrent()) {
                    AddressUsage temp = (AddressUsage) addressUsage;
                    temp.setEndDate(address.getStartDate(), modifiedBy);
                }
            }
        }
        this.appAddresses.add(address);
        this.setHouseholdAddress(address, modifiedBy);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteAppAddress(int ref, ModifiedByInterface modifiedBy) {
        if(this.isCurrentAddress(ref)) {
            AddressUsageInterface addr = this.getCurrentApplicationAddress();
            if(!addr.hasBeenModified()) {
                appAddresses.remove(addr);
                AddressUsage temp = (AddressUsage) this.getCurrentApplicationAddress();
                if(temp != null) {
                    temp.setEndDate(null, modifiedBy);
                }
                this.deleteHouseholdAddress(addr, modifiedBy);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * @param party
     * @param modifiedBy
     */
    public void addInvolvedParty(InvolvedParty party, ModifiedByInterface modifiedBy) {
        if(!this.isPersonHouseholdMember(party.getPersonRef())) {
            this.household.add(party);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param invPartyRef
     * @param end
     * @param endReason
     * @param modifiedBy
     */
    public void endInvolvedParty(int invPartyRef, Date end, Element endReason, ModifiedByInterface modifiedBy) {
        if(this.isHouseholdMember(invPartyRef)) {
            InvolvedParty party = (InvolvedParty) this.getInvolvedParty(invPartyRef);
            if(!party.isCurrent()) {
                if(!party.isMainInd()) {
                    party.endInvolvedParty(end, endReason, modifiedBy);
                    this.modifiedBy(modifiedBy);
                }
            }
        }
    }
    
    public void deleteInvolvedParty(int invPartyRef, ModifiedByInterface modifiedBy) {
        if(this.isHouseholdMember(invPartyRef)) {
            InvolvedPartyInterface party = this.getInvolvedParty(invPartyRef);
            if(!party.hasBeenModified() && !party.isMainInd()) {
                household.remove(party);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * @param invPartyRef
     * @param end
     * @param endReason
     * @param modifiedBy
     */
    public void changeMainApp(int invPartyRef, Date end, Element endReason, ModifiedByInterface modifiedBy) {
        if(this.isHouseholdMember(invPartyRef) && !this.getInvolvedParty(invPartyRef).isMainInd()) {
            if(this.getInvolvedParty(invPartyRef).isOver18()) {
                if(this.getMainApp() != null) {
                    InvolvedParty party = (InvolvedParty) this.getInvolvedParty(invPartyRef);
                    party.updateInvolvedParty(true, party.getStartDate(), this.getMainApp().getRelationship(), modifiedBy);
                    InvolvedParty mainApp = (InvolvedParty) this.getMainApp();
                    mainApp.endInvolvedParty(end, endReason, modifiedBy);
                    mainApp.setMainInd();
                    party.setMainInd();
                    this.modifiedBy(modifiedBy);
                }
            }
        }
    }
    
    /**
     * @param tenancyRef
     * @param modifiedBy
     * @return copy of propertiesInterestedIn
     */
    public List<PropertyInterface> setTenancy(int tenancyRef, ModifiedByInterface modifiedBy) {
        this.tenancyRef = tenancyRef;
        List<PropertyInterface> properties = new ArrayList(this.getPropertiesInterestedIn());
        this.clearInterestedProperties(modifiedBy);
        this.modifiedBy(modifiedBy);
        return properties;
    }
    
    public void clearTenancy(ModifiedBy modifiedBy) {
        this.tenancyRef = null;
        this.modifiedBy(modifiedBy);
    } 
    
    /**
     * @param property
     * @param modifiedBy
     */
    public void addInterestedProperty(PropertyInterface property, ModifiedByInterface modifiedBy) {
        if(!this.isInterestedProperty(property)) {
            this.propertiesInterestedIn.add(property);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param property
     * @param modifiedBy
     */
    public void endInterestInProperty(PropertyInterface property, ModifiedByInterface modifiedBy) {
        if(this.isInterestedProperty(property)) {
            this.propertiesInterestedIn.remove(property);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    
    
    /// ACCESSOR METHODS   ///
    
    /**
     * @param personRef
     * @return true if household contains a current InvolvedParty instance with a person ref ==  personRef
     */
    public boolean isPersonHouseholdMember(int personRef) {
        if(!this.household.isEmpty()) {
            for(InvolvedPartyInterface invParty : this.household) {
                if(invParty.isCurrent()) {
                    if(invParty.getPersonRef() == personRef) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * @param invPartyRef
     * @return true if household contains a current InvolvedParty instance with a ref ==  invPartyRef
     */
    public boolean isHouseholdMember(int invPartyRef) {
        if(!this.household.isEmpty()) {
            for(InvolvedPartyInterface invParty : this.household) {
                if(invParty.isCurrent() && invParty.getInvolvedPartyRef() == invPartyRef) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * @param invPartyRef
     * @return InvolvedParty instance from household with ref == invPartyRef
     */
    public InvolvedPartyInterface getInvolvedParty(int invPartyRef) {
        if(!this.household.isEmpty() && this.isHouseholdMember(invPartyRef)) {
            for(InvolvedPartyInterface party : this.household) {
                if(party.getPersonRef() == invPartyRef) {
                    return party;
                }
            }
        }
        return null;
    }
    
    /**
     * @return InvolvedParty from households with mainInd == true && current == true
     */
    public InvolvedPartyInterface getMainApp() {
        if(!this.household.isEmpty()) {
            for(InvolvedPartyInterface party : this.household) {
                if(party.isMainInd() && party.isCurrent()) {
                    return party;
                }
            }
        }
        return null;
    }
    
    /**
     * @param property
     * @return true if propertiesInterestedIn contains property
     */
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
    
    /**
     * @return true if appEndDate == null
     */
    private boolean isAppEndDateNull() {
        return this.appEndDate == null;
    }
    
    
    /**
     * @return appRef
     */
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
     * @return the appEndDate
     */
    @Override
    public Date getAppEndDate() {
        return this.appEndDate;
    }
    
    public boolean isCurrentAddress(int ref) {
        if(!this.appAddresses.isEmpty()) {
            AddressUsageInterface addr = this.appAddresses.get(this.appAddresses.size()-1);
            if(addr.getAddressUsageRef() == ref) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @return current AddressUsage
     */
    @Override
    public AddressUsageInterface getCurrentApplicationAddress() {
        if(!this.appAddresses.isEmpty()) {
            return this.appAddresses.get(this.appAddresses.size()-1);
        }
        return null;
    }
    
    /**
     * @return String of current Address
     */
    @Override
    public String getCurrentApplicationAddressString() {
        if(!this.appAddresses.isEmpty()) {
            return this.appAddresses.get(this.appAddresses.size()-1).getAddressString();
        }
        return null;
    }
    
    /**
     * @return appAddresses
     */
    @Override
    public List<AddressUsageInterface> getApplicationAddressess() {
        return Collections.unmodifiableList((List<AddressUsageInterface>) this.appAddresses);
    }

    /**
     * @return propertiesInterestedIn.isEmpty() == false
     */
    @Override
    public boolean isAppInterestedFlag() {
        return !this.propertiesInterestedIn.isEmpty();
    }

    /**
     * @return household
     */
    @Override
    public List<InvolvedPartyInterface> getHousehold() {
        return Collections.unmodifiableList(this.household);
    }

    /**
     * @return propertiesIntrestedIn
     */
    @Override
    public List<PropertyInterface> getPropertiesInterestedIn() {
        return Collections.unmodifiableList(this.propertiesInterestedIn);
    }

    /**
     * @return tenancyRef
     */
    @Override
    public Integer getTenancyRef() {
        return this.tenancyRef;
    }
    
    public boolean hasTenancyRef() {
        return tenancyRef != null;
    }

    /**
     * @return true if appEndDate == null || (appEndDate != null && appEndDate > TODAY)
     */
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
    public boolean hasNote(int ref) {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Note getNote(int ref) {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getRef() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Note> getNotes() {
        return Collections.unmodifiableList(this.notes);
    }
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the Application
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the last date the Application was modified
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return the list of modifiedBy object for the Application
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the Application
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return getCreatedBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return getCreatedDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    /**
     * @return String representation of the Application
     */
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