/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Element;
import interfaces.PersonInterface;
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
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Dwayne
 */
public class UpdatePerson extends JFrame {
    private ClientImpl client = null;
    private PersonInterface person = null;
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
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public UpdatePerson(ClientImpl client, PersonInterface person) {
        setClient(client);
        setPerson(person);
        layoutComponents();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    // Use of singleton pattern to ensure only one Person is initiated
    private void setPerson(PersonInterface person) {
        if (this.person == null) {
            this.person = person;
        }
    }
    
    private void layoutComponents() {
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            
            titleField = new JComboBox();
            titleField.addItem("  ---  ");
            for (Element temp : client.getCurrentTitles()) {
                titleField.addItem(temp.getCode());
            }
            titleField.setSelectedItem(person.getTitle().getCode());
            forenameField = new JTextField(15);
            forenameField.setText(person.getForename());
            middleNameField = new JTextField(15);
            middleNameField.setText(person.getMiddleNames());
            surnameField = new JTextField(15);
            surnameField.setText(person.getSurname());
            dobField = new JXDatePicker();
            dobField.setFormats(formatter);
            dobField.setDate(person.getDateOfBirth());
            genderField = new JComboBox();
            genderField.addItem("  ---  ");
            for (Element temp : client.getCurrentGenders()) {
                genderField.addItem(temp.getCode());
            }
            genderField.setSelectedItem(person.getGender().getCode());
            niField = new JTextField(10);
            niField.setText(person.getNI());
            maritalField = new JComboBox();
            maritalField.addItem("  ---  ");
            for (Element temp : client.getCurrentMaritalStatuses()) {
                maritalField.addItem(temp.getCode());
            }
            maritalField.setSelectedItem(person.getMaritalStatus().getCode());
            languageField = new JComboBox();
            languageField.addItem("  ---  ");
            for (Element temp : client.getCurrentLanguages()) {
                languageField.addItem(temp.getCode());
            }
            languageField.setSelectedItem(person.getLanguage().getCode());
            nationalityField = new JComboBox();
            nationalityField.addItem("  ---  ");
            for (Element temp : client.getCurrentNationalities()) {
                nationalityField.addItem(temp.getCode());
            }
            nationalityField.setSelectedItem(person.getNationality().getCode());
            ethnicField = new JComboBox();
            ethnicField.addItem("  ---  ");
            for (Element temp : client.getCurrentEthnicOrigins()) {
                ethnicField.addItem(temp.getCode());
            }
            ethnicField.setSelectedItem(person.getEthnicOrigin().getCode());
            religionField = new JComboBox();
            religionField.addItem("  ---  ");
            for (Element temp : client.getCurrentReligions()) {
                religionField.addItem(temp.getCode());
            }
            religionField.setSelectedItem(person.getReligion().getCode());
            sexualityField = new JComboBox();
            sexualityField.addItem("  ---  ");
            for (Element temp : client.getCurrentSexualities()) {
                sexualityField.addItem(temp.getCode());
            }
            sexualityField.setSelectedItem(person.getSexuality().getCode());
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        int result = 0;
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Person " + person.getPersonRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
                            System.out.println("1: " + !titleCode.equals("  ---  "));
                            System.out.println("2: " + forename != null);
                            System.out.println("3: " + !forename.isEmpty());
                            System.out.println("4: " + middleName != null);
                            System.out.println("5: " + !middleName.isEmpty());
                            System.out.println("6: " + surname != null);
                            System.out.println("7: " + !surname.isEmpty());
                            System.out.println("8: " + dob != null);
                            System.out.println("9: " + niNumber != null);
                            System.out.println("10: " + !niNumber.isEmpty());
                            System.out.println("11: " + !genderCode.equals("  ---  "));
                            System.out.println("12: " + !maritalCode.equals("  ---  "));
                            System.out.println("13: " + !languageCode.equals("  ---  "));
                            System.out.println("14: " + !ethnicCode.equals("  ---  "));
                            System.out.println("15: " + !religionCode.equals("  ---  "));
                            System.out.println("16: " + !sexualityCode.equals("  ---  "));
                            
                            if (!titleCode.equals("  ---  ") && forename != null && !forename.isEmpty() && surname != null && !surname.isEmpty() && dob != null && niNumber != null && !niNumber.isEmpty() && !genderCode.equals("  ---  ") && !maritalCode.equals("  ---  ") && !languageCode.equals("  ---  ") && !ethnicCode.equals("  ---  ") && !religionCode.equals("  ---  ") && !sexualityCode.equals("  ---  ")) {
                                try {
                                    result = client.updatePerson(person.getPersonRef(), titleCode, forename, middleName, surname, dob, nationalityCode, genderCode, maritalCode, ethnicCode, languageCode, nationalityCode, sexualityCode, religionCode);
                                    
                                    if (result > 0) {
                                        String message = "Person " + person.getPersonRef() + " has been successfully updated";
                                        String title = "Information";
                                        OKDialog.okDialog(UpdatePerson.this, message, title);
                                        setVisible(false);
                                        dispose();
                                    } else {
                                        String message = "There is some errors with the information supplied to UPDATE the Person\nPlease check the information supplied";
                                        String title = "Error";
                                        OKDialog.okDialog(UpdatePerson.this, message, title);
                                    }
                                } catch (RemoteException ex) {
                                    Logger.getLogger(UpdatePerson.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                String message = "There is some errors with the information supplied to UPDATE the Person\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(UpdatePerson.this, message, title);
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(UpdatePerson.class.getName()).log(Level.SEVERE, null, ex);
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
            
            this.setMinimumSize(new Dimension(1300, 400));
            this.setSize(1000, 400);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            
            JPanel buttonsPanel = new JPanel();
            
            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Update Person");
            
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
            Logger.getLogger(UpdatePerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
