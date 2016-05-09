/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.landlord;

import client_gui.lease.LeasePanel;
import client_gui.lease.UpdateLease;
import client_gui.lease.CreateLease;
import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.EndObject;
import client_gui.StringListener;
import client_gui.OKDialog;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.lease.LeaseDetails;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import client_gui.note.NotePanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import client_gui.person.PersonDetails;
import interfaces.LandlordInterface;
import interfaces.LeaseInterface;
import interfaces.Note;
import interfaces.PersonInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class LandlordDetails extends JFrame {
    
    private ClientImpl client = null;
    private LandlordInterface landlord = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private LeasePanel leasePanel;
    private NotePanel notePanel;
    private ModPanel modPanel;
    private JLabel name;
    private JLabel dob;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public LandlordDetails(ClientImpl client, LandlordInterface landlord) {
        super("MSc Properties");
        setClient(client);
        setLandlord(landlord);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Landlord is initiated
    private void setLandlord(LandlordInterface landlord) {
        if (this.landlord == null) {
            this.landlord = landlord;
        }
    }

    private void layoutComponents() {
        try {
            setJMenuBar(createMenuBar());

            detailsPanel = new JPanel();
            
            mainPanel = new JPanel();
            
            centrePanel = new JPanel();

            setLayout(new BorderLayout());

            this.setMinimumSize(new Dimension(1200, 700));
            this.setSize(1200, 700);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

            setupDetails();
            
            setUpMainPanel();

            this.add(detailsPanel, BorderLayout.NORTH);
            
            this.add(mainPanel, BorderLayout.CENTER);
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Landlord Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 30;

        JLabel appRef = new JLabel("Landlord Ref    ");
        Font font = appRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        appRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(appRef, gc);

        JLabel ref = new JLabel(String.valueOf(landlord.getLandlordRef()));
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel corrName = new JLabel("Name    ");
        corrName.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(corrName, gc);

        name = new JLabel(landlord.getPerson().getName());
        name.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(name, gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(new JLabel(""), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel(""), gc);

        JLabel pDOB = new JLabel("Date of Birth    ");
        pDOB.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pDOB, gc);

        dob = new JLabel(formatter.format(landlord.getPerson().getDateOfBirth()));
        dob.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(dob, gc);
    }
    
    private void setUpMainPanel() {
        /////// SET UP TABBED PANE
        
        mainPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));

        mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        mainPanel.setLayout(new BorderLayout());
        
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));
        
        buttonPanel = new ButtonPanel();
        
        buttonPanel.setButtonListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        tabbedPane = new JTabbedPane();
        
        leasePanel = new LeasePanel("Leases");
        
        try {
            leasePanel.setData(landlord.getLeases());
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        leasePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(landlord.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(landlord.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(leasePanel, "Leases");
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(landlord.getCreatedBy(), landlord.getCreatedDate(), landlord.getLastModifiedBy(), landlord.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }

    private void createLease() {
        CreateLease createLease = new CreateLease(client);
        createLease.setVisible(true);
        System.out.println("TEST - Create Lease");
    }

    private void updateLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Lease Ref: " + selection);
                LeaseInterface lease = client.getLease(selection);
                if (lease != null) {
                    System.out.println("Lease Name: " + lease.getAgreementName());
                    UpdateLease leaseDetails = new UpdateLease(client, lease);
                    leaseDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void endLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Lease Ref: " + selection);
            EndObject endLease = new EndObject(client, "Lease", selection);
            endLease.setVisible(true);
        }
    }

    private void deleteLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE lease " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Lease Delete - Yes button clicked");
                    int result = client.deleteLease(selection);
                    if (result > 0) {
                        String message = "Lease " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(LandlordDetails.this, message, title);
                    } else {
                        String message = "Lease " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(LandlordDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                LeaseInterface lease = client.getLease(selection);
                if (lease != null) {
                    LeaseDetails leaseDetails = new LeaseDetails(client, lease);
                    leaseDetails.setVisible(true);
                    setVisible(false);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Landlord", landlord.getLandlordRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = landlord.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Landlord", landlord.getLandlordRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE note " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Note Delete - Yes button clicked");
                    int result = client.deleteLandlordNote(landlord.getLandlordRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(LandlordDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(LandlordDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = landlord.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails leaseDetails = new NoteDetails(client, note, "Landlord", landlord.getLandlordRef());
                    leaseDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            if (landlord.getPerson().getDateOfBirth() != null) {
                dob.setText(formatter.format(landlord.getPerson().getDateOfBirth()));
            }
            if (landlord.getPerson().getName() != null) {
                name.setText(landlord.getPerson().getName());
            }
            leasePanel.setData(landlord.getLeases());
            notePanel.setData(landlord.getNotes());
            modPanel.setData(landlord.getModifiedBy());
            leasePanel.refresh();
            notePanel.refresh();
            modPanel.refresh();
            dob.setText(formatter.format(landlord.getPerson().getDateOfBirth()));
            name.setText(landlord.getPerson().getName());
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actionChoice(String text) {
        int pane = tabbedPane.getSelectedIndex();

        System.out.println(text);
        switch (text) {
            case "Create":
                System.out.println("TEST - Create Button");

                if (pane == 0) {
                    //Leases
                    createLease();
                } else if (pane == 1) {
                    //Notes
                    createNote();

                }
                break;

            case "Update":
                System.out.println("TEST - Update");

                if (pane == 0) {
                    //Leases
                    updateLease();
                    System.out.println("TEST - Update Lease");

                } else if (pane == 1) {
                    //Notes
                    updateNote();
                    System.out.println("TEST - Update Note");

                }
                break;

            case "End":
                if (pane == 0) {
                    //Leases
                    endLease();
                    System.out.println("TEST - End Lease");

                }
                break;
            case "Delete":
                if (pane == 0) {
                    //Leases
                    deleteLease();
                    System.out.println("TEST - Delete Lease");

                } else if (pane == 1) {
                    //Notes
                    deleteNote();
                    System.out.println("TEST - Delete Note");

                }
                break;

            case "View Details":
                if (pane == 0) {
                    //Leases
                    viewLease();
                    System.out.println("TEST - View Lease");

                } else if (pane == 1) {
                    //Notes
                    viewNote();
                    System.out.println("TEST - View Note");

                }
                break;

            case "Refresh":
                refresh();
                break;
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
        
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        
        
        // Link to Menu
        JMenu linksMenu = new JMenu("Link To");

        JMenuItem personItem = new JMenuItem("Person");
        
        linksMenu.add(personItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(actionsMenu);
        menuBar.add(linksMenu);
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
                int action = JOptionPane.showConfirmDialog(LandlordDetails.this,
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

                int action = JOptionPane.showConfirmDialog(LandlordDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        
        
        // Actions Menu

        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                refresh();
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Landlord " + landlord.getLandlordRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Landlord Delete - Yes button clicked");
                        int result = client.deleteLandlord(landlord.getLandlordRef());
                        if (result > 0) {
                            String message = "Landlord " + landlord.getLandlordRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(LandlordDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Landlord " + landlord.getLandlordRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(LandlordDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        // Links Menu

        personItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    PersonInterface person = landlord.getPerson();
                    if (person != null) {
                        PersonDetails personDetails = new PersonDetails(client, person);
                        personDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(LandlordDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
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


