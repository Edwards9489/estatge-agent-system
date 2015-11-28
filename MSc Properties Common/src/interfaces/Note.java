/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface Note extends Remote {
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    String getNote() throws RemoteException;
    int getRef() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
}