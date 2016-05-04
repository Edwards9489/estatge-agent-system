/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.office;

import client_gui.tenancy.TenancyPanel;
import client_gui.rentAcc.RentAccPanel;
import client_gui.leaseAcc.LeaseAccPanel;
import client_gui.empAccount.EmpAccPanel;
import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.EndObject;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.PDFFileFilter;
import client_gui.contact.ContactDetails;
import client_gui.document.DocumentPanel;
import client_gui.modifications.ModPanel;
import client_gui.contract.ContractPanel;
import client_gui.lease.LeasePanel;
import client_gui.note.NotePanel;
import client_gui.contact.ContactPanel;
import client_gui.contact.CreateContact;
import client_gui.contact.UpdateContact;
import client_gui.contract.ContractDetails;
import client_gui.contract.CreateContract;
import client_gui.contract.UpdateContract;
import client_gui.document.CreateDocument;
import client_gui.empAccount.EmpAccDetails;
import client_gui.lease.CreateLease;
import client_gui.lease.LeaseDetails;
import client_gui.lease.UpdateLease;
import client_gui.leaseAcc.LeaseAccDetails;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import client_gui.rentAcc.RentAccDetails;
import client_gui.tenancy.CreateTenancy;
import client_gui.tenancy.TenancyDetails;
import client_gui.tenancy.UpdateTenancy;
import interfaces.ContactInterface;
import interfaces.ContractInterface;
import interfaces.OfficeInterface;
import interfaces.Document;
import interfaces.EmployeeAccountInterface;
import interfaces.LeaseAccountInterface;
import interfaces.LeaseInterface;
import interfaces.Note;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
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
public class OfficeDetails extends JFrame {

