/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import client_gui.AboutFrame;
import client_gui.document.CreateDocument;
import client_gui.document.DocumentPanel;
import client_gui.modifications.ModPanel;
import client_gui.property.PropertySearch;
import client_gui.property.PropertyPanel;
import client_gui.invParty.UpdateInvParty;
import client_gui.invParty.InvPartyPanel;
import client_gui.invParty.CreateInvParty;
import client_gui.addressUsage.AddressUsageDetails;
import client_gui.addressUsage.CreateAddressUsage;
import client_gui.addressUsage.UpdateAddressUsage;
import client_gui.addressUsage.AddressUsagePanel;
import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.StringListener;
import client_gui.OKDialog;
import client_gui.PDFFileFilter;
import client_gui.note.CreateNote;
import client_gui.EndObject;
import client_gui.IntegerListener;
import client_gui.JPEGFileFilter;
import client_gui.PNGFileFilter;
import client_gui.document.UpdateDocument;
import client_gui.document.ViewPreviousDocument;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import client_gui.invParty.InvPartyDetails;
import client_gui.login.LoginForm;
import client_gui.note.NotePanel;
import client_gui.property.PropertyDetails;
import client_gui.tenancy.TenancyDetails;
import interfaces.AddressUsageInterface;
import interfaces.ApplicationInterface;
import interfaces.Document;
import interfaces.InvolvedPartyInterface;
import interfaces.Note;
import interfaces.PropertyInterface;
import interfaces.TenancyInterface;
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
public class AppDetails extends JFrame {
    
