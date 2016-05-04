/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

import client_gui.contact.ContactPanel;
import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.EndObject;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.PDFFileFilter;
import client_gui.addressUsage.AddressUsageDetails;
import client_gui.addressUsage.AddressUsagePanel;
import client_gui.addressUsage.CreateAddressUsage;
import client_gui.addressUsage.UpdateAddressUsage;
import client_gui.contact.ContactDetails;
import client_gui.contact.CreateContact;
import client_gui.contact.UpdateContact;
import client_gui.document.CreateDocument;
import client_gui.document.DocumentPanel;
import client_gui.modifications.ModPanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.NotePanel;
import client_gui.note.UpdateNote;
import interfaces.AddressUsageInterface;
import interfaces.PersonInterface;
import interfaces.Document;
import interfaces.ContactInterface;
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
    private JLabel gender;
    private JLabel ethnic;
    private JLabel name;
    private JLabel language;
    private JLabel nationality;
    private JLabel dob;
    private JLabel marStatus;
    private JLabel religion;
    private JLabel sex;

    
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

        JLabel pGender = new JLabel("Gender    ");
        pGender.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pGender, gc);

        gender = new JLabel(person.getGender().getCode());
        gender.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(gender, gc);

        JLabel ethnicLabel = new JLabel("Ethnicity    ");
        ethnicLabel.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(ethnicLabel, gc);

        ethnic = new JLabel(person.getEthnicOrigin().getCode());
        ethnic.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ethnic, gc);

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

        name = new JLabel(person.getName());
        name.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(name, gc);
        
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
        
        language = new JLabel(person.getLanguage().getCode());
        language.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(language, gc);

        JLabel pNationality = new JLabel("Nationality    ");
        pNationality.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pNationality, gc);
        
        nationality = new JLabel(person.getNationality().getCode());
        nationality.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(nationality, gc);
        
        
        /////////// NEXT ROW
        
        gc.gridx = 0;
        gc.gridy++;
        
        JLabel pDOB = new JLabel("Date of Birth    ");
        pDOB.setFont(plainFont);

        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pDOB, gc);
        
        dob = new JLabel(formatter.format(person.getDateOfBirth()));
        dob.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(dob, gc);

        JLabel pMarStatus = new JLabel("Marital Status    ");
        pMarStatus.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pMarStatus, gc);

        marStatus = new JLabel(person.getMaritalStatus().getCode());
        marStatus.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(marStatus, gc);

        JLabel pReligion = new JLabel("Religion    ");
        pReligion.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pReligion, gc);

        religion = new JLabel(person.getReligion().getCode());
        religion.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(religion, gc);

        JLabel pSex = new JLabel("Sexuality    ");
        pSex.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pSex, gc);

        sex = new JLabel(person.getSexuality().getCode());
        sex.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(sex, gc);
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
                            //Contacts
                            createContact();
                            System.out.println("Create Contact");
                            
                        } else if (pane == 1) {
                            //Addresses
                            createAddress();
                            System.out.println("Create Address");
                            
                        } else if (pane == 2) {
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
                            //Contacts
                            updateContact();
                            System.out.println("TEST - Update Contact");

                        } else if (pane == 1) {
                            //Addresses
                            updateAddress();
                            System.out.println("TEST - Update Address");
                            
                        } else if (pane == 2) {
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
                            //Involved Partys
                            endContact();
                            System.out.println("TEST - End Contact");

                        }
                        break;

                    case "Delete":
                        System.out.println("TEST - Delete Button");
                        if (pane == 0) {
                            //Contacts
                            deleteContact();
                            System.out.println("TEST - Delete Contact");

                        } else if (pane == 1) {
                            //Addresses
                            deleteAddress();
                            System.out.println("TEST - Delete Address");
                            
                        } else if (pane == 2) {
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
                            //Contacts
                            viewContact();
                            System.out.println("TEST - View Contact");

                        } else if (pane == 1) {
                            //Addresses
                            viewAddress();
                            System.out.println("TEST - View Address");

                        } else if (pane == 2) {
                            //Notes
                            viewNote();
                            System.out.println("TEST - View Note");

                        } else if (pane == 3) {
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
        });
        
        tabbedPane = new JTabbedPane();
        
        contactPanel = new ContactPanel("Contacts");
        
        try {
            contactPanel.setData(person.getContacts());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        contactPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int contactRef) {
                if(contactRef > 0) {
                    try {
                        ContactInterface contact = person.getContact(contactRef);
                        if(contact != null) {
                            System.out.println(contact.getContactValue());
                            System.out.println("TEST1-Contact");
                            ContactDetails contactGUI = new ContactDetails(client, contact);
                            contactGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        addressPanel = new AddressUsagePanel("Addresses");
        
        try {
            addressPanel.setData(person.getAddresses());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addressPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int addressRef) {
                if(addressRef > 0) {
                    try {
                        AddressUsageInterface address = client.getAddressUsage(addressRef);
                        if(address != null) {
                            System.out.println(address.getAddress().printAddress());
                            System.out.println("TEST1-Address Usage");
                            AddressUsageDetails addressGUI = new AddressUsageDetails(client, address);
                            addressGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(person.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int noteRef) {
                if(noteRef > 0) {
                    try {
                        Note note = person.getNote(noteRef);
                        if(note != null) {
                            System.out.println(note.getReference());
                            System.out.println("TEST1-Note");
                            NoteDetails noteGUI=  new NoteDetails(client, note);
                            noteGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        documentPanel = new DocumentPanel("Documents");
        
        try {
            documentPanel.setData(person.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        documentPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int documentRef) {
                if(documentRef > 0) {
                    try {
                        Document document = person.getDocument(documentRef);
                        if(document != null) {
                            System.out.println(document.getCurrentDocumentName());
                            System.out.println("TEST1-Document");
                            client.downloadPersonDocument(person.getPersonRef(), document.getDocumentRef(), document.getCurrentVersion());
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
                    ContactDetails contractDetails = new ContactDetails(client, contact);
                    contractDetails.setVisible(true);
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
                    AddressUsageDetails addressDetails = new AddressUsageDetails(client, address);
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
                    NoteDetails contractDetails = new NoteDetails(client, note);
                    contractDetails.setVisible(true);
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
                Document document = person.getDocument(selection);
                if (document != null) {
                    if(fileChooser.showOpenDialog(PersonDetails.this) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        int result = client.updatePersonDocument(person.getPersonRef(), document.getDocumentRef(), file.getPath());
                        if (result > 0) {
                            String message = "Document " + selection + " has been successfully updated";
                            String title = "Information";
                            OKDialog.okDialog(PersonDetails.this, message, title);
                        } else {
                            String message = "There is some errors with the information supplied to UPDATE Document " + document.getDocumentRef() + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(PersonDetails.this, message, title);
                        }
                        System.out.println(fileChooser.getSelectedFile());
                    }
                }
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
    
    private void refresh() {
        try {
            
            niNumber.setText(person.getNI());
            
            gender.setText(person.getGender().getCode());
            
            ethnic.setText(person.getEthnicOrigin().getCode());
            
            name.setText(person.getName());
            
            language.setText(person.getLanguage().getCode());
            
            nationality.setText(person.getNationality().getCode());
            
            dob.setText(formatter.format(person.getDateOfBirth()));
            
            marStatus.setText(person.getMaritalStatus().getCode());
            
            religion.setText(person.getReligion().getCode());
            
            sex.setText(person.getSexuality().getCode());
            
            
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

                int action = JOptionPane.showConfirmDialog(PersonDetails.this,
                        "Do you really want to exit the person?",
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
        return menuBar;
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new PersonDetails().setVisible(true);
//            }
//        });
//    }
}
