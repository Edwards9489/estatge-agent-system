/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import client_application.ClientImpl;
import client_gui.EndObject;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.StringListener;
import interfaces.LeaseInterface;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class LeaseSearch extends JFrame {

    private ClientImpl client = null;
    private IntegerListener listener = null;
    
    private JComboBox searchOn;
    private JTextField searchValue;
    private JButton searchButton;
    private JButton advSearchButton;
    private JButton createButton;

    private JButton okButton;
    private JButton cancelButton;
    
    private LeasePanel leasePanel;
    private JPanel searchResultsPanel;
    private JPanel detailsPanel;

    public LeaseSearch(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        this.layoutComponents();
    }

    public LeaseSearch(ClientImpl client, IntegerListener listener) {
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
        createButton = new JButton("Create");
        searchValue = new JTextField(15);
        searchOn = new JComboBox();
        searchOn.addItem("  ---  ");
        searchOn.addItem("Lease Ref");
        searchOn.addItem("Prop Ref");
        searchOn.addItem("Landlord Ref");
        searchOn.addItem("Office Code");
        searchOn.addItem("Lease Name");
        
        
        searchButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (!searchOn.getSelectedItem().equals("  ---  ") && !searchValue.getText().isEmpty()) {
                    try {
                        List<LeaseInterface> leases = new ArrayList();
                        String searchOnText = (String) searchOn.getSelectedItem();
                        searchOnText = searchOnText.trim();
                        switch (searchOnText) {
                            case "Lease Ref":
                                int leaseRef = Integer.parseInt(searchValue.getText());
                                if (leaseRef > 0) {
                                    LeaseInterface lease = client.getLease(leaseRef);
                                    if (lease != null) {
                                        leases.add(lease);
                                    }
                                }
                                break;
                                
                            case "Prop Ref":
                                int propRef = Integer.parseInt(searchValue.getText());
                                if (propRef > 0) {
                                    leases = client.getPropertyLeases(propRef);
                                }
                                break;
                                
                            case "Landlord Ref":
                                int landlordRef = Integer.parseInt(searchValue.getText());
                                if (landlordRef > 0) {
                                    leases = client.getLandlordLeases(landlordRef);
                                }
                                break;
                                
                            case "Office Code":
                                String officeCode = searchValue.getText();
                                leases = client.getOfficeLeases(officeCode);
                                break;
                                
                            case "Lease Name":
                                String name = searchValue.getText();
                                leases = client.getNameLeases(name);
                                break;
                        }
                        setData(leases);
                    } catch (RemoteException ex) {
                        Logger.getLogger(LeaseSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        advSearchButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ev) {
                LeaseAdvSearch advSearch = new LeaseAdvSearch(client);
                advSearch.setListener(new LeaseArrayListener() {
                    @Override
                    public void arrayOmitted(List<LeaseInterface> array) {
                        if (array != null) {
                            leasePanel.setData(array);
                        }
                    }
                });
                advSearch.setVisible(true);
                System.out.println("TEST - Adv Lease Search");
            }
        });
        
        createButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ev) {
                createLease();
            }
        });
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        if (listener != null) {
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (listener != null && leasePanel.getSelectedObjectRef() != null) {
                        listener.intOmitted(leasePanel.getSelectedObjectRef());
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
        
        leasePanel = new LeasePanel("Leases");
        
        leasePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        searchResultsPanel.add(leasePanel, BorderLayout.CENTER);
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
        
        advSearchButton.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 20, 10, 10);
        searchPanel.add(advSearchButton, gc);
        
        createButton.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 10, 10, 10);
        searchPanel.add(createButton, gc);
        
        
        // DETAILS PANEL
        
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        
        JLabel title = new JLabel("Lease Search");
        
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

    private void createLease() {
        CreateLease createLease = new CreateLease(client);
        createLease.setVisible(true);
        System.out.println("TEST - Create Lease");
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
                Logger.getLogger(LeaseSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void endLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            System.out.println("Lease Ref: " + selection);
            EndObject endLease = new EndObject(client, "Lease", selection);
            endLease.setVisible(true);
        }
    }

    private void deleteLease() {
        Integer selection = leasePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Lease " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Lease Delete - Yes button clicked");
                    int result = client.deleteLease(selection);
                    if (result > 0) {
                        String message = "Lease " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(LeaseSearch.this, message, title);
                    } else {
                        String message = "Lease " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(LeaseSearch.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(LeaseSearch.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(LeaseSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        leasePanel.refresh();
    }
    
    private void actionChoice(String text) {
        switch (text) {
            case "Create":
                createLease();
                break;

            case "Update":
                updateLease();
                break;

            case "End":
                endLease();
                break;

            case "Delete":
                deleteLease();
                break;

            case "View Details":
                viewLease();
                break;

            case "Refresh":
                refresh();
                break;

        }
    }
    
    private void setData(List<LeaseInterface> leases) {
        leasePanel.setData(leases);
        repaint();
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LeaseSearch().setVisible(true);
//            }
//        });
//    }
}
