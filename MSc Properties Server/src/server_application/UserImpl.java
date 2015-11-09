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
    
    public UserImpl(int employeeRef, String username, String password, String officeCode) throws RemoteException {
        this.employeeRef = employeeRef;
        this.username = username;
        this.password = password;
        this.officeCode = officeCode;
    }
    
    @Override
    public boolean isUser(String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }
    
    public void setPasswordReset(boolean reset) {
        this.forcePasswordReset = reset;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }
    
    public void setUserPermissions(boolean read, boolean write, boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate) {
        this.read = read;
        this.write = write;
        this.update = update;
        this.employeeRead = employeeRead;
        this.employeeWrite = employeeWrite;
        this.employeeUpdate = employeeUpdate;
    }
    
    @Override
    public int getEmployeeRef() {
        return this.employeeRef;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String getOfficeCode() {
        return this.officeCode;
    }
    
    @Override
    public boolean getPasswordReset() {
        return this.forcePasswordReset;
    }
    
    @Override
    public boolean getRead() {
        return this.read;
    }
    
    @Override
    public boolean getWrite() {
        return this.write;
    }
    
    @Override
    public boolean getUpdate() {
        return this.update;
    }
    
    @Override
    public boolean getEmployeeRead() {
        return this.employeeRead;
    }
    
    @Override
    public boolean getEmployeeWrite() {
        return this.employeeWrite;
    }
    
    @Override
    public boolean getEmployeeUpdate() {
        return this.employeeUpdate;
    }
}