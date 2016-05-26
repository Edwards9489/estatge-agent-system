/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.property;

import client_application.ClientImpl;
import client_gui.StringArrayListener;
import interfaces.Element;
import java.awt.BorderLayout;
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
public class PropertySearchPanel extends JPanel {
    private ClientImpl client = null;
    private StringArrayListener listener = null;
    
    private JXDatePicker acquiredDateField;
    private JXDatePicker leaseEndDateField;
    private JComboBox propTypeField;
    private JComboBox propSubTypeField;
    private JComboBox statusField;
    private JTextField createdByField;
    private JXDatePicker createdDateField;
    private JButton searchButton;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public PropertySearchPanel(ClientImpl client) {
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
        
        try {
            searchButton = new JButton("Search");
            acquiredDateField = new JXDatePicker();
            acquiredDateField.setFormats(dateFormatter);
            leaseEndDateField = new JXDatePicker();
            leaseEndDateField.setFormats(dateFormatter);
            propTypeField = new JComboBox();
            propTypeField.addItem("  ---  ");
            for (Element temp : client.getPropertyTypes()) {
                propTypeField.addItem(temp.getCode());
            }
            propSubTypeField = new JComboBox();
            propSubTypeField.addItem("  ---  ");
            for (Element temp : client.getPropertySubTypes()) {
                propSubTypeField.addItem(temp.getCode());
            }
            statusField = new JComboBox();
            statusField.addItem("  ---  ");
            statusField.addItem("NEW");
            statusField.addItem("VOID");
            statusField.addItem("OCCP");
            statusField.addItem("CLSD");
            createdByField = new JTextField(10);
            createdDateField = new JXDatePicker();
            createdDateField.setFormats(dateFormatter);
            
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    Date acquiredDate = acquiredDateField.getDate();
                    Date leaseEndDate = leaseEndDateField.getDate();
                    String propType = null;
                    String propSubType = null;
                    String statusCode = null;
                    String createdBy = null;
                    Date createdDate = createdDateField.getDate();
                    
                    if (!propTypeField.getSelectedItem().equals("  ---  ")) {
                        propType = (String) propTypeField.getSelectedItem();
                    }
                    if (!propSubTypeField.getSelectedItem().equals("  ---  ")) {
                        propSubType = (String) propSubTypeField.getSelectedItem();
                    }
                    if (!statusField.getSelectedItem().equals("  ---  ")) {
                        statusCode = (String) statusField.getSelectedItem();
                    }
                    if (!createdByField.getText().isEmpty()) {
                        createdBy = createdByField.getText();
                    }
                    
                    System.out.println("Acquired Date: " + acquiredDate);
                    System.out.println("Lease End Date: " + leaseEndDate);
                    System.out.println("Prop Type: " + propType);
                    System.out.println("Prop Sub Type: " + propSubType);
                    System.out.println("Status: " + statusCode);
                    System.out.println("Created By: " + createdBy);
                    System.out.println("Created Date: " + createdDate);
                    
                    List<String> array = new ArrayList();
                    if (acquiredDate != null) {
                        array.add(dateFormatter.format(acquiredDate));
                    } else {
                        array.add(null);
                    }
                    if (leaseEndDate != null) {
                        array.add(dateFormatter.format(leaseEndDate));
                    } else {
                        array.add(null);
                    }
                    array.add(propType);
                    array.add(propSubType);
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
            Border titleBorder = BorderFactory.createTitledBorder("Property Search Details");
            
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
            
            JLabel title = new JLabel("Acquired Date    ");
            Font font = title.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            title.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(title, gc);
            
            acquiredDateField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(acquiredDateField, gc);
            
            JLabel forename = new JLabel("Lease End Date    ");
            forename.setFont(boldFont);
            
            gc.gridx++;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(forename, gc);
            
            leaseEndDateField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(leaseEndDateField, gc);
            
            JLabel mNames = new JLabel("Prop Type    ");
            mNames.setFont(boldFont);
            
            gc.gridx++;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(mNames, gc);
            
            propTypeField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(propTypeField, gc);
            
            JLabel subType = new JLabel("Prop Sub Type    ");
            subType.setFont(boldFont);
            
            gc.gridx++;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(subType, gc);
            
            propSubTypeField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(propSubTypeField, gc);
            
            
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel surname = new JLabel("Status    ");
            surname.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(surname, gc);
            
            statusField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(statusField, gc);
            
            JLabel dobLabel = new JLabel("Created By    ");
            dobLabel.setFont(boldFont);
            
            gc.gridx++;
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
        } catch (RemoteException ex) {
            Logger.getLogger(PropertySearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