    private ClientImpl client = null;
    private ApplicationInterface application = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private InvPartyPanel invPartyPanel;
    private AddressUsagePanel addressPanel;
    private NotePanel notePanel;
    private PropertyPanel propInterestPanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    private JFileChooser fileChooser;
    private JLabel name;
    private JLabel status;
    private JLabel start;
    private JLabel end;
    private JLabel tenRef;
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public AppDetails(ClientImpl client, ApplicationInterface app) {
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
    private void setApplication(ApplicationInterface app) {
        if (application == null) {
            this.application = app;
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

            setMinimumSize(new Dimension(1200, 700));
            setSize(1200, 700);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            
            
            setupDetails();
            
            setUpMainPanel();

            this.add(detailsPanel, BorderLayout.NORTH);
            
            this.add(mainPanel, BorderLayout.CENTER);
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Application Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 40;

        JLabel appRef = new JLabel("App Ref    ");
        Font font = appRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        appRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(appRef, gc);

        JLabel ref = new JLabel(String.valueOf(application.getApplicationRef()));
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel corrName = new JLabel("Corr Name    ");
        corrName.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(corrName, gc);

        name = new JLabel(application.getAppCorrName());
        name.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(name, gc);

        JLabel appStatus = new JLabel("App Status    ");
        appStatus.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(appStatus, gc);

        status = new JLabel(application.getAppStatusCode());
        status.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(status, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel startDate = new JLabel("Start Date    ");
        startDate.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(startDate, gc);

        start = new JLabel(formatter.format(application.getAppStartDate()));
        start.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(start, gc);

        JLabel endDate = new JLabel("End Date    ");
        endDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(endDate, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        if (application.getAppEndDate() != null) {
            end = new JLabel(formatter.format(application.getAppEndDate()));
        } else {
            end = new JLabel("");
        }
        end.setFont(boldFont);
        detailsPanel.add(end, gc);

        JLabel tenancyRef = new JLabel("Tenancy Ref    ");
        tenancyRef.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(tenancyRef, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        if (application.hasTenancyRef()) {
            tenRef = new JLabel(String.valueOf(application.getTenancyRef()));
        } else {
            tenRef = new JLabel("");
        }
        tenRef.setFont(boldFont);
            detailsPanel.add(tenRef, gc);
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
        
        invPartyPanel = new InvPartyPanel("Involved Parties");
        
        try {
            invPartyPanel.setData(application.getHousehold());
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        invPartyPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        addressPanel = new AddressUsagePanel("Addresses");
        
        try {
            addressPanel.setData(application.getApplicationAddressess());
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addressPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(application.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        propInterestPanel = new PropertyPanel("Prop Interests");
        
        try {
            propInterestPanel.setData(application.getPropertiesInterestedIn());
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        propInterestPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        documentPanel = new DocumentPanel("Documents");
        
        try {
            documentPanel.setData(application.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(application.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(invPartyPanel, "Inv Parties");
        tabbedPane.add(addressPanel, "Addresses");
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(propInterestPanel, "Prop Interests");
        tabbedPane.add(documentPanel, "Documents");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(application.getCreatedBy(), application.getCreatedDate(), application.getLastModifiedBy(), application.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }

    private void createInvolvedParty() {
        try {
            CreateInvParty createInvParty = new CreateInvParty(client);
            createInvParty.setVisible(true);
            createInvParty.setAppField(application.getApplicationRef());
            System.out.println("TEST - Create Involved Party");
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateInvolvedParty() {
        Integer selection = invPartyPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Involved Party Ref: " + selection);
                InvolvedPartyInterface invParty = client.getInvolvedParty(selection);
                if (invParty != null) {
                    System.out.println("Involved Party Name: " + invParty.getPerson().getName());
                    UpdateInvParty invPartyDetails = new UpdateInvParty(client, invParty);
                    invPartyDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void endInvolvedParty() {
        Integer selection = invPartyPanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Involved Party Ref: " + selection);
            EndObject endInvParty = new EndObject(client, "Involved Party", selection);
            endInvParty.setVisible(true);
        }
    }

    private void deleteInvolvedParty() {
        Integer selection = invPartyPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Involved Party " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Involved Party Delete - Yes button clicked");
                    int result = client.deleteInvolvedParty(selection);
                    if (result > 0) {
                        String message = "Involved Party " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    } else {
                        String message = "Involved Party " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewInvolvedParty() {
        Integer selection = invPartyPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                InvolvedPartyInterface invParty = client.getInvolvedParty(selection);
                if (invParty != null) {
                    InvPartyDetails contractDetails = new InvPartyDetails(client, invParty);
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createAddress() {
        try {
            CreateAddressUsage createAddress = new CreateAddressUsage(client, "Application", application.getApplicationRef());
            createAddress.setVisible(true);
            System.out.println("TEST - Create Address");
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateAddress() {
        Integer selection = addressPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                AddressUsageInterface addressUsage = client.getAddressUsage(selection);
                if (addressUsage != null) {
                    UpdateAddressUsage addressDetails = new UpdateAddressUsage(client, addressUsage, "Application", application.getApplicationRef());
                    addressDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteAddress() {
        Integer selection = addressPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE address " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Address Delete - Yes button clicked");
                    int result = client.deleteApplicationAddressUsage(application.getApplicationRef(), selection);
                    if (result > 0) {
                        String message = "Address " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    } else {
                        String message = "Address " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewAddress() {
        if (addressPanel.getSelectedObjectRef() != null) {
            AddressUsageInterface address;
            try {
                address = client.getAddressUsage(addressPanel.getSelectedObjectRef());
                if (address != null) {
                    AddressUsageDetails addressDetails = new AddressUsageDetails(client, address, "Application", application.getApplicationRef());
                    addressDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Application", application.getApplicationRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = application.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Application", application.getApplicationRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteApplicationNote(application.getApplicationRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = application.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails contractDetails = new NoteDetails(client, note, "Application", application.getApplicationRef());
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createProperty() {
        PropertySearch createProperty = new PropertySearch(client, new IntegerListener() {
            @Override
            public void intOmitted(int propRef) {
                try {
                    client.addInterestedProperty(application.getApplicationRef(), propRef);
                } catch (RemoteException ex) {
                    Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        createProperty.setVisible(true);
        System.out.println("TEST - Create Property");
    }
    
    private void endProperty() {
        Integer selection = propInterestPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Property Ref: " + selection);
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to END interest in Property " + selection + "for Application " + application.getApplicationRef() + " ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Property End - Yes button clicked");
                    int result = client.endInterestInProperty(application.getApplicationRef(), selection);
                    if (result > 0) {
                        String message = "Interest in Property " + selection + " has been successfully ended for Application " + application.getApplicationRef();
                        String title = "Information";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    } else {
                        String message = "Errors with ended Interest in Property " + selection + " and has not been ended";
                        String title = "Error";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewProperty() {
        if (propInterestPanel.getSelectedObjectRef() != null) {
            PropertyInterface property;
            try {
                property = client.getProperty(propInterestPanel.getSelectedObjectRef());
                if (property != null) {
                    PropertyDetails propDetails = new PropertyDetails(client, property);
                    propDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createDocument() {
        try {
            CreateDocument createDoc = new CreateDocument(client, "Application", application.getApplicationRef());
            createDoc.setVisible(true);
            System.out.println("TEST - Create Document");
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                UpdateDocument updateDoc = new UpdateDocument(client, selection, "Application", application.getApplicationRef());
                updateDoc.setVisible(true);
                System.out.println("TEST - Update Document");
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteApplicationDocument(application.getApplicationRef(), selection);
                    if (result > 0) {
                        String message = "Document " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    } else {
                        String message = "Document " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(AppDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            Document document;
            try {
                document = application.getDocument(documentPanel.getSelectedObjectRef());
                client.downloadApplicationDocument(application.getApplicationRef(), document.getDocumentRef(), document.getCurrentVersion());
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewPreviousDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            final Document document;
            try {
                document = application.getDocument(documentPanel.getSelectedObjectRef());
                if (document != null) {
                    ViewPreviousDocument viewPreviousDocument = new ViewPreviousDocument(document.getCurrentVersion(), new IntegerListener() {
                        @Override
                        public void intOmitted(int ref) {
                            try {
                                client.downloadApplicationDocument(application.getApplicationRef(), document.getDocumentRef(), ref);
                            } catch (RemoteException ex) {
                                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    viewPreviousDocument.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            if (application.getAppCorrName() != null) {
                name.setText(application.getAppCorrName());
            }
            if (application.getAppStatusCode() != null) {
                status.setText(application.getAppStatusCode());
            }
            if (application.getAppStartDate() != null) {
                start.setText(formatter.format(application.getAppStartDate()));
            }
            if (application.getAppEndDate() != null) {
                end.setText(formatter.format(application.getAppEndDate()));
            }
            if (application.hasTenancyRef()) {
                tenRef = new JLabel(String.valueOf(application.getTenancyRef()));
            }
            invPartyPanel.setData(application.getHousehold());
            addressPanel.setData(application.getApplicationAddressess());
            notePanel.setData(application.getNotes());
            propInterestPanel.setData(application.getPropertiesInterestedIn());
            documentPanel.setData(application.getDocuments());
            modPanel.setData(application.getModifiedBy());
            invPartyPanel.refresh();
            addressPanel.refresh();
            notePanel.refresh();
            propInterestPanel.refresh();
            documentPanel.refresh();
            modPanel.refresh();
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actionChoice(String action) {
        int ref = tabbedPane.getSelectedIndex();
        switch (action) {
            case "Create":
                System.out.println("TEST - Create Button");

                if (ref == 0) {
                    //Involved Partys
                    createInvolvedParty();
                    System.out.println("Create Involved Party");

                } else if (ref == 1) {
                    //Addresses
                    createAddress();
                    System.out.println("Create Address");

                } else if (ref == 2) {
                    //Notes
                    createNote();
                    System.out.println("Create Note");

                } else if (ref == 3) {
                    //Properties
                    createProperty();
                    System.out.println("Create Property");

                } else if (ref == 4) {
                    //Documents
                    createDocument();
                    System.out.println("TEST - Create Document");

                }
                break;

            case "Update":
                System.out.println("TEST - Update Button");

                if (ref == 0) {
                    //Involved Partys
                    updateInvolvedParty();
                    System.out.println("TEST - Update Involved Party");

                } else if (ref == 1) {
                    //Addresses
                    updateAddress();
                    System.out.println("TEST - Update Address");

                } else if (ref == 2) {
                    //Notes
                    updateNote();
                    System.out.println("TEST - Update Note");

                } else if (ref == 4) {
                    //Document
                    updateDocument();
                    System.out.println("TEST - Update Document");

                }
                break;

             case "End":
                System.out.println("TEST - End Button");

                if (ref == 0) {
                    //Involved Partys
                    endInvolvedParty();
                    System.out.println("TEST - End Involved Party");

                } else if (ref == 3) {
                    //Addresses
                    endProperty();
                    System.out.println("TEST - End Property");

                }
                break;

            case "Delete":
                System.out.println("TEST - Delete Button");
                if (ref == 0) {
                    //Involved Partys
                    deleteInvolvedParty();
                    System.out.println("TEST - Delete Involved Party");

                } else if (ref == 1) {
                    //Addresses
                    deleteAddress();
                    System.out.println("TEST - Delete Address");

                } else if (ref == 2) {
                    //Notes
                    deleteNote();
                    System.out.println("TEST - Delete Note");

                } else if (ref == 4) {
                    //Document
                    deleteDocument();
                    System.out.println("TEST - Delete Document");

                }
                break;

            case "View Details":
                System.out.println("TEST - View Details Button");
                if (ref == 0) {
                    //Involved Parties
                    viewInvolvedParty();
                    System.out.println("TEST - View Involved Party");

                } else if (ref == 1) {
                    //Addresses
                    viewAddress();
                    System.out.println("TEST - View Addresses");

                } else if (ref == 2) {
                    //Notes
                    viewNote();
                    System.out.println("TEST - View Note");

                } else if (ref == 3) {
                    //Property
                    viewProperty();
                    System.out.println("TEST - View Property");

                } else if (ref == 4) {
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
        JMenuItem tenancyItem = null;
        try {
            if (application.hasTenancyRef()) {
                // Link to Menu
                JMenu linksMenu = new JMenu("Link To");
                tenancyItem = new JMenuItem("Tenancy");
                linksMenu.add(tenancyItem);
                menuBar.add(linksMenu);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                int action = JOptionPane.showConfirmDialog(AppDetails.this,
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

                int action = JOptionPane.showConfirmDialog(AppDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                UpdateApp appDetails = new UpdateApp(client, application);
                appDetails.setVisible(true);
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Application " + application.getApplicationRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Application Delete - Yes button clicked");
                        int result = client.deleteApplication(application.getApplicationRef());
                        if (result > 0) {
                            String message = "Application " + application.getApplicationRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(AppDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Application " + application.getApplicationRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(AppDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
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

        if (tenancyItem != null) {
            tenancyItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        TenancyInterface tenancy = client.getTenancy(application.getTenancyRef());
                        TenancyDetails tenDetails = new TenancyDetails(client, tenancy);
                        tenDetails.setVisible(true);
                    } catch (RemoteException ex) {
                        Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
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
