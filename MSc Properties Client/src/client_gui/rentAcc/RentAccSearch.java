/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.rentAcc;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.StringListener;
import interfaces.RentAccountInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class RentAccSearch extends JFrame {

    private ClientImpl client = null;
    private IntegerListener listener = null;
    
    private JComboBox searchOn;
    private JTextField searchValue;
    private JButton searchButton;
    private JButton advSearchButton;

    private JButton okButton;
    private JButton cancelButton;
    
    private RentAccPanel rentAccPanel;
    private JPanel searchResultsPanel;
    private JPanel detailsPanel;

    public RentAccSearch(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        this.layoutComponents();
    }

    public RentAccSearch(ClientImpl client, IntegerListener listener) {
        super("MSc Properties");
        setListener(listener);
        setClient(client);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one listener is initiated
    private void setListener(IntegerListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {
        
        searchButton = new JButton("Search");
        advSearchButton = new JButton("Adv Search");
        searchValue = new JTextField(15);
        searchOn = new JComboBox();
        searchOn.addItem("-");
        searchOn.addItem("Account Ref");
        searchOn.addItem("Prop Ref");
        searchOn.addItem("App Ref");
        searchOn.addItem("Tenancy Ref");
        searchOn.addItem("Office Code");
        searchOn.addItem("Account Name");
        
        searchButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (!searchOn.getSelectedItem().equals("-") && !searchValue.getText().isEmpty()) {
                    try {
                        List<RentAccountInterface> rentAccounts = new ArrayList();
                        String searchOnText = (String) searchOn.getSelectedItem();
                        searchOnText = searchOnText.trim();
                        
                        switch (searchOnText) {
                            case "Account Ref":
                                int accountRef = Integer.parseInt(searchValue.getText());
                                if (accountRef > 0) {
                                    RentAccountInterface rentAcc = client.getRentAccount(accountRef);
                                    if (rentAcc != null) {
                                        rentAccounts.add(rentAcc);
                                    }
                                }
                                break;
                                
                            case "Lease Ref":
                                int leaseRef = Integer.parseInt(searchValue.getText());
                                if (leaseRef > 0) {
                                    RentAccountInterface rentAcc = client.getTenancyRentAcc(leaseRef);
                                    if (rentAcc != null) {
                                        rentAccounts.add(rentAcc);
                                    }
                                }
                                break;
                                
                            case "Prop Ref":
                                int propRef = Integer.parseInt(searchValue.getText());
                                if (propRef > 0) {
                                    rentAccounts = client.getPropRentAccounts(propRef);
                                }
                                break;
                                
                            case "App Ref":
                                int appRef = Integer.parseInt(searchValue.getText());
                                if (appRef > 0) {
                                    rentAccounts = client.getApplicationRentAccounts(appRef);
                                }
                                break;
                                
                            case "Office Code":
                                String officeCode = searchValue.getText();
                                rentAccounts = client.getOfficeRentAcc(officeCode);
                                break;
                                
                            case "Account Name":
                                String name = searchValue.getText();
                                rentAccounts = client.getNameRentAcc(name);
                                break;
                        }
                        setData(rentAccounts);
                    } catch (RemoteException ex) {
                        Logger.getLogger(RentAccSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        advSearchButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ev) {
                RentAccAdvSearch advSearch = new RentAccAdvSearch(client);
                advSearch.setListener(new RentAccArrayListener() {
                    @Override
                    public void arrayOmitted(List<RentAccountInterface> array) {
                        if (array != null) {
                            rentAccPanel.setData(array);
                        }
                    }
                });
                advSearch.setVisible(true);
                System.out.println("TEST - Adv Rent Account Search");
            }
        });
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        if (listener != null) {
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (listener != null && rentAccPanel.getSelectedObjectRef() != null) {
                        listener.intOmitted(rentAccPanel.getSelectedObjectRef());
                        setVisible(false);
                        dispose();
                    }
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    setVisible(false);
                    dispose();
                }
            });
        }
        
        
        // RESULTS PANEL
        
        
        searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new BorderLayout());
        
        rentAccPanel = new RentAccPanel("Rent Accounts");

        rentAccPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        searchResultsPanel.add(rentAccPanel, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        
        if (listener != null) {
            
            ////////// BUTTONS PANEL //////////
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(okButton);
            buttonsPanel.add(cancelButton);

            Dimension btnSize = cancelButton.getPreferredSize();
            okButton.setPreferredSize(btnSize);
            searchResultsPanel.add(buttonsPanel, BorderLayout.SOUTH);
        }
        
        
        // SEARCH PANEL
        
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEmptyBorder());
        searchPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        //////// FIRST ROW //////////
        
        gc.gridy = 0;
        gc.gridx = 0;
        
        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel searchOnLabel = new JLabel("Search On");
        Font font = searchOnLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        searchOnLabel.setFont(boldFont);
        
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 10, 10, 5);
        searchPanel.add(searchOnLabel, gc);
        
        searchOn.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 5, 10, 10);
        searchPanel.add(searchOn, gc);
        
        searchValue.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 10, 10, 10);
        searchPanel.add(searchValue, gc);
        
        searchButton.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 10, 10, 20);
        searchPanel.add(searchButton, gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 10, 10, 10);
        searchPanel.add(new JLabel(""), gc);
        
        advSearchButton.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 20, 10, 10);
        searchPanel.add(advSearchButton, gc);
        
        
        // DETAILS PANEL
        
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        
        JLabel title = new JLabel("Rent Account Search");
        
        Font titleFont = new Font(font.getName(), Font.BOLD, font.getSize() + 10);
        title.setFont(titleFont);
        
        detailsPanel.add(title, BorderLayout.NORTH);
        detailsPanel.add(searchPanel, BorderLayout.CENTER);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(searchResultsPanel, BorderLayout.SOUTH);
        
        pack();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
    
    private void viewRentAcc() {
        Integer selection = rentAccPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                RentAccountInterface rentAcc = client.getRentAccount(selection);
                if (rentAcc != null) {
                    RentAccDetails rentAccDetails = new RentAccDetails(client, rentAcc);
                    rentAccDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(RentAccSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        rentAccPanel.refresh();
    }
    
    private void actionChoice(String text) {
        switch (text) {
            case "View Details":
                viewRentAcc();
                System.out.println("TEST - View Rent Account");
                break;

            case "Refresh":
                refresh();
                break;
        }
    }
    
    private void setData(List<RentAccountInterface> rentAccounts) {
        rentAccPanel.setData(rentAccounts);
        repaint();
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new RentAccSearch().setVisible(true);
//            }
//        });
//    }
}
