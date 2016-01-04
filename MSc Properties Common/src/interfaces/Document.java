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
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface Document extends Remote {
    int getDocumentRef() throws RemoteException;
    String getCurrentDocumentName() throws RemoteException;
    String getDocumentName(int version) throws RemoteException;
    File getDocument(int version) throws RemoteException;
    List<File> getPreviousVersions() throws RemoteException;
    boolean hasVersion(int version) throws RemoteException;
    String getDocumentPath(int version) throws RemoteException;
    String getFilePath(int version) throws RemoteException;
    Note getNote() throws RemoteException;
    String getComment() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
}