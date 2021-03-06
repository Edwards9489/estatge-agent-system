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
public interface JobRoleBenefitInterface extends Remote {
    int getBenefitRef() throws RemoteException;
    Element getBenefit() throws RemoteException;
    String getBenefitCode() throws RemoteException;
    String getStringValue() throws RemoteException;
    Double getDoubleValue() throws RemoteException;
    Date getStartDate() throws RemoteException;
    Date getEndDate() throws RemoteException;
    boolean isCurrent() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    String getJobRoleCode() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    Note getNote() throws RemoteException;
    String getComment() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
    boolean isSalaryBenefit() throws RemoteException;
}