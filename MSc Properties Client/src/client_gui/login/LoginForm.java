/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.login;

import client_gui.home_screen.HomeForm;
import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.OKDialog;
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
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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
    private JLabel forgotPassword;

    private PrefsDialog prefsDialog;
    private Preferences prefs;
    private String ip;
    private int environment;

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
        setJMenuBar(createMenuBar());
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');
        forgotPassword = new JLabel("Forgot your password?");

        prefsDialog = new PrefsDialog(this);

        prefs = Preferences.userRoot().node("db");

        prefsDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void preferenceSet(String serverAddr, int environment) {
                System.out.println(serverAddr + " : " + environment);
                prefs.put("IP Address", serverAddr);
                prefs.putInt("Environment", environment);
                LoginForm.this.ip = serverAddr;
                LoginForm.this.environment = environment;
            }
        });

        ip = prefs.get("IP Address", "127.0.0.1");
        environment = prefs.getInt("Environment", 0);

        prefsDialog.setDefaults(ip, environment);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String envir = null;
                String username = userField.getText();
                char[] password = passwordField.getPassword();
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
                System.out.println("TEST 5" + username != null);
                System.out.println("TEST 6" + !username.isEmpty());
                System.out.println("TEST 7" + password != null);
                if (ip != null && !ip.isEmpty() && envir != null && !envir.isEmpty() && username != null && !username.isEmpty() && password != null) {
                    try {
                        ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{ip, envir, username, new String(password)});
                        if (client != null && client.isServerSet()) {
                            if (client.isUser(username, new String(password))) {
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
                                System.out.println("Login Failed");
                                String message = "There is some errors with the information supplied to LOGIN \nPlease check the information supplied (including Settings) and try again";
                                String title = "Error";
                                OKDialog.okDialog(LoginForm.this, message, title);
                            }
                        } else {
                            System.out.println("Registration with Server Failed");
                            String message = "There is some errors with the information supplied to LOGIN \nPlease check the information supplied (including Settings) and try again";
                            String title = "Error";
                            OKDialog.okDialog(LoginForm.this, message, title);
                        }
                    } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException ex) {
                        Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Information Supplied Error");
                    String message = "There is some errors with the information supplied to LOGIN \nPlease check the information supplied (including Settings) and try again";
                    String title = "Error";
                    OKDialog.okDialog(LoginForm.this, message, title);
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
                dispose();
            }
        });

        this.setSize(300, 300);
        this.setMinimumSize(new Dimension(300, 300));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

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
        gc.gridx = 1;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(forgotPassword, gc);

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

                int action = JOptionPane.showConfirmDialog(LoginForm.this,
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
