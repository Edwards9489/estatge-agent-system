/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.property;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.address.AddressSearch;
import interfaces.AddressInterface;
import interfaces.Element;
import interfaces.PropertyInterface;
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
public class UpdateProperty extends JFrame {
    private ClientImpl client = null;
    private PropertyInterface property = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    
    private JXDatePicker startField;
    private JComboBox propTypeField;
    private JComboBox propSubTypeField;
    
    private JXDatePicker addrStartField;
    private JTextField addrField;
    private int addrRef = -1;
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public UpdateProperty(ClientImpl client, PropertyInterface property) {
        setClient(client);
        setProperty(property);
        layoutComponents();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setProperty(PropertyInterface property) {
        if (this.property == null) {
            this.property = property;
        }
    }
    
    private void layoutComponents() {
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            
            startField = new JXDatePicker();
            startField.setFormats(formatter);
            startField.setDate(property.getAcquiredDate());
            propTypeField = new JComboBox();
            propTypeField.addItem("  ---  ");
            for (Element temp : client.getCurrentPropertyTypes()) {
                propTypeField.addItem(temp.getCode());
            }
            propTypeField.setSelectedItem(property.getPropType().getCode());
            propSubTypeField = new JComboBox();
            propSubTypeField.addItem("  ---  ");
            for (Element temp : client.getCurrentPropertySubTypes()) {
                propSubTypeField.addItem(temp.getCode());
            }
            propSubTypeField.setSelectedItem(property.getPropSubType().getCode());
            addrField = new JTextField(40);
            
            updateAddress(property.getAddress().getAddressRef());
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        int result = 0;
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Property " + property.getPropRef()+ "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            Date startDate = startField.getDate();
                            String propTypeCode = (String) propTypeField.getSelectedItem();
                            String propSubTypeCode = (String) propSubTypeField.getSelectedItem();
                            
                            
                            if (startDate != null && !propTypeCode.equals("  ---  ") && !propSubTypeCode.equals("  ---  ") && addrRef > 0) {
                                try {
                                    
                                    result = client.updateProperty(property.getPropRef(), addrRef, startDate, propTypeCode, propSubTypeCode);
                                    
                                    if (result > 0) {
                                        String message = "Property " + property.getPropRef()+ " has been updated successfully";
                                        String title = "Information";
                                        OKDialog.okDialog(UpdateProperty.this, message, title);
                                        setVisible(false);
                                        dispose();
                                    } else {
                                        String message = "There is some errors with the information supplied to UPDATE the Property\nPlease check the information supplied";
                                        String title = "Error";
                                        OKDialog.okDialog(UpdateProperty.this, message, title);
                                    }
                                } catch (RemoteException ex) {
                                    Logger.getLogger(UpdateProperty.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                String message = "There is some errors with the information supplied to UPDATE the Property\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(UpdateProperty.this, message, title);
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(UpdateProperty.class.getName()).log(Level.SEVERE, null, ex);
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
            
            this.setMinimumSize(new Dimension(800, 300));
            this.setSize(800, 300);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            
            JPanel buttonsPanel = new JPanel();
            
            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Update Property");
            
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
            
            JLabel startDateLabel = new JLabel("Acquired Date  ");
            Font font = startDateLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            startDateLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(startDateLabel, gc);
            
            startField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(startField, gc);
            
            JLabel propTypeLabel = new JLabel("Property Type  ");
            propTypeLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(propTypeLabel, gc);
            
            propTypeField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(propTypeField, gc);
            
            JLabel propSubTypeLabel = new JLabel("Property Sub Type  ");
            propSubTypeLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(propSubTypeLabel, gc);
            
            propSubTypeField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(propSubTypeField, gc);
            
            ////////////// NEXT ROW /////////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel addrFieldLabel = new JLabel("Address  ");
            addrFieldLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrFieldLabel, gc);
            
            addrField.setFont(plainFont);
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
                Logger.getLogger(UpdateProperty.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (srchImge != null) {
                ImageIcon icon = new ImageIcon(srchImge);
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
                            updateAddress(addressRef);
                        }
                    });
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
            Logger.getLogger(UpdateProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateAddress(int addressRef) {
        try {
            if (client.addressExists(addressRef)) {
                AddressInterface address = client.getAddress(addressRef);
                if (address != null) {
                    setAddrField(address.printAddress());
                    addrRef = address.getAddressRef();
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CreateProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setAddrField(String text) {
        addrField.setText(text);
    }
}
