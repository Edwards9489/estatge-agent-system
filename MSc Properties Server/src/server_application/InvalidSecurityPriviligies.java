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
 * Exception that represents an user that was unable to authenticate.
 */
public class InvalidSecurityPriviligies extends SecurityException {

    /**
     * The name of the user
     */
    private String username;

  //////////////////////////////
    /**
     * Class constructor.
     *
     * @param username The name of the user.
     */
    public InvalidSecurityPriviligies(String username) {
        this.username = username;
    }

    /**
     * Converts the exception to a string.
     * @return exception
     */
    @Override
    public String toString() {
        return "User does not have privileges to invoke this method - user: " + username;
    }
}
