/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ContactInterface;
import interfaces.Element;
import interfaces.ModifiedByInterface;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class Contact implements ContactInterface {
    
    ///   VARIABLES   ///
    
    private final int contactRef;
    private Element contactType;
    private String contactValue;
    private Date startDate;
    private Date endDate;
    private final String createdBy;
    private final Date createdDate;
    private final List<ModifiedByInterface> modifiedBy;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Contact
     * @param ref
     * @param type
     * @param value
     * @param date
     * @param createdBy
     * @param createdDate
     */
    public Contact(int ref, Element type, String value, Date date, String createdBy, Date createdDate) {
        this.contactRef = ref;
        contactType = type;
        contactValue = value;
        startDate = date;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
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
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * @param endDate the endDate to set
     * @param modifiedBy
     */
    public void setEndDate(Date endDate, ModifiedByInterface modifiedBy) {
        if(endDate == null || endDate.after(this.startDate)) {
            this.endDate = endDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     * @param contactType
     * @param contactValue
     * @param startDate
     * @param modifiedBy
     */
    public void updateContact(Element contactType, String contactValue, Date startDate, ModifiedByInterface modifiedBy) {
        this.setContactType(contactType);
        this.setContactValue(contactValue);
        this.setStartDate(startDate);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return contactRef
     */
    @Override
    public int getContactRef() {
        return this.contactRef;
    }
    
    /**
     * @return contactType
     */
    @Override
    public Element getContactType() {
        return contactType;
    }
    
    /**
     * @return contactType description
     */
    @Override
    public String getContactTypeDescription() {
        return contactType.getDescription();
    }

    /**
     * @return contactValue
     */
    @Override
    public String getContactValue() {
        return contactValue;
    }

    /**
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * @return true if endDate == null || (endDate != null && endDate > TODAY)
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
    
    @Override
    public boolean hasBeenModified() {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the Contact
     */
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the last date the Contact was modified
     */
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return the list of modifiedBy objects for the Contact
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the Contact
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }

    /**
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * 
     * @return String representation of Contact
     */
    @Override
    public String toString() {
        return "AMEND toString()";
    }
}