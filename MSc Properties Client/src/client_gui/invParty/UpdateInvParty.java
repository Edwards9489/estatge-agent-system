/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.invParty;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Element;
import interfaces.InvolvedPartyInterface;
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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
public class UpdateInvParty extends JFrame {

    private ClientImpl client = null;
    private InvolvedPartyInterface invParty = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField personRefField;
    private JTextField appRefField;
    private JCheckBox mainField;
    private JCheckBox jointField;
    private JXDatePicker dateField;
    private JComboBox relationshipField;
    private SimpleDateFormat formatter;
    private JPanel controlsPanel;

    public UpdateInvParty(ClientImpl client, InvolvedPartyInterface invParty) {
        super("MSc Properties");
        setClient(client);
        setInvParty(invParty);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (this.client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setInvParty(InvolvedPartyInterface invParty) {
        if (this.invParty == null) {
            this.invParty = invParty;
        }
    }

    private void layoutComponents() {
        NumberFormat empFormat = NumberFormat.getNumberInstance();
        empFormat.setMaximumFractionDigits(0);
        empFormat.setMaximumIntegerDigits(10);

        NumberFormat lengthFormat = NumberFormat.getNumberInstance();
        lengthFormat.setMaximumFractionDigits(0);
        lengthFormat.setMaximumIntegerDigits(2);

        formatter = new SimpleDateFormat("dd-MM-yyyy");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        personRefField = new JFormattedTextField(empFormat);
        appRefField = new JFormattedTextField(lengthFormat);
        mainField = new JCheckBox();
        jointField = new JCheckBox();
        dateField = new JXDatePicker();
        dateField.setFormats(formatter);
        relationshipField = new JComboBox();
        relationshipField.addItem("-");

        try {
            for (Element temp : client.getRelationships()) {
                if (temp.isCurrent()) {
                    String relationshipCode = temp.getCode();
                    relationshipField.addItem(relationshipCode);
                }
            }
            
            personRefField.setText(String.valueOf(invParty.getPersonRef()));
            appRefField.setText(String.valueOf(invParty.getApplicationRef()));
            mainField.setSelected(invParty.isMainInd());
            jointField.setSelected(invParty.isJointInd());
            dateField.setDate(invParty.getStartDate());
            relationshipField.setSelectedItem(invParty.getRelationship().getCode());
            
            personRefField.setEnabled(false);
            appRefField.setEnabled(false);
            mainField.setEnabled(false);
            
            personRefField.setDisabledTextColor(Color.BLACK);
            appRefField.setDisabledTextColor(Color.BLACK);
        } catch (RemoteException ex) {
            Logger.getLogger(UpdateInvParty.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String relationshipCode = null;
                Date startDate = null;
                boolean correctInput = false;
                try {
                    if (!relationshipField.getSelectedItem().equals("-")) {
                        if (dateField.getDate() != null) {
                            relationshipCode = (String) relationshipField.getSelectedItem();
                            startDate = dateField.getDate();
                            correctInput = true;
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UpdateInvParty.class.getName()).log(Level.SEVERE, null, ex);
                    errorMessage();
                }

                if (correctInput) {

                    try {

                        System.out.println("Person Ref: " + invParty.getInvolvedPartyRef());
                        System.out.println("App Ref: " + invParty.getApplicationRef());
                        System.out.println("Start Date: " + dateField.getDate());
                        System.out.println("Main: " + invParty.isMainInd());
                        System.out.println("Joint: " + jointField.isSelected());
                        System.out.println("Relationship: " + relationshipCode);

                        int result;
                        if (startDate != null && relationshipCode != null) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Involved Party " + invParty.getInvolvedPartyRef() + " for Application " + invParty.getApplicationRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (answer == JOptionPane.YES_OPTION) {
                                result = client.updateInvolvedParty(invParty.getInvolvedPartyRef(), jointField.isSelected(), startDate, relationshipCode);
                                if (result > 0) {
                                    String message = "Involved Party " + invParty.getInvolvedPartyRef() + " for Application " + invParty.getApplicationRef() + " has been updated successfully";
                                    String title = "Information";
                                    OKDialog.okDialog(UpdateInvParty.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to UPDATE Involved Party for Application " + invParty.getApplicationRef() + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(UpdateInvParty.this, message, title);
                                }
                            }
                        } else {
                            String message = "There is some errors with the information supplied to UPDATE a new Involved Party " + invParty.getInvolvedPartyRef() + " for Application " + invParty.getApplicationRef() + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(UpdateInvParty.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(UpdateInvParty.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (!correctInput) {
                    errorMessage();
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

        this.setSize(700, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Update Involved Party");

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

        JLabel personRef = new JLabel("Person Ref    ");
        Font font = personRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        personRef.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(personRef, gc);

        personRefField.setFont(plainFont);
        Dimension dimension = personRefField.getPreferredSize();
        dimension.setSize(dimension.getWidth() + 80, dimension.getHeight());
        personRefField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(personRefField, gc);

        JLabel appRef = new JLabel("App Ref    ");
        appRef.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(appRef, gc);

        appRefField.setFont(plainFont);
        appRefField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(appRefField, gc);

        JLabel main = new JLabel("Main Ind    ");
        main.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(main, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(mainField, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel startDate = new JLabel("Start Date *    ");
        startDate.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(startDate, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(dateField, gc);

        JLabel relationship = new JLabel("Relationship *    ");
        relationship.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(relationship, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(relationshipField, gc);

        JLabel joint = new JLabel("Joint Ind *    ");
        joint.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(joint, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(jointField, gc);

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

    private void errorMessage() {
        GridBagConstraints gc = new GridBagConstraints();

        ////////// ERROR ROW //////////
        gc.gridx = 0;
        gc.gridy = 2;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel error = new JLabel("Check Information Entered and Try Again!");
        Font font = error.getFont();
        Font errorFont = new Font(font.getName(), Font.BOLD, 17);
        error.setForeground(Color.red);
        error.setFont(errorFont);

        gc.gridwidth = 7;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(error, gc);

        pack();
        repaint();
    }

    public void setPersonField(int pRef) {
        if (personRefField != null) {
            personRefField.setText(String.valueOf(pRef));
        }
    }

    public void setAppField(int appRef) {
        if (appRefField != null) {
            appRefField.setText(String.valueOf(appRef));
        }
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new UpdateInvParty().setVisible(true);
//            }
//        });
//    }
}
