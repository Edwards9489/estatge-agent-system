/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.element;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Element;
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
public class UpdateElement extends JFrame {

    ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private final String elementType;
    private JTextField codeField;
    private JTextField descriptionField;
    private JTextArea noteField;
    private JCheckBox currentField;
    private Element element;

    public UpdateElement(ClientImpl client, Element element, String elementType) {
        setClient(client);
        setElement(element);
        this.elementType = elementType;
        layoutComponents();
    }

    private void setClient(ClientImpl client) {
        if (this.client == null) {
            this.client = client;
        }
    }

    private void setElement(Element element) {
        if (this.element == null) {
            this.element = element;
        }
    }

    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        codeField = new JTextField(7);
        descriptionField = new JTextField(15);
        noteField = new JTextArea(2, 38);
        noteField.setLineWrap(true);
        currentField = new JCheckBox();
        
        try {
            codeField.setText(element.getCode());
            codeField.setEnabled(false);
            codeField.setDisabledTextColor(Color.BLACK);
            descriptionField.setText(element.getDescription());
            noteField.setText(element.getNote().getNote());
            currentField.setSelected(element.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(UpdateElement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String description = "";
                String comment = "";

                if (!descriptionField.getText().isEmpty()) {
                    description = descriptionField.getText();
                }

                if (!noteField.getText().isEmpty()) {
                    comment = noteField.getText();
                }
                
                int result = 0;
                try {
                    if (!description.isEmpty()) {
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE " + elementType + " Element with unique code " + element.getCode() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            switch (elementType) {
                                case "Title":
                                    result = client.updateTitle(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateTitle");
                                    break;
                                case "Gender":
                                    result = client.updateGender(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateGender");
                                    break;
                                case "Marital Status":
                                    result = client.updateMaritalStatus(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateMaritalStatus");
                                    break;
                                case "Ethnic Origin":
                                    result = client.updateEthnicOrigin(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateEthnicOrigin");
                                    break;
                                case "Language":
                                    result = client.updateLanguage(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateLanguage");
                                    break;
                                case "Nationality":
                                    result = client.updateNationality(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateNationality");
                                    break;
                                case "Sexuality":
                                    result = client.updateSexuality(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateSexuality");
                                    break;
                                case "Religion":
                                    result = client.updateReligion(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateReligion");
                                    break;
                                case "Contact Type":
                                    result = client.updateContactType(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateContactType");
                                    break;
                                case "End Reason":
                                    result = client.updateEndReason(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateEndReason");
                                    break;
                                case "Relationship":
                                    result = client.updateRelationship(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateRelationship");
                                    break;
                                case "Tenancy Type":
                                    result = client.updateTenancyType(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateTenancyType");
                                    break;
                                case "Job Benefit":
                                    result = client.updateJobBenefit(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateJobBenefit");
                                    break;
                                case "Job Requirement":
                                    result = client.updateJobRequirement(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updateJobRequirement");
                                    break;
                                case "Property Type":
                                    result = client.updatePropertyType(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updatePropertyType");
                                    break;
                                case "Property Sub Type":
                                    result = client.updatePropertySubType(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updatePropertySubType");
                                    break;
                                case "Property Element":
                                    result = client.updatePropertyElement(element.getCode(), description, currentField.isSelected(), comment);
                                    System.out.println("updatePropertyElement");
                                    break;
                                
                            }
                            if (result > 0) {
                                String message = "The new " + elementType + " Element has been updated with the unique code of " + element.getCode();
                                String title = "Information";
                                OKDialog.okDialog(UpdateElement.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to CREATE a new " + elementType + " Element\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(UpdateElement.this, message, title);
                            }
                        }
                    } else {
                        String message = "There is some errors with the information supplied to CREATE a new " + elementType + " Element\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(UpdateElement.this, message, title);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(UpdateElement.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(620, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Update " + elementType);

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
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(codeField, gc);

        JLabel descriptionLabel = new JLabel("Description    ");
        descriptionLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(descriptionLabel, gc);

        descriptionField.setFont(plainFont);
        descriptionField.setPreferredSize(bigDimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(descriptionField, gc);
        
        JLabel currentLabel = new JLabel("Current    ");
        currentLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(currentLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(currentField, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel commentLabel = new JLabel("Comment    ");
        commentLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(commentLabel, gc);

        noteField.setFont(plainFont);
        noteField.setSize(smallDimension);

        gc.gridx++;
        gc.gridwidth = 5;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(noteField, gc);

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
