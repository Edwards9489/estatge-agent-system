/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import client_gui.landlord.CreateLandlord;
import client_gui.note.NotePanel;
import client_gui.landlord.LandlordPanel;
import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.PDFFileFilter;
import client_gui.document.CreateDocument;
import client_gui.document.DocumentPanel;
import client_gui.landlord.LandlordDetails;
import client_gui.modifications.ModPanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import interfaces.LeaseInterface;
import interfaces.Document;
import interfaces.LandlordInterface;
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
import java.io.File;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
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
public class LeaseDetails extends JFrame {
    
    private ClientImpl client = null;
    private LeaseInterface lease = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;    
    private NotePanel notePanel;
    private LandlordPanel landlordPanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    private JFileChooser fileChooser;
    private JLabel length;
    private JLabel expenditure;
    private JLabel startDate;
    private JLabel name;
    private JLabel acEndDate;
    private JLabel endDate;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public LeaseDetails(ClientImpl client, LeaseInterface app) {
        super("MSc Properties");
        setClient(client);
        setLease(app);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Lease is initiated
    private void setLease(LeaseInterface app) {
        if (lease == null) {
            this.lease = app;
        }
    }

    private void layoutComponents() {
        try {
            
            fileChooser = new JFileChooser();
            // If you need more choosbale files then add more choosable files
            fileChooser.addChoosableFileFilter(new PDFFileFilter());
            
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
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Lease Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 20;

        JLabel leaseRef = new JLabel("Lease Ref    ");
        Font font = leaseRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        leaseRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseRef, gc);

        JLabel ref = new JLabel(String.valueOf(lease.getAgreementRef()));
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

        length = new JLabel(String.valueOf(lease.getLength()));
        length.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(length, gc);

        JLabel lExpenditure = new JLabel("Expenditure    ");
        lExpenditure.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lExpenditure, gc);
        
        expenditure = new JLabel(String.valueOf(lease.getExpenditure()));
        expenditure.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(expenditure, gc);

        JLabel lStartDate = new JLabel("Start Date    ");
        lStartDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lStartDate, gc);

        startDate = new JLabel(formatter.format(lease.getStartDate()));
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

        name = new JLabel(lease.getAgreementName());
        name.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(name, gc);

        JLabel empty1 = new JLabel("");
        empty1.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(empty1, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        JLabel empty2 = new JLabel("");
        empty2.setFont(boldFont);
        detailsPanel.add(empty2, gc);

        JLabel leasePropRef = new JLabel("Property Ref    ");
        leasePropRef.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leasePropRef, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        JLabel propRef = new JLabel(String.valueOf(lease.getPropertyRef()));
        propRef.setFont(boldFont);
        detailsPanel.add(propRef, gc);

        JLabel leaseEndDate = new JLabel("End Date    ");
        leaseEndDate.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseEndDate, gc);
        
        endDate = new JLabel(formatter.format(lease.getExpectedEndDate()));
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

        JLabel accRef = new JLabel(String.valueOf(lease.getAccountRef()));
        accRef.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(accRef, gc);

