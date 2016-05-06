/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.address;

import client_application.ClientImpl;
import client_gui.StringArrayListener;
import interfaces.Element;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Dwayne
 */
public class AddressSearchPanel extends JPanel {
    private ClientImpl client = null;
    private StringArrayListener listener = null;
    
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
    
    private JButton searchButton;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public AddressSearchPanel(ClientImpl client) {
        setClient(client);
        layoutComponents();
    }
    
    private void setClient(ClientImpl client) {
        if (this.client == null) {
            this.client = client;
        }
    }
    
    public void setListener(StringArrayListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }
    
    private void layoutComponents() {
        
        searchButton = new JButton("Search");
        buildingNumberField = new JTextField(3);
        buildingNameField = new JTextField(10);
        subStreetNumberField = new JTextField(3);
        subStreetField = new JTextField(10);
        streetNumberField = new JTextField(3);
        streetField = new JTextField(10);
        areaField = new JTextField(10);
        townField = new JTextField(10);
        countryField = new JTextField(10);
        postcodeField = new JTextField(8);
        createdByField = new JTextField(10);
        createdDateField = new JXDatePicker();
        createdDateField.setFormats(dateFormatter);
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
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
                
                System.out.println("Building Number: " + buildingNumber);
                System.out.println("Building Name: " + buildingName);
                System.out.println("Sub Street Number: " + subStreetNumber);
                System.out.println("Sub Street: " + subStreet);
                System.out.println("Street Number: " + streetNumber);
                System.out.println("Street: " + street);
                System.out.println("Area: " + area);
                System.out.println("Town: " + town);
                System.out.println("Country: " + country);
                System.out.println("Postcode: " + postcode);
                System.out.println("Created By: " + createdBy);
                System.out.println("Created Date: " + createdDate);
                
                List<String> array = new ArrayList();
                array.add(buildingNumber);
                array.add(buildingName);
                array.add(subStreetNumber);
                array.add(subStreet);
                array.add(streetNumber);
                array.add(street);
                array.add(area);
                array.add(town);
                array.add(country);
                array.add(postcode);
                array.add(createdBy);
                if (createdDate != null) {
                    array.add(dateFormatter.format(createdDate));
                } else {
                    array.add(null);
                }
                
                if (listener != null) {
                    listener.arrayOmitted(array);
                }
            }
        });
        
        
        setLayout(new BorderLayout());

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Address Search Details");

        setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        
        ///////// DETAILS PANEL ///////
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel title = new JLabel("Building Number    ");
        Font font = title.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        title.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(title, gc);

        buildingNumberField.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(buildingNumberField, gc);

        JLabel forename = new JLabel("Building Name    ");
        forename.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(forename, gc);

        buildingNameField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(buildingNameField, gc);

        JLabel mNames = new JLabel("Sub Street Number    ");
        mNames.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(mNames, gc);

        subStreetNumberField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(subStreetNumberField, gc);

        JLabel surname = new JLabel("Sub Street    ");
        surname.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(surname, gc);

        subStreetField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(subStreetField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel dobLabel = new JLabel("Street Number    ");
        dobLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(dobLabel, gc);
        
        streetNumberField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(streetNumberField, gc);

        JLabel ni = new JLabel("Street    ");
        ni.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(ni, gc);
        
        streetField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(streetField, gc);
        
        JLabel gender = new JLabel("Area    ");
        gender.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(gender, gc);
        
        areaField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(areaField, gc);

        JLabel maritalStatus = new JLabel("Town    ");
        maritalStatus.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(maritalStatus, gc);
        
        townField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(townField, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel ethnicOrigin = new JLabel("Country    ");
        ethnicOrigin.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(ethnicOrigin, gc);
        
        countryField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(countryField, gc);

        JLabel language = new JLabel("Postcode    ");
        language.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(language, gc);
        
        postcodeField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(postcodeField, gc);
        
        JLabel nationality = new JLabel("Created By    ");
        nationality.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(nationality, gc);
        
        createdByField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(createdByField, gc);

        JLabel sexuality = new JLabel("Created Date    ");
        sexuality.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(sexuality, gc);
        
        createdDateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(createdDateField, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(new JLabel(""), gc);
        
        
        ////////// SEARCH BUTTONS PANEL //////////
        
        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.setBorder(BorderFactory.createEmptyBorder());
        
        searchButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchButtonPanel.add(searchButton);
        
        
        ///////// ADD COMPONENTS TO PANEL ////////
        
        add(detailsPanel, BorderLayout.CENTER);
        add(searchButtonPanel, BorderLayout.SOUTH);
    }
}
