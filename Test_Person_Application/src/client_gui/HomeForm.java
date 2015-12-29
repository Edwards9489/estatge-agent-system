/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import client_application.ClientImpl;
import interfaces.AgreementInterface;
import interfaces.ContractInterface;
import interfaces.LeaseInterface;
import interfaces.RentAccountInterface;
import interfaces.TenancyInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

/**
 *
 * @author Dwayne
 */
public class HomeForm extends JFrame implements Observer {

    private JLabel title;
    private ButtonPanel buttonPanel;
    private ListPanel listsPanel;
    private ClientImpl client = null;

    public HomeForm() {
        super("MSc Properties");
        
        title = new JLabel("MSc Properties");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        setLayout(new BorderLayout());
        buttonPanel = new ButtonPanel();

        buttonPanel.setButtonListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                if (text.equals("Applications")) {
//					AppSearch appSearch = new AppSearch();
//					appSearch.setClient(client);
                } else if (text.equals("Properties")) {
//					PropSearch propSearch = new PropSearch();
//					propSearch.setClient(client);
                } else if (text.equals("Tenancies")) {
//					TenSearch tenSearch = new TenSearch();
//					tenSearch.setClient(client);
                } else if (text.equals("Leases")) {
//					LeaseSearch leaseSearch = new LeaseSearch();
//					leaseSearch.setClient(client);
                } else if (text.equals("Contracts") && client != null) {// && client.viewEmp()) {
//					ContractSearch contractSearch = new ContractSearch();
//					contractSearch.setClient(client);
                } else if (text.equals("Rent Accounts")) {
//					RentAccSearch rentAccSearch = new RentAccSearch();
//					rentAccSearch.setClient(client);
                } else if (text.equals("Lease Accounts")) {
//					LeaseAccSearch leaseAccSearch = new RentAccSearch();
//					leaseAccSearch.setClient(client);
                } else if (text.equals("Employee Accounts") && client != null) {// && client.viewEmp()) {
//					EmpAccSearch empAccSearch = new EmpAccSearch();
//					leaseAccSearch.setClient(client);
                } else if (text.equals("Reporting") && client != null) {// && client.canReport()) { // add another boolean field to User to determine if user can report
//					ReportingFrame reporting = new ReportingFrame();
//					reporting.setClient(client);
                } else if (text.equals("System Configuration")) {
//					SysConfigFrame config = new SysConfigFrame();
//					config.setClient(client);
                }
            }
        });
        
        
        
        try {
            this.updateAgreementsList(client.getUserAgreements());
        } catch (RemoteException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listsPanel.setTableListener(new TableListener() {
            @Override
            public void rowSelected(Object agreement) {
                if(agreement instanceof TenancyInterface) {
                    TenancyInterface tenancy = (TenancyInterface) agreement;
//                    TenancyDetailsForm tenancyForm = new TenancyDetailsForm();
//                    tenancyForm.setClient(client);
//                    tenancyForm.setTenancy(tenancy);
                } else if(agreement instanceof LeaseInterface) {
                    LeaseInterface lease = (LeaseInterface) agreement;
//                    LeaseDetailsForm leaseForm = new LeaseDetailsForm();
//                    leaseForm.setClient(client);
//                    leaseForm.setLease(lease);
                } else if(agreement instanceof ContractInterface) {
                    ContractInterface contract = (ContractInterface) agreement;
//                    ContractDetailsForm contractForm = new ContractDetailsForm();
//                    contractForm.setClient(client);
//                    contractForm.setContract(contract);
                }
            }
        });
        
        setJMenuBar(createMenuBar());
        
        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(listsPanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 700));
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            if(arg instanceof List<?>) {
                if (!((List<?>) arg).isEmpty() && ((List<?>) arg).get(0) instanceof AgreementInterface) {
                    List<AgreementInterface> agreements = (List<AgreementInterface>) arg;
                    this.updateAgreementsList(agreements);
                } else if (!((List<?>) arg).isEmpty() && ((List<?>) arg).get(0) instanceof RentAccountInterface) {
                    List<RentAccountInterface> accounts = (List<RentAccountInterface>) arg;
                    this.updateRentAccountsList(accounts);
                    }
            }
        } catch (Exception ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateAgreementsList(List<AgreementInterface> agreements) {
        System.out.println("Agreements Updated");
        listsPanel.setData(agreements);
        listsPanel.refresh();
    }
    
    private void updateRentAccountsList(List<RentAccountInterface> accounts) {
        System.out.println("Rent Accounts Updated");
    }
    
    public void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        return menuBar;
    }
}