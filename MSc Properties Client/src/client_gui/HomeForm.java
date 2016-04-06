/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import client_application.ClientImpl;
import interfaces.AccountInterface;
import interfaces.AgreementInterface;
import interfaces.LeaseInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Dwayne
 */
public class HomeForm extends JFrame implements Observer {

    private JLabel title;
    private ButtonPanel buttonPanel;
    private JPanel tablesPanel;
    private AgreementPanel tenanciesPanel;
    private AgreementPanel leasesPanel;
    private AccountPanel rentAccPanel;
    private ClientImpl client;

    public HomeForm(ClientImpl c) {
        super("MSc Properties");
        setClient(c);

        layoutComponents();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            if (arg instanceof List<?>) {
                if (!((List<?>) arg).isEmpty() && ((List<?>) arg).get(0) instanceof AgreementInterface) {
                    List<AgreementInterface> agreements = (List<AgreementInterface>) arg;
                    if (((List<?>) arg).get(0) instanceof TenancyInterface) {
                        this.updateTenanciesList(agreements);
                    } else if (((List<?>) arg).get(0) instanceof LeaseInterface) {
                        this.updateLeasesList(agreements);
                    }
                } else if (!((List<?>) arg).isEmpty() && ((List<?>) arg).get(0) instanceof RentAccountInterface) {
                    List<AccountInterface> accounts = (List<AccountInterface>) arg;
                    this.updateRentAccList(accounts);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTenanciesList(List<AgreementInterface> agreements) {
        System.out.println("Tenancies Updated");
        tenanciesPanel.setData(agreements);
        tenanciesPanel.refresh();
    }

    private void updateLeasesList(List<AgreementInterface> agreements) {
        System.out.println("Leases Updated");
        leasesPanel.setData(agreements);
        leasesPanel.refresh();
    }

    private void updateRentAccList(List<AccountInterface> accounts) {
        System.out.println("Rent Accounts Updated");
        rentAccPanel.setData(accounts);
        rentAccPanel.refresh();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
            this.client.addObserver(this);
        }
    }

    private void layoutComponents() {
        title = new JLabel("MSc Properties");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        setLayout(new BorderLayout());
        buttonPanel = new ButtonPanel();
        tablesPanel = new JPanel();
        tenanciesPanel = new AgreementPanel();
        leasesPanel = new AgreementPanel();
        rentAccPanel = new AccountPanel();

        buttonPanel.setButtonListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                System.out.println(text);
                System.out.println(text.equals("Applications"));
                if (text.equals("Applications")) {
                    AppSearch appSearch = new AppSearch();
                    appSearch.setClient(client);
                    setVisible(false);
                    appSearch.setVisible(true);
                } else if (text.equals("Properties")) {
                    PropSearch propSearch = new PropSearch();
                    propSearch.setClient(client);
                    setVisible(false);
                    propSearch.setVisible(true);
                } else if (text.equals("Tenancies")) {
                    TenSearch tenSearch = new TenSearch();
                    tenSearch.setClient(client);
                    setVisible(false);
                    tenSearch.setVisible(true);
                } else if (text.equals("Leases")) {
                    LeaseSearch leaseSearch = new LeaseSearch();
                    leaseSearch.setClient(client);
                    setVisible(false);
                    leaseSearch.setVisible(true);
                } else if (text.equals("Contracts") && client != null) {// && client.viewEmp()) {
                    ContractSearch contractSearch = new ContractSearch();
                    contractSearch.setClient(client);
                    setVisible(false);
                    contractSearch.setVisible(true);
                } else if (text.equals("Rent Accounts")) {
                    RentAccSearch rentAccSearch = new RentAccSearch();
                    rentAccSearch.setClient(client);
                    setVisible(false);
                    rentAccSearch.setVisible(true);
                } else if (text.equals("Lease Accounts")) {
                    LeaseAccSearch leaseAccSearch = new LeaseAccSearch();
                    leaseAccSearch.setClient(client);
                    setVisible(false);
                    leaseAccSearch.setVisible(true);
                } else if (text.equals("Employee Accounts") && client != null) {// && client.viewEmp()) {
                    EmpAccSearch empAccSearch = new EmpAccSearch();
                    empAccSearch.setClient(client);
                    setVisible(false);
                    empAccSearch.setVisible(true);
                } else if (text.equals("Reporting") && client != null) {// && client.canReport()) { // add another boolean field to User to determine if user can report
                    ReportingFrame reporting = new ReportingFrame();
                    reporting.setClient(client);
                    setVisible(false);
                    reporting.setVisible(true);
                } else if (text.equals("System Configuration")) {
                    SysConfigFrame config = new SysConfigFrame();
                    config.setClient(client);
                    setVisible(false);
                    config.setVisible(true);
                }
            }
        });

        try {
            this.updateTenanciesList(client.getUserTenancies());
            this.updateLeasesList(client.getUserLeases());
            this.updateRentAccList(client.getUserRentAccounts());
        } catch (RemoteException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        tenanciesPanel.setTableListener(new TableListener() {
            @Override
            public void rowSelected(Object agreement) {
                System.out.println(agreement instanceof TenancyInterface);
                if (agreement instanceof TenancyInterface) {
                    TenancyInterface tenancy = (TenancyInterface) agreement;
                    System.out.println("TEST1-Tenancy");
//                    TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                    tenancyForm.setClient(client);
//                    tenancyForm.setTenancy(tenancy);
                }
            }
        });

        leasesPanel.setTableListener(new TableListener() {
            @Override
            public void rowSelected(Object agreement) {
                if (agreement instanceof LeaseInterface) {
                    LeaseInterface lease = (LeaseInterface) agreement;
                    System.out.println("TEST1-Lease");
//                    LeaseDetailsForm leaseForm = new LeaseDetailsForm();
//                    leaseForm.setClient(client);
//                    leaseForm.setLease(lease);
                }
            }
        });

        rentAccPanel.setTableListener(new TableListener() {
            @Override
            public void rowSelected(Object account) {
                if (account instanceof RentAccountInterface) {
                    RentAccountInterface rentAcc = (RentAccountInterface) account;
                    System.out.println("TEST1-RentAcc");
//                    RentAccDetailsForm rentAccForm = new RentAccDetailsForm();
//                    rentAccForm.setClient(client);
//                    rentAccForm.setRentAcc(RentAcc);
                }
            }
        });

        setJMenuBar(createMenuBar());
        
        tablesPanel.setSize(700, 500);

        tablesPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.ipady = 700;
        gc.ipadx = 180;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        tablesPanel.add(tenanciesPanel, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.ipady = 700;
        gc.ipadx = 180;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        tablesPanel.add(leasesPanel, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.ipady = 700;
        gc.ipadx = 180;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        tablesPanel.add(rentAccPanel, gc);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(tablesPanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 700));
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
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

                int action = JOptionPane.showConfirmDialog(HomeForm.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }

//    public static void main(String[] args) {
//        HomeForm test = new HomeForm();
//        test.setVisible(true);
//    }
}