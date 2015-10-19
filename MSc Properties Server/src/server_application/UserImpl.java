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
    private boolean read;
    private boolean write;
    private boolean delete;
    
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
    
}