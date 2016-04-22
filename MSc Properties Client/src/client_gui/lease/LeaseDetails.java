/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.application.DocumentPanel;
import client_gui.application.ModPanel;
import interfaces.AddressUsageInterface;
import interfaces.LeaseInterface;
import interfaces.Document;
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
import javax.swing.JCheckBox;
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

    public LeaseDetails(ClientImpl client, LeaseInterface app) {
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
    private void setApplication(LeaseInterface app) {
        if (lease == null) {
            this.lease = app;
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

        JLabel length = new JLabel(String.valueOf(lease.getLength()));
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

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        JLabel expenditure = new JLabel(String.valueOf(lease.getExpenditure()));
        expenditure.setFont(boldFont);
        detailsPanel.add(expenditure, gc);

        JLabel lStartDate = new JLabel("Start Date    ");
        lStartDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lStartDate, gc);

        JLabel startDate = new JLabel(new SimpleDateFormat("dd-MM-YYYY").format(lease.getStartDate()));
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

        JLabel start = new JLabel(lease.getAgreementName());
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

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        JLabel endDate = new JLabel(new SimpleDateFormat("dd-MM-YYYY").format(lease.getExpectedEndDate()));
        endDate.setFont(boldFont);
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

        JLabel acEndDate;
        if(lease.getActualEndDate() != null) {
            acEndDate = new JLabel(new SimpleDateFormat("dd-MM-YYYY").format(lease.getActualEndDate()));
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
                        }
                        System.out.println("TEST1-Note");
//                        TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                        AddressUsageInterface address = client.getAddressUsage(addressRef);
                        if(address != null) {
                            System.out.println(address.getAddress().printAddress());
                        }
                        System.out.println("TEST1-Landlords");
//                        LeaseDetailsForm tenancyForm = new LeaseDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
                        }
                        System.out.println("TEST1-Document");
//                        LeaseDetailsForm tenancyForm = new LeaseDetailsForm();
//                        tenancyForm.setClient(client);
//                        tenancyForm.setTenancy(tenancy);
//                        tenancyForm.setVisible(true);
//                        setVisible(false);
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
