/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.empAccount;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.StringArrayListener;
import client_gui.contract.ContractSearch;
import client_gui.rentAcc.RentAccSearchPanel;
import client_gui.tenancy.TenSearch;
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
public class EmpAccSearchPanel extends JPanel {
    private ClientImpl client = null;
    private StringArrayListener listener = null;
    
    private JTextField nameField;
    private JXDatePicker startDateField;
    private JXDatePicker endDateField;
    private JTextField balanceField;
    private JTextField salaryField;
    private JTextField agreementRefField;
    private JComboBox officeField;
    private JTextField createdByField;
    private JXDatePicker createdDateField;
    private JButton searchButton;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public EmpAccSearchPanel(ClientImpl client) {
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
            nameField = new JTextField(35);
            startDateField = new JXDatePicker();
            startDateField.setFormats(dateFormatter);
            endDateField = new JXDatePicker();
            endDateField.setFormats(dateFormatter);
            balanceField = new JTextField(6);
            salaryField = new JTextField(6);
            agreementRefField = new JTextField(3);
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
                    Date endDate = endDateField.getDate();
                    String balance = null;
                    String salary = null;
                    String agreementRef = null;
                    String officeCode = null;
                    String createdBy = null;
                    Date createdDate = createdDateField.getDate();
                    
                    if (!nameField.getText().isEmpty()) {
                        name = nameField.getText();
                    }
                    if (!balanceField.getText().isEmpty()) {
                        balance = balanceField.getText();
                    }
                    if (!salaryField.getText().isEmpty()) {
                        salary = salaryField.getText();
                    }
                    if (!officeField.getSelectedItem().equals("-")) {
                        officeCode = (String) officeField.getSelectedItem();
                    }
                    if (!agreementRefField.getText().isEmpty()) {
                        agreementRef = agreementRefField.getText();
                    }
                    if (!createdByField.getText().isEmpty()) {
                        createdBy = createdByField.getText();
                    }
                    
                    System.out.println("Name: " + name);
                    System.out.println("Start Date: " + startDate);
                    System.out.println("End Date: " + endDate);
                    System.out.println("Balance: £" + balance);
                    System.out.println("Salary: £" + salary);
                    System.out.println("Agreement Ref: " + agreementRef);
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
                    if (endDate != null) {
                        array.add(dateFormatter.format(endDate));
                    } else {
                        array.add(null);
                    }
                    array.add(balance);
                    array.add(salary);
                    array.add(agreementRef);
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
            Border titleBorder = BorderFactory.createTitledBorder("Employee Account Search Details");
            
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
            
            JLabel nameLabel = new JLabel("Account Name    ");
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
            
            JLabel title = new JLabel("Start Date    ");
            title.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(title, gc);
            
            startDateField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(startDateField, gc);
            
            JLabel forename = new JLabel("End Date    ");
            forename.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(forename, gc);
            
            endDateField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(endDateField, gc);
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel contractRef = new JLabel("Contract Ref    ");
            contractRef.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(contractRef, gc);
            
            agreementRefField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(agreementRefField, gc);

            Image srchImge = null;
            JLabel contractThumb = new JLabel();

            try {
                srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(RentAccSearchPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (srchImge != null) {
                ImageIcon icon = new ImageIcon(srchImge);
                contractThumb.setIcon(icon);
            }

            contractThumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ContractSearch contractSearch = new ContractSearch(client, new IntegerListener() {
                        @Override
                        public void intOmitted(int tenRef) {
                            setContractRefField(tenRef);
                        }
                    });
                    contractSearch.setVisible(true);
                }
            });

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(contractThumb, gc);
            
            JLabel subType = new JLabel("Balance    ");
            subType.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(subType, gc);
            
            balanceField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(balanceField, gc);
            
            JLabel appRefLabel = new JLabel("Salary    ");
            appRefLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(appRefLabel, gc);
            
            salaryField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(salaryField, gc);
            
            JLabel officeLabel = new JLabel("Office    ");
            officeLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(officeLabel, gc);
            
            officeField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(officeField, gc);
            
            
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
            gc.gridwidth = 2;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(createdByField, gc);
            
            JLabel ni = new JLabel("Created Date    ");
            ni.setFont(boldFont);
            
            gc.gridx++;
            gc.gridwidth = 1;
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
            Logger.getLogger(EmpAccSearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setContractRefField(int tenRef) {
        if (agreementRefField != null) {
            agreementRefField.setText(String.valueOf(tenRef));
        }
    }
}
