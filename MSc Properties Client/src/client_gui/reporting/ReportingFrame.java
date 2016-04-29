/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.reporting;

import client_application.ClientImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Dwayne
 */
public class ReportingFrame extends JFrame {
    
    private JLabel title;
    private ClientImpl client = null;
    
    public ReportingFrame(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        layoutComponents();
        createMenuBar();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    private void layoutComponents() {
        title = new JLabel("MSc Properties");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        setLayout(new BorderLayout());
        
        this.setSize(1200, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem userAccount = new JMenuItem("User Account");
        JMenuItem changeUser = new JMenuItem("Change User");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        fileMenu.add(userAccount);
        fileMenu.add(changeUser);
        fileMenu.addSeparator(); // Is the faint lines between grouped menu items
        fileMenu.add(exitItem);
        
        
        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        
        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        
        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        
        // Set up Mnemonics for Menus
        
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        
        
        // Set up Accelerators
        
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        changeUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        userAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        manualItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        
        
        //Set up ActionListeners
        
        changeUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                
            }
        });
        
        userAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                
                int action = JOptionPane.showConfirmDialog(ReportingFrame.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                
                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }
}