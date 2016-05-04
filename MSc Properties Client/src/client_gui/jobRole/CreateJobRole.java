/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobRole;

import client_application.ClientImpl;
import client_gui.OKDialog;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class CreateJobRole extends JFrame {
    
    ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField codeField;
    private JTextField titleField;
    private JTextArea descriptionField;
    private JTextField salaryField;
    private JCheckBox fullTime;
    private JCheckBox read;
    private JCheckBox write;
    private JCheckBox update;
    private JCheckBox delete;
    private JCheckBox eRead;
    private JCheckBox eWrite;
    private JCheckBox eUpdate;
    private JCheckBox eDelete;
    
    public CreateJobRole(ClientImpl client) {
        setClient(client);
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
        codeField = new JTextField(7);
        titleField = new JTextField(13);
        salaryField = new JTextField(10);
        descriptionField = new JTextArea(3, 50);
        descriptionField.setLineWrap(true);
        fullTime = new JCheckBox();
        read = new JCheckBox();
        write = new JCheckBox();
        update = new JCheckBox();
        delete = new JCheckBox();
        eRead = new JCheckBox();
        eWrite = new JCheckBox();
        eUpdate = new JCheckBox();
        eDelete = new JCheckBox();
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String code = "";
                String jobRoleTitle = "";
                String description = "";
                double salary = 0.0;

                if (!codeField.getText().isEmpty()) {
                    code = codeField.getText();
                }
                
                if (!titleField.getText().isEmpty()) {
                    jobRoleTitle = titleField.getText();
                }

                if (!descriptionField.getText().isEmpty()) {
                    description = descriptionField.getText();
                }
                
                if (!salaryField.getText().isEmpty()) {
                    salary = Double.parseDouble(salaryField.getText());
                }
                
                int result = 0;
                try {
                    if (code != null && !code.isEmpty() && !code.equals("-") && jobRoleTitle != null && !jobRoleTitle.isEmpty() && description != null && !description.isEmpty() && salary > 0) {
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Job Role with unique code " + code + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            
                            result = client.createJobRole(code, jobRoleTitle, description, fullTime.isSelected(), salary, read.isSelected(), write.isSelected(), update.isSelected(), delete.isSelected(), eRead.isSelected(), eWrite.isSelected(), eUpdate.isSelected(), eDelete.isSelected());
                            if (result > 0) {
                                String message = "The new Job Role has been created with the unique code of " + code;
                                String title = "Information";
                                OKDialog.okDialog(CreateJobRole.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to CREATE a new Job Role\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(CreateJobRole.this, message, title);
                            }
                        }
                    } else {
                        String message = "There is some errors with the information supplied to CREATE a new Job Role\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(CreateJobRole.this, message, title);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(CreateJobRole.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(800, 450);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Job Role");

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        detailsPanel.setLayout(new GridBagLayout());
        detailsPanel.setSize(1000, 250);

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel codeLabel = new JLabel("Code    ");
        Font font = codeLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 14);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 14);
        codeLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(codeLabel, gc);

        codeField.setFont(plainFont);
        Dimension smallDimension = codeField.getPreferredSize();
        smallDimension.setSize(smallDimension.getWidth() + 40, smallDimension.getHeight());
        Dimension bigDimension = codeField.getPreferredSize();
        bigDimension.setSize(smallDimension.getWidth() + 60, smallDimension.getHeight());
        codeField.setPreferredSize(smallDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(codeField, gc);
        
        JLabel titleLabel = new JLabel("Title    ");
        titleLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(titleLabel, gc);

        titleField.setFont(plainFont);
        titleField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(titleField, gc);
        
        JLabel salaryLabel = new JLabel("Salary    ");
        salaryLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(salaryLabel, gc);

        salaryField.setFont(plainFont);
        salaryField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(salaryField, gc);
        
        JLabel fullTimeLabel = new JLabel("Full Time    ");
        fullTimeLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(fullTimeLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(fullTime, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel descriptionLabel = new JLabel("Description    ");
        descriptionLabel.setFont(boldFont);

        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(descriptionLabel, gc);

        descriptionField.setFont(plainFont);
        descriptionField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.gridwidth = 7;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(descriptionField, gc);
        
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel readLabel = new JLabel("Read    ");
        readLabel.setFont(boldFont);
        
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(readLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(read, gc);
        
        JLabel writeLabel = new JLabel("Write    ");
        writeLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(writeLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(write, gc);
        
        JLabel updateLabel = new JLabel("Update    ");
        updateLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(updateLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(update, gc);
        
        JLabel deleteLabel = new JLabel("Delete    ");
        deleteLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(deleteLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(delete, gc);
        
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel eReadLabel = new JLabel("eRead    ");
        eReadLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eReadLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eRead, gc);
        
        JLabel eWriteLabel = new JLabel("eWrite    ");
        eWriteLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eWriteLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eWrite, gc);
        
        JLabel eUpdateLabel = new JLabel("eUpdate    ");
        eUpdateLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eUpdateLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eUpdate, gc);
        
        JLabel eDeleteLabel = new JLabel("eDelete    ");
        eDeleteLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(eDeleteLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eDelete, gc);
        

        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        // Add components to main frame
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
