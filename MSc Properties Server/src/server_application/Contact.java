/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ContactInterface;
import interfaces.Element;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class Contact implements ContactInterface {
    
    ///   VARIABLES   ///
    
    private Element contactType;
    private String contactValue;
    private Date startDate;
    private Date endDate;
    private final String createdBy;
    private final Date createdDate;
    private final ArrayList<ModifiedBy> modifiedBy;
    
    ///   CONSTRUCTORS ///
    
    public Contact(Element type, String value, Date date, String createdBy) {
        contactType = type;
        contactValue = value;
        startDate = date;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        this.modifiedBy = new ArrayList();
    }
    
    
    
    /// MUTATOR METHODS   ///
    
    /**
     * @param contactType the contactType to set
     */
    private void setContactType(Element contactType) {
        this.contactType = contactType;
    }
    
    /**
     * @param contactValue the contactValue to set
     */
    private void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    /**
     * @param startDate the startDate to set
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * @param endDate the endDate to set
     */
    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public void updateContact(Element contactType, String contactValue, Date startDate) {
        setContactType(contactType);
        setContactValue(contactValue);
        setStartDate(startDate);
    }
    
    public void modifiedBy(ModifiedBy modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///

    /**
     * @return the contactType
     */
    @Override
    public Element getContactType() {
        return contactType;
    }
    
    @Override
    public String getContactTypeDescription() {
        return contactType.getDescription();
    }

    /**
     * @return the contactValue
     */
    @Override
    public String getContactValue() {
        return contactValue;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * @return the current
     */
    @Override
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
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    
    @Override
    public List getmodifiedBy() {
        return Collections.unmodifiableList(modifiedBy);
    }
}