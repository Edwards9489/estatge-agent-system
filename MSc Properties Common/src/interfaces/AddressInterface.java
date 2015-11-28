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
public interface AddressInterface extends Remote {
    int getAddressRef() throws RemoteException;
    String getBuildingNumber() throws RemoteException;
    String getBuildingName() throws RemoteException;
    String getSubStreetNumber() throws RemoteException;
    String getSubStreet() throws RemoteException;
    String getStreetNumber() throws RemoteException;
    String getStreet() throws RemoteException;
    String getArea() throws RemoteException;
    String getTown() throws RemoteException;
    String getCountry() throws RemoteException;
    String getPostcode() throws RemoteException;
    Note getNote() throws RemoteException;
    String getComment() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
    String printAddress() throws RemoteException;
}