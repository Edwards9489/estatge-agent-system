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
    
    private final int personRef;
    private String username;
    private String password;
    
    public UserImpl(int personRef, String username, String password) {
        this.personRef = personRef;
        this.username = username;
        this.password = password;
    }
    
    public boolean isUser(String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }
}