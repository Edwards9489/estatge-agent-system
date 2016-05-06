/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.leaseAcc;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import interfaces.LeaseAccountInterface;
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
public class LeaseAccSearch extends JFrame {

    private ClientImpl client = null;
    private IntegerListener listener = null;
    
    private JComboBox searchOn;
    private JTextField searchValue;
    private JButton searchButton;
    private JButton advSearchButton;

    private JButton okButton;
    private JButton cancelButton;
    
    private LeaseAccPanel leaseAccPanel;
    private JPanel searchResultsPanel;
    private JPanel detailsPanel;

    public LeaseAccSearch(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        this.layoutComponents();
    }

    public LeaseAccSearch(ClientImpl client, IntegerListener listener) {
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
        searchOn.addItem("Landlord Ref");
        searchOn.addItem("Lease Ref");
        searchOn.addItem("Office Code");
        searchOn.addItem("Account Name");
        
        searchButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (!searchOn.getSelectedItem().equals("-") && !searchValue.getText().isEmpty()) {
                    try {
                        List<LeaseAccountInterface> leaseAccounts = new ArrayList();
                        String searchOnText = (String) searchOn.getSelectedItem();
                        searchOnText = searchOnText.trim();
                        
                        switch (searchOnText) {
                            case "Account Ref":
                                int accountRef = Integer.parseInt(searchValue.getText());
                                if (accountRef > 0) {
                                    LeaseAccountInterface leaseAcc = client.getLeaseAccount(accountRef);
                                    if (leaseAcc != null) {
                                        leaseAccounts.add(leaseAcc);
                                    }
                                }
                                break;
                                
                            case "Lease Ref":
                                int leaseRef = Integer.parseInt(searchValue.getText());
                                if (leaseRef > 0) {
                                    LeaseAccountInterface leaseAcc = client.getLeaseLeaseAcc(leaseRef);
                                    if (leaseAcc != null) {
                                        leaseAccounts.add(leaseAcc);
                                    }
                                }
                                break;
                                
                            case "Prop Ref":
                                int propRef = Integer.parseInt(searchValue.getText());
                                if (propRef > 0) {
                                    leaseAccounts = client.getPropLeaseAccounts(propRef);
                                }
                                break;
                                
                            case "Landlord Ref":
                                int landlordRef = Integer.parseInt(searchValue.getText());
                                if (landlordRef > 0) {
                                    leaseAccounts = client.getLandlordLeaseAccounts(landlordRef);
                                }
                                break;
                                
                            case "Office Code":
                                String officeCode = searchValue.getText();
                                leaseAccounts = client.getOfficeLeaseAcc(officeCode);
                                break;
                                
                            case "Account Name":
                                String name = searchValue.getText();
                                leaseAccounts = client.getNameLeaseAcc(name);
                                break;
                        }
                        setData(leaseAccounts);
                    } catch (RemoteException ex) {
                        Logger.getLogger(LeaseAccSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        advSearchButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ev) {
                LeaseAccAdvSearch advSearch = new LeaseAccAdvSearch(client);
                advSearch.setListener(new LeaseAccArrayListener() {
                    @Override
                    public void arrayOmitted(List<LeaseAccountInterface> array) {
                        if (array != null) {
                            leaseAccPanel.setData(array);
                        }
                    }
                });
                advSearch.setVisible(true);
                System.out.println("TEST - Adv Lease Account Search");
            }
        });
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        if (listener != null) {
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (listener != null && leaseAccPanel.getSelectedObjectRef() != null) {
                        listener.intOmitted(leaseAccPanel.getSelectedObjectRef());
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
        
        leaseAccPanel = new LeaseAccPanel("Lease Accounts");
        
        searchResultsPanel.add(leaseAccPanel, BorderLayout.CENTER);
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
        
        JLabel title = new JLabel("Lease Account Search");
        
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
    
    private void setData(List<LeaseAccountInterface> leaseAccounts) {
        leaseAccPanel.setData(leaseAccounts);
        repaint();
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LeaseAccSearch().setVisible(true);
//            }
//        });
//    }
}
