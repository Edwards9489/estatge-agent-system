/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Dwayne
 */
public interface User extends Remote {
    boolean isUser(String username, String password) throws RemoteException;
    int getEmployeeRef() throws RemoteException;
    int getPersonRef() throws RemoteException;
    String getUsername() throws RemoteException;
    String getPassword() throws RemoteException;
    String getOfficeCode() throws RemoteException;
    boolean getPasswordReset() throws RemoteException;
    Boolean getRead() throws RemoteException;
    Boolean getWrite() throws RemoteException;
    Boolean getUpdate() throws RemoteException;
    Boolean getDelete() throws RemoteException;
    Boolean getEmployeeRead() throws RemoteException;
    Boolean getEmployeeWrite() throws RemoteException;
    Boolean getEmployeeUpdate() throws RemoteException;
    Boolean getEmployeeDelete() throws RemoteException;
}