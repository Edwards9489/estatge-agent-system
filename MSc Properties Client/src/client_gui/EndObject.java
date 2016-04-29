/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
import interfaces.JobRoleBenefitInterface;
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
    private final int ref;
    
    private JPanel controlsPanel;

    public EndObject(ClientImpl client, String endType, int ref) {
        super("MSc Properties");
        setClient(client);
        this.endType = endType;
        this.ref = ref;
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
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to END " + endType + " " + ref + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            switch (endType) {
                                case "Involved Party":
                                    InvolvedPartyInterface invParty = client.getInvolvedParty(ref);
                                    result = client.endInvolvedParty(invParty.getApplicationRef(), ref, endDate, endReasonText);
                                    break;
                                    
                                case "Job Role Benefit":
                                    JobRoleBenefitInterface benefit = client.getJobRoleBenefit(ref);
                                    result = client.endJobRoleBenefit(ref, benefit.getJobRoleCode(), endDate);
                                    break;
                                    
                                case "Tenancy":
                                    result = client.endTenancy(ref, endDate);
                                    break;
                                    
                                case "Lease":
                                    result = client.endLease(ref, endDate);
                                    break;
                                    
                                case "Contract":
                                    result = client.endContract(ref, endDate);
                                    break;
                            }
                            if (result > 0) {
                                String message = endType + " has been ended successfully";
                                String title = "Information";
                                OKDialog.okDialog(EndObject.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to END " + endType + " " + ref + "\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(EndObject.this, message, title);
                            }
                        }
                    } else {
                        String message = "There is some errors with the information supplied to END " + endType + " " + ref + "\nPlease check the information supplied";
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