        JLabel lfull = new JLabel("Full Management    ");
        lfull.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lfull, gc);

        JCheckBox full = new JCheckBox("", lease.isFullManagement());
        full.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(full, gc);

        JLabel lofficeCode = new JLabel("Office Code    ");
        lofficeCode.setFont(plainFont);
        
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lofficeCode, gc);

        JLabel officeCode = new JLabel(lease.getOfficeCode());
        officeCode.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(officeCode, gc);

        JLabel lAcEndDate = new JLabel("Actual End Date    ");
        lAcEndDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lAcEndDate, gc);

        if(lease.getActualEndDate() != null) {
            acEndDate = new JLabel(formatter.format(lease.getActualEndDate()));
        } else {
            acEndDate = new JLabel("");
        }
        acEndDate.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(acEndDate, gc);
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
                            //Landlords
                            createLandlord();
                            System.out.println("Create Involved Party");
                            
                        } else if (pane == 1) {
                            //Notes
                            createNote();
                            System.out.println("Create Note");
                            
                        } else if (pane == 2) {
                            //Documents
                            createDocument();
                            System.out.println("TEST - Create Document");
                            
                        }
                        break;
                        
                    case "Update":
                        System.out.println("TEST - Update Button");

                        if (pane == 1) {
                            //Notes
                            updateNote();
                            System.out.println("TEST - Update Note");
                            
                        } else if (pane == 2) {
                            //Document
                            updateDocument();
                            System.out.println("TEST - Update Document");
                            
                        }
                        break;
                        
                     case "End":
                        System.out.println("TEST - End Button");

                        if (pane == 0) {
                            //Landlords
                            endLandlord();
                            System.out.println("TEST - End Involved Party");

                        }
                        break;

                    case "Delete":
                        System.out.println("TEST - Delete Button");
                        if (pane == 1) {
                            //Notes
                            deleteNote();
                            System.out.println("TEST - Delete Note");
                            
                        } else if (pane == 2) {
                            //Document
                            deleteDocument();
                            System.out.println("TEST - Delete Document");
                            
                        }
                        break;

                    case "View Details":
                        System.out.println("TEST - View Details Button");
                        if (pane == 0) {
                            //Landlords
                            viewLandlord();
                            System.out.println("TEST - View Involved Party");

                        } else if (pane == 1) {
                            //Notes
                            viewNote();
                            System.out.println("TEST - View Note");

                        } else if (pane == 2) {
                            //Document
                            viewDocument();
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
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(lease.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int noteRef) {
                if(noteRef > 0) {
                    try {
                        Note note = lease.getNote(noteRef);
                        if(note != null) {
                            System.out.println(note.getReference());
                            System.out.println("TEST1-Note");
                            NoteDetails noteGUI=  new NoteDetails(client, note);
                            noteGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        landlordPanel = new LandlordPanel("Landlords");
        
        try {
            landlordPanel.setData(lease.getLandlords());
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        landlordPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int addressRef) {
                if(addressRef > 0) {
                    try {
                        LandlordInterface landlord = client.getLandlord(addressRef);
                        if(landlord != null) {
                            System.out.println(landlord.getPerson().getName());
                            System.out.println("TEST1-Landlord");
                            LandlordDetails invPartyGUI = new LandlordDetails(client, landlord);
                            invPartyGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        documentPanel = new DocumentPanel("Documents");
        
        try {
            documentPanel.setData(lease.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int documentRef) {
                if(documentRef > 0) {
                    try {
                        Document document = lease.getDocument(documentRef);
                        if(document != null) {
                            System.out.println(document.getCurrentDocumentName());
                            System.out.println("TEST1-Document");
                            client.downloadLeaseDocument(lease.getAgreementRef(), document.getDocumentRef(), document.getCurrentVersion());
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(lease.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(landlordPanel, "Landlords");
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(documentPanel, "Documents");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(lease.getCreatedBy(), lease.getCreatedDate(), lease.getLastModifiedBy(), lease.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }
    
    private void createLandlord() {
        CreateLandlord createLandlord = new CreateLandlord(client);
        createLandlord.setVisible(true);
        System.out.println("TEST - Create Landlord");
    }
    
    private void endLandlord() {
        Integer selection = landlordPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to END landlord " + selection + "for Lease " + lease.getAgreementRef() + " ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("End Landlord - Yes button clicked");
                    int result = client.endLeaseLandlord(selection, lease.getAgreementRef());
                    if (result > 0) {
                        String message = "Landlord " + selection + " has been successfully ended";
                        String title = "Information";
                        OKDialog.okDialog(LeaseDetails.this, message, title);
                    } else {
                        String message = "Landlord " + selection + " has dependent records and is not able to be ended";
                        String title = "Error";
                        OKDialog.okDialog(LeaseDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewLandlord() {
        Integer selection = landlordPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                LandlordInterface landlord = client.getLandlord(selection);
                if (landlord != null) {
                    LandlordDetails landlordDetails = new LandlordDetails(client, landlord);
                    landlordDetails.setVisible(true);
                    setVisible(false);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Lease", lease.getAgreementRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = lease.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Lease", lease.getAgreementRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteLeaseNote(lease.getAgreementRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(LeaseDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(LeaseDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = lease.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails landlordDetails = new NoteDetails(client, note);
                    landlordDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createDocument() {
        try {
            CreateDocument createDoc = new CreateDocument(client, "Lease", lease.getAgreementRef());
            createDoc.setVisible(true);
            System.out.println("TEST - Create Document");
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Document document = lease.getDocument(selection);
                if (document != null) {
                    if(fileChooser.showOpenDialog(LeaseDetails.this) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        int result = client.updateLeaseDocument(lease.getAgreementRef(), document.getDocumentRef(), file.getPath());
                        if (result > 0) {
                            String message = "Document " + selection + " has been successfully updated";
                            String title = "Information";
                            OKDialog.okDialog(LeaseDetails.this, message, title);
                        } else {
                            String message = "There is some errors with the information supplied to UPDATE Document " + document.getDocumentRef() + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(LeaseDetails.this, message, title);
                        }
                        System.out.println(fileChooser.getSelectedFile());
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteLeaseDocument(lease.getAgreementRef(), selection);
                    if (result > 0) {
                        String message = "Document " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(LeaseDetails.this, message, title);
                    } else {
                        String message = "Document " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(LeaseDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            Document document;
            try {
                document = lease.getDocument(documentPanel.getSelectedObjectRef());
                client.downloadLeaseDocument(lease.getAgreementRef(), document.getDocumentRef(), document.getCurrentVersion());
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            length.setText(String.valueOf(lease.getLength()));
            
            expenditure.setText(String.valueOf(lease.getExpenditure()));
            
            if (lease.getStartDate() != null) {
                startDate.setText(formatter.format(lease.getStartDate()));
            }
            if (lease.getAgreementName() != null) {
                name.setText(lease.getAgreementName());
            }
            if (lease.getExpectedEndDate() != null) {
                endDate.setText(formatter.format(lease.getExpectedEndDate()));
            }
            if (lease.getActualEndDate() != null) {
                acEndDate.setText(formatter.format(lease.getActualEndDate()));
            }
            
            landlordPanel.setData(lease.getLandlords());
            notePanel.setData(lease.getNotes());
            documentPanel.setData(lease.getDocuments());
            modPanel.setData(lease.getModifiedBy());
            landlordPanel.refresh();
            notePanel.refresh();
            documentPanel.refresh();
            modPanel.refresh();
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
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

                int action = JOptionPane.showConfirmDialog(LeaseDetails.this,
                        "Do you really want to exit the lease?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LeaseDetails().setVisible(true);
//            }
//        });
//    }
}
