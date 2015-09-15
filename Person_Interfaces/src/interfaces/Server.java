/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public interface Server extends Remote {
    
    void createPerson(Element title, String forename, String surname, Date dateOfBirth, Element gender) throws RemoteException;
    void   register(Client c) throws RemoteException;
    void unregister(Client c) throws RemoteException;
    boolean isAlive() throws RemoteException;
}