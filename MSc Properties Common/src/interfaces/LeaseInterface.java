/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface LeaseInterface extends AgreementInterface {
    List<LandlordInterface> getLandlords() throws RemoteException;
    PropertyInterface getProperty() throws RemoteException;
    int getPropertyRef() throws RemoteException;
    boolean isFullManagement() throws RemoteException;
    boolean isAlreadyLandlord(int landlordRef) throws RemoteException;
    double getExpenditure() throws RemoteException;
}