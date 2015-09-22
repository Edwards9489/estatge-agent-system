/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class ElementImpl implements Element {
    
    ///   VARIABLES   ///
    
    private final String code;
    private String description;
    private boolean current;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    public ElementImpl(String code, String description, String createdBy) {
        this.code = code;
        this.description = description;
        this.createdBy = createdBy;
        this.createdDate = new Date();
        current = true;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    private void setDescription(String description) {
        this.description = description;
    }
    
    private void setCurrent(boolean current) {
        this.current = current;
    }
    
    @Override
    public void updateElement(String description, boolean current) {
        setDescription(description);
        setCurrent(current);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    @Override
    public String getCode() {
        return code;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean isCurrent() {
        return current;
    }
}