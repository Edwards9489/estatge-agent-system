/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

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
public class PeopleSearchPanel extends JPanel {
    private ClientImpl client = null;
    private StringArrayListener listener = null;
    
    private JComboBox titleField;
    private JTextField forenameField;
    private JTextField middleNameField;
    private JTextField surnameField;
    private JXDatePicker dateField;
    private JTextField niField;
    private JComboBox genderField;
    private JComboBox maritalStatusField;
    private JComboBox ethnicOriginField;
    private JComboBox languageField;
    private JComboBox nationalityField;
    private JComboBox sexualityField;
    private JComboBox religionField;
    private JTextField createdByField;
    private JXDatePicker createdDateField;
    private JButton searchButton;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public PeopleSearchPanel(ClientImpl client) {
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
        titleField = new JComboBox();
        forenameField = new JTextField(10);
        middleNameField = new JTextField(10);
        surnameField = new JTextField(10);
        dateField = new JXDatePicker();
        dateField.setFormats(dateFormatter);
        niField = new JTextField(13);
        genderField = new JComboBox();
        maritalStatusField = new JComboBox();
        ethnicOriginField = new JComboBox();
        languageField = new JComboBox();
        nationalityField = new JComboBox();
        sexualityField = new JComboBox();
        religionField = new JComboBox();
        createdByField = new JTextField(10);
        createdDateField = new JXDatePicker();
        createdDateField.setFormats(dateFormatter);
        titleField.addItem("-");
        genderField.addItem("-");
        maritalStatusField.addItem("-");
        ethnicOriginField.addItem("-");
        languageField.addItem("-");
        nationalityField.addItem("-");
        sexualityField.addItem("-");
        religionField.addItem("-");

        try {
            for (Element temp : client.getTitles()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    titleField.addItem(code);
                }
            }
            
            for (Element temp : client.getGenders()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    genderField.addItem(code);
                }
            }

