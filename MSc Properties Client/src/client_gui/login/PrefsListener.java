/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.login;

/**
 *
 * @author Dwayne
 */
public interface PrefsListener {
    public void preferenceSet(String serverAddr, int environment);
}