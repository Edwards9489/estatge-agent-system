/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.property;

import client_gui.propertyElement.PropElementPanel;
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
import client_gui.address.AddressDetails;
import client_gui.document.CreateDocument;
import client_gui.document.DocumentPanel;
import client_gui.document.UpdateDocument;
import client_gui.document.ViewPreviousDocument;
import client_gui.element.ElementDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.landlord.LandlordDetails;
import client_gui.modifications.ModPanel;
import client_gui.landlord.LandlordPanel;
import client_gui.login.LoginForm;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.NotePanel;
import client_gui.note.UpdateNote;
import client_gui.propertyElement.CreatePropElement;
import client_gui.propertyElement.PropElementDetails;
import client_gui.propertyElement.UpdatePropElement;
import interfaces.AddressInterface;
import interfaces.PropertyInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.LandlordInterface;
import interfaces.Note;
import interfaces.PropertyElementInterface;
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
public class PropertyDetails extends JFrame {
    
    private ClientImpl client = null;
    private PropertyInterface property = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;    
    private PropElementPanel propElementPanel;
    private NotePanel notePanel;
    private LandlordPanel landlordPanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    private JFileChooser fileChooser;
    
    private JLabel status;
    private JLabel propType;
    private JLabel propSubType;
    private JLabel acquiredDate;
    private JLabel address;
    private JLabel leaseRef;
    private JLabel endDate;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public PropertyDetails(ClientImpl client, PropertyInterface app) {
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
    private void setApplication(PropertyInterface app) {
        if (property == null) {
            this.property = app;
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
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Property Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 20;

        JLabel propRefLabel = new JLabel("Property Ref    ");
        Font font = propRefLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        propRefLabel.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(propRefLabel, gc);

        JLabel propRef = new JLabel(String.valueOf(property.getPropRef()));
        propRef.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(propRef, gc);

        JLabel statusLabel = new JLabel("Status    ");
        statusLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(statusLabel, gc);

        status = new JLabel(property.getPropStatus());
        status.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(status, gc);

        JLabel typeLabel = new JLabel("Type Code    ");
        typeLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(typeLabel, gc);
        
        propType = new JLabel(property.getPropType().getCode());
        propType.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(propType, gc);
        
        JLabel subTypeLabel = new JLabel("Sub Type Code    ");
        subTypeLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(subTypeLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        if (property.getPropSubType() != null) {
            propSubType = new JLabel(property.getPropSubType().getCode());
        } else {
            propSubType = new JLabel("");
        }
        propSubType.setFont(boldFont);
        detailsPanel.add(propSubType, gc);

        JLabel acquiredLabel = new JLabel("Acquired Date    ");
        acquiredLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(acquiredLabel, gc);

        acquiredDate = new JLabel(formatter.format(property.getAcquiredDate()));
        acquiredDate.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(acquiredDate, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel addressLabel = new JLabel("Address    ");
        addressLabel.setFont(plainFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(addressLabel, gc);

        address = new JLabel(property.getAddress().printAddress());
        address.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 5;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(address, gc);

        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(new JLabel(), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel(), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(new JLabel(), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel(), gc);

        JLabel propLeaseRef = new JLabel("Lease Ref    ");
        propLeaseRef.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(propLeaseRef, gc);
        
        if(property.getLeaseRef() != null) {
            leaseRef = new JLabel(String.valueOf(property.getLeaseRef()));
        } else {
            leaseRef = new JLabel("");
        }
        leaseRef.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(leaseRef, gc);

        JLabel endDateLabel = new JLabel("Lease End Date    ");
        endDateLabel.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(endDateLabel, gc);
        
        if(property.getLeaseEndDate() != null) {
            endDate = new JLabel(formatter.format(property.getLeaseEndDate()));
        } else {
            endDate = new JLabel("");
        }
        endDate.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(endDate, gc);
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
        
        propElementPanel = new PropElementPanel("Property Elements");
        
        try {
            propElementPanel.setData(property.getPropertyElements());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        propElementPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(property.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        landlordPanel = new LandlordPanel("Landlords");
        
        try {
            landlordPanel.setData(property.getLandlords());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        landlordPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                Integer selection = landlordPanel.getSelectedObjectRef();
                switch (text) {
                        
                    case "View Details":
                        if (selection != null) {
                            try {
                                LandlordInterface landlord = client.getLandlord(selection);
                                if (landlord != null) {
                                    LandlordDetails landlordDetails = new LandlordDetails(client, landlord);
                                    landlordDetails.setVisible(true);
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
            documentPanel.setData(property.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(property.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(propElementPanel, "Property Elements");
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(landlordPanel, "Landlords");
        tabbedPane.add(documentPanel, "Documents");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(property.getCreatedBy(), property.getCreatedDate(), property.getLastModifiedBy(), property.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }
    
    private void createPropElement() {
        try {
            CreatePropElement createPropElement = new CreatePropElement(client, property.getPropRef());
            createPropElement.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updatePropElement() {
        Integer selection = propElementPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                PropertyElementInterface propElement = property.getPropElement(selection);
                if (propElement != null) {
                    UpdatePropElement updatePropElement = new UpdatePropElement(client, propElement, property.getPropRef());
                    updatePropElement.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void endPropElement() {
        Integer selection = propElementPanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Prop Element Ref: " + selection);
            EndObject endInvParty = new EndObject(client, "Property Element", selection);
            endInvParty.setVisible(true);
        }
    }
    
    private void deletePropElement() {
        Integer selection = propElementPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Property Element " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Note Delete - Yes button clicked");
                    int result = client.deleteLeaseNote(property.getPropRef(), selection);
                    if (result > 0) {
                        String message = "Property Element " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PropertyDetails.this, message, title);
                    } else {
                        String message = "Property Element " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PropertyDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewPropElement() {
        if (propElementPanel.getSelectedObjectRef() != null) {
            PropertyElementInterface propElement;
            try {
                propElement = property.getPropElement(propElementPanel.getSelectedObjectRef());
                if (propElement != null) {
                    PropElementDetails propElementDetails = new PropElementDetails(client, propElement);
                    propElementDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Property", property.getPropRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = property.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Property", property.getPropRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deletePropertyNote(property.getPropRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PropertyDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PropertyDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = property.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails landlordDetails = new NoteDetails(client, note, "Property", property.getPropRef());
                    landlordDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createDocument() {
        try {
            CreateDocument createDoc = new CreateDocument(client, "Property", property.getPropRef());
            createDoc.setVisible(true);
            System.out.println("TEST - Create Document");
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                UpdateDocument updateDoc = new UpdateDocument(client, selection, "Property", property.getPropRef());
                updateDoc.setVisible(true);
                System.out.println("TEST - Update Document");
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deletePropertyDocument(property.getPropRef(), selection);
                    if (result > 0) {
                        String message = "Document " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PropertyDetails.this, message, title);
                    } else {
                        String message = "Document " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PropertyDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            Document document;
            try {
                document = property.getDocument(documentPanel.getSelectedObjectRef());
                client.downloadPropertyDocument(property.getPropRef(), document.getDocumentRef(), document.getCurrentVersion());
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewPreviousDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            final Document document;
            try {
                document = property.getDocument(documentPanel.getSelectedObjectRef());
                if (document != null) {
                    ViewPreviousDocument viewPreviousDocument = new ViewPreviousDocument(document.getCurrentVersion(), new IntegerListener() {
                        @Override
                        public void intOmitted(int ref) {
                            try {
                                client.downloadPropertyDocument(property.getPropRef(), document.getDocumentRef(), ref);
                            } catch (RemoteException ex) {
                                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    viewPreviousDocument.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            status.setText(property.getPropStatus());
            
            propType.setText(property.getPropType().getCode());
            if (property.getPropSubType() != null) {
                propSubType.setText(property.getPropSubType().getCode());
            } else {
                propSubType.setText("");
            }
            propSubType.setText(property.getPropSubType().getCode());
            
            acquiredDate.setText(formatter.format(property.getAcquiredDate()));
            
            address.setText(property.getAddress().printAddress());
            
            if(property.getLeaseRef() != null) {
                leaseRef.setText(String.valueOf(property.getLeaseRef()));
            } else {
                leaseRef.setText("");
            }
            
            if(property.getLeaseEndDate() != null) {
                endDate.setText(formatter.format(property.getLeaseEndDate()));
            } else {
                endDate.setText("");
            }
            
            propElementPanel.setData(property.getPropertyElements());
            landlordPanel.setData(property.getLandlords());
            notePanel.setData(property.getNotes());
            documentPanel.setData(property.getDocuments());
            modPanel.setData(property.getModifiedBy());
            propElementPanel.refresh();
            landlordPanel.refresh();
            notePanel.refresh();
            documentPanel.refresh();
            modPanel.refresh();
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    createPropElement();
                    System.out.println("Create Prop Element");

                } else if (pane == 1) {
                    //Notes
                    createNote();
                    System.out.println("Create Note");

                } else if (pane == 3) {
                    //Documents
                    createDocument();
                    System.out.println("TEST - Create Document");

                }
                break;

            case "Update":
                System.out.println("TEST - Update Button");

                if (pane == 0) {
                    //Notes
                    updatePropElement();
                    System.out.println("TEST - Update Prop Element");

                } else if (pane == 1) {
                    //Notes
                    updateNote();
                    System.out.println("TEST - Update Note");

                } else if (pane == 3) {
                    //Document
                    updateDocument();
                    System.out.println("TEST - Update Document");

                }
                break;

             case "End":
                System.out.println("TEST - End Button");

                if (pane == 0) {
                    //Prop Elements
                    endPropElement();
                    System.out.println("TEST - End Prop Element");

                }
                break;

            case "Delete":
                System.out.println("TEST - Delete Button");
                if (pane == 0) {
                    //Notes
                    deletePropElement();
                    System.out.println("TEST - Delete Prop Element");

                } else if (pane == 1) {
                    //Notes
                    deleteNote();
                    System.out.println("TEST - Delete Note");

                } else if (pane == 3) {
                    //Document
                    deleteDocument();
                    System.out.println("TEST - Delete Document");

                }
                break;

            case "View Details":
                System.out.println("TEST - View Details Button");
                if (pane == 0) {
                    //Notes
                    viewPropElement();
                    System.out.println("TEST - View Prop Element");

                } else if (pane == 1) {
                    //Notes
                    viewNote();
                    System.out.println("TEST - View Note");

                } else if (pane == 2) {
                    //Notes
                    viewLandlord();
                    System.out.println("TEST - View Landlord");

                } else if (pane == 3) {
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

        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(updateItem);
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        
        // Link To Menu
        
        JMenu links = new JMenu("Link To");
        
        JMenuItem addressItem = new JMenuItem("Address");
        JMenuItem propTypeItem = new JMenuItem("Prop Type");
        JMenuItem propSubTypeItem = new JMenuItem("Prop Sub Type");
        
        links.add(addressItem);
        links.add(propTypeItem);
        links.add(propSubTypeItem);
        

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
                int action = JOptionPane.showConfirmDialog(PropertyDetails.this,
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

                int action = JOptionPane.showConfirmDialog(PropertyDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                UpdateProperty propertyDetails = new UpdateProperty(client, property);
                propertyDetails.setVisible(true);
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Property " + property.getPropRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Property Delete - Yes button clicked");
                        int result = client.deleteProperty(property.getPropRef());
                        if (result > 0) {
                            String message = "Property " + property.getPropRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(PropertyDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Property " + property.getPropRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(PropertyDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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

        addressItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    AddressInterface address = property.getAddress();
                    AddressDetails addressDetails = new AddressDetails(client, address);
                    addressDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        propTypeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = property.getPropType();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Property Type");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        propSubTypeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = property.getPropSubType();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Property Sub Type");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PropertyDetails.class.getName()).log(Level.SEVERE, null, ex);
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
