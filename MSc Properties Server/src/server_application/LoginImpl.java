/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

/**
 *
 * @author Dwayne
 */
import classes.InvalidUserException;
import interfaces.LoginInterface;
import interfaces.Server;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class LoginImpl extends UnicastRemoteObject implements LoginInterface {

    private final Server myServer;

    public LoginImpl(Server theServer) throws RemoteException {
        myServer = theServer;
    }

    
    @Override
    public Server login(String username, String password) throws RemoteException, SecurityException {
        
        if (myServer != null && myServer.isAlive()) {
            try {
                if (myServer.isUser(username, password)) {
                    
                    Server proxyServer = new ServerProxy(myServer.getUser(username), myServer);
                    return proxyServer;
                }
            } catch (IOException e) {
                throw new InvalidUserException(username);
            }
        } else {
            throw new RemoteException();
        }
        return null;
    }
    
    @Override
    public boolean resetPassword(String username, String email, int empRef, String answer) throws RemoteException {
        if (myServer != null && myServer.isAlive()) {
            try {
                if (myServer.forgotPassword(email, empRef, username, answer) > 0) {
                    return true;
                }
            } catch (IOException e) {
                throw new InvalidUserException(username);
            }
        } else {
            throw new RemoteException();
        }
        return false;
    }
}
