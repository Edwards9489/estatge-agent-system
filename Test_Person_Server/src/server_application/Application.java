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
    private final int app_refno;
    private boolean app_interested_flag; // indicates if app has an interest in any properties
    private Date app_created_date;
    private String app_corr_name;
    private String app_status_code; //indicates the status of the app, e.g. NEW, INTR, HSED, CLSD, DTE (due to end)
    private Date status_date; // indicates the date the status was changed
    private ArrayList<String> status_history = new ArrayList<>();
    private ArrayList<Date> status_date_history = new ArrayList<>();
    private ArrayList<Involved_Party> household = new ArrayList<>();
    private ArrayList<Involved_Party> historicHousehold = new ArrayList<>();
    private int app_tcy_refno; // indicates the tenancy ref, if one exists
    
    
    
    /**
     * Constructor for objects of class Person
     */
    public Application(int appRef, ArrayList<Person> household, Address address) {
        app_refno = appRef;
        app_created_date = new Date();
        app_status_code = "NEW";
        status_date = new Date();
    }
    
    public void setInterestedFlag(boolean interested) {
        app_interested_flag = interested;
    }
    
    public void updateCorrespondenceName(String name) {
        app_corr_name = name;
    }
    
    public void updateStatus(String status) {
        addHistoricStatus();
        app_status_code = status;
        updateStatusDate(new Date());
    }
    
    public void updateStatusDate(Date date) {
        status_date = date;
    }
    
    public void addHistoricStatus() {
        status_history.add(status_history.size(), app_status_code);
        status_date_history.add(status_date_history.size(), status_date);
    }
    
    public void addInvolvedParty(Involved_Party party) {
        household.add(household.size(), party);
    }
    
    public void endInvolvedParty(Involved_Party party, Date end, String endReason) {
        party.endInvolvedParty(end, endReason);
        household.remove(party);
        historicHousehold.add(historicHousehold.size(), party);
    }
    
    public void setTenancyRef(int tcyRef) {
        app_tcy_refno = tcyRef;
    }
}