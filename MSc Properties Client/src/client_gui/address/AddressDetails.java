/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.address;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.DetailsPanel;
import client_gui.OKDialog;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import interfaces.AddressInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class AddressDetails extends JFrame {

    private ClientImpl client = null;
    private AddressInterface address = null;
    private JButton closeButton;
    private JTextArea noteField;
    private JPanel mainPanel;
    private JPanel detailsPanel;
    private ModPanel modPanel;
    private JTabbedPane tabbedPane;
    
    private JLabel buildingNumber;
    private JLabel buildingName;
    private JLabel subStreetNumber;
    private JLabel subStreet;
    private JLabel streetNumber;
    private JLabel street;
    private JLabel area;
    private JLabel town;
    private JLabel country;
    private JLabel postcode;

    public AddressDetails(ClientImpl client, AddressInterface address) {
        super("MSc Properties");
        setClient(client);
        setAddress(address);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Address is initiated
    private void setAddress(AddressInterface address) {
        if (this.address == null) {
            this.address = address;
        }
    }

    private void layoutComponents() {
        
        setJMenuBar(createMenuBar());

        closeButton = new JButton("Close");
        modPanel = new ModPanel("Modifications");
        tabbedPane = new JTabbedPane();
        noteField = new JTextArea(2, 37);
        noteField.setLineWrap(true);
        noteField.setEnabled(false);
        noteField.setDisabledTextColor(Color.BLACK);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                setVisible(false);
                dispose();
            }
        });

        try {
            modPanel.setData(address.getModifiedBy());
            noteField.setText(address.getNote().getNote());

            this.setSize(750, 500);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

            ///////// DETAILS PANEL ////////////
            detailsPanel = new JPanel();
            //detailsPanel.setSize(450, 200);
            detailsPanel.setLayout(new GridBagLayout());

            GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
            gc.gridx = 0;
            gc.gridy = 0;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel buildingNumberLabel = new JLabel("Building Number  ");
            Font font = buildingNumberLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 17);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 17);
            buildingNumberLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(buildingNumberLabel, gc);

            buildingNumber = new JLabel(address.getBuildingNumber());
            buildingNumber.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(buildingNumber, gc);

            JLabel buildingNameLabel = new JLabel("Building Name  ");
            buildingNameLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(buildingNameLabel, gc);

            buildingName = new JLabel(address.getBuildingName());
            buildingName.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(buildingName, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel subStreetNumberLabel = new JLabel("Sub Street Number  ");
            subStreetNumberLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(subStreetNumberLabel, gc);

            subStreetNumber = new JLabel(address.getSubStreetNumber());
            subStreetNumber.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(subStreetNumber, gc);

            JLabel subStreetLabel = new JLabel("Sub Street  ");
            subStreetLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(subStreetLabel, gc);

            subStreet = new JLabel(address.getSubStreet());
            subStreet.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(subStreet, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel streetNumberLabel = new JLabel("Street Number  ");
            streetNumberLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(streetNumberLabel, gc);

            streetNumber = new JLabel(address.getStreetNumber());
            streetNumber.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(streetNumber, gc);

            JLabel streetLabel = new JLabel("Street  ");
            streetLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(streetLabel, gc);

            street = new JLabel(address.getStreet());
            street.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(street, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel areaLabel = new JLabel("Area  ");
            areaLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(areaLabel, gc);

            area = new JLabel(address.getArea());
            area.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(area, gc);

            JLabel townLabel = new JLabel("Town  ");
            townLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(townLabel, gc);

            town = new JLabel(address.getTown());
            town.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(town, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel countryLabel = new JLabel("Country  ");
            countryLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(countryLabel, gc);

            country = new JLabel(address.getCountry());
            country.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(country, gc);

            JLabel postcodeLabel = new JLabel("Postcode  ");
            postcodeLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(postcodeLabel, gc);

            postcode = new JLabel(address.getPostcode());
            postcode.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(postcode, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel commentLabel = new JLabel("Comment  ");
            commentLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(commentLabel, gc);

            noteField.setFont(plainFont);
            noteField.setText(address.getNote().getNote());
            noteField.setEnabled(false);

            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(noteField, gc);

            JPanel buttonsPanel = new JPanel();

            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("View Address");

            mainPanel = new JPanel();

            mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(detailsPanel, BorderLayout.CENTER);

            try {
                JPanel mods = new DetailsPanel(address.getCreatedBy(), address.getCreatedDate(), address.getLastModifiedBy(), address.getLastModifiedDate());
                mainPanel.add(mods, BorderLayout.SOUTH);
            } catch (RemoteException ex) {
                Logger.getLogger(AddressDetails.class.getName()).log(Level.SEVERE, null, ex);
            }

            ////////// BUTTONS PANEL //////////
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(closeButton);

            ///////// TABBED PANE /////////////
            tabbedPane.add(mainPanel, "Details");
            tabbedPane.add(modPanel, "Modifications");

            // Add sub panels to dialog
            setLayout(new BorderLayout());
            add(tabbedPane, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(AddressDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {
        try {
            if (address.getBuildingNumber() != null) {
                buildingNumber.setText(address.getBuildingNumber());
            }
            if (address.getBuildingName() != null) {
                buildingName.setText(address.getBuildingName());
            }
            if (address.getSubStreetNumber() != null) {
                subStreetNumber.setText(address.getSubStreetNumber());
            }
            if (address.getSubStreet() != null) {
                subStreet.setText(address.getSubStreet());
            }
            if (address.getStreetNumber() != null) {
                streetNumber.setText(address.getStreetNumber());
            }
            if (address.getStreet() != null) {
                street.setText(address.getStreet());
            }
            if (address.getArea() != null) {
                area.setText(address.getArea());
            }
            if (address.getTown() != null) {
                town.setText(address.getTown());
            }
            if (address.getCountry() != null) {
                country.setText(address.getCountry());
            }
            if (address.getPostcode() != null) {
                postcode.setText(address.getPostcode());
            }
            modPanel.setData(address.getModifiedBy());
            modPanel.refresh();
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(AddressDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem userAccount = new JMenuItem("User Account");
        JMenuItem changeUser = new JMenuItem("Change User");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(userAccount);
        fileMenu.add(changeUser);
        fileMenu.addSeparator(); // Is the faint lines between grouped menu items
        fileMenu.add(exitItem);
        
        
        // Actions Menu
        JMenu actionsMenu = new JMenu("Actions");

        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(updateItem);
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(actionsMenu);
        menuBar.add(helpMenu);

        // Set up Mnemonics for Menus
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // Set up Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        changeUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        userAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        manualItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        
        
        //Set up ActionListeners
        
        //File Menu
        
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int action = JOptionPane.showConfirmDialog(AddressDetails.this,
                        "Do you really want to change user?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                
                if (action == JOptionPane.OK_OPTION) {
                    System.gc();
                    Window windows[] = Window.getWindows(); 
                    for (int i=0; i<windows.length; i++) {
                        windows[i].dispose(); 
                        windows[i]=null;
                    }
                    new LoginForm().setVisible(true);
                    dispose();
                }
            }
        });

        userAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                UpdateEmployeeSecurity securityGUI = new UpdateEmployeeSecurity(client);
                securityGUI.setVisible(true);
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                int action = JOptionPane.showConfirmDialog(AddressDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(AddressDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        
        
        // Actions Menu
        
        updateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                UpdateAddress addressDetails = new UpdateAddress(client, address);
                addressDetails.setVisible(true);
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Address " + address.getAddressRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Address Delete - Yes button clicked");
                        int result = client.deleteAddress(address.getAddressRef());
                        if (result > 0) {
                            String message = "Address " + address.getAddressRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(AddressDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Address " + address.getAddressRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(AddressDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                refresh();
            }
        });
        
        
        // Help Menu

        manualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                // NEED TO DEVELOP USER MANUAL
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                AboutFrame about = new AboutFrame();
                about.setVisible(true);
            }
        });
        
        return menuBar;
    }
}
