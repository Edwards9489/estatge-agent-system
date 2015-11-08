/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

/**
 *
 * @author Dwayne
 */
public class HomeForm extends JFrame {

    private JLabel title;
    private ButtonPanel buttonPanel;
    private ListsPanel listsPanel;
    private ClientImpl client = null;

    public HomeForm() {
        super("MSc Properties");
        
        title = new JLabel("MSc Properties");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        title.setPreferredSize(new Dimension(600, 10));
        setLayout(new BorderLayout());
        buttonPanel = new ButtonPanel();
        listsPanel = new ListsPanel();

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

//			listsPanel.setListsPanelListener(new ListListener() {
//				pulic void listEventOccured(ListEvent ev) {
//				/// CODE for list Panel
//			}})
        setJMenuBar(createMenuBar());
        
        add(title, BorderLayout.NORTH);
        add(new SystemConfigHome(), BorderLayout.WEST);
        add(listsPanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 700));
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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