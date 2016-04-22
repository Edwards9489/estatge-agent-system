/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.employee;

import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.application.ModPanel;
import client_gui.contract.ContractDetails;
import client_gui.lease.NotePanel;
import interfaces.ContractInterface;
import interfaces.EmployeeInterface;
import interfaces.Note;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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
public class EmployeeDetails extends JFrame {

    private ClientImpl client = null;
    private EmployeeInterface employee = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private ContractPanel contractPanel;
    private NotePanel notePanel;
    private ModPanel modPanel;
    private JLabel ref;
    private JLabel dob;
    private JLabel code;
    private JLabel name;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");

    public EmployeeDetails(ClientImpl client, EmployeeInterface app) {
        super("MSc Properties");
        setClient(client);
        setEmployee(app);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Employee is initiated
    private void setEmployee(EmployeeInterface app) {
        if (employee == null) {
            this.employee = app;
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
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

            setupDetails();

            setUpMainPanel();

            this.add(detailsPanel, BorderLayout.NORTH);

            this.add(mainPanel, BorderLayout.CENTER);
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP EMPLOYEE DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Employee Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 30;

        JLabel eRef = new JLabel("Ref    ");
        Font font = eRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        eRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eRef, gc);

        ref = new JLabel(String.valueOf(employee.getEmployeeRef()));
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel eDOB = new JLabel("Date of Birth    ");
        eDOB.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eDOB, gc);

        dob = new JLabel(formatter.format(employee.getPerson().getDateOfBirth()));
        dob.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(dob, gc);

        JLabel officeCode = new JLabel("Office Code    ");
        officeCode.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(officeCode, gc);

        if (employee.getOfficeCode() != null) {
            code = new JLabel(employee.getOfficeCode());
        } else {
            code = new JLabel("");
        }
        code.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(code, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel eName = new JLabel("Name    ");
        eName.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eName, gc);

        name = new JLabel(employee.getPerson().getName());
        name.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(name, gc);

        JLabel empty = new JLabel("");
        empty.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(empty, gc);

        JLabel empty2 = new JLabel("");
        empty2.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(empty2, gc);
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
                int pane = tabbedPane.getSelectedIndex();

                System.out.println(text);
                switch (text) {
                    case "Create":
                        System.out.println("TEST - Create Button");

                        if (pane == 0) {
                            //Contracts
                            createContract();
                        } else if (pane == 1) {
                            //Notes
                            createNote();

                        }
                        break;

                    case "Update":
                        System.out.println("TEST - Update");

                        if (pane == 0) {
                            //Contracts
                            updateContract();
                            System.out.println("TEST - Update Contract");

                        } else if (pane == 1) {
                            //Notes
                            updateNote();
                            System.out.println("TEST - Update Note");

                        }
                        break;

                    case "End":
                        if (pane == 0) {
                            //Contracts
                            endContract();
                            System.out.println("TEST - End Contract");

                        }
                        break;
                    case "Delete":
                        if (pane == 0) {
                            //Contracts
                            deleteContract();
                            System.out.println("TEST - Delete Contract");

                        } else if (pane == 1) {
                            //Notes
                            deleteNote();
                            System.out.println("TEST - Delete Note");

                        }
                        break;

                    case "View Details":
                        if (pane == 0) {
                            //Contracts
                            viewContract();
                            System.out.println("TEST - View Contract");

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
        });

        tabbedPane = new JTabbedPane();

        contractPanel = new ContractPanel("Contacts");

        try {
            contractPanel.setData(employee.getContracts());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        contractPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int invPartyRef) {
                if (invPartyRef > 0) {
                    try {
                        ContractInterface invParty = client.getContract(invPartyRef);
                        if (invParty != null) {
                            System.out.println(invParty.getAgreementName());
                        }
                        System.out.println("TEST1-Contracts");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
                    } catch (RemoteException ex) {
                        Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        notePanel = new NotePanel("Notes");

        try {
            notePanel.setData(employee.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        notePanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int noteRef) {
                if (noteRef > 0) {
                    try {
                        Note note = employee.getNote(noteRef);
                        if (note != null) {
                            System.out.println(note.getReference());
                        }
                        System.out.println("TEST1-Note");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
                    } catch (RemoteException ex) {
                        Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        modPanel = new ModPanel("Modifications");

        try {
            modPanel.setData(employee.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        tabbedPane.setSize(800, 175);

        tabbedPane.add(contractPanel, "Contracts");
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(modPanel, "Modifications");

        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(employee.getCreatedBy(), employee.getCreatedDate(), employee.getLastModifiedBy(), employee.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
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

                int action = JOptionPane.showConfirmDialog(EmployeeDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }

    private void createContract() {
        CreateContract createContract = new CreateContract(client);
        createContract.setVisible(true);
        System.out.println("TEST - Create Contract");
    }

    private void updateContract() {
        Integer selection = contractPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Contract Ref: " + selection);
                ContractInterface contract = client.getContract(selection);
                if (contract != null) {
                    System.out.println("Contract Name: " + contract.getAgreementName());
                    UpdateContract contractDetails = new UpdateContract(client, contract);
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void endContract() {
        
    }

    private void deleteContract() {
        Integer selection = contractPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE contract " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Contract Delete - Yes button clicked");
                    int result = client.deleteContract(selection);
                    if (result > 0) {
                        String message = "Contract " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(EmployeeDetails.this, message, title);
                    } else {
                        String message = "Contract " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(EmployeeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewContract() {
        Integer selection = contractPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                ContractInterface contract = client.getContract(selection);
                if (contract != null) {
                    ContractDetails contractDetails = new ContractDetails(client, contract);
                    contractDetails.setVisible(true);
                    setVisible(false);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Employee", employee.getEmployeeRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = employee.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Employee", employee.getEmployeeRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteEmployeeNote(employee.getEmployeeRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(EmployeeDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(EmployeeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = employee.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails contractDetails = new NoteDetails(client, note);
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            contractPanel.setData(employee.getContracts());
            notePanel.setData(employee.getNotes());
            modPanel.setData(employee.getModifiedBy());
            contractPanel.refresh();
            notePanel.refresh();
            modPanel.refresh();
            dob.setText(formatter.format(employee.getPerson().getDateOfBirth()));
            code.setText(employee.getOfficeCode());
            name.setText(employee.getPerson().getName());
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new EmployeeDetails().setVisible(true);
//            }
//        });
//    }
}
