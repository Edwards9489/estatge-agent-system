/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import client_application.ClientImpl;
import client_gui.StringArrayListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ApplicationSearchPanel extends JPanel {
    private ClientImpl client = null;
    private StringArrayListener listener = null;
    
    private JTextField corrNameField;
    private JXDatePicker startDateField;
    private JXDatePicker endDateField;
    private JComboBox statusField;
    private JTextField createdByField;
    private JXDatePicker createdDateField;
    private JButton searchButton;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public ApplicationSearchPanel(ClientImpl client) {
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
        corrNameField = new JTextField(10);
        startDateField = new JXDatePicker();
        startDateField.setFormats(dateFormatter);
        endDateField = new JXDatePicker();
        endDateField.setFormats(dateFormatter);
        statusField = new JComboBox();
        statusField.addItem("-");
        statusField.addItem("NEW");
        statusField.addItem("HSED");
        statusField.addItem("CLSD");
        createdByField = new JTextField(10);
        createdDateField = new JXDatePicker();
        createdDateField.setFormats(dateFormatter);
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String corrName = null;
                Date startDate = startDateField.getDate();
                Date endDate = endDateField.getDate();
                String statusCode = null;
                String createdBy = null;
                Date createdDate = createdDateField.getDate();
                
                if (!corrNameField.getText().isEmpty()) {
                    corrName = corrNameField.getText();
                }
                if (!statusField.getSelectedItem().equals("-")) {
                    statusCode = (String) statusField.getSelectedItem();
                }
                if (!createdByField.getText().isEmpty()) {
                    createdBy = createdByField.getText();
                }
                
                System.out.println("Corr Name: " + corrName);
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                System.out.println("Status: " + statusCode);
                System.out.println("Created By: " + createdBy);
                System.out.println("Created Date: " + createdDate);
                
                List<String> array = new ArrayList();
                array.add(corrName);
                if (startDate != null) {
                    array.add(dateFormatter.format(startDate));
                } else {
                    array.add(null);
                }
                if (endDate != null) {
                    array.add(dateFormatter.format(endDate));
                } else {
                    array.add(null);
                }
                array.add(statusCode);
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
        Border titleBorder = BorderFactory.createTitledBorder("Application Search Details");

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

        JLabel title = new JLabel("Corr Name    ");
        Font font = title.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        title.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(title, gc);
        
        corrNameField.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(corrNameField, gc);

        JLabel forename = new JLabel("Start Date    ");
        forename.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(forename, gc);

        startDateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(startDateField, gc);

        JLabel mNames = new JLabel("End Date    ");
        mNames.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(mNames, gc);

        endDateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(endDateField, gc);

        JLabel surname = new JLabel("Status    ");
        surname.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(surname, gc);

        statusField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(statusField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel dobLabel = new JLabel("Created By    ");
        dobLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(dobLabel, gc);
        
        createdByField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(createdByField, gc);

        JLabel ni = new JLabel("Created Date    ");
        ni.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(ni, gc);
        
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
