/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Dwayne
 */
public interface Server extends Remote {
    
    void createPerson(String title, String forename, String surname, int year, int month, int day, String gender) throws RemoteException;
}