/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.landlord;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.person.PeopleSearchPanel;
import client_gui.StringArrayListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dwayne
 */
public class LandlordSearch extends JFrame {

    private ClientImpl client = null;
    private IntegerListener listener = null;

    private JButton okButton;
    private JButton cancelButton;
    private JButton createButton;

    private LandlordPanel landlordPanel;
    private JPanel searchResultsPanel;

    private SimpleDateFormat dateFormatter;

    public LandlordSearch(ClientImpl client) {
        super("MSc Properties");
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
    public void setListener(IntegerListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        createButton = new JButton("Create Landlord");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (landlordPanel.getSelectedObjectRef() != null) {
                    listener.intOmitted(landlordPanel.getSelectedObjectRef());
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
        
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                CreateLandlord landlordGUI = new CreateLandlord(client);
                landlordGUI.setVisible(true);
            }
        });

        landlordPanel = new LandlordPanel("Landlords");

        this.setSize(1200, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        PeopleSearchPanel detailsPanel = new PeopleSearchPanel(client);

        detailsPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 15) {
                    try {
                        Date dob = null;
                        Date createdDate = null;
                        
                        if (array.get(4) != null) {
                            dob = dateFormatter.parse(array.get(4));
                        }
                        if (array.get(14) != null) {
                            createdDate = dateFormatter.parse(array.get(14));
                        }
                        landlordPanel.setData(client.getPeopleLandlords(array.get(0), array.get(1), array.get(2), array.get(3), dob, array.get(5), array.get(6), array.get(7), array.get(8), array.get(9), array.get(10), array.get(11), array.get(12), array.get(13), createdDate));
                        landlordPanel.refresh();
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(LandlordSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new BorderLayout());

        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(createButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        //Add components to searchResultsPanel
        searchResultsPanel.add(landlordPanel, BorderLayout.CENTER);
        searchResultsPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(searchResultsPanel, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LandlordSearch().setVisible(true);
//            }
//        });
//    }
}
