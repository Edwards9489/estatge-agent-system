/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.ModifiedByInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class ElementImpl implements Element {
    
    ///   VARIABLES   ///
    
    private final String code;
    private String description;
    private boolean current;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class ElementImpl
     * @param code
     * @param description
     * @param createdBy
     * @param createdDate 
     */
    public ElementImpl(String code, String description, String createdBy, Date createdDate) {
        this.code = code;
        this.description = description;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        current = true;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * 
     * @param description 
     */
    private void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 
     * @param current 
     */
    public void setCurrent(boolean current) {
        this.current = current;
    }
    
    /**
     * 
     * @param modifiedBy 
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * 
     * @param description
     * @param current
     * @param modifiedBy 
     */
    public void updateElement(String description, boolean current, ModifiedByInterface modifiedBy) {
        setDescription(description);
        setCurrent(current);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * 
     * @return code
     */
    @Override
    public String getCode() {
        return this.code;
    }
    
    /**
     * 
     * @return description
     */
    @Override
    public String getDescription() {
        return this.description;
    }
    
    /**
     * 
     * @return current
     */
    @Override
    public boolean isCurrent() {
        return this.current;
    }
    
    /**
     * 
     * @return the name of the last user to modify the ElementImpl
     */
    @Override
    public String getLastModifiedBy() {
        return this.getLastModification().getModifiedBy();
    }
    
    /**
     * 
     * @return the last date the ElementImpl was modified
     */
    @Override
    public Date getLastModifiedDate() {
        return this.getLastModification().getModifiedDate();
    }
    
    /**
     * 
     * @return the last modifiedBy object for ElementImpl
     */
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for ElementImpl
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return createdBy
     */
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    /**
     * 
     * @return createdDate
     */
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    /**
     * 
     * @return String representation of the ElementImpl
     */
    @Override
    public String toString() {
        String temp = "Code: " + this.code + "\nDescription: " + this.description + "\nCurrent: " + this.isCurrent()
                + "\nCreated By: " + this.createdBy + "\nCreated Date: " + this.createdDate + "\nModifiedBy\n" + this.getModifiedBy();
        return temp;
    }
}