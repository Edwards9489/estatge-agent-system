/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.contact;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Element;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Dwayne
 */
public class CreateContact extends JFrame {

    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private final String type;
    private int ref;
    private String code;
    private JPanel controlsPanel;
    private JTextField valueField;
    private JComboBox contactTypeField;
    private JXDatePicker dateField;
    private JTextArea textArea;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public CreateContact(ClientImpl client, String type, int ref) {
        setClient(client);
        this.ref = ref;
        this.type = type;
        layoutComponents();
    }

    public CreateContact(ClientImpl client, String type, String code) {
        setClient(client);
        this.code = code;
        this.type = type;
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    private void layoutComponents() {
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");

            valueField = new JTextField();
            contactTypeField = new JComboBox();

            contactTypeField.addItem("  ---  ");
            for (Element contactType : client.getCurrentContactTypes()) {
                contactTypeField.addItem(contactType.getCode());
            }

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    int result = 0;
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Contact for " + type + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        String contactTypeCode = (String) contactTypeField.getSelectedItem();
                        String value = valueField.getText();
                        Date date = dateField.getDate();
                        String comment = textArea.getText();

                        if (contactTypeCode != null && !contactTypeCode.equals("  ---  ") && value != null && !value.isEmpty() && date != null) {
                            try {
                                switch (type) {
                                    case "Person":
                                        result = client.createPersonContact(ref, contactTypeCode, value, date, comment);
                                        System.out.println("createPersonContact");
                                        break;
                                    case "Office":
                                        result = client.createOfficeContact(code, contactTypeCode, value, date, comment);
                                        System.out.println("createOfficeContact");
                                        break;
                                }

                                if (result > 0) {
                                    String message = "The new Contact for " + type + " has been created and assigned Contact Ref " + result;
                                    String title = "Information";
                                    OKDialog.okDialog(CreateContact.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to CREATE the Contact\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(CreateContact.this, message, title);
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(CreateContact.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            String message = "There is some errors with the information supplied to CREATE the Contact\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateContact.this, message, title);
                        }
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

            this.setSize(600, 320);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

            JPanel buttonsPanel = new JPanel();

            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Create Contact");

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

            JLabel contact = new JLabel("Contact Type    ");
            Font font = contact.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            contact.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(contact, gc);

            contactTypeField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(contactTypeField, gc);

            JLabel startDate = new JLabel("Start Date    ");
            startDate.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(startDate, gc);

            dateField = new JXDatePicker();
            dateField.setFormats(formatter);
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

            valueField.setFont(plainFont);
            Dimension dimension = valueField.getPreferredSize();
            dimension.setSize(dimension.getWidth() + 370, dimension.getHeight());
            valueField.setPreferredSize(dimension);

            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(valueField, gc);

            ///////////// NEXT ROW ///////////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel comment = new JLabel("Comment    ");
            comment.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.gridwidth = 1;
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
            Logger.getLogger(CreateContact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
