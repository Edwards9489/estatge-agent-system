/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Dwayne
 */
public interface User {
    boolean isUser(String username, String password);
    int getEmployeeRef();
    String getUsername();
    String getPassword();
    String getOfficeCode();
    boolean getPasswordReset();
    boolean getRead();
    boolean getWrite();
    boolean getUpdate();
    boolean getEmployeeRead();
    boolean getEmployeeWrite();
    boolean getEmployeeUpdate();
}