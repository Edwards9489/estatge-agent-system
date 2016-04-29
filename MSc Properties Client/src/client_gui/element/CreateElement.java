/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.element;

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
public class CreateElement extends JFrame {

    ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private final String elementType;
    private JTextField codeField;
    private JTextField descriptionField;
    private JTextArea noteField;

    public CreateElement(ClientImpl client, String elementType) {
        setClient(client);
        this.elementType = elementType;
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
        descriptionField = new JTextField(15);
        noteField = new JTextArea(2, 35);
        noteField.setLineWrap(true);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String code = "";
                String description = "";
                String comment = "";

                if (!codeField.getText().isEmpty()) {
                    code = codeField.getText();
                }

                if (!descriptionField.getText().isEmpty()) {
                    description = descriptionField.getText();
                }

                if (!noteField.getText().isEmpty()) {
                    comment = noteField.getText();
                }
                
                int result = 0;
                try {
                    if (!code.isEmpty() && !description.isEmpty()) {
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new " + elementType + " Element with unique code " + code + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            switch (elementType) {
                                case "Title":
                                    result = client.createTitle(code, description, comment);
                                    System.out.println("createTitle");
                                    break;
                                case "Gender":
                                    result = client.createGender(code, description, comment);
                                    System.out.println("createGender");
                                    break;
                                case "Marital Status":
                                    result = client.createMaritalStatus(code, description, comment);
                                    System.out.println("createMaritalStatus");
                                    break;
                                case "Ethnic Origin":
                                    result = client.createEthnicOrigin(code, description, comment);
                                    System.out.println("createEthnicOrigin");
                                    break;
                                case "Language":
                                    result = client.createLanguage(code, description, comment);
                                    System.out.println("createLanguage");
                                    break;
                                case "Nationality":
                                    result = client.createNationality(code, description, comment);
                                    System.out.println("createNationality");
                                    break;
                                case "Sexuality":
                                    result = client.createSexuality(code, description, comment);
                                    System.out.println("createSexuality");
                                    break;
                                case "Religion":
                                    result = client.createReligion(code, description, comment);
                                    System.out.println("createReligion");
                                    break;
                                case "Contact Type":
                                    result = client.createContactType(code, description, comment);
                                    System.out.println("createContactType");
                                    break;
                                case "End Reason":
                                    result = client.createEndReason(code, description, comment);
                                    System.out.println("createEndReason");
                                    break;
                                case "Relationship":
                                    result = client.createRelationship(code, description, comment);
                                    System.out.println("createRelationship");
                                    break;
                                case "Tenancy Type":
                                    result = client.createTenancyType(code, description, comment);
                                    System.out.println("createTenancyType");
                                    break;
                                case "Job Benefit":
                                    result = client.createJobBenefit(code, description, comment);
                                    System.out.println("createJobBenefit");
                                    break;
                                case "Job Requirement":
                                    result = client.createJobRequirement(code, description, comment);
                                    System.out.println("createJobRequirement");
                                    break;
                                case "Property Type":
                                    result = client.createPropertyType(code, description, comment);
                                    System.out.println("createPropertyType");
                                    break;
                                case "Property Sub Type":
                                    result = client.createPropertySubType(code, description, comment);
                                    System.out.println("createPropertySubType");
                                    break;
                                case "Property Element":
                                    result = client.createPropertyElement(code, description, comment);
                                    System.out.println("createPropertyElement");
                                    break;
                                
                            }
                            if (result > 0) {
                                String message = "The new " + elementType + " Element has been created with the unique code of " + code;
                                String title = "Information";
                                OKDialog.okDialog(CreateElement.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to CREATE a new " + elementType + " Element\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(CreateElement.this, message, title);
                            }
                        }
                    } else {
                        String message = "There is some errors with the information supplied to CREATE a new " + elementType + " Element\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(CreateElement.this, message, title);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(CreateElement.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(570, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create " + elementType);

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
        gc.gridwidth = 3;
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
