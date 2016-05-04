/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.login;

import client_gui.home_screen.HomeForm;
import client_application.ClientImpl;
import client_gui.employee.UpdateEmployeeSecurity;
import interfaces.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class LoginForm extends JFrame {
    private JButton okButton;
    private JButton cancelButton;
    private JTextField userField;
    private JPasswordField passwordField;
    private JLabel invalidPassword;
    private JLabel noUserFound;
    private Boolean invPassword;
    private Boolean invUser;
    private JTextField envField;
    private JLabel forgotPassword;
    
    public LoginForm() {
        this.layoutComponents();
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
    
    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');
        invalidPassword = new JLabel("Invalid Password Entered. Please Try Again...");
        noUserFound = new JLabel("Username does not exist. Please Try Again...");
        invPassword = false;
        invUser = false;
        envField = new JTextField(10);
        forgotPassword = new JLabel("Forgot your password?");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                String username = userField.getText();
                char[] password = passwordField.getPassword();
                String address = "127.0.0.1";
                String envir = null;
                if(!envField.getText().isEmpty()) {
                    envir = envField.getText();
                } else if (envField.getText().isEmpty()) {
                    envir = "LIVE";
                }
                
                
                try {
                    ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{address, envir, username, new String(password)});
                    if (client != null && client.isServerSet()){
                        if(client.isUser(username, new String(password))) {
                            User user = client.getUser();
                            if (user != null && user.getPasswordReset()) {
                                UpdateEmployeeSecurity securityGUI = new UpdateEmployeeSecurity(client);
                                securityGUI.setVisible(true);
                            } else {
                                HomeForm home = new HomeForm(client);
                                home.setVisible(true);
                                setVisible(false);
                            }
                        } else {
                            invUser = true;
                            layoutComponents();
                        }
                    }
                } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println(username + " : " + new String(password));
                
                // by wrapping Array of chars in a String it allows you to read the password
                // usually when you do sys out on Array of chars it outputs reference to Array object
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        
        forgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
                forgotPasswordForm.setVisible(true);
                setVisible(false);
            }
        });

        this.setSize(300, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("MSc Properties Login");
        
        
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
        controlsPanel.add(new JLabel("Password: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(passwordField, gc);
        
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
        
        ////////// NEXT ROW //////////
        if (invUser || invPassword) {
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            if (invUser) {
                controlsPanel.add(noUserFound, gc);
            } else if (invPassword) {
                controlsPanel.add(invalidPassword, gc);
            }
        }
        
        ////////// NEXT ROW //////////
        gc.gridx = 1;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(forgotPassword, gc);

//        gc.gridx++;
//        gc.anchor = GridBagConstraints.WEST;
//        gc.insets = new Insets(0, 0, 0, 5);
//        controlsPanel.add(envField, gc);

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