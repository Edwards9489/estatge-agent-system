/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.invParty;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.EndObject;
import client_gui.StringListener;
import client_gui.OKDialog;
import client_gui.application.AppDetails;
import client_gui.element.ElementDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import client_gui.note.NotePanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import client_gui.person.PersonDetails;
import interfaces.ApplicationInterface;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
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
import javax.swing.JCheckBox;
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
public class InvPartyDetails extends JFrame {
    
    private ClientImpl client = null;
    private InvolvedPartyInterface invParty = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private NotePanel notePanel;
    private ModPanel modPanel;
    private JLabel relation;
    private JCheckBox main2;
    private JCheckBox jnt;
    private JLabel start;
    private JLabel sDate;
    private JLabel endDate;
    private JLabel eReason;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public InvPartyDetails(ClientImpl client, InvolvedPartyInterface invParty) {
        super("MSc Properties");
        setClient(client);
        setInvolvedParty(invParty);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one InvolvedParty is initiated
    private void setInvolvedParty(InvolvedPartyInterface invParty) {
        if (this.invParty == null) {
            this.invParty = invParty;
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
            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Involved Party Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 30;

        JLabel appRef = new JLabel("Ref    ");
        Font font = appRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        appRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(appRef, gc);

        JLabel ref = new JLabel(String.valueOf(invParty.getInvolvedPartyRef()));
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel pGender = new JLabel("App Ref    ");
        pGender.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pGender, gc);
        
        JLabel gender = new JLabel(String.valueOf(invParty.getApplicationRef()));
        gender.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(gender, gc);
        
        JLabel relationship = new JLabel("Relationship    ");
        relationship.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(relationship, gc);
        
        relation = new JLabel(invParty.getRelationship().getCode());
        relation.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(relation, gc);
        
        JLabel main = new JLabel("Main Ind    ");
        main.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(main, gc);
        
        main2 = new JCheckBox();
        main2.setSelected(invParty.isMainInd());
        main2.setEnabled(false);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(main2, gc);
        
        JLabel joint = new JLabel("Joint Ind    ");
        joint.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(joint, gc);
        
        jnt = new JCheckBox();
        jnt.setSelected(invParty.isJointInd());
        jnt.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(jnt, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel startDate = new JLabel("Name    ");
        startDate.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(startDate, gc);

        start = new JLabel(invParty.getPerson().getName());
        start.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(start, gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(new JLabel(""), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel(""), gc);
        
        JLabel stDate = new JLabel("Start Date    ");
        stDate.setFont(plainFont);
        
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(stDate, gc);
        
        sDate = new JLabel(formatter.format(invParty.getStartDate()));
        sDate.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(sDate, gc);
        
        JLabel eDate = new JLabel("End Date    ");
        eDate.setFont(plainFont);
        
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eDate, gc);
                
        if (invParty.getEndDate() != null) {
            endDate = new JLabel(formatter.format(invParty.getEndDate()));
        } else {
            endDate = new JLabel("");
        }
        
        endDate.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(endDate, gc);
        
        JLabel endReason = new JLabel("End Reason    ");
        endReason.setFont(plainFont);
        
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(endReason, gc);
                
        if (invParty.getEndReason() != null) {
            eReason = new JLabel(invParty.getEndReason().getCode());
        } else {
            eReason = new JLabel("");
        }
        
        eReason.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eReason, gc);
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
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(invParty.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(invParty.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(invParty.getCreatedBy(), invParty.getCreatedDate(), invParty.getLastModifiedBy(), invParty.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Involved Party", invParty.getInvolvedPartyRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = invParty.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Involved Party", invParty.getInvolvedPartyRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteInvolvedPartyNote(invParty.getInvolvedPartyRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(InvPartyDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(InvPartyDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = invParty.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails contractDetails = new NoteDetails(client, note, "Involved Party", invParty.getInvolvedPartyRef());
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            relation.setText(invParty.getRelationship().getCode());
            main2.setSelected(invParty.isMainInd());
            jnt.setSelected(invParty.isJointInd());
            start.setText(invParty.getPerson().getName());
            sDate.setText(formatter.format(invParty.getStartDate()));
            if (invParty.getEndDate() != null) {
                endDate.setText(formatter.format(invParty.getEndDate()));
            } else {
                endDate.setText("");
            }
            if (invParty.getEndReason() != null) {
                eReason.setText(invParty.getEndReason().getCode());
            } else {
                eReason.setText("");
            }
            notePanel.setData(invParty.getNotes());
            modPanel.setData(invParty.getModifiedBy());
            notePanel.refresh();
            modPanel.refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actionChoice(String text) {
        int pane = tabbedPane.getSelectedIndex();

        System.out.println(text);
        switch (text) {
            case "Create":
                System.out.println("TEST - Create Button");

                if (pane == 0) {
                    //Notes
                    createNote();
                    System.out.println("Create Note");

                }
                break;

            case "Update":
                System.out.println("TEST - Update Button");

                if (pane == 0) {
                    //Notes
                    updateNote();
                    System.out.println("TEST - Update Note");

                }
                break;

            case "Delete":
                System.out.println("TEST - Delete Button");
                if (pane == 0) {
                    //Notes
                    deleteNote();
                    System.out.println("TEST - Delete Note");

                }
                break;

            case "View Details":
                System.out.println("TEST - View Details Button");
                if (pane == 0) {
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

        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem endItem = new JMenuItem("End");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(updateItem);
        actionsMenu.add(endItem);
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        
        
        // Link to Menu
        JMenu linksMenu = new JMenu("Link To");

        JMenuItem application = new JMenuItem("Application");
        JMenuItem person = new JMenuItem("Person");
        JMenuItem relationship = new JMenuItem("Relationship");
        
        linksMenu.add(application);
        linksMenu.add(person);
        linksMenu.add(relationship);
        

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
                int action = JOptionPane.showConfirmDialog(InvPartyDetails.this,
                        "Do you really want to change user?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                
                if (action == JOptionPane.OK_OPTION) {
                    try {
                        System.gc();
                        Window windows[] = Window.getWindows();
                        for (int i=0; i<windows.length; i++) {
                            windows[i].dispose();
                            windows[i]=null;
                        }
                        client.logout();
                        new LoginForm().setVisible(true);
                        dispose();
                    } catch (RemoteException ex) {
                        Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

                int action = JOptionPane.showConfirmDialog(InvPartyDetails.this,
                        "Do you really want to exit the contact?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                UpdateInvParty updateContact1 = new UpdateInvParty(client, invParty);
                updateContact1.setVisible(true);
            }
        });
        
        endItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    EndObject endInvParty = new EndObject(client, "Involved Party", invParty.getInvolvedPartyRef());
                    endInvParty.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Involved Party " + invParty.getInvolvedPartyRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Contact Delete - Yes button clicked");
                        int result = client.deleteInvolvedParty(invParty.getInvolvedPartyRef());
                        if (result > 0) {
                            String message = "Involved Party " + invParty.getInvolvedPartyRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(InvPartyDetails.this, message, title);
                        } else {
                            String message = "Involved Party " + invParty.getInvolvedPartyRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(InvPartyDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                refresh();
            }
        });
        
        
        // Links Menu

        application.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    ApplicationInterface application = client.getApplication(invParty.getApplicationRef());
                    if (application != null) {
                        AppDetails appDetails = new AppDetails(client, application);
                        appDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        person.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    PersonInterface person = invParty.getPerson();
                    if (person != null) {
                        PersonDetails personDetails = new PersonDetails(client, person);
                        personDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        relationship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element relationship = invParty.getRelationship();
                    if (relationship != null) {
                        ElementDetails relationshipDetails = new ElementDetails(client, relationship, "Relationship");
                        relationshipDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        try {
            if (!invParty.isCurrent()) {
                JMenuItem endReason = new JMenuItem("End Reason");
                linksMenu.add(endReason);
                endReason.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        try {
                            Element endReason = invParty.getEndReason();
                            if (endReason != null) {
                                ElementDetails endReasonDetails = new ElementDetails(client, endReason, "Relationship");
                                endReasonDetails.setVisible(true);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        } catch (RemoteException ex) {
            Logger.getLogger(InvPartyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
                AboutFrame about = new AboutFrame();
                about.setVisible(true);
            }
        });
        return menuBar;
    }
}
