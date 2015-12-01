/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Dwayne
 */
public class UserImpl extends UnicastRemoteObject implements User {
    
    ///   VARIABLES   ///
        
    private final int employeeRef;
    private final int personRef;
    private final String username;
    private String password;
    private String officeCode;
    private boolean forcePasswordReset;
    private boolean read;
    private boolean write;
    private boolean update;
    private boolean employeeRead;
    private boolean employeeWrite;
    private boolean employeeUpdate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class UserImpl
     * @param employeeRef
     * @param personRef
     * @param username
     * @param password
     * @param officeCode
     * @throws RemoteException 
     */
    public UserImpl(int employeeRef, int personRef, String username, String password, String officeCode) throws RemoteException {
        this.employeeRef = employeeRef;
        this.personRef = personRef;
        this.username = username;
        this.password = password;
        this.officeCode = officeCode;
        this.forcePasswordReset = true;
    }
    
    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    @Override
    public boolean isUser(String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }
    
    /**
     * 
     * @param reset 
     */
    public void setPasswordReset(boolean reset) {
        this.forcePasswordReset = reset;
    }
    
    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 
     * @param officeCode 
     */
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }
    
    /**
     * 
     * @param read
     * @param write
     * @param update
     * @param employeeRead
     * @param employeeWrite
     * @param employeeUpdate 
     */
    public void setUserPermissions(boolean read, boolean write, boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate) {
        this.read = read;
        this.write = write;
        this.update = update;
        this.employeeRead = employeeRead;
        this.employeeWrite = employeeWrite;
        this.employeeUpdate = employeeUpdate;
    }
    
    /**
     * 
     * @return employeeRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getEmployeeRef() throws RemoteException {
        return this.employeeRef;
    }
    
    /**
     * 
     * @return employeeRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPersonRef() throws RemoteException {
        return this.personRef;
    }
    
    /**
     * 
     * @return username
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getUsername() throws RemoteException {
        return this.username;
    }
    
    /**
     * 
     * @return password
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getPassword() throws RemoteException {
        return this.password;
    }
    
    /**
     * 
     * @return officeCode
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getOfficeCode() throws RemoteException {
        return this.officeCode;
    }
    
    /**
     * 
     * @return forcePasswordReset
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean getPasswordReset() throws RemoteException {
        return this.forcePasswordReset;
    }
    
    /**
     * 
     * @return read
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean getRead() throws RemoteException {
        return this.read;
    }
    
    /**
     * 
     * @return write
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean getWrite() throws RemoteException {
        return this.write;
    }
    
    /**
     * 
     * @return update
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean getUpdate() throws RemoteException {
        return this.update;
    }
    
    /**
     * 
     * @return employeeRead
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean getEmployeeRead() throws RemoteException {
        return this.employeeRead;
    }
    
    /**
     * 
     * @return employeeWrite
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean getEmployeeWrite() throws RemoteException {
        return this.employeeWrite;
    }
    
    /**
     * 
     * @return employeeUpdate
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean getEmployeeUpdate() throws RemoteException {
        return this.employeeUpdate;
    }
}