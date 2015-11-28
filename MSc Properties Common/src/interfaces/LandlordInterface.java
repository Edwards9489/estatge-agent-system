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
public interface LandlordInterface extends Remote {
    int getLandlordRef() throws RemoteException;
    PersonInterface getPerson() throws RemoteException;
    int getPersonRef() throws RemoteException;
    boolean hasLease(int ref) throws RemoteException;
    List<LeaseInterface> getLeases() throws RemoteException;
    LeaseInterface getLease(int ref) throws RemoteException;
    boolean hasNote(int ref) throws RemoteException;
    Note getNote(int ref) throws RemoteException;
    List<Note> getNotes() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
}