/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

import client_gui.contact.ContactPanel;
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
import client_gui.addressUsage.AddressUsageDetails;
import client_gui.addressUsage.AddressUsagePanel;
import client_gui.addressUsage.CreateAddressUsage;
import client_gui.addressUsage.UpdateAddressUsage;
import client_gui.contact.ContactDetails;
import client_gui.contact.CreateContact;
import client_gui.contact.UpdateContact;
import client_gui.document.CreateDocument;
import client_gui.document.DocumentPanel;
import client_gui.document.UpdateDocument;
import client_gui.document.ViewPreviousDocument;
import client_gui.element.ElementDetails;
import client_gui.employee.EmployeeDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.landlord.LandlordDetails;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.NotePanel;
import client_gui.note.UpdateNote;
import interfaces.AddressUsageInterface;
import interfaces.PersonInterface;
import interfaces.Document;
import interfaces.ContactInterface;
import interfaces.Element;
import interfaces.EmployeeInterface;
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
public class PersonDetails extends JFrame {
    
    private ClientImpl client = null;
    private PersonInterface person = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private ContactPanel contactPanel;
    private AddressUsagePanel addressPanel;
    private NotePanel notePanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    private JFileChooser fileChooser;
    
    private JLabel niNumber;
    private JLabel pGender;
    private JLabel pEthnic;
    private JLabel pName;
    private JLabel pLanguage;
    private JLabel pNationality;
    private JLabel pDOB;
    private JLabel pMarStatus;
    private JLabel pReligion;
    private JLabel pSex;

    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public PersonDetails(ClientImpl client, PersonInterface app) {
        super("MSc Properties");
        setClient(client);
        setPerson(app);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Person is initiated
    private void setPerson(PersonInterface app) {
        if (person == null) {
            this.person = app;
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
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Person Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 30;

        JLabel personRefLabel = new JLabel("Person Ref    ");
        Font font = personRefLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        personRefLabel.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(personRefLabel, gc);

        JLabel personRef = new JLabel(String.valueOf(person.getPersonRef()));
        personRef.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(personRef, gc);

        JLabel niLabel = new JLabel("National Insurance N.o    ");
        niLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(niLabel, gc);

        niNumber = new JLabel(person.getNI());
        niNumber.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(niNumber, gc);

        JLabel genderLabel = new JLabel("Gender    ");
        genderLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(genderLabel, gc);

        pGender = new JLabel(person.getGender().getCode());
        pGender.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pGender, gc);

        JLabel ethnicLabel = new JLabel("Ethnicity    ");
        ethnicLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(ethnicLabel, gc);

        pEthnic = new JLabel(person.getEthnicOrigin().getCode());
        pEthnic.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pEthnic, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel nameLabel = new JLabel("Name    ");
        nameLabel.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(nameLabel, gc);

        pName = new JLabel(person.getName());
        pName.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pName, gc);
        
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

        JLabel languageLabel = new JLabel("Language    ");
        languageLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(languageLabel, gc);
        
        pLanguage = new JLabel(person.getLanguage().getCode());
        pLanguage.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pLanguage, gc);

        JLabel nationalityLabel = new JLabel("Nationality    ");
        nationalityLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(nationalityLabel, gc);
        
