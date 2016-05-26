/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.contract;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.StringArrayListener;
import client_gui.employee.EmployeeSearch;
import interfaces.JobRoleInterface;
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
public class ContractSearchPanel extends JPanel {
    private ClientImpl client = null;
    private StringArrayListener listener = null;
    
    private JTextField nameField;
    private JXDatePicker startDateField;
    private JXDatePicker expectedEndDateField;
    private JXDatePicker actualEndDateField;
    private JTextField lengthField;
    private JTextField empRefField;
    private JComboBox jobRoleField;
    private JTextField accountRefField;
    private JComboBox officeField;
    private JTextField createdByField;
    private JXDatePicker createdDateField;
    private JButton searchButton;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public ContractSearchPanel(ClientImpl client) {
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
            nameField = new JTextField(45);
            startDateField = new JXDatePicker();
            startDateField.setFormats(dateFormatter);
            expectedEndDateField = new JXDatePicker();
            expectedEndDateField.setFormats(dateFormatter);
            actualEndDateField = new JXDatePicker();
            actualEndDateField.setFormats(dateFormatter);
            lengthField = new JTextField(3);
            empRefField = new JTextField(3);
            jobRoleField = new JComboBox();
            jobRoleField.addItem("  ---  ");
            for (JobRoleInterface temp : client.getJobRoles()) {
                jobRoleField.addItem(temp.getJobRoleCode());
            }
            accountRefField = new JTextField(3);
            officeField = new JComboBox();
            officeField.addItem("  ---  ");
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
                    String empRef = null;
                    String jobRoleCode = null;
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
                    if (!empRefField.getText().isEmpty()) {
                        empRef = empRefField.getText();
                    }
                    if (!jobRoleField.getSelectedItem().equals("  ---  ")) {
                        jobRoleCode = (String) jobRoleField.getSelectedItem();
                    }
                    if (!officeField.getSelectedItem().equals("  ---  ")) {
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
                    System.out.println("Employee Ref: " + empRef);
                    System.out.println("Job Role Code: " + jobRoleCode);
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
                    array.add(empRef);
                    array.add(jobRoleCode);
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
            Border titleBorder = BorderFactory.createTitledBorder("Contract Search Details");
            
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
            gc.gridwidth = 4;
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
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(new JLabel(""), gc);
            
            JLabel surname = new JLabel("Job Role Code    ");
            surname.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(surname, gc);
            
            jobRoleField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(jobRoleField, gc);
            
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
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(actualEndDateField, gc);
            
            JLabel appRefLabel = new JLabel("Employee Ref    ");
            appRefLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(appRefLabel, gc);
            
            empRefField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(empRefField, gc);

            Image srchImge = null;
            JLabel empThumb = new JLabel();

            try {
                srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(CreateContract.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (srchImge != null) {
                ImageIcon icon = new ImageIcon(srchImge);
                empThumb.setIcon(icon);
            }

            empThumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    EmployeeSearch empSearch = new EmployeeSearch(client);
                    empSearch.setVisible(true);
                    empSearch.setListener(new IntegerListener() {
                        @Override
                        public void intOmitted(int empRef) {
                            setEmpField(empRef);
                        }
                    });
                }
            });

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(empThumb, gc);
            
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel officeLabel = new JLabel("Office    ");
            officeLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
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
            
            
            ////////// SEARCH BUTTONS PANEL //////////
            
            JPanel searchButtonPanel = new JPanel();
            searchButtonPanel.setBorder(BorderFactory.createEmptyBorder());
            
            searchButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            searchButtonPanel.add(searchButton);
            
            
            ///////// ADD COMPONENTS TO PANEL ////////
            
            add(detailsPanel, BorderLayout.CENTER);
            add(searchButtonPanel, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(ContractSearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEmpField(int empRef) {
        if (empRefField != null) {
            empRefField.setText(String.valueOf(empRef));
        }
    }
}
