/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 *
 * @author Dwayne
 */
public interface Server extends Remote {
    void   register(Client c) throws RemoteException;
    void unregister(Client c) throws RemoteException;
    void uploadDocument(String fileName, byte[] buffer, String createdBy) throws RemoteException, SQLException;
    boolean isAlive() throws RemoteException;
    boolean isUser(String username, String password) throws RemoteException;
    byte[] downloadDocument(String fileName, String createdBy) throws RemoteException, SQLException;
}