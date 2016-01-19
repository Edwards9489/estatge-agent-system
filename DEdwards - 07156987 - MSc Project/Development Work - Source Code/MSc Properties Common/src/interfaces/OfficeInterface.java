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
public interface OfficeInterface extends Remote {
    String getOfficeCode() throws RemoteException;
    AddressInterface getAddress() throws RemoteException;
    Date getStartDate() throws RemoteException;
    Date getEndDate() throws RemoteException;
    boolean hasAgreement(int ref) throws RemoteException;
    boolean hasAgreements() throws RemoteException;
    AgreementInterface getAgreement(int ref) throws RemoteException;
    List<AgreementInterface> getAgreements() throws RemoteException;
    boolean hasAccount(int ref) throws RemoteException;
    boolean hasAccounts() throws RemoteException;
    AccountInterface getAccount(int ref) throws RemoteException;
    List<AccountInterface> getAccounts() throws RemoteException;
    ContactInterface getContact(int ref) throws RemoteException;
    List<ContactInterface> getContacts() throws RemoteException;
    boolean hasContact(int ref) throws RemoteException;
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