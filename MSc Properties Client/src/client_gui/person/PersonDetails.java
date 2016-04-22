/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.application.AddressPanel;
import client_gui.application.DocumentPanel;
import client_gui.application.ModPanel;
import client_gui.lease.NotePanel;
import interfaces.AddressUsageInterface;
import interfaces.PersonInterface;
import interfaces.Document;
import interfaces.InvolvedPartyInterface;
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
public class PersonDetails extends JFrame {
    
    private ClientImpl client = null;
    private PersonInterface person = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private ContactPanel contactPanel;
    private AddressPanel addressPanel;
    private NotePanel notePanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    
    //     contacts - addresses - notes - documents - modifications
    
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

        JLabel appRef = new JLabel("Person Ref    ");
        Font font = appRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        appRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(appRef, gc);

        JLabel ref = new JLabel(String.valueOf(person.getPersonRef()));
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel corrName = new JLabel("National Insurance N.o    ");
        corrName.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(corrName, gc);

        JLabel name = new JLabel(person.getNI());
        name.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(name, gc);

        JLabel pGender = new JLabel("Gender    ");
        pGender.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pGender, gc);

        JLabel gender = new JLabel(person.getGender().getCode());
        gender.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(gender, gc);

        JLabel appStatus = new JLabel("Ethnicity    ");
        appStatus.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(appStatus, gc);

        JLabel status = new JLabel(person.getEthnicOrigin().getCode());
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

        JLabel startDate = new JLabel("Name    ");
        startDate.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(startDate, gc);

        JLabel start = new JLabel(person.getName());
        start.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(start, gc);

        JLabel empty1 = new JLabel("");
        empty1.setFont(plainFont);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(empty1, gc);

        JLabel empty2 = new JLabel("");
        empty2.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(empty2, gc);

        JLabel tenancyRef = new JLabel("Language    ");
        tenancyRef.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(tenancyRef, gc);
        
        JLabel tenRef = new JLabel(person.getLanguage().getCode());
        tenRef.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(tenRef, gc);

        JLabel pNationality = new JLabel("Nationality    ");
        pNationality.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(pNationality, gc);
        
        JLabel nationality = new JLabel(person.getNationality().getCode());
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
        
        JLabel dob = new JLabel(new SimpleDateFormat("dd-MM-YYYY").format(person.getDateOfBirth()));
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

        JLabel marStatus = new JLabel(person.getMaritalStatus().getCode());
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

        JLabel religion = new JLabel(person.getReligion().getCode());
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

        JLabel sex = new JLabel(person.getSexuality().getCode());
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
                if (text.equals("Create")) {
//                    AppSearch appSearch = new AppSearch();
//                    appSearch.setClient(client);
//                    setVisible(false);
//                    appSearch.setVisible(true);
                } else if (text.equals("Update")) {
//                    PropSearch propSearch = new PropSearch();
//                    propSearch.setClient(client);
//                    setVisible(false);
//                    propSearch.setVisible(true);
                } else if (text.equals("Delete")) {
//                    TenSearch tenSearch = new TenSearch();
//                    tenSearch.setClient(client);
//                    setVisible(false);
//                    tenSearch.setVisible(true);
                } else if (text.equals("View Details")) {
//                    LeaseSearch leaseSearch = new LeaseSearch();
//                    leaseSearch.setClient(client);
//                    setVisible(false);
//                    leaseSearch.setVisible(true);
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
            public void intOmitted(int invPartyRef) {
                if(invPartyRef > 0) {
                    try {
                        InvolvedPartyInterface invParty = client.getInvolvedParty(invPartyRef);
                        if(invParty != null) {
                            System.out.println(invParty.getPerson().getName());
                        }
                        System.out.println("TEST1-Contacts");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
                    } catch (RemoteException ex) {
                        Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        addressPanel = new AddressPanel("Addresses");
        
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
                        }
                        System.out.println("TEST1-Address Usage");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                        }
                        System.out.println("TEST1-Note");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                        }
                        System.out.println("TEST1-Document");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
