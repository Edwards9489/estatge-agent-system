/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.home_screen;

import client_application.ClientImpl;
import client_gui.application.AppSearch;
import client_gui.contract.ContractSearch;
import client_gui.empAccount.EmpAccSearch;
import client_gui.leaseAcc.LeaseAccSearch;
import client_gui.lease.LeaseSearch;
import client_gui.property.PropSearch;
import client_gui.rentAcc.RentAccSearch;
import client_gui.reporting.ReportingFrame;
import client_gui.StringListener;
import client_gui.systemConfig.SysConfigFrame;
import client_gui.IntegerListener;
import client_gui.lease.LeaseDetails;
import client_gui.rentAcc.RentAccDetails;
import client_gui.tenancy.TenSearch;
import client_gui.tenancy.TenancyDetails;
import interfaces.AccountInterface;
import interfaces.AgreementInterface;
import interfaces.LeaseInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
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
                    if (agreements.get(0) instanceof TenancyInterface) {
                        this.updateTenanciesList(agreements);
                    } else if (agreements.get(0) instanceof LeaseInterface) {
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
        tenanciesPanel.setData(agreements);
        tenanciesPanel.refresh();
    }

    private void updateLeasesList(List<AgreementInterface> agreements) {
        leasesPanel.setData(agreements);
        leasesPanel.refresh();
    }

    private void updateRentAccList(List<AccountInterface> accounts) {
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
        setJMenuBar(createMenuBar());
        title = new JLabel("MSc Properties");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        setLayout(new BorderLayout());
        buttonPanel = new ButtonPanel();
        tablesPanel = new JPanel();
        
        tenanciesPanel = new AgreementPanel("Tenancies");
        leasesPanel = new AgreementPanel("Leases");
        rentAccPanel = new AccountPanel("Rent Accounts");

        buttonPanel.setButtonListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                System.out.println("Button Text: " + text);
                switch (text) {
                    case "Applications" :
                        AppSearch appSearch = new AppSearch(client);
                        appSearch.setVisible(true);
                        break;
                        
                    case "Properties" :
                        PropSearch propSearch = new PropSearch(client);
                        propSearch.setVisible(true);
                        break;
                        
                    case "Tenancies" :
                        TenSearch tenSearch = new TenSearch(client);
                        tenSearch.setVisible(true);
                        break;
                        
                    case "Leases" :
                        LeaseSearch leaseSearch = new LeaseSearch(client);
                        leaseSearch.setVisible(true);
                        break;
                        
                    case "Contracts" :
                        ContractSearch contractSearch = new ContractSearch(client);
                        contractSearch.setVisible(true);
                        break;
                        
                    case "Rent Accounts" :
                        RentAccSearch rentAccSearch = new RentAccSearch(client);
                        rentAccSearch.setVisible(true);
                        break;
                        
                    case "Lease Accounts" :
                        LeaseAccSearch leaseAccSearch = new LeaseAccSearch(client);
                        leaseAccSearch.setVisible(true);
                        break;
                        
                    case "Employee Accounts" :
                        EmpAccSearch empAccSearch = new EmpAccSearch(client);
                        empAccSearch.setVisible(true);
                        break;
                        
                    case "Reporting" :
                        ReportingFrame reporting = new ReportingFrame(client);
                        reporting.setVisible(true);
                        break;
                        
                    case "System Config" :
                        SysConfigFrame config = new SysConfigFrame(client);
                        config.setVisible(true);
                        System.out.println("Sys Config Parameter: " + text);
                        break;
                        
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

        tenanciesPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int tenancyRef) {
                if(tenancyRef > 0) {
                    try {
                        TenancyInterface tenancy = client.getTenancy(tenancyRef);
                        if(tenancy != null) {
                            TenancyDetails tenancyGUI = new TenancyDetails(client, tenancy);
                            tenancyGUI.setVisible(true);
                            System.out.println(tenancy.getAgreementName());
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        leasesPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int leaseRef) {
                if(leaseRef > 0) {
                    try {
                        LeaseInterface lease = client.getLease(leaseRef);
                        if(lease != null) {
                            LeaseDetails leaseGUI = new LeaseDetails(client, lease);
                            leaseGUI.setVisible(true);
                            System.out.println(lease.getAgreementName());
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        rentAccPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int rentAccRef) {
                if(rentAccRef > 0) {
                    try {
                        RentAccountInterface rentAcc = client.getRentAccount(rentAccRef);
                        if(rentAcc != null) {
                            RentAccDetails rentAccGUI = new RentAccDetails(client, rentAcc);
                            rentAccGUI.setVisible(true);
                            System.out.println(rentAcc.getAccName());
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
        tablesPanel.setSize(700, 500);
        
        tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.PAGE_AXIS));
        tablesPanel.add(tenanciesPanel);
        tablesPanel.add(leasesPanel);
        tablesPanel.add(rentAccPanel);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(tablesPanel, BorderLayout.CENTER);

        this.setMinimumSize(new Dimension(1200, 700));
        this.setSize(1200, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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
            @Override
            public void actionPerformed(ActionEvent ev) {

            }
        });

        userAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
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
}