            for (Element temp : client.getMaritalStatuses()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    maritalStatusField.addItem(code);
                }
            }

            for (Element temp : client.getEthnicOrigins()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    ethnicOriginField.addItem(code);
                }
            }

            for (Element temp : client.getLanguages()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    languageField.addItem(code);
                }
            }

            for (Element temp : client.getNationalities()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    nationalityField.addItem(code);
                }
            }

            for (Element temp : client.getSexualities()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    sexualityField.addItem(code);
                }
            }

            for (Element temp : client.getReligions()) {
                if (temp.isCurrent()) {
                    String code = temp.getCode();
                    religionField.addItem(code);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(PeopleSearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String titleCode = null;
                String forename = null;
                String middleName = null;
                String surname = null;
                String niNumber = null;
                String genderCode = null;
                String maritalStatusCode = null;
                String ethnicOriginCode = null;
                String languageCode = null;
                String nationalityCode = null;
                String sexualityCode = null;
                String religionCode = null;
                String createdBy = null;
                
                if (!titleField.getSelectedItem().equals("-")) {
                    titleCode = (String) titleField.getSelectedItem();
                }
                if (!forenameField.getText().isEmpty()) {
                    forename = forenameField.getText();
                }
                if (!middleNameField.getText().isEmpty()) {
                    middleName = middleNameField.getText();
                }
                if (!surnameField.getText().isEmpty()) {
                    surname = surnameField.getText();
                }
                
                Date dob = dateField.getDate();
                
                if (!niField.getText().isEmpty()) {
                    niNumber = niField.getText();
                }
                if (!genderField.getSelectedItem().equals("-")) {
                    genderCode = (String) genderField.getSelectedItem();
                }
                if (!maritalStatusField.getSelectedItem().equals("-")) {
                    maritalStatusCode = (String) maritalStatusField.getSelectedItem();
                }
                if (!ethnicOriginField.getSelectedItem().equals("-")) {
                    ethnicOriginCode = (String) ethnicOriginField.getSelectedItem();
                }
                if (!languageField.getSelectedItem().equals("-")) {
                    languageCode = (String) languageField.getSelectedItem();
                }
                if (!nationalityField.getSelectedItem().equals("-")) {
                    nationalityCode = (String) nationalityField.getSelectedItem();
                }
                if (!sexualityField.getSelectedItem().equals("-")) {
                    sexualityCode = (String) sexualityField.getSelectedItem();
                }
                if (!religionField.getSelectedItem().equals("-")) {
                    religionCode = (String) religionField.getSelectedItem();
                }
                if (!createdByField.getText().isEmpty()) {
                    createdBy = createdByField.getText();
                }
                
                Date createdDate = createdDateField.getDate();
                
                System.out.println("Title: " + titleCode);
                System.out.println("Forename: " + forename);
                System.out.println("Middle Name: " + middleName);
                System.out.println("Surname: " + surname);
                System.out.println("Date of Birth: " + dob);
                System.out.println("NI number: " + niNumber);
                System.out.println("Gender: " + genderCode);
                System.out.println("Marital Status: " + maritalStatusCode);
                System.out.println("Ethnic Origin: " + ethnicOriginCode);
                System.out.println("Language: " + languageCode);
                System.out.println("Nationality: " + nationalityCode);
                System.out.println("Sexuality: " + sexualityCode);
                System.out.println("Religion: " + religionCode);
                System.out.println("Created By: " + createdBy);
                System.out.println("Created Date: " + createdDate);
                
                List<String> array = new ArrayList();
                array.add(titleCode);
                array.add(forename);
                array.add(middleName);
                array.add(surname);
                if (dob != null) {
                    array.add(dateFormatter.format(dob));
                } else {
                    array.add(null);
                }
                array.add(niNumber);
                array.add(genderCode);
                array.add(maritalStatusCode);
                array.add(ethnicOriginCode);
                array.add(languageCode);
                array.add(nationalityCode);
                array.add(sexualityCode);
                array.add(religionCode);
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
        Border titleBorder = BorderFactory.createTitledBorder("Search");

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

        JLabel title = new JLabel("Title    ");
        Font font = title.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        title.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(title, gc);

        titleField.setFont(plainFont);
        Dimension dimension = titleField.getPreferredSize();
        dimension.setSize(dimension.getWidth() + 80, dimension.getHeight());
        titleField.setPreferredSize(dimension);
        
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(titleField, gc);

        JLabel forename = new JLabel("Forename    ");
        forename.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(forename, gc);

        forenameField.setFont(plainFont);
        forenameField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(forenameField, gc);

        JLabel mNames = new JLabel("Middle Names    ");
        mNames.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(mNames, gc);

        middleNameField.setFont(plainFont);
        middleNameField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(middleNameField, gc);

        JLabel surname = new JLabel("Surname    ");
        surname.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(surname, gc);

        surnameField.setFont(plainFont);
        surnameField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(surnameField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel dobLabel = new JLabel("Date of Birth    ");
        dobLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(dobLabel, gc);
        
        dateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(dateField, gc);

        JLabel ni = new JLabel("NI number    ");
        ni.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(ni, gc);
        
        niField.setFont(plainFont);
        niField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(niField, gc);
        
        JLabel gender = new JLabel("Gender    ");
        gender.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(gender, gc);
        
        genderField.setFont(plainFont);
        genderField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(genderField, gc);

        JLabel maritalStatus = new JLabel("Marital Status    ");
        maritalStatus.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(maritalStatus, gc);
        
        maritalStatusField.setFont(plainFont);
        maritalStatusField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(maritalStatusField, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel ethnicOrigin = new JLabel("Ethnic Origin    ");
        ethnicOrigin.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(ethnicOrigin, gc);
        
        ethnicOriginField.setFont(plainFont);
        ethnicOriginField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ethnicOriginField, gc);

        JLabel language = new JLabel("Language    ");
        language.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(language, gc);
        
        languageField.setFont(plainFont);
        languageField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(languageField, gc);
        
        JLabel nationality = new JLabel("Nationality    ");
        nationality.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(nationality, gc);
        
        nationalityField.setFont(plainFont);
        nationalityField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(nationalityField, gc);

        JLabel sexuality = new JLabel("Sexuality    ");
        sexuality.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(sexuality, gc);
        
        sexualityField.setFont(plainFont);
        sexualityField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(sexualityField, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel religion = new JLabel("Religion    ");
        religion.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(religion, gc);
        
        religionField.setFont(plainFont);
        religionField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(religionField, gc);

        JLabel createdByLabel = new JLabel("Created By    ");
        createdByLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(createdByLabel, gc);
        
        createdByField.setFont(plainFont);
        createdByField.setPreferredSize(dimension);

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

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(createdDateField, gc);
        
        
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
