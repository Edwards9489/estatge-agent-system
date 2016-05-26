/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.office;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.OfficeInterface;
import java.awt.BorderLayout;
import java.awt.Color;
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
public class UpdateOffice extends JFrame {
    private ClientImpl client = null;
    private OfficeInterface office = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    private JTextField addrField;
    private JXDatePicker dateField;
    private JTextField longField;
    private JTextField latField;
    private JTextField codeField;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public UpdateOffice(ClientImpl client, OfficeInterface office) {
        setClient(client);
        setOffice(office);
        layoutComponents();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    // Use of singleton pattern to ensure only one Office is initiated
    private void setOffice(OfficeInterface office) {
        if (this.office == null) {
            this.office = office;
        }
    }
    
    private void layoutComponents() {
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            codeField = new JTextField(8);
            longField = new JTextField(8);
            latField = new JTextField(8);
            addrField = new JTextField(55);
            codeField.setText(office.getOfficeCode());
            longField.setText(String.valueOf(office.getAddrLong()));
            latField.setText(String.valueOf(office.getAddrLat()));
            addrField.setText(office.getAddress().printAddress());
            dateField = new JXDatePicker();
            dateField.setFormats(formatter);
            dateField.setDate(office.getStartDate());
            codeField.setEnabled(false);
            longField.setEnabled(false);
            latField.setEnabled(false);
            addrField.setEnabled(false);
            codeField.setDisabledTextColor(Color.BLACK);
            longField.setDisabledTextColor(Color.BLACK);
            latField.setDisabledTextColor(Color.BLACK);
            addrField.setDisabledTextColor(Color.BLACK);
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        int result = 0;
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Office with code " + office.getOfficeCode() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            Date date = dateField.getDate();
                            if (date != null) {
                                try {
                                    result = client.updateOffice(office.getOfficeCode(), date);
                                    
                                    if (result > 0) {
                                        String message = "The Office with code " + office.getOfficeCode() + " has been updated successfully";
                                        String title = "Information";
                                        OKDialog.okDialog(UpdateOffice.this, message, title);
                                        setVisible(false);
                                        dispose();
                                    } else {
                                        String message = "There is some errors with the information supplied to UPDATE the Office\nPlease check the information supplied";
                                        String title = "Error";
                                        OKDialog.okDialog(UpdateOffice.this, message, title);
                                    }
                                } catch (RemoteException ex) {
                                    Logger.getLogger(UpdateOffice.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(UpdateOffice.class.getName()).log(Level.SEVERE, null, ex);
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
            
            this.setSize(1100, 250);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            
            JPanel buttonsPanel = new JPanel();
            
            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Update Office");
            
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
            
            JLabel officeLabel = new JLabel("Office Code *    ");
            Font font = officeLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            officeLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(officeLabel, gc);
            
            codeField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(codeField, gc);
            
            JLabel startDate = new JLabel("Start Date    ");
            startDate.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(startDate, gc);
            
            dateField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(dateField, gc);

            JLabel longLabel = new JLabel("Address Long    ");
            longLabel.setFont(boldFont);


            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(longLabel, gc);

            longField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(longField, gc);

            JLabel latLabel = new JLabel("Address Lat    ");
            latLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(latLabel, gc);

            latField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(latField, gc);
            
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel address = new JLabel("Address *    ");
            address.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(address, gc);
            
            addrField.setFont(plainFont);
            
            gc.gridx++;
            gc.gridwidth = 7;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrField, gc);
            
            
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
            Logger.getLogger(UpdateOffice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
