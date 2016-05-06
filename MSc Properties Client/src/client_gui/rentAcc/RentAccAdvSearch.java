/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.rentAcc;

import client_application.ClientImpl;
import client_gui.StringArrayListener;
import client_gui.tenancy.TenancySearchPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Dwayne
 */
public class RentAccAdvSearch extends JFrame {

    private ClientImpl client = null;
    private RentAccArrayListener listener = null;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private SimpleDateFormat dateFormatter;

    public RentAccAdvSearch(ClientImpl client) {
        setClient(client);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Client is initiated
    public void setListener(RentAccArrayListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {
        
        tabbedPane = new JTabbedPane();
        
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        
        RentAccSearchPanel rentAccPanel = new RentAccSearchPanel(client);

        rentAccPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 9) {
                    try {
                        Date startDate = null;
                        Date endDate = null;
                        Integer balance = null;
                        Double rent = null;
                        Integer agreementRef = null;
                        Date createdDate = null;
                        
                        if (array.get(1) != null) {
                            startDate = dateFormatter.parse(array.get(1));
                        }
                        if (array.get(2) != null) {
                            endDate = dateFormatter.parse(array.get(3));
                        }
                        if (array.get(3) != null && !array.get(3).isEmpty()) {
                            balance = Integer.parseInt(array.get(3));
                        }
                        if (array.get(4) != null && !array.get(4).isEmpty()) {
                            rent = Double.parseDouble(array.get(4));
                        }
                        if (array.get(5) != null && !array.get(5).isEmpty()) {
                            agreementRef = Integer.parseInt(array.get(5));
                        }
                        if (array.get(8) != null) {
                            createdDate = dateFormatter.parse(array.get(8));
                        }
                        
                        if (listener != null) {
                            listener.arrayOmitted(client.getRentAccounts(array.get(0), startDate, endDate, balance, rent, agreementRef, array.get(6), array.get(7), createdDate));
                            setVisible(false);
                            dispose();
                        }
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(RentAccAdvSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        TenancySearchPanel tenancyPanel = new TenancySearchPanel(client);

        tenancyPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 12) {
                    try {
                        Date startDate = null;
                        Date expectedEndDate = null;
                        Date endDate = null;
                        Integer length = null;
                        Integer propRef = null;
                        Integer appRef = null;
                        Integer accountRef = null;
                        Date createdDate = null;
                        
                        if (array.get(1) != null) {
                            startDate = dateFormatter.parse(array.get(1));
                        }
                        if (array.get(2) != null) {
                            expectedEndDate = dateFormatter.parse(array.get(2));
                        }
                        if (array.get(3) != null) {
                            endDate = dateFormatter.parse(array.get(3));
                        }
                        if (array.get(4) != null && !array.get(4).isEmpty()) {
                            length = Integer.parseInt(array.get(4));
                        }
                        if (array.get(5) != null && !array.get(5).isEmpty()) {
                            propRef = Integer.parseInt(array.get(5));
                        }
                        if (array.get(6) != null && !array.get(6).isEmpty()) {
                            appRef = Integer.parseInt(array.get(6));
                        }
                        if (array.get(8) != null && !array.get(8).isEmpty()) {
                            accountRef = Integer.parseInt(array.get(8));
                        }
                        if (array.get(11) != null) {
                            createdDate = dateFormatter.parse(array.get(11));
                        }
                        
                        if (listener != null) {
                            listener.arrayOmitted(client.getTenanciesRentAccounts(array.get(0), startDate, expectedEndDate, endDate, length, propRef, appRef, array.get(7), accountRef, array.get(9), array.get(10), createdDate));
                            setVisible(false);
                            dispose();
                        }
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(RentAccAdvSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
        /// TABBED PANE
        
        tabbedPane.add(rentAccPanel, "Rent Acc Details");
        tabbedPane.add(tenancyPanel, "Tenancy Details");
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        this.setSize(1350, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }
}
