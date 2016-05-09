/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.ApplicationInterface;
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
public class UpdateApp extends JFrame {
    private ClientImpl client = null;
    private ApplicationInterface application = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    private JTextField corrNameField;
    private JXDatePicker appStartDateField;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public UpdateApp(ClientImpl client, ApplicationInterface application) {
        setClient(client);
        setApplication(application);
        layoutComponents();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setApplication(ApplicationInterface application) {
        if (this.application == null) {
            this.application = application;
        }
    }
    
    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        corrNameField = new JTextField(17);
        appStartDateField = new JXDatePicker();
        appStartDateField.setFormats(formatter);
        try {
            corrNameField.setText(application.getAppCorrName());
            appStartDateField.setDate(application.getAppStartDate());
        } catch (RemoteException ex) {
            Logger.getLogger(UpdateApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Application " + application.getApplicationRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        String corrName = null;
                        Date appStartDate = appStartDateField.getDate();
                        
                        if (!corrNameField.getText().isEmpty()) {
                            corrName = corrNameField.getText();
                        }
                        
                        if (corrName != null && !corrName.isEmpty() && appStartDate != null) {
                            try {
                                int result = client.updateApplication(application.getApplicationRef(), corrName, appStartDate);
                                
                                if (result > 0) {
                                    String message = "Application " + application.getApplicationRef() + " has been updated successfully";
                                    String title = "Information";
                                    OKDialog.okDialog(UpdateApp.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to UPDATE Application " + application.getApplicationRef() + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(UpdateApp.this, message, title);
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(UpdateApp.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            String message = "There is some errors with the information supplied to UPDATE Application " + application.getApplicationRef() + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(UpdateApp.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(UpdateApp.class.getName()).log(Level.SEVERE, null, ex);
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
        
        this.setSize(700, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Update Application");

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

        JLabel corrNameLabel = new JLabel("Correspondence Name    ");
        Font font = corrNameLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        corrNameLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(corrNameLabel, gc);

        corrNameField.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(corrNameField, gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        JLabel startDate = new JLabel("Start Date    ");
        startDate.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(startDate, gc);
        
        appStartDateField.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(appStartDateField, gc);
        

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
