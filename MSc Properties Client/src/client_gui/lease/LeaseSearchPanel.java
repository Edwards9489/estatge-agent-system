/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.StringArrayListener;
import client_gui.property.PropertySearch;
import interfaces.OfficeInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class LeaseSearchPanel extends JPanel {
    private ClientImpl client = null;
    private StringArrayListener listener = null;
    
    private JTextField nameField;
    private JXDatePicker startDateField;
    private JXDatePicker expectedEndDateField;
    private JXDatePicker actualEndDateField;
    private JTextField lengthField;
    private JTextField propRefField;
    private JCheckBox managementField;
    private JTextField expenditureField;
    private JTextField accountRefField;
    private JComboBox officeField;
    private JTextField createdByField;
    private JXDatePicker createdDateField;
    private JButton searchButton;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public LeaseSearchPanel(ClientImpl client) {
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
            nameField = new JTextField(30);
            startDateField = new JXDatePicker();
            startDateField.setFormats(dateFormatter);
            expectedEndDateField = new JXDatePicker();
            expectedEndDateField.setFormats(dateFormatter);
            actualEndDateField = new JXDatePicker();
            actualEndDateField.setFormats(dateFormatter);
            lengthField = new JTextField(3);
            propRefField = new JTextField(3);
            managementField = new JCheckBox();
            expenditureField = new JTextField(3);
            accountRefField = new JTextField(3);
            officeField = new JComboBox();
            officeField.addItem("-");
            for (OfficeInterface temp : client.getOffices()) {
                officeField.addItem(temp.getOfficeCode());
            }
            createdByField = new JTextField(10);
            createdDateField = new JXDatePicker();
            createdDateField.setFormats(dateFormatter);
            
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    String name = null;
                    Date startDate = startDateField.getDate();
                    Date expectedEndDate = expectedEndDateField.getDate();
                    Date actualEndDate = actualEndDateField.getDate();
                    String length = null;
                    String propRef = null;
                    String expenditure = null;
                    Boolean management = managementField.isSelected();
                    String accountRef = null;
                    String officeCode = null;
                    String createdBy = null;
                    Date createdDate = createdDateField.getDate();
                    
                    if (!nameField.getText().isEmpty()) {
                        name = nameField.getText();
                    }
                    if (!lengthField.getText().isEmpty()) {
                        length = lengthField.getText();
                    }
                    if (!propRefField.getText().isEmpty()) {
                        propRef = propRefField.getText();
                    }
                    if (!expenditureField.getText().isEmpty()) {
                        expenditure = expenditureField.getText();
                    }
                    if (!officeField.getSelectedItem().equals("-")) {
                        officeCode = (String) officeField.getSelectedItem();
                    }
                    if (!accountRefField.getText().isEmpty()) {
                        accountRef = accountRefField.getText();
                    }
                    if (!createdByField.getText().isEmpty()) {
                        createdBy = createdByField.getText();
                    }
                    
                    System.out.println("Name: " + name);
                    System.out.println("Start Date: " + startDate);
                    System.out.println("Expected End Date: " + expectedEndDate);
                    System.out.println("Actual End Date: " + actualEndDate);
                    System.out.println("Length: " + length);
                    System.out.println("Prop Ref: " + propRef);
                    System.out.println("Management: " + management);
                    System.out.println("Expenditure: " + expenditure);
                    System.out.println("Account Ref: " + accountRef);
                    System.out.println("Office: " + officeCode);
                    System.out.println("Created By: " + createdBy);
                    System.out.println("Created Date: " + createdDate);
                    
                    List<String> array = new ArrayList();
                    array.add(name);
                    if (startDate != null) {
                        array.add(dateFormatter.format(startDate));
                    } else {
                        array.add(null);
                    }
                    if (expectedEndDate != null) {
                        array.add(dateFormatter.format(expectedEndDate));
                    } else {
                        array.add(null);
                    }
                    if (actualEndDate != null) {
                        array.add(dateFormatter.format(startDate));
                    } else {
                        array.add(null);
                    }
                    array.add(length);
                    array.add(propRef);
                    array.add(management.toString());
                    array.add(expenditure);
                    array.add(accountRef);
                    array.add(officeCode);
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
            Border titleBorder = BorderFactory.createTitledBorder("Lease Search Details");
            
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
            
            JLabel nameLabel = new JLabel("Agreement Name    ");
            Font font = nameLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            nameLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(nameLabel, gc);
            
            nameField.setFont(plainFont);
            
            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(nameField, gc);
            
            gc.gridx++;
            gc.gridwidth = 1;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(new JLabel(""), gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(new JLabel(""), gc);
            
            JLabel propRefLabel = new JLabel("Prop Ref    ");
            propRefLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(propRefLabel, gc);
            
            propRefField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(propRefField, gc);
            
            Image srchImge = null;
            JLabel propThumb = new JLabel();

            try {
                srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(LeaseSearchPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (srchImge != null) {
                ImageIcon icon = new ImageIcon(srchImge);
                propThumb.setIcon(icon);
            }

            propThumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    PropertySearch propSearch = new PropertySearch(client, new IntegerListener() {
                        @Override
                        public void intOmitted(int empRef) {
                            setPropField(empRef);
                        }
                    });
                    propSearch.setVisible(true);
                }
            });
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(propThumb, gc);
            
            JLabel appRefLabel = new JLabel("Expenditure    ");
            appRefLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(appRefLabel, gc);
            
            expenditureField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(expenditureField, gc);
            
            
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel title = new JLabel("Start Date    ");
            title.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(title, gc);
            
            startDateField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(startDateField, gc);
            
            JLabel forename = new JLabel("Expected End Date    ");
            forename.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(forename, gc);
            
            expectedEndDateField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(expectedEndDateField, gc);
            
            JLabel mNames = new JLabel("Actual End Date    ");
            mNames.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(mNames, gc);
            
            actualEndDateField.setFont(plainFont);
            
            gc.gridx++;
            gc.gridwidth = 2;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(actualEndDateField, gc);
            
            gc.gridx++;
            gc.gridwidth = 1;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(new JLabel(""), gc);
            
            JLabel subType = new JLabel("Length    ");
            subType.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(subType, gc);
            
            lengthField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(lengthField, gc);
            
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel surname = new JLabel("Full Management    ");
            surname.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(surname, gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(managementField, gc);
            
            JLabel officeLabel = new JLabel("Office    ");
            officeLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(officeLabel, gc);
            
            createdByField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(officeField, gc);
            
            JLabel dobLabel = new JLabel("Created By    ");
            dobLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(dobLabel, gc);
            
            createdByField.setFont(plainFont);
            
            gc.gridx++;
            gc.gridwidth = 2;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(createdByField, gc);
            
            gc.gridx++;
            gc.gridwidth = 1;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(new JLabel(""), gc);
            
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
            
            
            ////////// SEARCH BUTTONS PANEL //////////
            
            JPanel searchButtonPanel = new JPanel();
            searchButtonPanel.setBorder(BorderFactory.createEmptyBorder());
            
            searchButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            searchButtonPanel.add(searchButton);
            
            
            ///////// ADD COMPONENTS TO PANEL ////////
            
            add(detailsPanel, BorderLayout.CENTER);
            add(searchButtonPanel, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseSearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPropField(int propRef) {
        if (propRefField != null) {
            propRefField.setText(String.valueOf(propRef));
        }
    }
}
