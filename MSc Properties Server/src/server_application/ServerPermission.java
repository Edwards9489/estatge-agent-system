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
 * Permission to call a method on the server.
 *
 * Actually, all the functionality is already provided in the base class
 * BasicPermission. Thus, all that this class is doing is serving as a type
 * name.
 *
 * Permissions to call methods are specified as follows:
 *
 * authrmi.permissions.ServerPermission "methodName" -> authorizes the call of a
 * certain method authrmi.permissions.ServerPermission "*" -> authorizes the
 * call of all methods
 */
public class ServerPermission
        extends java.security.BasicPermission {

    /**
     * Creates a permission with a name.
     */
    public ServerPermission(String name) {
        super(name);
    }

    /**
     * Creates a permission with a name and an action string. The action string
     * is not used, but this constructor must exist so that the policy file
     * parser works.
     */
    public ServerPermission(String name, String actions) {
        super(name, actions);
    }
}
