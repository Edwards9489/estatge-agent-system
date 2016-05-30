/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.reporting;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.EndObject;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.StringArrayListener;
import client_gui.StringListener;
import client_gui.contract.ContractPanel;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.lease.LeaseDetails;
import client_gui.lease.UpdateLease;
import client_gui.lease.CreateLease;
import client_gui.lease.LeasePanel;
import client_gui.login.LoginForm;
import client_gui.contract.CreateContract;
import client_gui.contract.ContractDetails;
import client_gui.contract.UpdateContract;
import client_gui.tenancy.CreateTenancy;
import client_gui.tenancy.TenancyDetails;
import client_gui.tenancy.TenancyPanel;
import client_gui.tenancy.UpdateTenancy;
import interfaces.LeaseInterface;
import interfaces.ContractInterface;
import interfaces.TenancyInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author Dwayne
 */
public class ReportingFrame extends JFrame {
    
    private JLabel frameTitle;
    private ClientImpl client = null;
    private TenancyPanel tenancyPanel;
    private LeasePanel leasePanel;
    private ContractPanel contractPanel;
    private JPanel panel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private ReportingHome reportingTree;
    private JTextArea textArea;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    
    public ReportingFrame(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        layoutComponents();
        setJMenuBar(createMenuBar());
        createMenuBar();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    private void layoutComponents() {
        frameTitle = new JLabel("MSc Properties");
        Font font = frameTitle.getFont();
        frameTitle.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        textArea = new JTextArea(800, 400);
        textArea.setEnabled(false);
        textArea.setLineWrap(true);
        textArea.setDisabledTextColor(Color.BLACK);
        this.setLayout(new BorderLayout());
        
        tenancyPanel = new TenancyPanel("Tenancies");
        
        tenancyPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String action) {
                action = action.trim();
                switch (action) {
                    case "Create":
                        createTenancy();
                        break;
                    
                    case "End":
                        endTenancy();
                        break;
                    
                    case "View Details":
                        viewTenancy();
                        break;
                        
                    case "Update":
                        updateTenancy();
                        break;
                        
                    case "Delete":
                        deleteTenancy();
                        break;
                }
            }
        });
        
        contractPanel = new ContractPanel("Contracts");
        
        contractPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String action) {
                action = action.trim();
                switch (action) {
                    case "Create":
                        createContract();
                        break;
                    
                    case "View Details":
                        viewContract();
                        break;
                        
                    case "Update":
                        updateContract();
                        break;
                        
                    case "Delete":
                        deleteContract();
                        break;
                }
            }
        });
        
        leasePanel = new LeasePanel("Leases");
        
        leasePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String action) {
                action = action.trim();
                switch (action) {
                    case "Create":
                        createLease();
                        break;
                    
                    case "View Details":
                        viewLease();
                        break;
                        
                    case "Update":
                        updateLease();
                        break;
                        
                    case "Delete":
                        deleteLease();
                        break;
                }
            }
        });
        
        reportingTree = new ReportingHome();
        panel = new JPanel();
        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        
        this.setSize(1200, 750);
        this.setMinimumSize(new Dimension(1200, 750));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        reportingTree.setListener(new IntegerListener() {
            @Override
            public void intOmitted(int ref) {
                final int reportType = ref;
                ReportingPanel reportPanel = new ReportingPanel(client, reportType);
                reportPanel.setListener(new StringArrayListener() {
                    @Override
                    public void arrayOmitted(List<String> array) {
                        System.out.println("Report Type: " + reportType);
                        switch (reportType) {
                            case 1:
                                if (array != null && array.size() == 3) {
                                    try {
                                        Integer employeeRef = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            employeeRef = Integer.parseInt(array.get(0));
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        tenancyPanel.setData(client.getTenanciesByEmployee(employeeRef, startDate, endDate));
                                        removeCenterPanel();
                                        centerPanel.add(tenancyPanel);
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getTenanciesByEmployee");
                                break;
                                
                            case 2:
                                if (array != null && array.size() == 3) {
                                    try {
                                        Integer employeeRef = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            employeeRef = Integer.parseInt(array.get(0));
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        leasePanel.setData(client.getLeasesByEmployee(employeeRef, startDate, endDate));
                                        removeCenterPanel();
                                        centerPanel.add(leasePanel);
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getLeasesByEmployee");
                                break;
                                
                            case 3:
                                if (array != null && array.size() == 3) {
                                    try {
                                        Integer employeeRef = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            employeeRef = Integer.parseInt(array.get(0));
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        contractPanel.setData(client.getContractsByEmployee(employeeRef, startDate, endDate));
                                        removeCenterPanel();
                                        centerPanel.add(contractPanel);
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getContractsByEmployee");
                                break;
                                
                            case 4:
                                if (array != null && array.size() == 3) {
                                    try {
                                        String officeCode = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            officeCode = array.get(0);
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        tenancyPanel.setData(client.getTenanciesByOffice(officeCode, startDate, endDate));
                                        removeCenterPanel();
                                        centerPanel.add(tenancyPanel);
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getTenanciesByOffice");
                                break;
                                
                            case 5:
                                if (array != null && array.size() == 3) {
                                    try {
                                        String officeCode = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            officeCode = array.get(0);
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        leasePanel.setData(client.getLeasesByOffice(officeCode, startDate, endDate));
                                        removeCenterPanel();
                                        centerPanel.add(leasePanel);
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getLeasesByOffice");
                                break;
                                
                            case 6:
                                if (array != null && array.size() == 3) {
                                    try {
                                        String officeCode = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            officeCode = array.get(0);
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        contractPanel.setData(client.getContractsByOffice(officeCode, startDate, endDate));
                                        removeCenterPanel();
                                        centerPanel.add(contractPanel);
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getContractsByOffice");
                                break;
                                
                            case 7:
                                if (array != null && array.size() == 3) {
                                    try {
                                        String officeCode = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            officeCode = array.get(0);
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        double value = client.getExpenditureForOffice(officeCode, startDate, endDate);
                                        String report = "\n\n\nExpenditure Between " + dateFormatter.format(startDate) + " and " +
                                                dateFormatter.format(endDate) + " for Office " + officeCode + " = £" + value + "\n\n\n";
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getExpenditureForOffice");
                                break;
                                
                            case 8:
                                if (array != null && array.size() == 3) {
                                    try {
                                        String officeCode = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            officeCode = array.get(0);
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        double value = client.getRevenueForOffice(officeCode, startDate, endDate);
                                        String report = "\n\n\nRevenue Between " + dateFormatter.format(startDate) + " and " +
                                                dateFormatter.format(endDate) + " for Office " + officeCode + " = £" + value + "\n\n\n";
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getRevenueForOffice");
                                break;
                                
                            case 9:
                                if (array != null && array.size() == 3) {
                                    try {
                                        String officeCode = null;
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        if (array.get(0) != null) {
                                            officeCode = array.get(0);
                                        }
                                        try {
                                            if (array.get(1) != null) {
                                                startDate = dateFormatter.parse(array.get(1));
                                            }
                                            if (array.get(2) != null) {
                                                endDate = dateFormatter.parse(array.get(2));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        double value = client.getProfitForOffice(officeCode, startDate, endDate);
                                        String report = "\n\n\nProfit Between " + dateFormatter.format(startDate) + " and " +
                                                dateFormatter.format(endDate) + " for Office " + officeCode + "  =  £" + value + "\n\n\n";
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getProfitForOffice");
                                break;
                                
                            case 10:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        double value = client.getExpenditureOverall(startDate, endDate);
                                        String report = "\n\n\nOverall Expenditure Between " + dateFormatter.format(startDate) +
                                                " and " + dateFormatter.format(endDate) + " =  £" + value + "\n\n\n";
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getExpenditureOverall");
                                break;
                                
                            case 11:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        double value = client.getRevenueOverall(startDate, endDate);
                                        String report = "\n\n\nOverall Revenue Between " + dateFormatter.format(startDate) +
                                                " and " + dateFormatter.format(endDate) + " =  £" + value + "\n\n\n";
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getRevenueOverall");
                                break;
                                
                            case 12:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        double value = client.getProfitOverall(startDate, endDate);
                                        String report = "\n\n\nOverall Profit Between " + dateFormatter.format(startDate) +
                                                " and " + dateFormatter.format(endDate) + " =  £" + value + "\n\n\n";
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("getProfitOverall");
                                break;
                                
                            case 13:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        String report = client.generateEmployeeReport(startDate, endDate);
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("generateEmployeeReport");
                                break;
                                
                            case 14:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        String report = client.generateOfficeReport(startDate, endDate);
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("generateOfficeReport");
                                break;
                                
                            case 15:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        String report = client.generateOfficeFinanceReport(startDate, endDate);
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("generateOfficeFinanceReport");
                                break;
                                
                            case 16:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        String report = client.generateFinanceReport(startDate, endDate);
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("generateFinanceReport");
                                break;
                                
                            case 17:
                                if (array != null && array.size() == 2) {
                                    try {
                                        Date startDate = null;
                                        Date endDate = null;
                                        
                                        try {
                                            if (array.get(0) != null) {
                                                startDate = dateFormatter.parse(array.get(0));
                                            }
                                            if (array.get(1) != null) {
                                                endDate = dateFormatter.parse(array.get(1));
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        String report = client.generateReport(startDate, endDate);
                                        textArea.setText(report);
                                        removeCenterPanel();
                                        centerPanel.add(new JScrollPane(textArea));
                                        centerPanel.setVisible(true);
                                        northPanel.setVisible(false);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                System.out.println("generateReport");
                                break;
                        }
                    }
                });
                removeNorthPanel();
                northPanel.add(reportPanel);
                reportPanel.setVisible(true);
                northPanel.setVisible(true);
                centerPanel.setVisible(false);
                repaint();
            }
        });
        
        panel.setLayout(new BorderLayout());
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        
        this.add(reportingTree, BorderLayout.WEST);
        this.add(panel, BorderLayout.CENTER);
    }
    
    private void removeNorthPanel() {
        BorderLayout layout = (BorderLayout) northPanel.getLayout();
        if (layout.getLayoutComponent(BorderLayout.NORTH) != null) {
            Component component = layout.getLayoutComponent(BorderLayout.NORTH);
            northPanel.remove(component);
            component.setVisible(false);
        }
    }
    
    private void removeCenterPanel() {
        BorderLayout layout = (BorderLayout) centerPanel.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            Component component = layout.getLayoutComponent(BorderLayout.CENTER);
            centerPanel.remove(component);
            component.setVisible(false);
        }
    }
    
    private void createTenancy() {
        CreateTenancy createTenancy = new CreateTenancy(client);
        createTenancy.setVisible(true);
    }
    
    private void endTenancy() {
        Integer selection = tenancyPanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Tenancy Ref: " + selection);
            EndObject endTenancy = new EndObject(client, "Tenancy", selection);
            endTenancy.setVisible(true);
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
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                        OKDialog.okDialog(ReportingFrame.this, message, title);
                    } else {
                        String message = "Tenancy " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(ReportingFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void createLease() {
        CreateLease createLease = new CreateLease(client);
        createLease.setVisible(true);
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
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Lease " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Lease - Yes button clicked");
                    int result = client.deleteLease(selection);
                    if (result > 0) {
                        String message = "Lease " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(ReportingFrame.this, message, title);
                    } else {
                        String message = "Lease " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(ReportingFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void createContract() {
        CreateContract createContract = new CreateContract(client);
        createContract.setVisible(true);
    }

    private void updateContract() {
        Integer selection = contractPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                ContractInterface contract = client.getContract(selection);
                if (contract != null) {
                    UpdateContract updateContract = new UpdateContract(client, contract);
                    updateContract.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteContract() {
        Integer selection = contractPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Contract " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Contract Delete - Yes button clicked");
                    int result = client.deleteContract(selection);
                    if (result > 0) {
                        String message = "Contract " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(ReportingFrame.this, message, title);
                    } else {
                        String message = "Contract " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(ReportingFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

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
        
        //File Menu
        
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int action = JOptionPane.showConfirmDialog(ReportingFrame.this,
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

                int action = JOptionPane.showConfirmDialog(ReportingFrame.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(ReportingFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
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