/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.address;

import client_application.ClientImpl;
import client_gui.DetailsPanel;
import client_gui.OKDialog;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class UpdateAddress extends JFrame {

    ClientImpl client = null;
    AddressInterface address = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JPanel detailsPanel;

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
    private JTextArea textArea;

    public UpdateAddress(ClientImpl client, AddressInterface address) {
        super("MSc Properties");
        setClient(client);
        setAddress(address);
        layoutComponents();
    }

    private void setClient(ClientImpl client) {
        if (this.client == null) {
            this.client = client;
        }
    }

    private void setAddress(AddressInterface address) {
        if (this.address == null) {
            this.address = address;
        }
    }

    private void layoutComponents() {
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            textArea = new JTextArea(4, 40);
            textArea.setLineWrap(true);
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

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        int result = 0;
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Address " + address.getAddressRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            if (streetField.getText() != null && areaField.getText() != null && townField.getText() != null && countryField.getText() != null && postcodeField.getText() != null) {
                                String buildingNumber = buildingNumberField.getText();
                                String buildingName = buildingNameField.getText();
                                String subStreetNumber = subStreetNumberField.getText();
                                String subStreet = subStreetField.getText();
                                String streetNumber = streetNumberField.getText();
                                String street = streetField.getText();
                                String area = areaField.getText();
                                String town = townField.getText();
                                String country = countryField.getText();
                                String postcode = postcodeField.getText();
                                String comment = textArea.getText();

                                result = client.updateAddress(address.getAddressRef(), buildingNumber, buildingName, subStreetNumber, subStreet, streetNumber, street, area, town, country, postcode, comment);

                                if (result > 0) {
                                    String message = "Address " + address.getAddressRef() + " has been amended successfully";
                                    String title = "Information";
                                    OKDialog.okDialog(UpdateAddress.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to UPDATE Address " + address.getAddressRef() + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(UpdateAddress.this, message, title);
                                }
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(UpdateAddress.class.getName()).log(Level.SEVERE, null, ex);
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

            this.setSize(750, 400);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

            JPanel buttonsPanel = new JPanel();

            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Update Address");

            detailsPanel = new JPanel();
            detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

            /////////// DETAILS PANEL //////////////////
            detailsPanel.setLayout(new GridBagLayout());

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

            if (address.getBuildingNumber() != null) {
                buildingNumberField.setText(address.getBuildingNumber());
            }

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
            if (address.getBuildingName() != null) {
                buildingNameField.setText(address.getBuildingName());
            }

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
            if (address.getSubStreetNumber() != null) {
                subStreetNumberField.setText(address.getSubStreetNumber());
            }

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
            if (address.getSubStreet() != null) {
                subStreetField.setText(address.getSubStreet());
            }

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
            if (address.getStreetNumber() != null) {
                streetNumberField.setText(address.getStreetNumber());
            }

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
            if (address.getStreet() != null) {
                streetField.setText(address.getStreet());
            }

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
            if (address.getArea() != null) {
                areaField.setText(address.getArea());
            }

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
            if (address.getTown() != null) {
                townField.setText(address.getTown());
            }

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
            if (address.getCountry() != null) {
                countryField.setText(address.getCountry());
            }

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
            if (address.getPostcode() != null) {
                postcodeField.setText(address.getPostcode());
            }

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(postcodeField, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel commentLabel = new JLabel("Comment    ");

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(commentLabel, gc);

            if (address.getComment() != null) {
                textArea.setText(address.getComment());
            }

            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(textArea, gc);

            ////////// BUTTONS PANEL //////////
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(okButton);
            buttonsPanel.add(cancelButton);

            Dimension btnSize = cancelButton.getPreferredSize();
            okButton.setPreferredSize(btnSize);

            ///////////// MOD PANEL /////////////////
            JPanel modPanel = new JPanel();
            try {
                modPanel = new DetailsPanel(address.getCreatedBy(), address.getCreatedDate(), address.getLastModifiedBy(), address.getLastModifiedDate());
            } catch (RemoteException ex) {
                Logger.getLogger(UpdateAddress.class.getName()).log(Level.SEVERE, null, ex);
            }

            ///////////// MAIN PANEL ////////////////
            mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(detailsPanel, BorderLayout.CENTER);
            mainPanel.add(modPanel, BorderLayout.SOUTH);

            // Add sub panels to dialog
            setLayout(new BorderLayout());
            add(detailsPanel, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(UpdateAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
