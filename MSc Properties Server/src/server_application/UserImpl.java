/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.User;
import java.rmi.RemoteException;

/**
 *
 * @author Dwayne
 */
public class UserImpl implements User {
    
    ///   VARIABLES   ///
        
    private final int employeeRef;
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
     * @param username
     * @param password
     * @param officeCode
     * @throws RemoteException 
     */
    public UserImpl(int employeeRef, String username, String password, String officeCode) throws RemoteException {
        this.employeeRef = employeeRef;
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
     */
    @Override
    public int getEmployeeRef() {
        return this.employeeRef;
    }
    
    /**
     * 
     * @return username
     */
    @Override
    public String getUsername() {
        return this.username;
    }
    
    /**
     * 
     * @return password
     */
    @Override
    public String getPassword() {
        return this.password;
    }
    
    /**
     * 
     * @return officeCode
     */
    @Override
    public String getOfficeCode() {
        return this.officeCode;
    }
    
    /**
     * 
     * @return forcePasswordReset
     */
    @Override
    public boolean getPasswordReset() {
        return this.forcePasswordReset;
    }
    
    /**
     * 
     * @return read
     */
    @Override
    public boolean getRead() {
        return this.read;
    }
    
    /**
     * 
     * @return write
     */
    @Override
    public boolean getWrite() {
        return this.write;
    }
    
    /**
     * 
     * @return update
     */
    @Override
    public boolean getUpdate() {
        return this.update;
    }
    
    /**
     * 
     * @return employeeRead
     */
    @Override
    public boolean getEmployeeRead() {
        return this.employeeRead;
    }
    
    /**
     * 
     * @return employeeWrite
     */
    @Override
    public boolean getEmployeeWrite() {
        return this.employeeWrite;
    }
    
    /**
     * 
     * @return employeeUpdate
     */
    @Override
    public boolean getEmployeeUpdate() {
        return this.employeeUpdate;
    }
}