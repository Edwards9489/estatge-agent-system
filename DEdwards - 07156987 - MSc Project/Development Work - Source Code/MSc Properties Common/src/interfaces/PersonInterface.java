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
public interface PersonInterface extends Remote {
    int getPersonRef() throws RemoteException;
    Element getTitle() throws RemoteException;
    String getForename() throws RemoteException;
    String getMiddleNames() throws RemoteException;
    String getSurname() throws RemoteException;
    String getName() throws RemoteException;
    Date getDateOfBirth() throws RemoteException;
    String getNI() throws RemoteException;
    Element getGender() throws RemoteException;
    boolean isOver18() throws RemoteException;
    Element getMaritalStatus() throws RemoteException;
    Element getEthnicOrigin() throws RemoteException;
    Element getLanguage() throws RemoteException;
    Element getNationality() throws RemoteException;
    Element getSexuality() throws RemoteException;
    Element getReligion() throws RemoteException;
    AddressUsageInterface getCurrentAddress() throws RemoteException;
    String getCurrentAddressString() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    boolean hasNote(int ref) throws RemoteException;
    Note getNote(int ref) throws RemoteException;
    List<Note> getNotes() throws RemoteException;
    boolean hasDocument(int ref) throws RemoteException;
    boolean hasDocument(String fileName) throws RemoteException;
    Document getDocument(int ref) throws RemoteException;
    List<Document> getDocuments() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
    boolean hasContact(int contactRef) throws RemoteException;
    List<ContactInterface> getContacts() throws RemoteException;
    List<AddressUsageInterface> getAddresses() throws RemoteException;
}