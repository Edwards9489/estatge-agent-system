/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.tenancy;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.EndObject;
import client_gui.StringListener;
import client_gui.OKDialog;
import client_gui.application.AppDetails;
import client_gui.document.CreateDocument;
import client_gui.document.DocumentPanel;
import client_gui.empAccount.EmpAccDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.NotePanel;
import client_gui.note.UpdateNote;
import client_gui.office.OfficeDetails;
import client_gui.property.PropertyDetails;
import interfaces.ApplicationInterface;
import interfaces.TenancyInterface;
import interfaces.Document;
import interfaces.EmployeeAccountInterface;
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.PropertyInterface;
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
import java.io.File;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
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
public class TenancyDetails extends JFrame {
    
    private ClientImpl client = null;
    private TenancyInterface tenancy = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;    
    private NotePanel notePanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    private JFileChooser fileChooser;
    private JLabel length;
    private JLabel expenditure;
    private JLabel charges;
    private JLabel startDate;
    private JLabel name;
    private JLabel acEndDate;
    private JLabel endDate;
    private JLabel type;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public TenancyDetails(ClientImpl client, TenancyInterface app) {
        super("MSc Properties");
        setClient(client);
        setApplication(app);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Application is initiated
    private void setApplication(TenancyInterface app) {
        if (tenancy == null) {
            this.tenancy = app;
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
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Tenancy Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 20;

        JLabel leaseRef = new JLabel("Tenancy Ref    ");
        Font font = leaseRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        leaseRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseRef, gc);

        JLabel ref = new JLabel(String.valueOf(tenancy.getAgreementRef()));
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel leaseLength = new JLabel("Length    ");
        leaseLength.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseLength, gc);

        length = new JLabel(String.valueOf(tenancy.getLength()));
        length.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(length, gc);

        JLabel lStartDate = new JLabel("Start Date    ");
        lStartDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lStartDate, gc);

        startDate = new JLabel(formatter.format(tenancy.getStartDate()));
        startDate.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(startDate, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel leaseName = new JLabel("Name    ");
        leaseName.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseName, gc);

        name = new JLabel(tenancy.getAgreementName());
        name.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(name, gc);

