/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import client_application.ClientImpl;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleBenefitInterface;
import interfaces.PropertyElementInterface;
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
import javax.swing.border.Border;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Dwayne
 */
public class EndObject extends JFrame {

    ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JXDatePicker endDateField;
    private JComboBox endReasonField;
    private SimpleDateFormat formatter;
    private final String endType;
    private final int objectRef;
    private String code;
    private int ref;
    
    private JPanel controlsPanel;

    public EndObject(ClientImpl client, String endType, int objectRef) {
        super("MSc Properties");
        setClient(client);
        this.endType = endType;
        this.objectRef = objectRef;
        layoutComponents();
    }
    
    public EndObject(ClientImpl client, String endType, int objectRef, int ref) {
        super("MSc Properties");
        setClient(client);
        this.endType = endType;
        this.objectRef = objectRef;
        this.ref = ref;
        layoutComponents();
    }
    
    public EndObject(ClientImpl client, String endType, int objectRef, String code) {
        super("MSc Properties");
        setClient(client);
        this.endType = endType;
        this.objectRef = objectRef;
        this.code = code;
        layoutComponents();
    }

    private void setClient(ClientImpl client) {
        if (this.client == null) {
            this.client = client;
        }
    }

    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        endDateField = new JXDatePicker();
        endDateField.setFormats(formatter);
        endReasonField = new JComboBox();

        endReasonField.addItem("-");
        endReasonField.setEnabled(endType.equals("Involved Party"));

        try {
            for (Element temp : client.getEndReasons()) {
                if (temp.isCurrent()) {
                    String endReasonCode = temp.getCode();
                    endReasonField.addItem(endReasonCode);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(EndObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                Date endDate = endDateField.getDate();
                String endReasonText = (String) endReasonField.getSelectedItem();

                System.out.println("End Date: " + endDateField.getDate());
                System.out.println("End Reason: " + endReasonText);

                int result = -1;
                try {
                    if (endDate != null && (!endReasonText.equals("-") || !endType.equals("Involved Party"))) {
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to END " + endType + " " + objectRef + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            switch (endType) {
                                case "Involved Party":
                                    InvolvedPartyInterface invParty = client.getInvolvedParty(objectRef);
                                    result = client.endInvolvedParty(invParty.getApplicationRef(), objectRef, endDate, endReasonText);
                                    break;
                                    
                                case "Job Role Benefit":
                                    JobRoleBenefitInterface benefit = client.getJobRoleBenefit(objectRef);
                                    result = client.endJobRoleBenefit(objectRef, benefit.getJobRoleCode(), endDate);
                                    break;
                                    
                                case "Property Element":
                                    PropertyElementInterface propElement = client.getPropertyElement(objectRef);
                                    result = client.endPropertyElement(objectRef, propElement.getPropRef(), endDate);
                                    break;
                                    
                                case "Tenancy":
                                    result = client.endTenancy(objectRef, endDate);
                                    break;
                                    
                                case "Lease":
                                    result = client.endLease(objectRef, endDate);
                                    break;
                                    
                                case "Contract":
                                    result = client.endContract(objectRef, endDate);
                                    break;
                                    
                                case "Person Contact":
                                    result = client.endPersonContact(objectRef, ref, endDate);
                                    break;
                                    
                                case "Office Contact":
                                    result = client.endOfficeContact(code, objectRef, endDate);
                                    break;
                                    
                                case "Person Address":
                                    result = client.endPersonAddressUsage(objectRef, ref, endDate);
                                    break;
                                    
                                case "Application Address":
                                    result = client.endApplicationAddressUsage(objectRef, ref, endDate);
                                    break;
                            }
                            if (result > 0) {
                                String message = endType + " has been ended successfully";
                                String title = "Information";
                                OKDialog.okDialog(EndObject.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to END " + endType + " " + objectRef + "\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(EndObject.this, message, title);
                            }
                        }
                    } else {
                        String message = "There is some errors with the information supplied to END " + endType + " " + objectRef + "\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(EndObject.this, message, title);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(EndObject.class.getName()).log(Level.SEVERE, null, ex);
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
        
        this.setSize(350, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("End " + endType);
        
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

        JLabel endDateLabel = new JLabel("End Date    ");
        Font font = endDateLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        endDateLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(endDateLabel, gc);
        
        endDateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(endDateField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel endReason = new JLabel("End Reason    ");
        endReason.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(endReason, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(endReasonField, gc);
        
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
    }
}
