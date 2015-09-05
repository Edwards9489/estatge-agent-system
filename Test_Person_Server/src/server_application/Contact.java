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
public class Contact {
    private ContactType contactType;
    private String contactValue;
    private Date startDate;
    private Date endDate;
    private boolean current;
    private final String createdBy;
    private final Date createdDate;
    private ArrayList<String> modifedBy;
    private ArrayList<Date> modifedDate;
    
    public Contact(ContactType type, String value, Date date, String createdBy) {
        contactType = type;
        contactValue = value;
        startDate = date;
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }
    
    public void updateEndDate(Date date) {
        endDate = date;
    }
}