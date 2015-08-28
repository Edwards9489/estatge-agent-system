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
    private Date appCreatedDate;
    private String appCorrName;
    private String appStatusCode; //indicates the status of the app, e.g. NEW, INTR, HSED, CLSD, DTE (due to end)
    private Date statusDate; // indicates the date the status was changed
    private ArrayList<String> statusHistory = new ArrayList<>();
    private ArrayList<Date> statusDateHistory = new ArrayList<>();
    private ArrayList<InvolvedParty> household = new ArrayList<>();
    private ArrayList<InvolvedParty> historicHousehold = new ArrayList<>();
    private ArrayList<Property> propertiesIntrestedIn = new ArrayList();
    private Tenancy tenancy;
    
    
    
    /**
     * Constructor for objects of class Person
     */
    public Application(int appRef, ArrayList<InvolvedParty> household, Address address, String corrName) {
        this.appRef = appRef;
        appCreatedDate = new Date();
        appCorrName = corrName;
        appStatusCode = "NEW";
        statusDate = new Date();
        this.household = household;
    }
    
    public int getApplicationRef() {
        return appRef;
    }
    
    public void setInterestedFlag(boolean interested) {
        appInterestedFlag = interested;
    }
    
    public void updateCorrespondenceName(String name) {
        appCorrName = name;
    }
    
    public void updateStatus(String status) {
        addHistoricStatus();
        appStatusCode = status;
        updateStatusDate(new Date());
    }
    
    public void updateStatusDate(Date date) {
        statusDate = date;
    }
    
    public void addHistoricStatus() {
        statusHistory.add(statusHistory.size(), appStatusCode);
        statusDateHistory.add(statusDateHistory.size(), statusDate);
    }
    
    public void addInvolvedParty(InvolvedParty party) {
        household.add(household.size(), party);
    }
    
    public void endInvolvedParty(InvolvedParty party, Date end, String endReason) {
        party.endInvolvedParty(end, endReason);
        household.remove(party);
        historicHousehold.add(historicHousehold.size(), party);
    }
    
    public void setTenancy(Tenancy tenancy) {
        this.tenancy = tenancy;
    }
    
    public void addInterestedProperty(Property property) {
        propertiesIntrestedIn.add(household.size(), property);
    }
    
    public void endInterestInProperty(Property property) {
        propertiesIntrestedIn.remove(property);
    }
}