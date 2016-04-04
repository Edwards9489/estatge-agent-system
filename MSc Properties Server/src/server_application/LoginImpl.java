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
import interfaces.LoginInterface;
import interfaces.Server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.security.auth.*;
import java.util.*;

/**
 * Implements the server object that allows clients to login.
 */
public class LoginImpl extends UnicastRemoteObject implements LoginInterface {

    /**
     * The real server object
     */
    private final Server myServer;

  ////////////////////////
    /**
     * Class constructor.
     *
     * @param theServer The real server object.
     * @throws RemoteException
     */
    public LoginImpl(Server theServer) throws RemoteException {
        myServer = theServer;
    }

    /**
     * Allows a client to login and get an interface to the server.
     * @throws RemoteException
     */
    @Override
    public Server login(String username, String password) throws RemoteException, SecurityException {
        // Creates a subject that represents the user
        Subject user = new Subject();
        user.getPrincipals().add(new RMILoginPrincipal(username));

        // Check if this user can login. If not, an exception is thrown
        // Checks if the user is known and the password matches
        String realPassword = null;

        try {
            Properties passwords = new Properties();
            passwords.load(new java.io.FileInputStream(Constants.PASS_FILENAME));

            realPassword = passwords.getProperty(username);
        } catch (java.io.IOException e) {
            throw new InvalidUserException(username);
        }

        if ((realPassword == null) || !realPassword.equals(password)) {
            throw new InvalidUserException(username);
        }

        // Return a reference to a proxy object that encapsulates the access
        // to the server, for this client
        return new ServerProxy(user, myServer);
    }
}