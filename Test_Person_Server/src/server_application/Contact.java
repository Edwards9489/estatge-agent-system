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
    
    public Contact(ContactType type, String value, Date date) {
        contactType = type;
        contactValue = value;
        startDate = date;
    }
    
    public void updateEndDate(Date date) {
        endDate = date;
    }
}