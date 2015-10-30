/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;
import interfaces.User;

/**
 *
 * @author Dwayne
 */
public class UserImpl implements User {
    
    ///   VARIABLES   ///
        
    private final int employeeRef;
    private final String username;
    private String password;
    private boolean forcePasswordReset;
    private boolean read;
    private boolean write;
    private boolean update;
    private boolean employeeRead;
    private boolean employeeWrite;
    private boolean employeeUpdate;
    
    ///   CONSTRUCTORS ///
    
    public UserImpl(int employeeRef, String username, String password) {
        this.employeeRef = employeeRef;
        this.username = username;
        this.password = password;
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
    
    public void setUserPermissions(boolean read, boolean write, boolean update, boolean employeeRead, boolean employeeWrite, boolean employeeUpdate) {
        this.read = read;
        this.write = write;
        this.update = update;
        this.employeeRead = employeeRead;
        this.employeeWrite = employeeWrite;
        this.employeeUpdate = employeeUpdate;
    }
    
    public int getEmployeeRef() {
        return this.employeeRef;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public boolean getPasswordReset() {
        return this.forcePasswordReset;
    }
    
    public boolean getRead() {
        return this.read;
    }
    
    public boolean getWrite() {
        return this.write;
    }
    
    public boolean getUpdate() {
        return this.update;
    }
    
    public boolean getEmployeeRead() {
        return this.employeeRead;
    }
    
    public boolean getEmployeeWrite() {
        return this.employeeWrite;
    }
    
    public boolean getEmployeeUpdate() {
        return this.employeeUpdate;
    }
}