/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.RemoteObservableInterface;
import interfaces.RemoteObserver;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class RemoteObservable extends UnicastRemoteObject implements RemoteObservableInterface {
    private final List<RemoteObserver> observers = new ArrayList<RemoteObserver>();
    
    public RemoteObservable() throws RemoteException {
        super();
    }
    
    @Override
    public void addObserver(RemoteObserver ro) throws RemoteException {
        observers.add(ro);
    }
    
    protected void notifyObservers(Remote remoteObject) {
        for(RemoteObserver ro : observers) {
            try {
                ro.update(remoteObject);
            } catch (RemoteException ex)
            {}
        }
    }
}