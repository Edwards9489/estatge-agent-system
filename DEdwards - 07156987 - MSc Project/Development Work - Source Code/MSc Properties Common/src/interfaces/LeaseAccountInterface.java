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
public interface LeaseAccountInterface extends AccountInterface {
    LeaseInterface getLease() throws RemoteException;
    int getLeaseRef() throws RemoteException;
    double getExpenditure() throws RemoteException;
}