    private ClientImpl client = null;
    private OfficeInterface office = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;
    private TenancyPanel tenancyPanel;
    private LeasePanel leasePanel;
    private ContractPanel contractPanel;
    private RentAccPanel rentAccPanel;
    private LeaseAccPanel leaseAccPanel;
    private EmpAccPanel empAccPanel;
    private NotePanel notePanel;
    private ContactPanel contactPanel;
    private DocumentPanel documentPanel;
    private ModPanel modPanel;
    private JFileChooser fileChooser;
    private JLabel length;
    private JLabel expenditure;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public OfficeDetails(ClientImpl client, OfficeInterface app) {
        super("MSc Properties");
        setClient(client);
        setOffice(app);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Application is initiated
    private void setOffice(OfficeInterface app) {
        if (office == null) {
            this.office = app;
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
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

            setupDetails();

            setUpMainPanel();

            this.add(detailsPanel, BorderLayout.NORTH);

            this.add(mainPanel, BorderLayout.CENTER);
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Office Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 20;

        JLabel officeCode = new JLabel("Office Code    ");
        Font font = officeCode.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        officeCode.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(officeCode, gc);

        JLabel ref = new JLabel(office.getOfficeCode());
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel leaseLength = new JLabel("Start Date    ");
        leaseLength.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseLength, gc);

        length = new JLabel(formatter.format(office.getStartDate()));
        length.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(length, gc);

        JLabel lExpenditure = new JLabel("End Date    ");
        lExpenditure.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lExpenditure, gc);

        if (office.getEndDate() != null) {
            expenditure = new JLabel(formatter.format(office.getEndDate()));
        } else {
            expenditure = new JLabel("");
        }
        expenditure.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(expenditure, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel leaseName = new JLabel("Address    ");
        leaseName.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseName, gc);

        JLabel start = new JLabel(office.getAddress().printAddress());
        start.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 5;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(start, gc);

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

        JLabel empty3 = new JLabel("");
        empty3.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(empty3, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        JLabel empty4 = new JLabel("");
        empty4.setFont(boldFont);
        detailsPanel.add(empty4, gc);
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
                String title = tabbedPane.getTitleAt(pane);
                if (title != null) {
                    System.out.println(text);
                    switch (text) {
                        case "Create":
                            System.out.println("TEST - Create Button");

                        switch (title) {
                            case "Tenancies":
                                //Tenancies
                                createTenancy();
                                System.out.println("Create Tenancy");
                                break;
                            case "Leases":
                                //Leases
                                createLease();
                                System.out.println("Create Lease");
                                break;
                            case "Contacts":
                                //Documents
                                createContact();
                                System.out.println("TEST - Create Contact");
                                break;
                            case "Notes":
                                //Documents
                                createNote();
                                System.out.println("TEST - Create Note");
                                break;
                            case "Documents":
                                //Documents
                                createDocument();
                                System.out.println("TEST - Create Document");
                                break;
                            case "Contracts":
                                //Documents
                                createContract();
                                System.out.println("TEST - Create Contract");
                                break;
                        }
                            break;

                        case "Update":
                            System.out.println("TEST - Update Button");

                        switch (title) {
                            case "Tenancies":
                                //Tenancies
                                updateTenancy();
                                System.out.println("TEST - Update Tenancy");
                                break;
                            case "Leases":
                                //Lease
                                updateLease();
                                System.out.println("TEST - Update Lease");
                                break;
                            case "Contacts":
                                //Notes
                                updateContact();
                                System.out.println("TEST - Update Contact");
                                break;
                            case "Notes":
                                //Notes
                                updateNote();
                                System.out.println("TEST - Update Note");
                                break;
                            case "Documents":
                                //Document
                                updateDocument();
                                System.out.println("TEST - Update Document");
                                break;
                            case "Contracts":
                                //Document
                                updateContract();
                                System.out.println("TEST - Update Contract");
                                break;
                        }
                            break;

                        case "End":
                            System.out.println("TEST - End Button");

                        switch (title) {
                            case "Tenancies":
                                //Tenancy
                                endTenancy();
                                System.out.println("TEST - End Tenancy");
                                break;
                            case "Leases":
                                //Lease
                                endLease();
                                System.out.println("TEST - End Lease");
                                break;
                            case "Contacts":
                                //Contact
                                endContact();
                                System.out.println("TEST - End Contact");
                                break;
                            case "Contracts":
                                //Lease
                                endContract();
                                System.out.println("TEST - End Contract");
                                break;
                        }
                            break;

                        case "Delete":
                            System.out.println("TEST - Delete Button");
                        switch (title) {
                            case "Tenancies":
                                //Tenancy
                                deleteTenancy();
                                System.out.println("TEST - Delete Tenancy");
                                break;
                            case "Leases":
                                //Lease
                                deleteLease();
                                System.out.println("TEST - Delete Lease");
                                break;
                            case "Contacts":
                                //Notes
                                deleteContact();
                                System.out.println("TEST - Delete Contact");
                                break;
                            case "Notes":
                                //Notes
                                deleteNote();
                                System.out.println("TEST - Delete Note");
                                break;
                            case "Documents":
                                //Document
                                deleteDocument();
                                System.out.println("TEST - Delete Document");
                                break;
                            case "Contracts":
                                //Document
                                deleteContract();
                                System.out.println("TEST - Delete Contract");
                                break;
                        }
                            break;

                        case "View Details":
                            System.out.println("TEST - View Details Button");
                        switch (title) {
                            case "Tenancies":
                                //Tenancy
                                viewTenancy();
                                System.out.println("TEST - View Tenancy");
                                break;
                            case "Leases":
                                //Lease
                                viewLease();
                                System.out.println("TEST - View Lease");
                                break;
                            case "Rent Accounts":
                                //Rent Account
                                viewRentAcc();
                                System.out.println("TEST - View Rent Account");
                                break;
                            case "Lease Accounts":
                                //Lease Account
                                viewLeaseAcc();
                                System.out.println("TEST - View Lease Account");
                                break;
                            case "Contacts":
                                //Notes
                                viewContact();
                                System.out.println("TEST - View Contact");
                                break;
                            case "Notes":
                                //Notes
                                viewNote();
                                System.out.println("TEST - View Note");
                                break;
                            case "Documents":
                                //Document
                                viewDocument();
                                System.out.println("TEST - View Document");
                                break;
                            case "Contracts":
                                //Contract
                                viewContract();
                                System.out.println("TEST - View Contract");
                                break;
                            case "Employee Accounts":
                                //Employee Account
                                viewEmployeeAcc();
                                System.out.println("TEST - View Employee Account");
                                break;
                        }
                            break;

                        case "Refresh":
                            refresh();
                            break;
                    }
                }
            }
        });

        tabbedPane = new JTabbedPane();

        tenancyPanel = new TenancyPanel("Tenancies");

        try {
            tenancyPanel.setData(office.getTenancies());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        tenancyPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int tenancyRef) {
                if (tenancyRef > 0) {
                    try {
                        TenancyInterface invParty = client.getTenancy(tenancyRef);
                        if (invParty != null) {
                            System.out.println(invParty.getAgreementName());
                            System.out.println("TEST1-Tenancy");
                            TenancyDetails tenancyGUI = new TenancyDetails(client, invParty);
                            tenancyGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        leasePanel = new LeasePanel("Leases");

        try {
            leasePanel.setData(office.getLeases());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        leasePanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int leaseRef) {
                if (leaseRef > 0) {
                    try {
                        LeaseInterface lease = client.getLease(leaseRef);
                        if (lease != null) {
                            System.out.println(lease.getAgreementName());
                            System.out.println("TEST1-Lease");
                            LeaseDetails leaseGUI = new LeaseDetails(client, lease);
                            leaseGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        contractPanel = new ContractPanel("Contracts");

        try {
            contractPanel.setData(office.getContracts());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        contractPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int contractRef) {
                if (contractRef > 0) {
                    try {
                        ContractInterface contract = client.getContract(contractRef);
                        if (contract != null) {
                            System.out.println(contract.getAgreementName());
                            System.out.println("TEST1-Contract");
                            ContractDetails contractGUI = new ContractDetails(client, contract);
                            contractGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        rentAccPanel = new RentAccPanel("Rent Account");

        try {
            rentAccPanel.setData(office.getRentAccounts());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        rentAccPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int rentAccRef) {
                if (rentAccRef > 0) {
                    try {
                        RentAccountInterface rentAcc = client.getRentAccount(rentAccRef);
                        if (rentAcc != null) {
                            System.out.println(rentAcc.getAccName());
                            System.out.println("TEST1-Rent Account");
                            RentAccDetails rentAccGUI = new RentAccDetails(client, rentAcc);
                            rentAccGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        leaseAccPanel = new LeaseAccPanel("Lease Accounts");

        try {
            leaseAccPanel.setData(office.getLeaseAccounts());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        leaseAccPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int leaseAccRef) {
                if (leaseAccRef > 0) {
                    try {
                        LeaseAccountInterface leaseAcc = client.getLeaseAccount(leaseAccRef);
                        if (leaseAcc != null) {
                            System.out.println(leaseAcc.getAccName());
                            System.out.println("TEST1-Lease Account");
                            LeaseAccDetails rentAccGUI = new LeaseAccDetails(client, leaseAcc);
                            rentAccGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        empAccPanel = new EmpAccPanel("Employee Accounts");

        try {
            empAccPanel.setData(office.getEmployeeAccounts());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        empAccPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int empAccRef) {
                if (empAccRef > 0) {
                    try {
                        EmployeeAccountInterface empAcc = client.getEmployeeAccount(empAccRef);
                        if (empAcc != null) {
                            System.out.println(empAcc.getAccName());
                            System.out.println("TEST1-Lease Account");
                            EmpAccDetails empAccGUI = new EmpAccDetails(client, empAcc);
                            empAccGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        notePanel = new NotePanel("Notes");

        try {
            notePanel.setData(office.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        notePanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int noteRef) {
                if (noteRef > 0) {
                    try {
                        Note note = office.getNote(noteRef);
                        if (note != null) {
                            System.out.println(note.getReference());
                            System.out.println("TEST1-Note");
                            NoteDetails noteGUI = new NoteDetails(client, note);
                            noteGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        contactPanel = new ContactPanel("Contacts");

        try {
            contactPanel.setData(office.getContacts());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        contactPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int contactRef) {
                if (contactRef > 0) {
                    try {
                        ContactInterface contact = office.getContact(contactRef);
                        if (contact != null) {
                            System.out.println(contact.getContactRef());
                            System.out.println("TEST1-Contact");
                            ContactDetails contactGUI = new ContactDetails(client, contact);
                            contactGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        documentPanel = new DocumentPanel("Documents");

        try {
            documentPanel.setData(office.getDocuments());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        documentPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int documentRef) {
                if (documentRef > 0) {
                    try {
                        Document document = office.getDocument(documentRef);
                        if (document != null) {
                            System.out.println(document.getCurrentDocumentName());
                            System.out.println("TEST1-Document");
                            client.downloadOfficeDocument(office.getOfficeCode(), document.getDocumentRef(), document.getCurrentVersion());
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        modPanel = new ModPanel("Modifications");

        try {
            modPanel.setData(office.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        tabbedPane.setSize(800, 175);

        try {

            tabbedPane.add(tenancyPanel, "Tenancies");
            tabbedPane.add(leasePanel, "Leases");
            if (client.getUser().getEmployeeRead()) {
                tabbedPane.add(contractPanel, "Contracts");
            }
            tabbedPane.add(rentAccPanel, "Rent Accounts");
            tabbedPane.add(leaseAccPanel, "Lease Accounts");
            if (client.getUser().getEmployeeRead()) {
                tabbedPane.add(empAccPanel, "Employee Accounts");
            }
            tabbedPane.add(contactPanel, "Contacts");
            tabbedPane.add(notePanel, "Notes");
            tabbedPane.add(documentPanel, "Documents");
            tabbedPane.add(modPanel, "Modifications");

            centrePanel.add(tabbedPane);

            centrePanel.add(new DetailsPanel(office.getCreatedBy(), office.getCreatedDate(), office.getLastModifiedBy(), office.getLastModifiedDate()));

        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }

    private void createTenancy() {
        try {
            CreateTenancy createTenancy = new CreateTenancy(client);
            createTenancy.setOfficeField(office.getOfficeCode());
            createTenancy.setVisible(true);
            System.out.println("TEST - Create Tenancy");
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTenancy() {
        Integer selection = tenancyPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                TenancyInterface tenancy = client.getTenancy(selection);
                if (tenancy != null) {
                    UpdateTenancy updateTenancy = new UpdateTenancy(client, tenancy);
                    updateTenancy.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void endTenancy() {
        Integer selection = tenancyPanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Tenancy Ref: " + selection);
            EndObject endTenancy = new EndObject(client, "Tenancy", selection);
            endTenancy.setVisible(true);
        }
    }

    private void deleteTenancy() {
        Integer selection = tenancyPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE tenancy " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Tenancy Delete - Yes button clicked");
                    int result = client.deleteTenancy(selection);
                    if (result > 0) {
                        String message = "Tenancy " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    } else {
                        String message = "Tenancy " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewTenancy() {
        if (tenancyPanel.getSelectedObjectRef() != null) {
            TenancyInterface tenancy;
            try {
                tenancy = client.getTenancy(tenancyPanel.getSelectedObjectRef());
                if (tenancy != null) {
                    TenancyDetails tenancyDetails = new TenancyDetails(client, tenancy);
                    tenancyDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewRentAcc() {
        if (rentAccPanel.getSelectedObjectRef() != null) {
            RentAccountInterface rentAcc;
            try {
                rentAcc = client.getRentAccount(rentAccPanel.getSelectedObjectRef());
                if (rentAcc != null) {
                    RentAccDetails rentAccDetails = new RentAccDetails(client, rentAcc);
                    rentAccDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createLease() {
        try {
            CreateLease createLease = new CreateLease(client);
            createLease.setOfficeField(office.getOfficeCode());
            createLease.setVisible(true);
            System.out.println("TEST - Create Lease");
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                LeaseInterface lease = client.getLease(selection);
                if (lease != null) {
                    UpdateLease updateLease = new UpdateLease(client, lease);
                    updateLease.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void endLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Lease Ref: " + selection);
            EndObject endLease = new EndObject(client, "Lease", selection);
            endLease.setVisible(true);
        }
    }

    private void deleteLease() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE lease " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Lease Delete - Yes button clicked");
                    int result = client.deleteLease(selection);
                    if (result > 0) {
                        String message = "Lease " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    } else {
                        String message = "Lease " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewLease() {
        if (leasePanel.getSelectedObjectRef() != null) {
            LeaseInterface lease;
            try {
                lease = client.getLease(leasePanel.getSelectedObjectRef());
                if (lease != null) {
                    LeaseDetails leaseDetails = new LeaseDetails(client, lease);
                    leaseDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewLeaseAcc() {
        if (leaseAccPanel.getSelectedObjectRef() != null) {
            LeaseAccountInterface leaseAcc;
            try {
                leaseAcc = client.getLeaseAccount(leaseAccPanel.getSelectedObjectRef());
                if (leaseAcc != null) {
                    LeaseAccDetails leaseDetails = new LeaseAccDetails(client, leaseAcc);
                    leaseDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createContract() {
        try {
            CreateContract createContract = new CreateContract(client);
            createContract.setOfficeField(office.getOfficeCode());
            createContract.setVisible(true);
            System.out.println("TEST - Create Lease");
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateContract() {
        Integer selection = contactPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                ContractInterface contract = client.getContract(selection);
                if (contract != null) {
                    UpdateContract updateContract = new UpdateContract(client, contract);
                    updateContract.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void endContract() {
        Integer selection = contractPanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Contract Ref: " + selection);
            EndObject endContract = new EndObject(client, "Contract", selection);
            endContract.setVisible(true);
        }
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
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    } else {
                        String message = "Contract " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewContract() {
        if (contractPanel.getSelectedObjectRef() != null) {
            ContractInterface contract;
            try {
                contract = client.getContract(contractPanel.getSelectedObjectRef());
                if (contract != null) {
                    ContractDetails contractDetails = new ContractDetails(client, contract);
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewEmployeeAcc() {
        if (empAccPanel.getSelectedObjectRef() != null) {
            EmployeeAccountInterface empAcc;
            try {
                empAcc = client.getEmployeeAccount(empAccPanel.getSelectedObjectRef());
                if (empAcc != null) {
                    EmpAccDetails empAccDetails = new EmpAccDetails(client, empAcc);
                    empAccDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Office", office.getOfficeCode());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = office.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Office", office.getOfficeCode());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteOfficeNote(office.getOfficeCode(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = office.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails noteDetails = new NoteDetails(client, note);
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createContact() {
        try {
            CreateContact createContact = new CreateContact(client, "Office", office.getOfficeCode());
            createContact.setVisible(true);
            System.out.println("TEST - Create Contact");
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateContact() {
        Integer selection = contactPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                ContactInterface contact = office.getContact(selection);
                if (contact != null) {
                    UpdateContact updateContact = new UpdateContact(client, contact, "Office", office.getOfficeCode());
                    updateContact.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void endContact() {
        Integer selection = contactPanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Contact Ref: " + selection);
            EndObject endContact = new EndObject(client, "Contact", selection);
            endContact.setVisible(true);
        }
    }

    private void deleteContact() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE contact " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Contact Delete - Yes button clicked");
                    int result = client.deleteOfficeContact(office.getOfficeCode(), selection);
                    if (result > 0) {
                        String message = "Contact " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    } else {
                        String message = "Contact " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewContact() {
        if (contactPanel.getSelectedObjectRef() != null) {
            ContactInterface contact;
            try {
                contact = office.getContact(contactPanel.getSelectedObjectRef());
                if (contact != null) {
                    ContactDetails contactDetails = new ContactDetails(client, contact);
                    contactDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createDocument() {
        try {
            CreateDocument createDoc = new CreateDocument(client, "Office", office.getOfficeCode());
            createDoc.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDocument() {
        Integer selection = documentPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Document document = office.getDocument(selection);
                if (document != null) {
                    if (fileChooser.showOpenDialog(OfficeDetails.this) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        int result = client.updateOfficeDocument(office.getOfficeCode(), document.getDocumentRef(), file.getPath());
                        if (result > 0) {
                            String message = "Document " + selection + " has been successfully updated";
                            String title = "Information";
                            OKDialog.okDialog(OfficeDetails.this, message, title);
                        } else {
                            String message = "There is some errors with the information supplied to UPDATE Document " + document.getDocumentRef() + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(OfficeDetails.this, message, title);
                        }
                        System.out.println(fileChooser.getSelectedFile());
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                    int result = client.deleteOfficeDocument(office.getOfficeCode(), selection);
                    if (result > 0) {
                        String message = "Document " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    } else {
                        String message = "Document " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(OfficeDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewDocument() {
        if (documentPanel.getSelectedObjectRef() != null) {
            Document document;
            try {
                document = office.getDocument(documentPanel.getSelectedObjectRef());
                client.downloadOfficeDocument(office.getOfficeCode(), document.getDocumentRef(), document.getCurrentVersion());
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refresh() {
        try {
            length.setText(formatter.format(office.getStartDate()));
            if (office.getEndDate() != null) {
                expenditure.setText(formatter.format(office.getEndDate()));
            } else {
                expenditure.setText("");
            }
            notePanel.setData(office.getNotes());
            documentPanel.setData(office.getDocuments());
            modPanel.setData(office.getModifiedBy());
            notePanel.refresh();
            documentPanel.refresh();
            modPanel.refresh();
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
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

                int action = JOptionPane.showConfirmDialog(OfficeDetails.this,
                        "Do you really want to exit the office?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
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
//                new OfficeDetails().setVisible(true);
//            }
//        });
//    }
}
