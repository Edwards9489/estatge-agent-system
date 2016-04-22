/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Dwayne
 */
public class OKDialog {
    
    public static int okDialog(Component frame, String message, String title) {
        Object[] options = {"OK"};
        return JOptionPane.showOptionDialog(frame, message, title, JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
}
