/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.office;

import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.application.DocumentPanel;
import client_gui.application.ModPanel;
import client_gui.employee.ContractPanel;
import client_gui.landlord.LeasePanel;
import client_gui.lease.NotePanel;
import client_gui.person.ContactPanel;
import interfaces.ContactInterface;
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

        JLabel leaseRef = new JLabel("Office Code    ");
        Font font = leaseRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        leaseRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseRef, gc);

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

        JLabel length = new JLabel(new SimpleDateFormat("dd-MM-YYYY").format(office.getStartDate()));
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
        
        JLabel expenditure;
        if (office.getEndDate() != null) {
            expenditure = new JLabel(new SimpleDateFormat("dd-MM-YYYY").format(office.getEndDate()));
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
//                    OfficeSearch leaseSearch = new OfficeSearch();
//                    leaseSearch.setClient(client);
//                    setVisible(false);
//                    leaseSearch.setVisible(true);
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
                if(tenancyRef > 0) {
                    try {
                        TenancyInterface contract = (TenancyInterface) office.getAgreement(tenancyRef);
                        if(contract != null) {
                            System.out.println(contract.getAgreementName());
                        }
                        System.out.println("TEST1-Tenancy");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                if(leaseRef > 0) {
                    try {
                        LeaseInterface lease = (LeaseInterface) office.getAgreement(leaseRef);
                        if(lease != null) {
                            System.out.println(lease.getAgreementName());
                        }
                        System.out.println("TEST1-Lease");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
            public void intOmitted(int leaseRef) {
                if(leaseRef > 0) {
                    try {
                        LeaseInterface lease = (LeaseInterface) office.getAgreement(leaseRef);
                        if(lease != null) {
                            System.out.println(lease.getAgreementName());
                        }
                        System.out.println("TEST1-Contract");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                if(rentAccRef > 0) {
                    try {
                        RentAccountInterface rentAcc = (RentAccountInterface) office.getAccount(rentAccRef);
                        if(rentAcc != null) {
                            System.out.println(rentAcc.getAccName());
                        }
                        System.out.println("TEST1-Rent Account");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                if(leaseAccRef > 0) {
                    try {
                        LeaseAccountInterface contract = (LeaseAccountInterface) office.getAccount(leaseAccRef);
                        if(contract != null) {
                            System.out.println(contract.getAccName());
                        }
                        System.out.println("TEST1-Lease Account");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                if(empAccRef > 0) {
                    try {
                        EmployeeAccountInterface empAcc = (EmployeeAccountInterface) office.getAgreement(empAccRef);
                        if(empAcc != null) {
                            System.out.println(empAcc.getAccName());
                        }
                        System.out.println("TEST1-Employee Account");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                if(noteRef > 0) {
                    try {
                        Note note = office.getNote(noteRef);
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
            public void intOmitted(int addressRef) {
                if(addressRef > 0) {
                    try {
                        ContactInterface address = office.getContact(addressRef);
                        if(address != null) {
                            System.out.println(address.getContactType() + " : " + address.getContactValue());
                        }
                        System.out.println("TEST1-Contacts");
//                        OfficeDetailsForm tenancyForm = new OfficeDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                if(documentRef > 0) {
                    try {
                        Document document = office.getDocument(documentRef);
                        if(document != null) {
                            System.out.println(document.getCurrentDocumentName());
                        }
                        System.out.println("TEST1-Document");
//                        OfficeDetailsForm tenancyForm = new OfficeDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                        "Do you really want to exit the application?",
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
