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
/**
 * Class used for representing an authenticated user in the system.
 */
public class RMILoginPrincipal
        implements java.security.Principal {

    /**
     * The username
     */
    private String username;

  ////////////////////////
    /**
     * Class constructor.
     *
     * @param username The username of the user.
     */
    public RMILoginPrincipal(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username.
     */
    public String getName() {
        return username;
    }
}
