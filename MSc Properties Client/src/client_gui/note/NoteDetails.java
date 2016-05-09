/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.note;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.DetailsPanel;
import client_gui.OKDialog;
import client_gui.modifications.ModPanel;
import client_gui.employee.EmployeeDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.login.LoginForm;
import interfaces.Note;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class NoteDetails extends JFrame {

    private ClientImpl client = null;
    private Note note = null;
    private final String noteType;
    private String objectCode = null;
    private int objectRef = -1;
    private JButton closeButton;
    private JTextArea textArea;
    private JPanel mainPanel;
    private JPanel controlsPanel;
    private ModPanel modPanel;
    private JTabbedPane tabbedPane;

    public NoteDetails(ClientImpl client, Note note, String noteType, int objectRef) {
        super("MSc Properties");
        setClient(client);
        setNote(note);
        this.noteType = noteType;
        this.objectRef = objectRef;
        this.layoutComponents();
    }

    public NoteDetails(ClientImpl client, Note note, String noteType, String objectCode) {
        super("MSc Properties");
        setClient(client);
        setNote(note);
        this.noteType = noteType;
        this.objectCode = objectCode;
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    // Use of singleton pattern to ensure only one Note is initiated
    private void setNote(Note note) {
        if (this.note == null) {
            this.note = note;
        }
    }

    private void layoutComponents() {
        
        setJMenuBar(createMenuBar());

        closeButton = new JButton("Close");
        modPanel = new ModPanel("Modifications");
        tabbedPane = new JTabbedPane();
        textArea = new JTextArea(9, 43);
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                setVisible(false);
                dispose();
            }
        });
        
        try {
            modPanel.setData(note.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            textArea.setText(note.getNote());
        } catch (RemoteException ex) {
            Logger.getLogger(NoteDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setSize(550, 360);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("View Note");
        
        mainPanel = new JPanel();
        controlsPanel = new JPanel();
        controlsPanel.setSize(450, 200);
        
        mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.setLayout(new BorderLayout());
        controlsPanel.add(textArea, BorderLayout.CENTER);
        
        mainPanel.add(controlsPanel, BorderLayout.CENTER);
        
        try {
            JPanel mods = new DetailsPanel(note.getCreatedBy(), note.getCreatedDate(), note.getLastModifiedBy(), note.getLastModifiedDate());
            mainPanel.add(mods, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(NoteDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(closeButton);
        
        
        ///////// TABBED PANE /////////////
        
        tabbedPane.add(mainPanel, "Details");
        tabbedPane.add(modPanel, "Modifications");
        
        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    private void refresh() {
        try {
            modPanel.setData(note.getModifiedBy());
            modPanel.refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(NoteDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        
        // Actions Menu
        JMenu actionsMenu = new JMenu("Actions");

        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(updateItem);
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(actionsMenu);
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
        
        //File Menu
        
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int action = JOptionPane.showConfirmDialog(NoteDetails.this,
                        "Do you really want to change user?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                
                if (action == JOptionPane.OK_OPTION) {
                    System.gc();
                    Window windows[] = Window.getWindows(); 
                    for (int i=0; i<windows.length; i++) {
                        windows[i].dispose(); 
                        windows[i]=null;
                    }
                    new LoginForm().setVisible(true);
                    dispose();
                }
            }
        });

        userAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                UpdateEmployeeSecurity securityGUI = new UpdateEmployeeSecurity(client);
                securityGUI.setVisible(true);
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                int action = JOptionPane.showConfirmDialog(NoteDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(NoteDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        
        
        // Actions Menu
        
        updateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (objectCode != null) {
                    UpdateNote updateNote = new UpdateNote(client, note, noteType, objectCode);
                    updateNote.setVisible(true);
                } else if (objectRef > 0) {
                    UpdateNote updateNote = new UpdateNote(client, note, noteType, objectRef);
                    updateNote.setVisible(true);
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Note " + note.getReference() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Note Delete - Yes button clicked");
                        int result = 0;
                        switch (noteType) {
                            case "Person":
                                client.deletePersonNote(objectRef, note.getReference());
                                break;
                                
                            case "Office":
                                client.deleteOfficeNote(objectCode, note.getReference());
                                break;
                                
                            case "Involved Party":
                                client.deleteInvolvedPartyNote(objectRef, note.getReference());
                                break;
                                
                            case "Application":
                                client.deleteApplicationNote(objectRef, note.getReference());
                                break;
                                
                            case "Employee":
                                client.deleteEmployeeNote(objectRef, note.getReference());
                                break;
                                
                            case "Landlord":
                                client.deleteLandlordNote(objectRef, note.getReference());
                                break;
                                
                            case "Property":
                                client.deletePropertyNote(objectRef, note.getReference());
                                break;
                                
                            case "Job Role":
                                client.deleteJobRoleNote(objectCode, note.getReference());
                                break;
                                
                            case "Tenancy":
                                client.deleteTenancyNote(objectRef, note.getReference());
                                break;
                                
                            case "Lease":
                                client.deleteLeaseNote(objectRef, note.getReference());
                                break;
                                
                            case "Contract":
                                client.deleteContractNote(objectRef, note.getReference());
                                break;
                                
                            case "Rent Account":
                                client.deleteRentAccNote(objectRef, note.getReference());
                                break;
                                
                            case "Lease Account":
                                client.deleteLeaseAccNote(objectRef, note.getReference());
                                break;
                                
                            case "Employee Account":
                                client.deleteEmployeeAccNote(objectRef, note.getReference());
                                break;
                            
                        }
                        if (result > 0) {
                            String message = "Note " + note.getReference() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(NoteDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Note " + note.getReference() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(NoteDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(NoteDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                refresh();
            }
        });
        
        
        // Help Menu

        manualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                // NEED TO DEVELOP USER MANUAL
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                AboutFrame about = new AboutFrame(client);
                about.setVisible(true);
            }
        });
        
        return menuBar;
    }
}
