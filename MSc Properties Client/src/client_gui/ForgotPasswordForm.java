/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import client_application.ClientImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class ForgotPasswordForm extends JFrame {
    private JButton okButton;
    private JButton cancelButton;
    private JTextField userField;
    private JTextField emailField;
    private JTextField empRefField;
    private JTextField answerField;
    private JTextField envField;
    
    public ForgotPasswordForm() {
        this.layoutComponents();
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ForgotPasswordForm().setVisible(true);
            }
        });
    }
    
    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        userField = new JTextField(10);
        emailField = new JTextField(10);
        empRefField = new JTextField(10);
        answerField = new JTextField(10);
        envField = new JTextField(10);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                String user = userField.getText();
                String email = emailField.getText();
                String empRef = empRefField.getText();
                String answer = answerField.getText();
                String address = "127.0.0.1";
                String envir = null;
                
                System.out.println("TEST1 - Employee: " + empRef);
                
                if(!envField.getText().isEmpty()) {
                    envir = envField.getText();
                } else if (envField.getText().isEmpty()) {
                    envir = "LIVE";
                }
                
                
                try {
                    boolean result = ClientImpl.resetClient(new String[]{address, envir, user, email, empRef, answer});
                    if (result){
                        System.out.println("Password Reset: " + user);
                        LoginForm login = new LoginForm();
                        login.setVisible(true);
                        setVisible(false);
                    } else {
                        System.out.println("Password Reset Failed");
                        layoutComponents();
                    }
                } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
                    Logger.getLogger(ForgotPasswordForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        setSize(300, 350);
        
        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("MSc Properties Forgot Password");
        
        
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        controlsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel("Username: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(userField, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel("Email: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(emailField, gc);
        
        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel("Employee Ref: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(empRefField, gc);
        
        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel("Memorable Location: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(answerField, gc);
        
        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel("Environment: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(envField, gc);

        ////////// BUTTONS PANEL //////////
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        
        
        // Add sub panels to dialog
        
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}