/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.contract;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.ContractInterface;
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
public class UpdateContract extends JFrame {

    private ClientImpl client = null;
    private ContractInterface contract;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JTextField lengthField;
    private JXDatePicker dateField;
    private SimpleDateFormat formatter;
    private JPanel controlsPanel;

    public UpdateContract(ClientImpl client, ContractInterface contract) {
        super("MSc Properties");
        this.setContract(contract);
        this.setClient(client);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    // Use of singleton pattern to ensure only one Contract is initiated
    private void setContract(ContractInterface contract) {
        System.out.println("Before assignment" + this.contract != null);
        if (this.contract == null) {
            System.out.println("Contract is being assigned");
            this.contract = contract;
            System.out.println(this.contract != null);
        }
    }

    private void layoutComponents() {
        try {
            NumberFormat lengthFormat = NumberFormat.getNumberInstance();
            lengthFormat.setMaximumFractionDigits(0);
            lengthFormat.setMaximumIntegerDigits(2);
            
            formatter = new SimpleDateFormat("dd-MM-yyyy");
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            nameField = new JTextField(40);
            lengthField = new JFormattedTextField(lengthFormat);
            dateField = new JXDatePicker();
            dateField.setFormats(formatter);
            
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    String lengthText = null;
                    boolean correctInput = false;
                    
                    try {
                        lengthText = lengthField.getText();
                        correctInput = true;
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateContract.class.getName()).log(Level.SEVERE, null, ex);
                        errorMessage();
                    }
                    
                    if (correctInput) {
                        String name = nameField.getText();
                        int length = Integer.parseInt(lengthText);
                        Date startDate = dateField.getDate();
                        
                        System.out.println("Name: " + name);
                        System.out.println("Length: " + length);
                        System.out.println("Start Date (Field): " + dateField.getDate());
                        
                        int result;
                        try {
                            if (length > -1 && startDate != null) {
                                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE contract " + contract.getAgreementRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (answer == JOptionPane.YES_OPTION) {
                                    result = client.updateContract(contract.getAgreementRef(), name, startDate, length);
                                    if (result > 0) {
                                        String message = "Contract " + contract.getAgreementRef() + " has been updated successfully";
                                        String title = "Information";
                                        OKDialog.okDialog(UpdateContract.this, message, title);
                                        setVisible(false);
                                        dispose();
                                    } else {
                                        String message = "There is some errors with the information supplied to UPDATE contract " + contract.getAgreementRef() + "\nPlease check the information supplied";
                                        String title = "Error";
                                        OKDialog.okDialog(UpdateContract.this, message, title);
                                    }
                                }
                            } else {
                                String message = "There is some errors with the information supplied to UPDATE contract " + contract.getAgreementRef() + "\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(UpdateContract.this, message, title);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(UpdateContract.class.getName()).log(Level.SEVERE, null, ex);
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
            
            
            int space = 15;
            
            JPanel buttonsPanel = new JPanel();
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Update Contract");
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());
            
            controlsPanel = new JPanel();
            controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
            controlsPanel.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            
            ///////// FIRST ROW ///////////
            
            JLabel name = new JLabel("Name *    ");
            Font font = name.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 15);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
            name.setFont(boldFont);
            
            gc.gridx = 0;
            gc.gridy = 0;
            gc.weightx = 1;
            gc.weighty = 1;
            
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(name, gc);
            
            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(nameField, gc);
            
            
            /////// NEXT ROW ////////
            
            JLabel startDate = new JLabel("Start Date *    ");
            startDate.setFont(boldFont);
            
            gc.gridx = 0;
            gc.gridy++;
            gc.weightx = 1;
            gc.weighty = 1;
            
            gc.gridwidth = 1;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(startDate, gc);
            
            
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(dateField, gc);
            
            
            
            JLabel length = new JLabel("Length *    ");
            length.setFont(boldFont);
            
            gc.gridx++;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(length, gc);
            
            
            lengthField.setFont(plainFont);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(lengthField, gc);
            
            
            
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(okButton);
            buttonsPanel.add(cancelButton);
            Dimension btnSize = cancelButton.getPreferredSize();
            okButton.setPreferredSize(btnSize);
            
            lengthField.setFont(plainFont);
            Dimension dimension = lengthField.getPreferredSize();
            dimension.setSize(dimension.getWidth() + 80, dimension.getHeight());
            
            lengthField.setPreferredSize(dimension);
            
            nameField.setText(contract.getAgreementName());
            lengthField.setText(String.valueOf(contract.getLength()));
            dateField.setDate(contract.getStartDate());
            
            setLayout(new BorderLayout());
            add(controlsPanel, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(UpdateContract.class.getName()).log(Level.SEVERE, null, ex);
        }
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

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new UpdateContract().setVisible(true);
//            }
//        });
//    }
}