        pNationality = new JLabel(person.getNationality().getCode());
        pNationality.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pNationality, gc);
        
        
        /////////// NEXT ROW
        
        gc.gridx = 0;
        gc.gridy++;
        
        JLabel dobLabel = new JLabel("Date of Birth    ");
        dobLabel.setFont(plainFont);

        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(dobLabel, gc);
        
        pDOB = new JLabel(formatter.format(person.getDateOfBirth()));
        pDOB.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pDOB, gc);

        JLabel marStatusLabel = new JLabel("Marital Status    ");
        marStatusLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(marStatusLabel, gc);

        pMarStatus = new JLabel(person.getMaritalStatus().getCode());
        pMarStatus.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pMarStatus, gc);

        JLabel religionLabel = new JLabel("Religion    ");
        religionLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(religionLabel, gc);

        pReligion = new JLabel(person.getReligion().getCode());
        pReligion.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pReligion, gc);

        JLabel sexLabel = new JLabel("Sexuality    ");
        sexLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(sexLabel, gc);

        pSex = new JLabel(person.getSexuality().getCode());
        pSex.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(pSex, gc);
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
        
        contactPanel = new ContactPanel("Contacts");
        
        try {
            contactPanel.setData(person.getContacts());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        contactPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        addressPanel = new AddressUsagePanel("Addresses");
        
        try {
            addressPanel.setData(person.getAddresses());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addressPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(person.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        documentPanel = new DocumentPanel("Documents");
        
        try {
            documentPanel.setData(person.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(person.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(contactPanel, "Contacts");
        tabbedPane.add(addressPanel, "Addresses");
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(documentPanel, "Documents");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(person.getCreatedBy(), person.getCreatedDate(), person.getLastModifiedBy(), person.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }

    private void createContact() {
        try {
            CreateContact createContact = new CreateContact(client, "Person", person.getPersonRef());
            createContact.setVisible(true);
            System.out.println("TEST - Create Contact");
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateContact() {
        Integer selection = contactPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Contact Ref: " + selection);
                ContactInterface contact = person.getContact(selection);
                if (contact != null) {
                    System.out.println("Contact Value: " + contact.getContactValue());
                    UpdateContact updateContact = new UpdateContact(client, contact, "Person", person.getPersonRef());
                    updateContact.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void endContact() {
        Integer selection = contactPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Involved Party Ref: " + selection);
                EndObject endInvParty = new EndObject(client, "Person Contact", selection, person.getPersonRef());
                endInvParty.setVisible(true);
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteContact() {
        Integer selection = contactPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Contact " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Involved Party Delete - Yes button clicked");
                    int result = client.deletePersonContact(person.getPersonRef(), selection);
                    if (result > 0) {
                        String message = "Contact " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    } else {
                        String message = "Contact " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewContact() {
        Integer selection = contactPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                ContactInterface contact = person.getContact(selection);
                if (contact != null) {
                    ContactDetails contactDetails = new ContactDetails(client, contact, "Person", person.getPersonRef());
                    contactDetails.setVisible(true);
                    setVisible(false);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createAddress() {
        try {
            CreateAddressUsage createAddress = new CreateAddressUsage(client, "Person", person.getPersonRef());
            createAddress.setVisible(true);
            System.out.println("TEST - Create Address");
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateAddress() {
        Integer selection = addressPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                AddressUsageInterface addressUsage = client.getAddressUsage(selection);
                if (addressUsage != null) {
                    UpdateAddressUsage addressDetails = new UpdateAddressUsage(client, addressUsage, "Person", person.getPersonRef());
                    addressDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deletePersonAddressUsage(person.getPersonRef(), selection);
                    if (result > 0) {
                        String message = "Address " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    } else {
                        String message = "Address " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewAddress() {
        if (addressPanel.getSelectedObjectRef() != null) {
            AddressUsageInterface address;
            try {
                address = client.getAddressUsage(addressPanel.getSelectedObjectRef());
                if (address != null) {
                    AddressUsageDetails addressDetails = new AddressUsageDetails(client, address, "Person", person.getPersonRef());
                    addressDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Person", person.getPersonRef());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = person.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Person", person.getPersonRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deletePersonNote(person.getPersonRef(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = person.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails noteDetails = new NoteDetails(client, note, "Person", person.getPersonRef());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createDocument() {
        try {
            CreateDocument createDoc = new CreateDocument(client, "Person", person.getPersonRef());
            createDoc.setVisible(true);
            System.out.println("TEST - Create Document");
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                UpdateDocument updateDoc = new UpdateDocument(client, selection, "Person", person.getPersonRef());
                updateDoc.setVisible(true);
                System.out.println("TEST - Update Document");
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deletePersonDocument(person.getPersonRef(), selection);
                    if (result > 0) {
                        String message = "Document " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    } else {
                        String message = "Document " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PersonDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            Document document;
            try {
                document = person.getDocument(documentPanel.getSelectedObjectRef());
                client.downloadPersonDocument(person.getPersonRef(), document.getDocumentRef(), document.getCurrentVersion());
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewPreviousDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            final Document document;
            try {
                document = person.getDocument(documentPanel.getSelectedObjectRef());
                if (document != null) {
                    ViewPreviousDocument viewPreviousDocument = new ViewPreviousDocument(document.getCurrentVersion(), new IntegerListener() {
                        @Override
                        public void intOmitted(int ref) {
                            try {
                                client.downloadPersonDocument(person.getPersonRef(), document.getDocumentRef(), ref);
                            } catch (RemoteException ex) {
                                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    viewPreviousDocument.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            
            niNumber.setText(person.getNI());
            
            pGender.setText(person.getGender().getCode());
            
            pEthnic.setText(person.getEthnicOrigin().getCode());
            
            pName.setText(person.getName());
            
            pLanguage.setText(person.getLanguage().getCode());
            
            pNationality.setText(person.getNationality().getCode());
            
            pDOB.setText(formatter.format(person.getDateOfBirth()));
            
            pMarStatus.setText(person.getMaritalStatus().getCode());
            
            pReligion.setText(person.getReligion().getCode());
            
            pSex.setText(person.getSexuality().getCode());
            
            
            contactPanel.setData(person.getContacts());
            addressPanel.setData(person.getAddresses());
            notePanel.setData(person.getNotes());
            documentPanel.setData(person.getDocuments());
            modPanel.setData(person.getModifiedBy());
            contactPanel.refresh();
            addressPanel.refresh();
            notePanel.refresh();
            documentPanel.refresh();
            modPanel.refresh();
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actionChoice(String text) {
        int ref = tabbedPane.getSelectedIndex();

        System.out.println(text);
        switch (text) {
            case "Create":
                System.out.println("TEST - Create Button");

                if (ref == 0) {
                    //Contacts
                    createContact();
                    System.out.println("Create Contact");

                } else if (ref == 1) {
                    //Addresses
                    createAddress();
                    System.out.println("Create Address");

                } else if (ref == 2) {
                    //Notes
                    createNote();
                    System.out.println("Create Note");

                } else if (ref == 3) {
                    //Documents
                    createDocument();
                    System.out.println("TEST - Create Document");

                }
                break;

            case "Update":
                System.out.println("TEST - Update Button");

                if (ref == 0) {
                    //Contacts
                    updateContact();
                    System.out.println("TEST - Update Contact");

                } else if (ref == 1) {
                    //Addresses
                    updateAddress();
                    System.out.println("TEST - Update Address");

                } else if (ref == 2) {
                    //Notes
                    updateNote();
                    System.out.println("TEST - Update Note");

                } else if (ref == 3) {
                    //Document
                    updateDocument();
                    System.out.println("TEST - Update Document");

                }
                break;

             case "End":
                System.out.println("TEST - End Button");

                if (ref == 0) {
                    //Involved Partys
                    endContact();
                    System.out.println("TEST - End Contact");

                }
                break;

            case "Delete":
                System.out.println("TEST - Delete Button");
                if (ref == 0) {
                    //Contacts
                    deleteContact();
                    System.out.println("TEST - Delete Contact");

                } else if (ref == 1) {
                    //Addresses
                    deleteAddress();
                    System.out.println("TEST - Delete Address");

                } else if (ref == 2) {
                    //Notes
                    deleteNote();
                    System.out.println("TEST - Delete Note");

                } else if (ref == 3) {
                    //Document
                    deleteDocument();
                    System.out.println("TEST - Delete Document");

                }
                break;

            case "View Details":
                System.out.println("TEST - View Details Button");
                if (ref == 0) {
                    //Contacts
                    viewContact();
                    System.out.println("TEST - View Contact");

                } else if (ref == 1) {
                    //Addresses
                    viewAddress();
                    System.out.println("TEST - View Address");

                } else if (ref == 2) {
                    //Notes
                    viewNote();
                    System.out.println("TEST - View Note");

                } else if (ref == 3) {
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
        
        JMenuItem titleItem = new JMenuItem("Title");
        JMenuItem genderItem = new JMenuItem("Gender");
        JMenuItem maritalStatusItem = new JMenuItem("Marital Status");
        JMenuItem ethnicOriginItem = new JMenuItem("Ethnic Origin");
        JMenuItem languageItem = new JMenuItem("Language");
        JMenuItem nationalityItem = new JMenuItem("Nationality");
        JMenuItem sexualityItem = new JMenuItem("Sexuality");
        JMenuItem religionItem = new JMenuItem("Religion");
        
        links.add(titleItem);
        links.add(genderItem);
        links.add(maritalStatusItem);
        links.add(ethnicOriginItem);
        links.add(languageItem);
        links.add(nationalityItem);
        links.add(sexualityItem);
        links.add(religionItem);
        

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
                int action = JOptionPane.showConfirmDialog(PersonDetails.this,
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

                int action = JOptionPane.showConfirmDialog(PersonDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                UpdatePerson personDetails = new UpdatePerson(client, person);
                personDetails.setVisible(true);
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Person " + person.getPersonRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Person Delete - Yes button clicked");
                        int result = client.deletePerson(person.getPersonRef());
                        if (result > 0) {
                            String message = "Person " + person.getPersonRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(PersonDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Person " + person.getPersonRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(PersonDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
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
        
        try {
            if (client.personEmployeeExists(person.getPersonRef())) {
                JMenuItem employee = new JMenuItem("Employee");
                links.add(employee);

                employee.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        try {
                            EmployeeInterface employee = client.getPersonEmployee(person.getPersonRef());
                            EmployeeDetails empDetails = new EmployeeDetails(client, employee);
                            empDetails.setVisible(true);
                        } catch (RemoteException ex) {
                            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }

            if (client.personLandlordExists(person.getPersonRef())) {
                JMenuItem landlord = new JMenuItem("Employee");
                links.add(landlord);

                landlord.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        try {
                            LandlordInterface landlord = client.getPersonLandlord(person.getPersonRef());
                            LandlordDetails landlordDetails = new LandlordDetails(client, landlord);
                            landlordDetails.setVisible(true);
                        } catch (RemoteException ex) {
                            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        titleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getTitle();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Title");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        genderItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getGender();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Gender");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        maritalStatusItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getMaritalStatus();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Marital Status");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ethnicOriginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getEthnicOrigin();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Ethnic Origin");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        languageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getLanguage();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Language");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        nationalityItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getNationality();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Nationality");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        sexualityItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getSexuality();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Sexuality");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        religionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Element element = person.getReligion();
                    ElementDetails elementDetails = new ElementDetails(client, element, "Religion");
                    elementDetails.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
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
