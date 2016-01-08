/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.ModifiedByInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class ModifiedBy extends UnicastRemoteObject implements ModifiedByInterface {
    
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
     * @throws java.rmi.RemoteException 
     */
    public ModifiedBy(String description, String modifiedBy, Date modifedDate) throws RemoteException {
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifedDate;
        this.description = description;
    }
    
    
    
    ///   MUTATOR METHODS   ///

    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return modifiedBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getModifiedBy() throws RemoteException {
        return modifiedBy;
    }

    /**
     * @return modifiedDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getModifiedDate() throws RemoteException {
        return modifiedDate;
    }
    
    /**
     * @return description
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getDescription() throws RemoteException {
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