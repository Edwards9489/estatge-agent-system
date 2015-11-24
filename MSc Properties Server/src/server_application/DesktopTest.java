/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class DesktopTest {
    public static void main(String[] args) {
        try {
            System.out.println(Desktopt.openFile("D:\\TESTING\\TEST3.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(DesktopTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
