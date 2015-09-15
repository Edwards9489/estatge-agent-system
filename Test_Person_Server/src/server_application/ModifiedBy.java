/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class ModifiedBy {
    private String modifiedBy;
    private Date modifiedDate;
    private String description;
    
    public ModifiedBy(String modifiedBy, String description) {
        this.modifiedBy = modifiedBy;
        this.modifiedDate = new Date();
        this.description = description;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    public String toString() {
        return modifiedBy + " " + description + " on " + modifiedDate;
    }
    
    
}
