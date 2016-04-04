/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.rmi.Remote;

/**
 *
 * @author Dwayne
 */
/**
 * Interface for client users to login.
 */
public interface LoginInterface extends Remote {

    /**
     * Method that allows clients to login, returning an interface to the
     * server.
     *
     * @param username The name of the user.
     * @param password The password of the user.
     * @return A reference to a proxy of the server object.
     * @throws java.rmi.RemoteException
     * @throws SecurityException If the client is not allowed to login.
     */
    public Server login(String username, String password) throws java.rmi.RemoteException, SecurityException;
}