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
public interface JobRoleInterface extends Remote {
    String getJobRoleCode() throws RemoteException;
    String getJobTitle() throws RemoteException;
    String getJobDescription() throws RemoteException;
    Element getJobRequirement(String requirementCode) throws RemoteException;
    List<Element> getJobRequirements() throws RemoteException;
    boolean isFullTime() throws RemoteException;
    double getSalary() throws RemoteException;
    boolean isCurrent() throws RemoteException;
    boolean hasBenefit(int benefitRef) throws RemoteException;
    boolean hasCurrentBenefit(String code) throws RemoteException;
    JobRoleBenefitInterface getJobBenefit(int benefitRef) throws RemoteException;
    List<JobRoleBenefitInterface> getBenefits() throws RemoteException;
    boolean hasBeenModified() throws RemoteException;
    boolean getRead() throws RemoteException;
    boolean getWrite() throws RemoteException;
    boolean getUpdate() throws RemoteException;
    boolean getDelete() throws RemoteException;
    boolean getEmployeeRead() throws RemoteException;
    boolean getEmployeeWrite() throws RemoteException;
    boolean getEmployeeUpdate() throws RemoteException;
    boolean getEmployeeDelete() throws RemoteException;
    String getLastModifiedBy() throws RemoteException;
    Date getLastModifiedDate() throws RemoteException;
    ModifiedByInterface getLastModification() throws RemoteException;
    List<ModifiedByInterface> getModifiedBy() throws RemoteException;
    boolean hasNote(int ref) throws RemoteException;
    Note getNote(int ref) throws RemoteException;
    List<Note> getNotes() throws RemoteException;
    String getCreatedBy() throws RemoteException;
    Date getCreatedDate() throws RemoteException;
}