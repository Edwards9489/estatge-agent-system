/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public interface Document extends Remote {
    int getDocumentRef() throws RemoteException;
    String getDocumentName() throws RemoteException;
    File getDocument() throws RemoteException;
    String getDocumentPath() throws RemoteException;
    Note getNote() throws RemoteException;
    String getComment() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
}