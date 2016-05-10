/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.login;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.OKDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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
    
    private PrefsDialog prefsDialog;
    private Preferences prefs;
    private String ip;
    private int environment;
    
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
        
        setJMenuBar(createMenuBar());
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        userField = new JTextField(10);
        emailField = new JTextField(10);
        empRefField = new JTextField(10);
        answerField = new JTextField(10);
        
        prefsDialog = new PrefsDialog(this);
        
        prefs = Preferences.userRoot().node("db");
        
        prefsDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void preferenceSet(String serverAddr, int environment) {
                System.out.println(serverAddr + " : " + environment);
                prefs.put("IP Address", serverAddr);
                prefs.putInt("Environment", environment);
            }
        });
        
        ip = prefs.get("IP Address", "127.0.0.1");
        environment = prefs.getInt("Environment", 1);
        
        prefsDialog.setDefaults(ip, environment);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                String user = userField.getText();
                String email = emailField.getText();
                String empRef = empRefField.getText();
                String answer = answerField.getText();
                String envir = null;
                
                switch (environment) {
                    case 0:
                        envir = "LIVE";
                        break;
                        
                    case 1:
                        envir = "TEST";
                        break;
                        
                    case 2:
                        envir = "TRAIN";
                        break;
                        
                }
                System.out.println("TEST 1" + ip != null);
                System.out.println("TEST 2" + !ip.isEmpty());
                System.out.println("TEST 3" + envir != null);
                System.out.println("TEST 4" + !envir.isEmpty());
                System.out.println("TEST 5" + user != null);
                System.out.println("TEST 6" + !user.isEmpty());
                System.out.println("TEST 7" + email != null);
                System.out.println("TEST 8" + !email.isEmpty());
                System.out.println("TEST 9" + empRef != null);
                System.out.println("TEST 10" + !empRef.isEmpty());
                System.out.println("TEST 11" + answer != null);
                System.out.println("TEST 12" + !answer.isEmpty());
                if (ip != null && !ip.isEmpty() && envir != null && !envir.isEmpty() && user != null && !user.isEmpty() && email != null && !email.isEmpty() && empRef != null && !empRef.isEmpty() && answer != null && !answer.isEmpty()) {
                    try {
                        boolean result = ClientImpl.resetClient(new String[]{ip, envir, user, email, empRef, answer});
                        if (result){
                            System.out.println("Password Reset: " + user);
                            LoginForm login = new LoginForm();
                            login.setVisible(true);
                            setVisible(false);
                        } else {
                            System.out.println("Password Reset Failed");
                            String message = "There is some errors with the information supplied to RESET PASSWORD \nPlease check the information supplied (including Settings) and try again";
                            String title = "Error";
                            OKDialog.okDialog(ForgotPasswordForm.this, message, title);
                        }
                    } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
                        Logger.getLogger(ForgotPasswordForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Information Supplied Error");
                    String message = "There is some errors with the information supplied to RESET PASSWORD \nPlease check the information supplied (including Settings) and try again";
                    String title = "Error";
                    OKDialog.okDialog(ForgotPasswordForm.this, message, title);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
                setVisible(false);
                dispose();
            }
        });

        this.setSize(300, 350);
        this.setMinimumSize(new Dimension(300, 350));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
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

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exitItem);
        
        
        // Prefrences Menu
        JMenu settingsMenu = new JMenu("Settings");

        JMenuItem prefsItem = new JMenuItem("Prefrences...");
        
        settingsMenu.add(prefsItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);

        // Set up Mnemonics for Menus
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // Set up Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        
        
        //Set up ActionListeners
        
        //File Menu
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                int action = JOptionPane.showConfirmDialog(ForgotPasswordForm.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        // Settings Menu
        
        prefsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });
        
        
        // Help Menu

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                AboutFrame about = new AboutFrame();
                about.setVisible(true);
            }
        });
        
        return menuBar;
    }
}