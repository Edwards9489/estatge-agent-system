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
public interface TenancyInterface extends AgreementInterface {
    int getPropertyRef() throws RemoteException;
    PropertyInterface getProperty() throws RemoteException;
    int getApplicationRef() throws RemoteException;
    ApplicationInterface getApplication() throws RemoteException;
    Element getTenType() throws RemoteException;
    double getRent() throws RemoteException;
    double getCharges() throws RemoteException;
    double getExpectedRevenue() throws RemoteException;
}