/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.AddressUsageInterface;
import interfaces.ApplicationInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PropertyInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class Application extends UnicastRemoteObject implements ApplicationInterface {
    
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
    private final List<Document> documents;
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
     * @throws java.rmi.RemoteException 
     */
    public Application(int appRef, String corrName, Date appStartDate, String statusCode, String createdBy, Date createdDate) throws RemoteException {
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
        this.documents = new ArrayList();
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
     * @throws java.rmi.RemoteException
     */
    public Application(int appRef, String corrName, Date appStartDate, InvolvedParty mainApp, AddressUsage address, String createdBy, Date createdDate) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public void updateApplication(String name, Date startDate, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public void setAppAddress(AddressUsage address, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.appAddresses.isEmpty()) {
            for(AddressUsageInterface addressUsage : this.appAddresses) {
                if(addressUsage.isCurrent()) {
                    AddressUsage temp = (AddressUsage) addressUsage;
                    temp.setEndDate(address.getStartDate(), modifiedBy);
                }
            }
        }
        this.appAddresses.add(address);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteAppAddress(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.isCurrentAddress(ref)) {
            AddressUsageInterface addr = this.getCurrentApplicationAddress();
            if(!addr.hasBeenModified()) {
                appAddresses.remove(addr);
                AddressUsage temp = (AddressUsage) this.getCurrentApplicationAddress();
                if(temp != null) {
                    temp.setEndDate(null, modifiedBy);
                }
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     * @param party
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void addInvolvedParty(InvolvedParty party, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public void endInvolvedParty(int invPartyRef, Date end, Element endReason, ModifiedByInterface modifiedBy) throws RemoteException {
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
    
    public void deleteInvolvedParty(int invPartyRef, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public void changeMainApp(int invPartyRef, Date end, Element endReason, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public List<PropertyInterface> setTenancy(int tenancyRef, ModifiedByInterface modifiedBy) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public void addInterestedProperty(PropertyInterface property, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.isInterestedProperty(property)) {
            this.propertiesInterestedIn.add(property);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param property
     * @param modifiedBy
     * @throws java.rmi.RemoteException
     */
    public void endInterestInProperty(PropertyInterface property, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.isInterestedProperty(property)) {
            this.propertiesInterestedIn.remove(property);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    public void createDocument(Document document, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasDocument(document.getDocumentRef())) {
            documents.add(document);
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void deleteDocument(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasDocument(ref)) {
            documents.remove(this.getDocument(ref));
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    /// ACCESSOR METHODS   ///
    
    /**
     * @param personRef
     * @return true if household contains a current InvolvedParty instance with a person ref ==  personRef
     * @throws java.rmi.RemoteException
     */
    public boolean isPersonHouseholdMember(int personRef) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public boolean isHouseholdMember(int invPartyRef) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public InvolvedPartyInterface getInvolvedParty(int invPartyRef) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    public InvolvedPartyInterface getMainApp() throws RemoteException {
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
    private boolean isInterestedProperty(PropertyInterface property) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getApplicationRef() throws RemoteException {
        return this.appRef;
    }

    /**
     * @return the appCorrName
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getAppCorrName() throws RemoteException {
        return this.appCorrName;
    }

    /**
     * @return the appStatusCode
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getAppStatusCode() throws RemoteException {
        return this.appStatusCode;
    }
    
    /**
     * @return the appStartDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getAppStartDate() throws RemoteException {
        return this.appStartDate;
    }
    
    /**
     * @return the appEndDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getAppEndDate() throws RemoteException {
        return this.appEndDate;
    }
    
    public boolean isCurrentAddress(int ref) throws RemoteException {
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
     * @throws java.rmi.RemoteException
     */
    @Override
    public AddressUsageInterface getCurrentApplicationAddress() throws RemoteException {
        if(!this.appAddresses.isEmpty()) {
            return this.appAddresses.get(this.appAddresses.size()-1);
        }
        return null;
    }
    
    /**
     * @return String of current Address
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCurrentApplicationAddressString() throws RemoteException {
        if(!this.appAddresses.isEmpty()) {
            return this.appAddresses.get(this.appAddresses.size()-1).getAddressString();
        }
        return null;
    }
    
    /**
     * @return appAddresses
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<AddressUsageInterface> getApplicationAddressess() throws RemoteException {
        return Collections.unmodifiableList((List<AddressUsageInterface>) this.appAddresses);
    }

    /**
     * @return propertiesInterestedIn.isEmpty() == false
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isAppInterestedFlag() throws RemoteException {
        return !this.propertiesInterestedIn.isEmpty();
    }

    /**
     * @return household
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<InvolvedPartyInterface> getHousehold() throws RemoteException {
        return Collections.unmodifiableList(this.household);
    }

    /**
     * @return propertiesIntrestedIn
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<PropertyInterface> getPropertiesInterestedIn() throws RemoteException {
        return Collections.unmodifiableList(this.propertiesInterestedIn);
    }

    /**
     * @return tenancyRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public Integer getTenancyRef() throws RemoteException {
        return this.tenancyRef;
    }
    
    public boolean hasTenancyRef() throws RemoteException {
        return tenancyRef != null;
    }

    /**
     * @return true if appEndDate == null || (appEndDate != null && appEndDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(this.isAppEndDateNull()) {
            return true;
        }
        else {
            return this.getAppEndDate().after(new Date());
        }
    }
    
    @Override
    public boolean hasNote(int ref) throws RemoteException {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getReference() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Note getNote(int ref) throws RemoteException {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getReference() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
    }
    
    @Override
    public boolean hasDocument(int ref) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean hasDocument(String fileName) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(fileName.equals(document.getDocumentName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Document getDocument(int ref) throws RemoteException {
        if(this.hasDocument(ref)) {
            for (Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return document;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Document> getDocuments() throws RemoteException {
        return Collections.unmodifiableList(this.documents);
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the Application
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the last date the Application was modified
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return the list of modifiedBy object for the Application
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the Application
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return getCreatedBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }

    /**
     * @return getCreatedDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }
    
    /**
     * @return String representation of the Application
     */
    @Override
    public String toString() {
        try {
            String temp = "\nApplication Ref: " + this.getApplicationRef() + "\nApplication Correspondence Name: " + this.getAppCorrName() +
                    "\nApplication Status Code: " + this.getAppStatusCode() + "\nApplication Address: " + this.getCurrentApplicationAddressString() +
                    "\nApplication Addresses\n" + this.getApplicationAddressess() + "\nApplication Start Date: " + this.getAppStartDate() +
                    "\nApplication End Date: " + this.getAppEndDate() + "\nIs Current: " + this.isCurrent() + "\nInterested in Properties: " +
                    this.isAppInterestedFlag() + "\nHousehold\n" + this.getHousehold() + "\nProperties Interested In\n" + this.getPropertiesInterestedIn() +
                    "\nTenancy Ref\n" + this.getTenancyRef() + "\nCreated By: " + this.getCreatedBy() + "\nCreated Date: " + this.getCreatedDate() +
                    "\nLast Modified By: " + this.getLastModifiedBy() + "\nLast Modified Date: " + this.getLastModifiedDate() + "\nModified By\n" + this.getModifiedBy();
            return temp;
        } catch (RemoteException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}