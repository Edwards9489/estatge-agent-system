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
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public ElementImpl(String code, String description, String createdBy, Date createdDate) {
        this.code = code;
        this.description = description;
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        current = true;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setDescription(String description) {
        this.description = description;
    }
    
    public void setCurrent(boolean current) {
        this.current = current;
    }
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    @Override
    public void updateElement(String description, boolean current, ModifiedByInterface modifiedBy) {
        setDescription(description);
        setCurrent(current);
        this.modifiedBy(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    @Override
    public String getCode() {
        return this.code;
    }
    
    @Override
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public boolean isCurrent() {
        return this.current;
    }
    
    @Override
    public String getLastModifiedBy() {
        return this.getLastModification().getModifiedBy();
    }
    
    @Override
    public Date getLastModifiedDate() {
        return this.getLastModification().getModifiedDate();
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    @Override
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    @Override
    public String toString() {
        String temp = "Code: " + this.code + "\nDescription: " + this.description + "\nCurrent: " + this.isCurrent()
                + "\nCreated By: " + this.createdBy + "\nCreated Date: " + this.createdDate + "\nModifiedBy\n" + this.getModifiedBy();
        return temp;
    }
}