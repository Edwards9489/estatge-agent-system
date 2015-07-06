/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;
import client_gui.*;

/**
 *
 * @author Dwayne
 */
public class Main {
    
    private static Main test;
    
    public Main() {
        test = this;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Home_Form form = new Home_Form(test);
        form.setVisible(true);
        
    }
}