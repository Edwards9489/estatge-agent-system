/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.propertyElement;

import client_application.ClientImpl;
import client_gui.OKDialog;
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
import javax.swing.JCheckBox;
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
public class UpdatePropElement extends JFrame {

    private ClientImpl client = null;
    private PropertyElementInterface propElement = null;
    private JButton okButton;
    private JButton cancelButton;
    private final int ref;
    private JPanel controlsPanel;
    private JXDatePicker dateField;
    private JTextField value;
    private JCheckBox charge;
    private JTextArea textArea;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public UpdatePropElement(ClientImpl client, PropertyElementInterface propElement, int ref) {
        setClient(client);
        setPropertyElement(propElement);
        this.ref = ref;
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setPropertyElement(PropertyElementInterface propElement) {
        if (this.propElement == null) {
            this.propElement = propElement;
        }
    }

    private void layoutComponents() {
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        dateField = new JXDatePicker();
        value = new JTextField(10);
        charge = new JCheckBox();
        textArea = new JTextArea(3, 30);

        try {
            dateField.setDate(propElement.getStartDate());
            if (propElement.isCharge()) {
                value.setText(String.valueOf(propElement.getDoubleValue()));
            } else {
                value.setText(propElement.getStringValue());
            }
            charge.setSelected(propElement.isCharge());
            if (propElement.getNote() != null) {
                textArea.setText(propElement.getComment());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(UpdatePropElement.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    Date startDate = dateField.getDate();
                    String stringValue = null;
                    Double doubleValue = 0.0;
                    String comment = textArea.getText();
                    if (charge.isSelected()) {
                        doubleValue = Double.parseDouble(value.getText());
                    } else {
                        stringValue = value.getText();
                    }

                    if (startDate != null && (stringValue != null || doubleValue != 0.0)) {
                        int result = 0;
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Property Element " + propElement.getPropertyElementRef() + " for Property " + ref + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (answer == JOptionPane.YES_OPTION) {
                            try {
                                System.out.println("Prop Ref: " + ref);
                                System.out.println("Property Element Ref: " + propElement.getPropertyElementRef());
                                System.out.println("Start Date: " + startDate);
                                System.out.println("String Value: " + stringValue);
                                System.out.println("Double Value: " + doubleValue);
                                System.out.println("Charge? " + charge.isSelected());
                                System.out.println("Comment: " + comment);
                                
                                result = client.updatePropertyElement(propElement.getPropertyElementRef(), ref, startDate, stringValue, doubleValue, charge.isSelected(), comment);
                                
                                System.out.println("Result: " + result);
                                
                                if (result > 0) {
                                    String message = "Property Element " + propElement.getPropertyElementRef() + " for Property " + ref + " has been successfully updated";
                                    String title = "Information";
                                    OKDialog.okDialog(UpdatePropElement.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to UPDATE Property Element " + propElement.getPropertyElementRef() + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(UpdatePropElement.this, message, title);
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(UpdatePropElement.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        String message = "There is some errors with the information supplied to UPDATE Property Element " + propElement.getPropertyElementRef() + "\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(UpdatePropElement.this, message, title);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(UpdatePropElement.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(700, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Update Property Element");

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
            propElementRef.setText(String.valueOf(propElement.getPropertyElementRef()));
            propElementRef.setFont(plainFont);
        } catch (RemoteException ex) {
            Logger.getLogger(UpdatePropElement.class.getName()).log(Level.SEVERE, null, ex);
        }

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(propElementRef, gc);

        JLabel propRefLabel = new JLabel("Prop Ref    ");
        propRefLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(propRefLabel, gc);
        
        JLabel propRef = new JLabel(String.valueOf(ref));
        propRef.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(propRef, gc);
        
        JLabel chargeLabel= new JLabel("Charge    ");
        chargeLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(chargeLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(charge, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel startDate = new JLabel("Start Date    ");
        startDate.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(startDate, gc);

        dateField.setFormats(formatter);
        dateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(dateField, gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        JLabel valueLabel = new JLabel("Value    ");
        valueLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(valueLabel, gc);
        
        value.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(value, gc);
        
        
        //////////// NEXT ROW //////////////
        
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

        textArea.setLineWrap(true);
        textArea.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 5;
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
    }
}
