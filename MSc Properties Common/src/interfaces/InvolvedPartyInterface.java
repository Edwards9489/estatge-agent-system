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
public interface InvolvedPartyInterface extends Remote {
    int getInvolvedPartyRef() throws RemoteException;
    int getApplicationRef() throws RemoteException;
    int getPersonRef() throws RemoteException;
    PersonInterface getPerson() throws RemoteException;
    Date getStartDate() throws RemoteException;
    Date getEndDate() throws RemoteException;
    Element getEndReason() throws RemoteException;
    Element getRelationship() throws RemoteException;
    boolean isJointInd() throws RemoteException;
    boolean isMainInd() throws RemoteException;
    boolean isCurrent() throws RemoteException;
    boolean isOver18() throws RemoteException;
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