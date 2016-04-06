/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface Client extends Remote {
    boolean isAlive() throws RemoteException;
    String getUsername() throws RemoteException;
    String getOfficeCode() throws RemoteException;
    void updateUserTenancies(List<AgreementInterface> agreements) throws RemoteException;
    void updateUserLeases(List<AgreementInterface> agreements) throws RemoteException;
    void updateUserRentAccounts(List<AccountInterface> rentAccounts) throws RemoteException;
}