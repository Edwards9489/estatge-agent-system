/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import javax.swing.JFrame;

/**
 *
 * @author Dwayne
 */
public class AppAdvancedSearch extends JFrame {
    private ClientImpl client = null;
    private IntegerListener listener = null;

    public AppAdvancedSearch(ClientImpl client) {
        setClient(client);
        layoutComponents();
    }
    
    private void layoutComponents() {
        
    }
    
    private void setClient(ClientImpl client) {
        if (this.client == null) {
            this.client = client;
        }
    }
    
    public void setListener(IntegerListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }
}
