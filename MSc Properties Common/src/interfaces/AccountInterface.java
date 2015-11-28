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
public interface AccountInterface extends Remote {
    int getAccRef() throws RemoteException;
    String getAccName() throws RemoteException;
    Date getStartDate() throws RemoteException;
    Date getEndDate() throws RemoteException;
    double getBalance() throws RemoteException;
    String getOfficeCode() throws RemoteException;
    boolean isNegativeInd() throws RemoteException;
    boolean isCurrent() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    boolean hasTransaction(int ref) throws RemoteException;
    boolean hasNote(int ref) throws RemoteException;
    boolean hasDocument(int ref) throws RemoteException;
    boolean hasDocument(String fileName) throws RemoteException;
    Document getDocument(int ref) throws RemoteException;
    List<Document> getDocuments() throws RemoteException;
    TransactionInterface getTransaction(int ref) throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    Note getNote(int ref) throws RemoteException;
    List<Note> getNotes() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
    List<TransactionInterface> getTransactions() throws RemoteException;
}