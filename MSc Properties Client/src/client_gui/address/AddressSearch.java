/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.address;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.StringListener;
import interfaces.AddressInterface;
import java.awt.BorderLayout;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Dwayne
 */
public class AddressSearch extends JFrame {

    ClientImpl client = null;
    IntegerListener listener = null;
    private JButton okButton;
    private JButton cancelButton;
    private JButton searchButton;
    private JButton createButton;
    private JPanel detailsPanel;
    private AddressPanel addressPanel;

    private JTextField buildingNameField;
    private JTextField buildingNumberField;
    private JTextField subStreetNumberField;
    private JTextField subStreetField;
    private JTextField streetNumberField;
    private JTextField streetField;
    private JTextField areaField;
    private JTextField townField;
    private JTextField countryField;
    private JTextField postcodeField;
    private JTextField createdByField;
    private JXDatePicker createdDateField;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    public AddressSearch(ClientImpl client) {
        setClient(client);
        layoutComponents();
    }

    private void setClient(ClientImpl client) {
        if (this.client == null) {
            this.client = client;
        }
    }

    public void setListener(IntegerListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        searchButton = new JButton("Search");
        createButton = new JButton("Create Address");

        buildingNumberField = new JTextField(3);
        buildingNameField = new JTextField(15);
        subStreetNumberField = new JTextField(3);
        subStreetField = new JTextField(15);
        streetNumberField = new JTextField(3);
        streetField = new JTextField(15);
        areaField = new JTextField(15);
        townField = new JTextField(15);
        countryField = new JTextField(15);
        postcodeField = new JTextField(8);
        createdByField = new JTextField(15);
        createdDateField = new JXDatePicker();
        createdDateField.setFormats(dateFormatter);
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    String buildingNumber = null;
                    String buildingName = null;
                    String subStreetNumber = null;
                    String subStreet = null;
                    String streetNumber = null;
                    String street = null;
                    String area = null;
                    String town = null;
                    String country = null;
                    String postcode = null;
                    String createdBy = null;
                    Date createdDate = null;
                    
                    if (!buildingNumberField.getText().isEmpty()) {
                        buildingNumber = buildingNumberField.getText();
                    }
                    
                    if (!buildingNameField.getText().isEmpty()) {
                        buildingName = buildingNameField.getText();
                    }
                    
                    if (!subStreetNumberField.getText().isEmpty()) {
                        subStreetNumber = subStreetNumberField.getText();
                    }
                    
                    if (!subStreetField.getText().isEmpty()) {
                        subStreet = subStreetField.getText();
                    }
                    
                    if (!streetNumberField.getText().isEmpty()) {
                        streetNumber = streetNumberField.getText();
                    }
                    
                    if (!streetField.getText().isEmpty()) {
                        street = streetField.getText();
                    }
                    
                    if (!areaField.getText().isEmpty()) {
                        area = areaField.getText();
                    }
                    
                    if (!townField.getText().isEmpty()) {
                        town = townField.getText();
                    }
                    
                    if (!countryField.getText().isEmpty()) {
                        country = countryField.getText();
                    }
                    
                    if (!postcodeField.getText().isEmpty()) {
                        postcode = postcodeField.getText();
                    }
                    
                    if (!createdByField.getText().isEmpty()) {
                        createdBy = createdByField.getText();
                    }
                    
                    if (createdDateField.getDate() != null) {
                        createdDate = createdDateField.getDate();
                    }
                    
                    List<AddressInterface> address = client.getAddresses(buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, createdBy, createdDate);
                    
                    System.out.println("Addresses Size: " + address.size());
                    
                    addressPanel.setData(address);
                    addressPanel.refresh();
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (addressPanel.getSelectedObjectRef() != null) {
                    listener.intOmitted(addressPanel.getSelectedObjectRef());
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
                createAddress();
            }
        });
        
        addressPanel = new AddressPanel("Addresses");
        
        addressPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });

        this.setSize(1200, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Address Search");

        detailsPanel = new JPanel();
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        detailsPanel.setLayout(new GridBagLayout());
        detailsPanel.setSize(1000, 250);

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel buildingNumberLabel = new JLabel("Building Number    ");
        Font font = buildingNumberLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 14);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 14);
        buildingNumberLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(buildingNumberLabel, gc);

        buildingNumberField.setFont(plainFont);
        Dimension smallDimension = buildingNumberField.getPreferredSize();
        smallDimension.setSize(smallDimension.getWidth() + 40, smallDimension.getHeight());
        Dimension bigDimension = buildingNumberField.getPreferredSize();
        bigDimension.setSize(smallDimension.getWidth() + 60, smallDimension.getHeight());
        buildingNumberField.setPreferredSize(smallDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(buildingNumberField, gc);

        JLabel buildingNameLabel = new JLabel("Building Name    ");
        buildingNameLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(buildingNameLabel, gc);

        buildingNameField.setFont(plainFont);
        buildingNameField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(buildingNameField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel subStreetNumberLabel = new JLabel("Sub Street Number    ");
        subStreetNumberLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(subStreetNumberLabel, gc);

        subStreetNumberField.setFont(plainFont);
        subStreetNumberField.setSize(smallDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(subStreetNumberField, gc);

        JLabel subStreetLabel = new JLabel("Sub Street    ");
        subStreetLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(subStreetLabel, gc);

        subStreetField.setFont(plainFont);
        subStreetField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(subStreetField, gc);
        
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel streetNumberLabel = new JLabel("Street Number    ");
        streetNumberLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(streetNumberLabel, gc);

        streetNumberField.setFont(plainFont);
        streetNumberField.setSize(smallDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(streetNumberField, gc);

        JLabel streetLabel = new JLabel("Street    ");
        streetLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(streetLabel, gc);

        streetField.setFont(plainFont);
        streetField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(streetField, gc);
        
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel areaLabel = new JLabel("Area    ");
        areaLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(areaLabel, gc);

        areaField.setFont(plainFont);
        areaField.setSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(areaField, gc);

        JLabel townLabel = new JLabel("Town    ");
        townLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(townLabel, gc);

        townField.setFont(plainFont);
        townField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(townField, gc);
        
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel countryLabel = new JLabel("Country    ");
        countryLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(countryLabel, gc);

        countryField.setFont(plainFont);
        countryField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(countryField, gc);

        JLabel postcodeLabel = new JLabel("Postcode    ");
        postcodeLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(postcodeLabel, gc);

        postcodeField.setFont(plainFont);
        postcodeField.setSize(smallDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(postcodeField, gc);
        
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel createdByLabel = new JLabel("Created By    ");
        createdByLabel.setFont(boldFont);

        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(createdByLabel, gc);
        
        createdByField.setFont(plainFont);
        createdByField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(createdByField, gc);
        
        JLabel createdDateLabel = new JLabel("Created Date    ");
        createdDateLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(createdDateLabel, gc);
        
        createdDateField.setFont(plainFont);
        createdDateField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(createdDateField, gc);
        
        
        ////////// SEARCH BUTTONS PANEL //////////
        
        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.setBorder(BorderFactory.createEmptyBorder());
        
        searchButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchButtonPanel.add(searchButton);
        
        
        ///////// CONTROLS PANEL //////////
        
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BorderLayout());
        
        controlsPanel.add(detailsPanel, BorderLayout.CENTER);
        controlsPanel.add(searchButtonPanel, BorderLayout.SOUTH);
        

        ////////// BUTTONS PANEL //////////
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(createButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = searchButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        cancelButton.setPreferredSize(btnSize);
        

        // Add sub panels to dialog
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(controlsPanel, BorderLayout.CENTER);
        mainPanel.add(addressPanel, BorderLayout.SOUTH);
        
        
        // Add components to main frame
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    private void createAddress() {
        CreateAddress createAddress = new CreateAddress(client);
        createAddress.setVisible(true);
    }

    private void updateAddress() {
        Integer selection = addressPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                AddressInterface address = client.getAddress(selection);
                if (address != null) {
                    UpdateAddress updateAddress = new UpdateAddress(client, address);
                    updateAddress.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AddressSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteAddress() {
        Integer selection = addressPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE address " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Address Delete - Yes button clicked");
                    int result = client.deleteAddress(selection);
                    if (result > 0) {
                        String message = "Address " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(AddressSearch.this, message, title);
                    } else {
                        String message = "Address " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(AddressSearch.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AddressSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewAddress() {
        if (addressPanel.getSelectedObjectRef() != null) {
            AddressInterface address;
            try {
                address = client.getAddress(addressPanel.getSelectedObjectRef());
                if (address != null) {
                    AddressDetails addressDetails = new AddressDetails(client, address);
                    addressDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AddressSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        addressPanel.refresh();
    }
    
    private void actionChoice(String text) {
        switch (text) {
            case "Create":
                createAddress();
                break;

            case "View":
                viewAddress();
                break;

            case "Update":
                updateAddress();
                break;

            case "Delete":
                deleteAddress();
                break;

            case "Refresh":
                refresh();
                break;
        }
    }
}
