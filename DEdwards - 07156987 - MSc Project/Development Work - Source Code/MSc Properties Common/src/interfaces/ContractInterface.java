/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.RemoteException;

/**
 *
 * @author Dwayne
 */
public interface ContractInterface extends AgreementInterface {
    EmployeeInterface getEmployee() throws RemoteException;
    int getEmployeeRef() throws RemoteException;
    JobRoleInterface getJobRole() throws RemoteException;
    String getJobRoleCode() throws RemoteException;
}