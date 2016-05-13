/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.rentAcc;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.IntegerListener;
import client_gui.JPEGFileFilter;
import client_gui.StringListener;
import client_gui.OKDialog;
import client_gui.PDFFileFilter;
import client_gui.PNGFileFilter;
import client_gui.document.CreateDocument;
import client_gui.document.DocumentPanel;
import client_gui.document.UpdateDocument;
import client_gui.document.ViewPreviousDocument;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import client_gui.note.NotePanel;
import client_gui.transaction.TransactionPanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import client_gui.office.OfficeDetails;
import client_gui.tenancy.TenancyDetails;
import client_gui.transaction.CreateTransaction;
import client_gui.transaction.TransactionDetails;
import interfaces.Document;
import interfaces.RentAccountInterface;
import interfaces.Note;
import interfaces.OfficeInterface;
import interfaces.TenancyInterface;
import interfaces.TransactionInterface;
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
public class RentAccDetails extends JFrame {
    
    private ClientImpl client = null;
    private RentAccountInterface rentAcc = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private NotePanel notePanel;
    private TransactionPanel transactionPanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    private JFileChooser fileChooser;
    private JLabel length;
    private JLabel start;
    private JLabel startDate;
    private JLabel propRef;
    private JLabel endDate;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public RentAccDetails(ClientImpl client, RentAccountInterface app) {
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
    private void setApplication(RentAccountInterface app) {
        if (rentAcc == null) {
            this.rentAcc = app;
        }
    }

    private void layoutComponents() {
        try {
            fileChooser = new JFileChooser();
            // If you need more choosbale files then add more choosable files
            fileChooser.addChoosableFileFilter(new PDFFileFilter());
            fileChooser.addChoosableFileFilter(new PNGFileFilter());
            fileChooser.addChoosableFileFilter(new JPEGFileFilter());
            
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
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Rent Account Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 30;

        JLabel leaseRef = new JLabel("Account Ref    ");
        Font font = leaseRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        leaseRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseRef, gc);

        JLabel ref = new JLabel(String.valueOf(rentAcc.getAccRef()));
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel leaseLength = new JLabel("Rent    ");
        leaseLength.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseLength, gc);

        length = new JLabel("£" + String.valueOf(rentAcc.getRent()));
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

        startDate = new JLabel(formatter.format(rentAcc.getStartDate()));
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

        start = new JLabel(rentAcc.getAccName());
        start.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(start, gc);

        JLabel leasePropRef = new JLabel("Balance    ");
        leasePropRef.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leasePropRef, gc);
        
        propRef = new JLabel("£" + String.valueOf(rentAcc.getBalance()));
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
        
        if(rentAcc.getEndDate() != null) {
             endDate = new JLabel(formatter.format(rentAcc.getEndDate()));
        } else {
            endDate  = new JLabel("");
        }
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        endDate.setFont(boldFont);
        detailsPanel.add(endDate, gc);
        
        
        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel lAccRef = new JLabel("Tenancy Ref    ");

        lAccRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lAccRef, gc);

        JLabel accRef = new JLabel(String.valueOf(rentAcc.getTenancyRef()));
        accRef.setFont(boldFont);
        
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(accRef, gc);
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
            notePanel.setData(rentAcc.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        transactionPanel = new TransactionPanel("Transactions");
        
        try {
            transactionPanel.setData(rentAcc.getTransactions());
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        transactionPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        documentPanel = new DocumentPanel("Documents");
        
        try {
            documentPanel.setData(rentAcc.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(rentAcc.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(transactionPanel, "Transactions");
        tabbedPane.add(documentPanel, "Documents");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(rentAcc.getCreatedBy(), rentAcc.getCreatedDate(), rentAcc.getLastModifiedBy(), rentAcc.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }
    
    private void createTransaction() {
        try {
            CreateTransaction createTransaction = new CreateTransaction(client, "Rent Account", rentAcc.getAccRef());
            createTransaction.setVisible(true);
            System.out.println("TEST - Create Transaction");
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteTransaction() {
        Integer selection = transactionPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE transaction " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Note Transaction - Yes button clicked");
                    int result = client.deleteRentAccTransaction(rentAcc.getAccRef(), selection);
                    if (result > 0) {
                        String message = "Transaction " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(RentAccDetails.this, message, title);
                    } else {
                        String message = "Transaction " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(RentAccDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewTransaction() {
        if (transactionPanel.getSelectedObjectRef() != null) {
            TransactionInterface transaction;
            try {
                transaction = rentAcc.getTransaction(transactionPanel.getSelectedObjectRef());
                if (transaction != null) {
                    TransactionDetails rentAccTransaction = new TransactionDetails(client, transaction, "Rent Account");
                    rentAccTransaction.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Rent Account", rentAcc.getAccRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = rentAcc.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Rent Account", rentAcc.getAccRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteRentAccNote(rentAcc.getAccRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(RentAccDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(RentAccDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = rentAcc.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails rentAccDetails = new NoteDetails(client, note, "Rent Account", rentAcc.getAccRef());
                    rentAccDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void createDocument() {
        try {
            CreateDocument createDoc = new CreateDocument(client, "Rent Account", rentAcc.getAccRef());
            createDoc.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                UpdateDocument updateDoc = new UpdateDocument(client, selection, "Employee Account", rentAcc.getAccRef());
                updateDoc.setVisible(true);
                System.out.println("TEST - Update Document");
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteRentAccDocument(rentAcc.getAccRef(), selection);
                    if (result > 0) {
                        String message = "Document " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(RentAccDetails.this, message, title);
                    } else {
                        String message = "Document " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(RentAccDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            Document document;
            try {
                document = rentAcc.getDocument(documentPanel.getSelectedObjectRef());
                client.downloadRentAccDocument(rentAcc.getAccRef(), document.getDocumentRef(), document.getCurrentVersion());
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewPreviousDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            final Document document;
            try {
                document = rentAcc.getDocument(documentPanel.getSelectedObjectRef());
                if (document != null) {
                    ViewPreviousDocument viewPreviousDocument = new ViewPreviousDocument(document.getCurrentVersion(), new IntegerListener() {
                        @Override
                        public void intOmitted(int ref) {
                            try {
                                client.downloadRentAccDocument(rentAcc.getAccRef(), document.getDocumentRef(), ref);
                            } catch (RemoteException ex) {
                                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    viewPreviousDocument.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            length.setText("£" + String.valueOf(rentAcc.getRent()));
            if (rentAcc.getStartDate() != null) {
                startDate.setText(formatter.format(rentAcc.getStartDate()));
            }
            if (rentAcc.getAccName() != null) {
                start.setText(rentAcc.getAccName());
            }
            if (rentAcc.getEndDate() != null) {
                endDate.setText(formatter.format(rentAcc.getEndDate()));
            }
            
            propRef.setText("£" + String.valueOf(rentAcc.getBalance()));
            
            notePanel.setData(rentAcc.getNotes());
            transactionPanel.setData(rentAcc.getTransactions());
            documentPanel.setData(rentAcc.getDocuments());
            modPanel.setData(rentAcc.getModifiedBy());
            notePanel.refresh();
            transactionPanel.refresh();
            documentPanel.refresh();
            modPanel.refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    //Transactions
                    createTransaction();
                    System.out.println("TEST - Create Transaction");

                } else if (pane == 2) {
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

                } else if (pane == 2) {
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
                    deleteTransaction();
                    System.out.println("TEST - Delete Transaction");

                } else if (pane == 2) {
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
                    viewTransaction();
                    System.out.println("TEST - View Transaction");

                } else if (pane == 2) {
                    //Document
                    viewDocument();
                    System.out.println("TEST - View Document");

                }
                break;
                
            case "View Previous":
                viewPreviousDocument();
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

        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(refreshItem);
        
        
        // Link to Menu
        JMenu linksMenu = new JMenu("Link To");

        JMenuItem tenancyItem = new JMenuItem("Tenancy");
        JMenuItem officeItem = new JMenuItem("Office");
        
        linksMenu.add(tenancyItem);
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
                int action = JOptionPane.showConfirmDialog(RentAccDetails.this,
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

                int action = JOptionPane.showConfirmDialog(RentAccDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        // Links Menu

        tenancyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    TenancyInterface tenancy = rentAcc.getTenancy();
                    if (tenancy != null) {
                        TenancyDetails tenancyDetails = new TenancyDetails(client, tenancy);
                        tenancyDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        officeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    OfficeInterface office = client.getOffice(rentAcc.getOfficeCode());
                    if (office != null) {
                        OfficeDetails officeDetails = new OfficeDetails(client, office);
                        officeDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(RentAccDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                AboutFrame about = new AboutFrame();
                about.setVisible(true);
            }
        });
        
        return menuBar;
    }
}
