/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobRequirement;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Element;
import interfaces.JobRoleInterface;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class CreateJobRequirement extends JFrame {

    ClientImpl client = null;
    JobRoleInterface jobRole = null;
    private JButton okButton;
    private JButton cancelButton;
    private JComboBox requirementsField;
    private JTextArea textArea;
    private JPanel detailsPanel;

    public CreateJobRequirement(ClientImpl client, JobRoleInterface jobRole) {
        super("MSc Properties");
        setClient(client);
        setJobRole(jobRole);
        layoutComponents();
    }

    private void setClient(ClientImpl model) {
        if (client == null) {
            client = model;
        }
    }

    private void setJobRole(JobRoleInterface jobRole) {
        if (this.jobRole == null) {
            this.jobRole = jobRole;
        }
    }

    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        textArea = new JTextArea(4, 20);
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);

        requirementsField = new JComboBox();
        requirementsField.addItem("-");
        try {
            for (Element requirement : client.getCurrentJobRequirements()) {

                if (!jobRole.hasRequirement(requirement.getCode())) {
                    requirementsField.addItem(requirement.getCode());
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CreateJobRequirement.class.getName()).log(Level.SEVERE, null, ex);
        }

        requirementsField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String requirementCode = (String) requirementsField.getSelectedItem();
                if (!requirementCode.equals("-")) {
                    try {
                        Element requirement = client.getJobRequirement(requirementCode);
                        textArea.setText(requirement.getDescription());
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateJobRequirement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    textArea.setText("");
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String requirementCode = (String) requirementsField.getSelectedItem();
                try {
                    int result = 0;
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to ASSIGN the Requirement " + requirementCode + " for Job Role " + jobRole.getJobRoleCode() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        result = client.createJobRoleRequirement(jobRole.getJobRoleCode(), requirementCode);
                        if (result > 0 && !requirementCode.equals("-")) {
                            String message = "The Requirement has been assigned to the Job Role " + jobRole.getJobRoleCode();
                            String title = "Information";
                            OKDialog.okDialog(CreateJobRequirement.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "There is some errors with the information supplied to CREATE the Note\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateJobRequirement.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(CreateJobRequirement.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(400, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Job Requirement");

        //////// CONTROLS PANEL ///////////
        detailsPanel = new JPanel();
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel requirementLabel = new JLabel("Requirement  ");
        Font font = requirementLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);
        requirementLabel.setFont(plainFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(requirementLabel, gc);

        requirementsField.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(requirementsField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel descriptionLabel = new JLabel("Description    ");
        descriptionLabel.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(descriptionLabel, gc);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(textArea, gc);

        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