        JLabel leasePropRef = new JLabel("Property Ref    ");
        leasePropRef.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leasePropRef, gc);
        
        JLabel propRef = new JLabel(String.valueOf(tenancy.getPropertyRef()));
        propRef.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(propRef, gc);

        JLabel leaseEndDate = new JLabel("End Date    ");
        leaseEndDate.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseEndDate, gc);
        
        endDate = new JLabel(formatter.format(tenancy.getExpectedEndDate()));
        endDate.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(endDate, gc);
        
        
        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel lAccRef = new JLabel("Account Ref    ");

        lAccRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lAccRef, gc);

        JLabel accRef = new JLabel(String.valueOf(tenancy.getAccountRef()));
        accRef.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(accRef, gc);

        JLabel lfull = new JLabel("App Ref    ");
        lfull.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lfull, gc);

        JLabel full = new JLabel(String.valueOf(tenancy.getApplication().getApplicationRef()));
        full.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(full, gc);

        JLabel lAcEndDate = new JLabel("Actual End Date    ");
        lAcEndDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lAcEndDate, gc);

        if(tenancy.getActualEndDate() != null) {
            acEndDate = new JLabel(formatter.format(tenancy.getActualEndDate()));
        } else {
            acEndDate = new JLabel("");
        }
        acEndDate.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(acEndDate, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel lofficeCode = new JLabel("Office Code    ");
        lofficeCode.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lofficeCode, gc);

        JLabel officeCode = new JLabel(tenancy.getOfficeCode());
        officeCode.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(officeCode, gc);

        JLabel lExpenditure = new JLabel("Rent    ");
        lExpenditure.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lExpenditure, gc);
        
        expenditure = new JLabel("£" + String.valueOf(tenancy.getRent()));
        expenditure.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(expenditure, gc);
        
        JLabel tcharges = new JLabel("Charges    ");
        tcharges.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(tcharges, gc);
        
        charges = new JLabel("£" + String.valueOf(tenancy.getCharges()));
        charges.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(charges, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel tenType = new JLabel("Type Code    ");
        tenType.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(tenType, gc);

        type = new JLabel(tenancy.getTenType().getCode());
        type.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(type, gc);
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
            notePanel.setData(tenancy.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        documentPanel = new DocumentPanel("Documents");
        
        try {
            documentPanel.setData(tenancy.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(tenancy.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(documentPanel, "Documents");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(tenancy.getCreatedBy(), tenancy.getCreatedDate(), tenancy.getLastModifiedBy(), tenancy.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Tenancy", tenancy.getAgreementRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = tenancy.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Tenancy", tenancy.getAgreementRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteTenancyNote(tenancy.getAgreementRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(TenancyDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(TenancyDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = tenancy.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails landlordDetails = new NoteDetails(client, note, "Tenancy", tenancy.getAgreementRef());
                    landlordDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createDocument() {
        try {
            CreateDocument createDoc = new CreateDocument(client, "Tenancy", tenancy.getAgreementRef());
            createDoc.setVisible(true);
            System.out.println("TEST - Create Document");
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Document document = tenancy.getDocument(selection);
                if (document != null) {
                    if(fileChooser.showOpenDialog(TenancyDetails.this) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        int result = client.updateTenancyDocument(tenancy.getAgreementRef(), document.getDocumentRef(), file.getPath());
                        if (result > 0) {
                            String message = "Document " + selection + " has been successfully updated";
                            String title = "Information";
                            OKDialog.okDialog(TenancyDetails.this, message, title);
                        } else {
                            String message = "There is some errors with the information supplied to UPDATE Document " + document.getDocumentRef() + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(TenancyDetails.this, message, title);
                        }
                        System.out.println(fileChooser.getSelectedFile());
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE document " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Document Delete - Yes button clicked");
                    int result = client.deleteTenancyDocument(tenancy.getAgreementRef(), selection);
                    if (result > 0) {
                        String message = "Document " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(TenancyDetails.this, message, title);
                    } else {
                        String message = "Document " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(TenancyDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            Document document;
            try {
                document = tenancy.getDocument(documentPanel.getSelectedObjectRef());
                client.downloadTenancyDocument(tenancy.getAgreementRef(), document.getDocumentRef(), document.getCurrentVersion());
            } catch (RemoteException ex) {
                Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            length.setText(String.valueOf(tenancy.getLength()));
            
            expenditure.setText("£" + String.valueOf(tenancy.getRent()));
            
            charges.setText("£" + String.valueOf(tenancy.getCharges()));
            
            if (tenancy.getStartDate() != null) {
                startDate.setText(formatter.format(tenancy.getStartDate()));
            }
            if (tenancy.getAgreementName() != null) {
                name.setText(tenancy.getAgreementName());
            }
            if (tenancy.getExpectedEndDate() != null) {
                endDate.setText(formatter.format(tenancy.getExpectedEndDate()));
            }
            if (tenancy.getActualEndDate() != null) {
                acEndDate.setText(formatter.format(tenancy.getActualEndDate()));
            }
            if (tenancy.getTenType().getCode() != null) {
                type.setText(tenancy.getTenType().getCode());
            }
            
            notePanel.setData(tenancy.getNotes());
            documentPanel.setData(tenancy.getDocuments());
            modPanel.setData(tenancy.getModifiedBy());
            notePanel.refresh();
            documentPanel.refresh();
            modPanel.refresh();
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
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

                } else if (pane == 1) {
                    //Documents
                    createDocument();
                    System.out.println("TEST - Create Document");

                }
                break;

            case "Update":
                System.out.println("TEST - Update Button");

                if (pane == 0) {
                    //Notes
                    updateNote();
                    System.out.println("TEST - Update Note");

                } else if (pane == 1) {
                    //Document
                    updateDocument();
                    System.out.println("TEST - Update Document");

                }
                break;

            case "Delete":
                System.out.println("TEST - Delete Button");
                if (pane == 0) {
                    //Notes
                    deleteNote();
                    System.out.println("TEST - Delete Note");

                } else if (pane == 1) {
                    //Document
                    deleteDocument();
                    System.out.println("TEST - Delete Document");

                }
                break;

            case "View Details":
                System.out.println("TEST - View Details Button");
                if (pane == 0) {
                    //Notes
                    viewNote();
                    System.out.println("TEST - View Note");

                } else if (pane == 1) {
                    //Document
                    viewDocument();
                    System.out.println("TEST - View Document");

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
        
        JMenuItem applicationItem = new JMenuItem("Application");
        JMenuItem propertyItem = new JMenuItem("Property");
        JMenuItem accountItem = new JMenuItem("Account");
        JMenuItem officeItem = new JMenuItem("Office");
        
        linksMenu.add(applicationItem);
        linksMenu.add(propertyItem);
        linksMenu.add(accountItem);
        linksMenu.add(officeItem);
        

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
                int action = JOptionPane.showConfirmDialog(TenancyDetails.this,
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

                int action = JOptionPane.showConfirmDialog(TenancyDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                UpdateTenancy updateTenancy = new UpdateTenancy(client, tenancy);
                updateTenancy.setVisible(true);
            }
        });
        
        endItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    EndObject endAddress = new EndObject(client, "Tenancy", tenancy.getAgreementRef());
                    endAddress.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Tenancy " + tenancy.getAgreementRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Tenancy Delete - Yes button clicked");
                        int result = client.deleteTenancy(tenancy.getAgreementRef());
                        if (result > 0) {
                            String message = "Tenancy " + tenancy.getAgreementRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(TenancyDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Tenancy " + tenancy.getAgreementRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(TenancyDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
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

        applicationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    ApplicationInterface application = tenancy.getApplication();
                    if (application != null) {
                        AppDetails appDetails = new AppDetails(client, application);
                        appDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        propertyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    PropertyInterface property = tenancy.getProperty();
                    if (property != null) {
                        PropertyDetails propDetails = new PropertyDetails(client, property);
                        propDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        accountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    EmployeeAccountInterface empAcc = client.getEmployeeAccount(tenancy.getAccountRef());
                    if (empAcc != null) {
                        EmpAccDetails empAccDetails = new EmpAccDetails(client, empAcc);
                        empAccDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        officeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    OfficeInterface office = client.getOffice(tenancy.getOfficeCode());
                    if (office != null) {
                        OfficeDetails officeDetails = new OfficeDetails(client, office);
                        officeDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TenancyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
