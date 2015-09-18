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
    private final String createdBy;
    private final Date createdDate;
    private ArrayList<ModifiedBy> modifiedBy;
    
    public Contact(ContactType type, String value, Date date, String createdBy) {
        contactType = type;
        contactValue = value;
        startDate = date;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        this.modifiedBy = new ArrayList();
    }

    /**
     * @return the contactType
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     * @param contactType the contactType to set
     */
    public void setContactType(ContactType contactType, String modifiedBy) {
        this.contactType = contactType;
        modifiedBy(modifiedBy, "amended contact type");
    }

    /**
     * @return the contactValue
     */
    public String getContactValue() {
        return contactValue;
    }

    /**
     * @param contactValue the contactValue to set
     */
    public void setContactValue(String contactValue, String modifiedBy) {
        this.contactValue = contactValue;
        modifiedBy(modifiedBy, "amended contact value");
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate, String modifiedBy) {
        this.startDate = startDate;
        modifiedBy(modifiedBy, "amended start date");
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate, String modifiedBy) {
        this.endDate = endDate;
    }

    /**
     * @return the current
     */
    public boolean isCurrent() {
        if(endDate == null) {
            return true;
        }
        else {
            return endDate.before(new Date());
        }
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
    
    public void modifiedBy(String modifiedBy, String description) {
        //ModifiedBy temp = new ModifiedBy(modifiedBy, description, );
        
        //this.modifiedBy.add(temp);
    }
    
    public List getmodifiedBy() {
        return Collections.unmodifiableList(modifiedBy);
    }
}