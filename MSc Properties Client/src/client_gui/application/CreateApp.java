/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.address.AddressSearch;
import client_gui.person.PersonSearch;
import interfaces.AddressInterface;
import interfaces.PersonInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
public class CreateApp extends JFrame {
    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    private JTextField corrNameField;
    private JXDatePicker appStartDateField;
    private JTextField personField;
    private int personRef = -1;
    private int addrRef = -1;
    private JTextField addrField;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public CreateApp(ClientImpl client) {
        setClient(client);
        layoutComponents();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        corrNameField = new JTextField(20);
        appStartDateField = new JXDatePicker();
        appStartDateField.setFormats(formatter);
        personField = new JTextField(30);
        addrField = new JTextField(30);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Application?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    String corrName = null;
                    Date appStartDate = appStartDateField.getDate();
                    
                    if (!corrNameField.getText().isEmpty()) {
                        corrName = corrNameField.getText();
                    }
                    
                    if (corrName != null && !corrName.isEmpty() && appStartDate != null && personRef > 0 && addrRef > 0) {
                        try {
                            int result = client.createApplication(corrName, appStartDate, personRef, addrRef, appStartDate);

                            if (result > 0) {
                                String message = "The new Application has been created and assigned Application Ref " + result;
                                String title = "Information";
                                OKDialog.okDialog(CreateApp.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to CREATE the Application\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(CreateApp.this, message, title);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        String message = "There is some errors with the information supplied to CREATE the Application\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(CreateApp.this, message, title);
                    }
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
        
        this.setSize(1100, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Application");

        controlsPanel = new JPanel();
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        controlsPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        ////////// FIRST ROW //////////
        
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel corrNameLabel = new JLabel("Correspondence Name    ");
        Font font = corrNameLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        corrNameLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(corrNameLabel, gc);

        corrNameField.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(corrNameField, gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        JLabel startDate = new JLabel("Start Date    ");
        startDate.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(startDate, gc);
        
        appStartDateField.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(appStartDateField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel personLabel = new JLabel("Main Applicant    ");
        personLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(personLabel, gc);

        personField.setFont(plainFont);
        personField.setEnabled(false);
        personField.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 4;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(personField, gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);

        Image srchImge = null;
        JLabel personThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            personThumb.setIcon(icon);
        }

        personThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PersonSearch personSearch = new PersonSearch(client);
                personSearch.setVisible(true);
                personSearch.setListener(new IntegerListener() {
                    @Override
                    public void intOmitted(int pRef) {
                        try {
                            if (client.personExists(pRef)) {
                                PersonInterface person = client.getPerson(pRef);
                                if (person != null) {
                                    setPersonField(person.getName());
                                    personRef = person.getPersonRef();
                                }
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(personThumb, gc);
        
        ///////// NEXT ROW ///////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel addressLabel = new JLabel("Applicant Address    ");
        addressLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(addressLabel, gc);

        addrField.setFont(plainFont);
        addrField.setEnabled(false);
        addrField.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 4;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(addrField, gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        Image srchImge2 = null;
        JLabel addrThumb = new JLabel();

        try {
            srchImge2 = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge2 != null) {
            ImageIcon icon = new ImageIcon(srchImge2);
            addrThumb.setIcon(icon);
        }

        addrThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddressSearch addrSearch = new AddressSearch(client);
                addrSearch.setVisible(true);
                addrSearch.setListener(new IntegerListener() {
                    @Override
                    public void intOmitted(int addressRef) {
                        try {
                            if (client.addressExists(addressRef)) {
                                AddressInterface address = client.getAddress(addressRef);
                                if (address != null) {
                                    setAddrField(address.printAddress());
                                    addrRef = address.getAddressRef();
                                }
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(addrThumb, gc);
        

        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    private void setPersonField(String text) {
        personField.setText(text);
    }
    
    private void setAddrField(String text) {
        addrField.setText(text);
    }
}
