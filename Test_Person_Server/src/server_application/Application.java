/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class Application {
    // instance variables - replace the example below with your own
    private final int appRef;
    private boolean appInterestedFlag; // indicates if app has an interest in any properties
    private String appCorrName;
    private String appStatusCode; //indicates the status of the app, e.g. NEW, INTR, HSED, CLSD, DTE (due to end)
    private Date statusDate; // indicates the date the status was changed
    private ArrayList<String> statusHistory = new ArrayList<>();
    private ArrayList<Date> statusDateHistory = new ArrayList<>();
    private ArrayList<InvolvedParty> household = new ArrayList<>();
    private ArrayList<InvolvedParty> historicHousehold = new ArrayList<>();
    private ArrayList<Property> propertiesIntrestedIn = new ArrayList();
    private Tenancy tenancy;
    private final String createdBy;
    private final Date createdDate;
    private AddressUsage appAddress;
    
    
    
    /**
     * Constructor for objects of class Person
     */
    public Application(int appRef, ArrayList<InvolvedParty> household, AddressUsage address, String corrName, String createdBy) {
        this.appRef = appRef;
        appCorrName = corrName;
        appStatusCode = "NEW";
        statusDate = new Date();
        this.household = household;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        appAddress = address;
    }
    
    public int getApplicationRef() {
        return appRef;
    }
    
    public void setInterestedFlag(boolean interested) {
        appInterestedFlag = interested;
    }
    
    public void setCorrespondenceName(String name) {
        appCorrName = name;
    }
    
    public void setStatus(String status) {
        addHistoricStatus();
        appStatusCode = status;
        setStatusDate(new Date());
    }
    
    public void setStatusDate(Date date) {
        statusDate = date;
    }
    
    public void addHistoricStatus() {
        getStatusHistory().add(getStatusHistory().size(), getAppStatusCode());
        getStatusDateHistory().add(getStatusDateHistory().size(), getStatusDate());
    }
    
    public void addInvolvedParty(InvolvedParty party) {
        getHousehold().add(getHousehold().size(), party);
    }
    
    public void endInvolvedParty(InvolvedParty party, Date end, EndReason endReason) {
        party.endInvolvedParty(end, endReason);
        getHousehold().remove(party);
        getHistoricHousehold().add(getHistoricHousehold().size(), party);
    }
    
    public void setTenancy(Tenancy tenancy) {
        this.tenancy = tenancy;
    }
    
    public void addInterestedProperty(Property property) {
        getPropertiesIntrestedIn().add(getHousehold().size(), property);
    }
    
    public void endInterestInProperty(Property property) {
        getPropertiesIntrestedIn().remove(property);
    }

    /**
     * @return the appInterestedFlag
     */
    public boolean isAppInterestedFlag() {
        return appInterestedFlag;
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
     * @return the statusDate
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * @return the statusHistory
     */
    public ArrayList<String> getStatusHistory() {
        return statusHistory;
    }

    /**
     * @return the statusDateHistory
     */
    public ArrayList<Date> getStatusDateHistory() {
        return statusDateHistory;
    }

    /**
     * @return the household
     */
    public ArrayList<InvolvedParty> getHousehold() {
        return household;
    }

    /**
     * @return the historicHousehold
     */
    public ArrayList<InvolvedParty> getHistoricHousehold() {
        return historicHousehold;
    }

    /**
     * @return the propertiesIntrestedIn
     */
    public ArrayList<Property> getPropertiesIntrestedIn() {
        return propertiesIntrestedIn;
    }

    /**
     * @return the tenancy
     */
    public Tenancy getTenancy() {
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
}