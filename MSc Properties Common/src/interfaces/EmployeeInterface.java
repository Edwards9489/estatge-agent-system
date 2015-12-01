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
public interface EmployeeInterface extends Remote {
    int getEmployeeRef() throws RemoteException;
    PersonInterface getPerson() throws RemoteException;
    int getPersonRef() throws RemoteException;
    String getOfficeCode() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    boolean hasNote(int ref) throws RemoteException;
    Note getNote(int ref) throws RemoteException;
    List<Note> getNotes() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
    User getUser() throws RemoteException;
    ContractInterface getContract() throws RemoteException;
    List<ContractInterface> getContracts() throws RemoteException;
}