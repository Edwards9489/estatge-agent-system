/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobBenefit;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Element;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Dwayne
 */
public class UpdateJobRoleBenefit extends JFrame {
    ClientImpl client = null;
    JobRoleBenefitInterface jobRoleBenefit = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    private JComboBox elementField;
    private JCheckBox charge;
    private JTextField value;
    private JXDatePicker dateField;
    private JTextArea textArea;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public UpdateJobRoleBenefit(ClientImpl client, JobRoleBenefitInterface jobRoleBenefit) {
        super("MSc Properties");
        setClient(client);
        setJobRoleBenefit(jobRoleBenefit);
        layoutComponents();
    }
    
    private void setClient(ClientImpl model) {
        if (this.client == null) {
            this.client = model;
        }
    }
    
    private void setJobRoleBenefit(JobRoleBenefitInterface jobRoleBenefit) {
        if (this.jobRoleBenefit == null) {
            this.jobRoleBenefit = jobRoleBenefit;
        }
    }
    
    private void layoutComponents() {
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            
            value = new JTextField(10);
            
            if (jobRoleBenefit.isSalaryBenefit()) {
                value.setText(String.valueOf(jobRoleBenefit.getDoubleValue()));
            } else {
                value.setText(jobRoleBenefit.getStringValue());
            }
            
            charge = new JCheckBox();
            charge.setSelected(jobRoleBenefit.isSalaryBenefit());
            
            elementField = new JComboBox();
            elementField.addItem("  ---  ");
            try {
                for (Element benefits : client.getCurrentJobBenefits()) {
                    elementField.addItem(benefits.getCode());
                }
            } catch (RemoteException ex) {
                Logger.getLogger(UpdateJobRoleBenefit.class.getName()).log(Level.SEVERE, null, ex);
            }
            elementField.setSelectedItem(jobRoleBenefit.getBenefitCode());
            elementField.setEnabled(false);
            
            dateField = new JXDatePicker();
            dateField.setFormats(formatter);
            dateField.setDate(jobRoleBenefit.getStartDate());
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        int result = 0;
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Job Role Benefit with ref " + jobRoleBenefit.getBenefitRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            Date startDate = dateField.getDate();
                            String stringValue = null;
                            Double doubleValue = 0.0;
                            String comment = textArea.getText();
                            if (charge.isSelected()) {
                                doubleValue = Double.parseDouble(value.getText());
                            } else {
                                stringValue = value.getText();
                            }
                            
                            if (startDate != null && (stringValue != null || doubleValue > 0.0)) {
                                try {
                                    result = client.updateJobRoleBenefit(jobRoleBenefit.getBenefitRef(), jobRoleBenefit.getJobRoleCode(), startDate, charge.isSelected(), stringValue, doubleValue, comment);
                                    if (result > 0) {
                                        String message = "The Job Role Benefit with Ref " + jobRoleBenefit.getBenefitRef() + " has been updated successfully";
                                        String title = "Information";
                                        OKDialog.okDialog(UpdateJobRoleBenefit.this, message, title);
                                        setVisible(false);
                                        dispose();
                                    } else {
                                        String message = "There is some errors with the information supplied to UPDATE the Job Role Benefit\nPlease check the information supplied";
                                        String title = "Error";
                                        OKDialog.okDialog(UpdateJobRoleBenefit.this, message, title);
                                    }
                                } catch (RemoteException ex) {
                                    Logger.getLogger(UpdateJobRoleBenefit.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                String message = "There is some errors with the information supplied to UPDATE the Job Role Benefit\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(UpdateJobRoleBenefit.this, message, title);
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(UpdateJobRoleBenefit.class.getName()).log(Level.SEVERE, null, ex);
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
            
            this.setSize(550, 350);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            
            JPanel buttonsPanel = new JPanel();
            
            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Update Job Role Benefit");
            
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

            JLabel propElementRefLabel = new JLabel("Ref    ");
            Font font = propElementRefLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            propElementRefLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(propElementRefLabel, gc);

            JLabel propElementRef = new JLabel();
            try {
                propElementRef.setText(String.valueOf(jobRoleBenefit.getBenefitRef()));
                propElementRef.setFont(plainFont);
            } catch (RemoteException ex) {
                Logger.getLogger(UpdateJobRoleBenefit.class.getName()).log(Level.SEVERE, null, ex);
            }

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(propElementRef, gc);

            JLabel propRefLabel = new JLabel("Job Role    ");
            propRefLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(propRefLabel, gc);

            JLabel propRef = new JLabel(jobRoleBenefit.getJobRoleCode());
            propRef.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(propRef, gc);
            
            
            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel element = new JLabel("Element    ");
            element.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(element, gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(elementField, gc);
            
            JLabel addrStartDate = new JLabel("Start Date  ");
            addrStartDate.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrStartDate, gc);
            
            dateField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(dateField, gc);
            
            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel valueLabel = new JLabel("Value    ");
            valueLabel.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(valueLabel, gc);
            
            value.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(value, gc);
            
            JLabel isChargeLabel = new JLabel("Salary Benefit  ");
            isChargeLabel.setFont(boldFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(isChargeLabel, gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(charge, gc);
            
            //////////// NEXT ROW //////////////////
            
            gc.gridx = 0;
            gc.gridy++;
            
            gc.weightx = 1;
            gc.weighty = 1;
            
            JLabel comment = new JLabel("Comment    ");
            comment.setFont(boldFont);
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(comment, gc);
            
            textArea = new JTextArea(3, 30);
            textArea.setLineWrap(true);
            textArea.setFont(plainFont);
            
            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(textArea, gc);
            
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
            Logger.getLogger(UpdateJobRoleBenefit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
