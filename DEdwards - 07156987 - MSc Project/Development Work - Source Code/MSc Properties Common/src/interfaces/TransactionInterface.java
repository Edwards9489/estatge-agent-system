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
public interface TransactionInterface extends Remote {
    int getTransactionRef() throws RemoteException;
    int getAccountRef() throws RemoteException;
    int getFromRef() throws RemoteException;
    int getToRef() throws RemoteException;
    double getAmount() throws RemoteException;
    Date getTransactionDate() throws RemoteException;
    Note getNote() throws RemoteException;
    String getComment() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
    boolean isDebit() throws RemoteException;
}