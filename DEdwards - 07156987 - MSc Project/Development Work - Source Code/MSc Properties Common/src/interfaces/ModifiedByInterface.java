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
public interface ModifiedByInterface extends Remote {
    String getModifiedBy() throws RemoteException;
    Date getModifiedDate() throws RemoteException;
    String getDescription() throws RemoteException;
}