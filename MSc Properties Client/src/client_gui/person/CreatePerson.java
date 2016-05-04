/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.address.AddressSearch;
import interfaces.AddressInterface;
import interfaces.Element;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
public class CreatePerson extends JFrame {
    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    
    private JComboBox titleField;
    private JTextField forenameField;
    private JTextField middleNameField;
    private JTextField surnameField;
    private JComboBox genderField;
    private JXDatePicker dobField;
    private JTextField niField;
    private JComboBox maritalField;
    private JComboBox languageField;
    private JComboBox nationalityField;
    private JComboBox ethnicField;
    private JComboBox religionField;
    private JComboBox sexualityField;
    private JCheckBox createAddress;
    
    private boolean addrEnabled;
    private JXDatePicker addrStartField;
    private JTextField addrField;
    private int addrRef = -1;
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public CreatePerson(ClientImpl client) {
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
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            
            titleField = new JComboBox();
            titleField.addItem("-");
            for (Element temp : client.getCurrentTitles()) {
                titleField.addItem(temp.getCode());
            }
            forenameField = new JTextField(15);
            middleNameField = new JTextField(15);
            surnameField = new JTextField(15);
            dobField = new JXDatePicker();
            dobField.setFormats(formatter);
            genderField = new JComboBox();
            genderField.addItem("-");
            for (Element temp : client.getCurrentGenders()) {
                genderField.addItem(temp.getCode());
            }
            niField = new JTextField(10);
            maritalField = new JComboBox();
            maritalField.addItem("-");
            for (Element temp : client.getCurrentMaritalStatuses()) {
                maritalField.addItem(temp.getCode());
            }
            languageField = new JComboBox();
            languageField.addItem("-");
            for (Element temp : client.getCurrentLanguages()) {
                languageField.addItem(temp.getCode());
            }
            nationalityField = new JComboBox();
            nationalityField.addItem("-");
            for (Element temp : client.getCurrentNationalities()) {
                nationalityField.addItem(temp.getCode());
            }
            ethnicField = new JComboBox();
            ethnicField.addItem("-");
            for (Element temp : client.getCurrentEthnicOrigins()) {
                ethnicField.addItem(temp.getCode());
            }
            religionField = new JComboBox();
            religionField.addItem("-");
            for (Element temp : client.getCurrentReligions()) {
                religionField.addItem(temp.getCode());
            }
            sexualityField = new JComboBox();
            sexualityField.addItem("-");
            for (Element temp : client.getCurrentSexualities()) {
                sexualityField.addItem(temp.getCode());
            }
            
            addrEnabled = false;
            createAddress = new JCheckBox();
            createAddress.setSelected(false);
            addrField = new JTextField(30);
            addrStartField = new JXDatePicker();
            addrStartField.setFormats(formatter);
            addressEnabled(false);
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    int result = 0;
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Person?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        String titleCode = (String) titleField.getSelectedItem();
                        String forename = null;
                        String middleName = null;
                        String surname = null;
                        Date dob = dobField.getDate();
                        String genderCode = (String) genderField.getSelectedItem();
                        String niNumber = null;
                        String maritalCode = (String) maritalField.getSelectedItem();
                        String languageCode = (String) languageField.getSelectedItem();
                        String nationalityCode = (String) nationalityField.getSelectedItem();
                        String ethnicCode = (String) ethnicField.getSelectedItem();
                        String religionCode = (String) religionField.getSelectedItem();
                        String sexualityCode = (String) sexualityField.getSelectedItem();
                        
                        Date addrStart = addrStartField.getDate();
                        
                        if (!forenameField.getText().isEmpty()) {
                            forename = forenameField.getText();
                        }
                        if (!middleNameField.getText().isEmpty()) {
                            middleName = middleNameField.getText();
                        }
                        if (!surnameField.getText().isEmpty()) {
                            surname = surnameField.getText();
                        }
                        
                        if (!niField.getText().isEmpty()) {
                            niNumber = niField.getText();
                        }
                        System.out.println("1: " + !titleCode.equals("-"));
                        System.out.println("2: " + forename != null);
                        System.out.println("3: " + !forename.isEmpty());
                        System.out.println("4: " + middleName != null);
                        System.out.println("5: " + !middleName.isEmpty());
                        System.out.println("6: " + surname != null);
                        System.out.println("7: " + !surname.isEmpty());
                        System.out.println("8: " + dob != null);
                        System.out.println("9: " + niNumber != null);
                        System.out.println("10: " + !niNumber.isEmpty());
                        System.out.println("11: " + !genderCode.equals("-"));
                        System.out.println("12: " + !maritalCode.equals("-"));
                        System.out.println("13: " + !languageCode.equals("-"));
                        System.out.println("14: " + !ethnicCode.equals("-"));
                        System.out.println("15: " + !religionCode.equals("-"));
                        System.out.println("16: " + !sexualityCode.equals("-"));
                        System.out.println("17: " + !createAddress.isSelected());
                        System.out.println("18: " + (addrRef > 0));
                        System.out.println("19: " + addrStart != null);
                        System.out.println("20: " + (!createAddress.isSelected() || (addrRef > 0 && addrStart != null)));
                        
                        if (!titleCode.equals("-") && forename != null && !forename.isEmpty() && surname != null && !surname.isEmpty() && dob != null && niNumber != null && !niNumber.isEmpty() && !genderCode.equals("-") && !maritalCode.equals("-") && !languageCode.equals("-") && !ethnicCode.equals("-") && !religionCode.equals("-") && !sexualityCode.equals("-") && (!createAddress.isSelected() || (addrRef > 0 && addrStart != null))) {
                            try {
                                System.out.println("WITHIN IF STATEMENT 1");
                                if (createAddress.isSelected()) {
                                    System.out.println("WITHIN IF STATEMENT 2");
                                    result = client.createPerson(titleCode, forename, middleName, surname, dob, niNumber, genderCode, maritalCode, ethnicCode, languageCode, nationalityCode, sexualityCode, religionCode, addrRef, addrStart);
                                } else { //(!createAddress.isSelected())
                                    System.out.println("WITHIN IF STATEMENT 3");
                                    result = client.createPerson(titleCode, forename, middleName, surname, dob, niNumber, genderCode, maritalCode, ethnicCode, languageCode, nationalityCode, sexualityCode, religionCode);
                                }
                                
                                if (result > 0) {
                                    System.out.println("WITHIN IF STATEMENT 4");
                                    String message = "The new Person has been created and assigned Person Ref " + result;
                                    String title = "Information";
                                    OKDialog.okDialog(CreatePerson.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    System.out.println("WITHIN IF STATEMENT 5");
                                    String message = "There is some errors with the information supplied to CREATE the new Person\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(CreatePerson.this, message, title);
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(CreatePerson.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.out.println("WITHIN IF STATEMENT 6");
                            String message = "There is some errors with the information supplied to CREATE the new Person\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreatePerson.this, message, title);
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
            
            this.setMinimumSize(new Dimension(1300, 700));
            this.setSize(1300, 700);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            
            JPanel buttonsPanel = new JPanel();
            
            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Create Person");
            
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
            
            JLabel titleLabel = new JLabel("Title  ");
            Font font = titleLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            titleLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(titleLabel, gc);
            
            titleField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(titleField, gc);
            
            JLabel forenameLabel = new JLabel("Forename  ");
            forenameLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(forenameLabel, gc);
            
            forenameField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(forenameField, gc);
            
            JLabel middleLabel = new JLabel("Middle Names  ");
            middleLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(middleLabel, gc);
            
            middleNameField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(middleNameField, gc);
            
            JLabel surnameLabel = new JLabel("Middle Names  ");
            surnameLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(surnameLabel, gc);
            
            surnameField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(surnameField, gc);
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel dobLabel = new JLabel("Date of Birth  ");
            dobLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(dobLabel, gc);
            
            dobField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(dobField, gc);
            
            JLabel genderLabel = new JLabel("Gender  ");
            genderLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(genderLabel, gc);
            
            genderField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(genderField, gc);
            
            JLabel niLabel = new JLabel("NI Number  ");
            niLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(niLabel, gc);
            
            niField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(niField, gc);
            
            JLabel maritalLabel = new JLabel("Marital Status  ");
            maritalLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(maritalLabel, gc);
            
            maritalField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(maritalField, gc);
            
            ////////////// NEXT ROW /////////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel languageLabel = new JLabel("Language  ");
            languageLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(languageLabel, gc);
            
            languageField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(languageField, gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            JLabel nationalityLabel = new JLabel("Nationality  ");
            nationalityLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(nationalityLabel, gc);
            
            nationalityField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(nationalityField, gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            JLabel ethnicLabel = new JLabel("Ethnic Origin  ");
            ethnicLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(ethnicLabel, gc);
            
            ethnicField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(ethnicField, gc);
            
            ////////////// NEXT ROW /////////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel religionLabel = new JLabel("Religion  ");
            religionLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(religionLabel, gc);
            
            religionField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(religionField, gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            JLabel sexualityLabel = new JLabel("Sexuality  ");
            sexualityLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(sexualityLabel, gc);
            
            sexualityField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(sexualityField, gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            JLabel addressLabel = new JLabel("Create Address?  ");
            addressLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addressLabel, gc);
            
            createAddress.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (createAddress.isSelected()) {
                        addressEnabled(true);
                    } else {
                        addressEnabled(false);
                    }
                }
            });
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(createAddress, gc);
            
            ////////////// NEXT ROW /////////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            ////////////// NEXT ROW /////////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel addrFieldLabel = new JLabel("Address  ");
            addrFieldLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrFieldLabel, gc);
            
            addrField.setFont(plainFont);
            Dimension dimension = addrField.getPreferredSize();
            dimension.setSize(dimension.getWidth() + 390, dimension.getHeight());
            addrField.setPreferredSize(dimension);
            addrField.setEnabled(false);
            addrField.setDisabledTextColor(Color.BLACK);
            
            gc.gridx++;
            gc.gridwidth = 4;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrField, gc);
            
            Image srchImge = null;
            JLabel addrThumb = new JLabel();
            
            try {
                srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(CreatePerson.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (srchImge != null) {
                ImageIcon icon = new ImageIcon(srchImge);
                addrThumb.setIcon(icon);
            }
            
            addrThumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (addrEnabled) {
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
                                    Logger.getLogger(CreatePerson.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                }
            });
            
            gc.gridx++;
            gc.gridwidth = 1;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(new JLabel(""), gc);
            
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
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(addrThumb, gc);
            
            JLabel addrStartLabel = new JLabel("Start Date  ");
            addrStartLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrStartLabel, gc);
            
            addrStartField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(addrStartField, gc);
            
            
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
        } catch (RemoteException ex) {
            Logger.getLogger(CreatePerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addressEnabled(boolean enable) {
        addrStartField.setEnabled(enable);
        addrEnabled = enable;
    }
    
    private void setAddrField(String text) {
        addrField.setText(text);
    }
}
