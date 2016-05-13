/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import client_gui.note.NotePanel;
import client_gui.landlord.LandlordPanel;
import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.EndObject;
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
import client_gui.empAccount.EmpAccDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.landlord.LandlordDetails;
import client_gui.landlord.LandlordSearch;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import client_gui.office.OfficeDetails;
import client_gui.property.PropertyDetails;
import interfaces.LeaseInterface;
import interfaces.Document;
import interfaces.EmployeeAccountInterface;
import interfaces.LandlordInterface;
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

        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(new JLabel(""), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel(""), gc);

        JLabel leasePropRef = new JLabel("Property Ref    ");
        leasePropRef.setFont(plainFont);

        gc.gridx++;
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

        JCheckBox full = new JCheckBox();
        full.setSelected(lease.isFullManagement());
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
                actionChoice(text);
            }
        });
        
        tabbedPane = new JTabbedPane();
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(lease.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        landlordPanel = new LandlordPanel("Landlords");
        
        try {
            landlordPanel.setData(lease.getLandlords());
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        landlordPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                Integer selection = landlordPanel.getSelectedObjectRef();
                switch (text) {
                    case "Create":
                        createLandlord();
                        break;
                        
                    case "Delete":
                        if (selection != null) {
                            try {
                                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Landlord " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (answer == JOptionPane.YES_OPTION) {
                                    System.out.println("Landlord Delete - Yes button clicked");
                                    int result = client.deleteLandlord(selection);
                                    if (result > 0) {
                                        String message = "Landlord " + selection + " has been successfully deleted";
                                        String title = "Information";
                                        OKDialog.okDialog(LeaseDetails.this, message, title);
                                    } else {
                                        String message = "Landlord " + selection + " has dependent records and is not able to be deleted";
                                        String title = "Error";
                                        OKDialog.okDialog(LeaseDetails.this, message, title);
                                    }
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        
                    case "View Details":
                        if (selection != null) {
                            try {
                                LandlordInterface landlord = client.getLandlord(selection);
                                if (landlord != null) {
                                    LandlordDetails landlordDetails = new LandlordDetails(client, landlord);
                                    landlordDetails.setVisible(true);
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        
                    case "Refresh":
                        refresh();
                        break;
                    
                }
            }
        });
        
        documentPanel = new DocumentPanel("Documents");
        
        try {
            documentPanel.setData(lease.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
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
        LandlordSearch landlordSearch = new LandlordSearch(client);
        landlordSearch.setListener(new IntegerListener() {
            @Override
            public void intOmitted(int ref) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to ADD Landlord " + ref + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        int result = client.createLeaseLandlord(lease.getAgreementRef(), ref);
                        if (result > 0) {
                            String message = "Landlord " + ref + " has been successfully added to Lease";
                            String title = "Information";
                            OKDialog.okDialog(LeaseDetails.this, message, title);
                        } else if (result < 1) {
                            String message = "Landlord " + ref + " is already assigned to Lease and can not be added again";
                            String title = "Error";
                            OKDialog.okDialog(LeaseDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        landlordSearch.setVisible(true);
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
                    NoteDetails landlordDetails = new NoteDetails(client, note, "Lease", lease.getAgreementRef());
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
                UpdateDocument updateDoc = new UpdateDocument(client, selection, "Lease", lease.getAgreementRef());
                updateDoc.setVisible(true);
                System.out.println("TEST - Update Document");
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
    
    private void viewPreviousDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            final Document document;
            try {
                document = lease.getDocument(documentPanel.getSelectedObjectRef());
                if (document != null) {
                    ViewPreviousDocument viewPreviousDocument = new ViewPreviousDocument(document.getCurrentVersion(), new IntegerListener() {
                        @Override
                        public void intOmitted(int ref) {
                            try {
                                client.downloadLeaseDocument(lease.getAgreementRef(), document.getDocumentRef(), ref);
                            } catch (RemoteException ex) {
                                Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    viewPreviousDocument.setVisible(true);
                }
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
    
    private void actionChoice(String text) {
        int pane = tabbedPane.getSelectedIndex();

        System.out.println(text);
        switch (text) {
            case "Create":
                System.out.println("TEST - Create Button");

                if (pane == 0) {
                    //Landlords
                    createLandlord();
                    System.out.println("Create Landlord");

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
                    System.out.println("TEST - End Landlord");

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
                    System.out.println("TEST - View Landlord");

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

        JMenuItem propertyItem = new JMenuItem("Property");
        JMenuItem accountItem = new JMenuItem("Account");
        JMenuItem officeItem = new JMenuItem("Office");
        
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
                int action = JOptionPane.showConfirmDialog(LeaseDetails.this,
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

                int action = JOptionPane.showConfirmDialog(LeaseDetails.this,
                        "Do you really want to exit the application?",
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
        
        
        // Actions Menu
        
        updateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                UpdateLease updateLease = new UpdateLease(client, lease);
                updateLease.setVisible(true);
            }
        });
        
        endItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    EndObject endAddress = new EndObject(client, "Lease", lease.getAgreementRef());
                    endAddress.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Lease " + lease.getAgreementRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Lease Delete - Yes button clicked");
                        int result = client.deleteLease(lease.getAgreementRef());
                        if (result > 0) {
                            String message = "Lease " + lease.getAgreementRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(LeaseDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Lease " + lease.getAgreementRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(LeaseDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
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

        propertyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    PropertyInterface property = lease.getProperty();
                    if (property != null) {
                        PropertyDetails propDetails = new PropertyDetails(client, property);
                        propDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        accountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    EmployeeAccountInterface empAcc = client.getEmployeeAccount(lease.getAccountRef());
                    if (empAcc != null) {
                        EmpAccDetails empAccDetails = new EmpAccDetails(client, empAcc);
                        empAccDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        officeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    OfficeInterface office = client.getOffice(lease.getOfficeCode());
                    if (office != null) {
                        OfficeDetails officeDetails = new OfficeDetails(client, office);
                        officeDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(LeaseDetails.class.getName()).log(Level.SEVERE, null, ex);
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
