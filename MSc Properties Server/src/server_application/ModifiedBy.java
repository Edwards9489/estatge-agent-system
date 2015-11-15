/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ModifiedByInterface;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class ModifiedBy implements ModifiedByInterface {
    
    ///   VARIABLES   ///
    
    private final String modifiedBy;
    private final Date modifiedDate;
    private final String description;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class ModifiedBy
     * @param modifiedBy
     * @param modifedDate
     * @param description 
     */
    public ModifiedBy(String modifiedBy, Date modifedDate, String description) {
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifedDate;
        this.description = description;
    }
    
    
    
    ///   MUTATOR METHODS   ///

    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return modifiedBy
     */
    @Override
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @return modifiedDate
     */
    @Override
    public Date getModifiedDate() {
        return modifiedDate;
    }
    
    /**
     * @return description
     */
    @Override
    public String getDescription() {
        return description;
    }
    
    /**
     * 
     * @return String representation of ModifiedBy
     */
    @Override
    public String toString() {
        return modifiedBy + " " + description + " on " + modifiedDate;
    }
}