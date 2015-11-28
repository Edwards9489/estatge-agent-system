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
public interface ApplicationInterface extends Remote {
    int getApplicationRef() throws RemoteException;
    String getAppCorrName() throws RemoteException;
    Date getAppStartDate() throws RemoteException;
    Date getAppEndDate() throws RemoteException;
    String getAppStatusCode() throws RemoteException;
    AddressUsageInterface getCurrentApplicationAddress() throws RemoteException;
    String getCurrentApplicationAddressString() throws RemoteException;
    List<AddressUsageInterface> getApplicationAddressess() throws RemoteException;
    boolean isAppInterestedFlag() throws RemoteException;
    List<InvolvedPartyInterface> getHousehold() throws RemoteException;
    List<PropertyInterface> getPropertiesInterestedIn() throws RemoteException;
    Integer getTenancyRef() throws RemoteException;
    boolean isCurrent() throws RemoteException;
    boolean hasNote(int ref) throws RemoteException;
    Note getNote(int ref) throws RemoteException;
    List<Note> getNotes() throws RemoteException;
    boolean hasDocument(int ref) throws RemoteException;
    boolean hasDocument(String fileName) throws RemoteException;
    Document getDocument(int ref) throws RemoteException;
    List<Document> getDocuments() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
}