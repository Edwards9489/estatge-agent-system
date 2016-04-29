/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.transaction;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.person.PersonSearch;
import interfaces.EmployeeAccountInterface;
import interfaces.LeaseAccountInterface;
import interfaces.PersonInterface;
import interfaces.RentAccountInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Dwayne
 */
public class CreateTransaction extends JFrame {

    ClientImpl client = null;
    private final String transactionType;
    private int accRef = 0;
    private JButton okButton;
    private JButton cancelButton;

    private JPanel detailsPanel;

    private JTextField accRefField;
    private JTextField accNameField;
    private JTextField toField;
    private JTextField toNameField;
    private JTextField fromField;
    private JTextField fromNameField;
    private JTextField amountField;
    private JCheckBox isDebitField;
    private JTextArea textArea;
    private final NumberFormat refFormat = NumberFormat.getNumberInstance();

    public CreateTransaction(ClientImpl client, String transactionType, int accRef) {
        super("MSc Properties");
        this.accRef = accRef;
        this.transactionType = transactionType;
        refFormat.setMaximumFractionDigits(0);
        refFormat.setMaximumIntegerDigits(10);
        setClient(client);
        layoutComponents();
    }

    private void setClient(ClientImpl model) {
        if (this.client == null) {
            this.client = model;
        }
    }

    private void layoutComponents() {
        try {
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");

            accRefField = new JFormattedTextField(refFormat);
            accRefField.setText(String.valueOf(accRef));
            Dimension dimension = accRefField.getPreferredSize();
            dimension.setSize(dimension.width + 30, dimension.height + 3);
            Dimension dimension2 = accRefField.getPreferredSize();
            dimension2.setSize(dimension.width + 60, dimension.height + 3);
            accRefField.setPreferredSize(dimension);
            accNameField = new JTextField(20);
            accNameField.setEnabled(false);
            accNameField.setDisabledTextColor(Color.BLACK);
            System.out.println("Transaction Type: " + transactionType);
            switch (transactionType) {
                case "Rent Account":
                    RentAccountInterface rentAcc = client.getRentAccount(accRef);
                    if (rentAcc != null) {
                        accNameField.setText(rentAcc.getAccName());

                    }
                    break;

                case "Lease Account":
                    LeaseAccountInterface leaseAcc = client.getLeaseAccount(accRef);
                    if (leaseAcc != null) {
                        accNameField.setText(leaseAcc.getAccName());
                    }
                    break;

                case "Employee Account":
                    EmployeeAccountInterface empAcc = client.getEmployeeAccount(accRef);
                    if (empAcc != null) {
                        accNameField.setText(empAcc.getAccName());
                    }
                    break;
            }
            toField = new JFormattedTextField(refFormat);
            toField.setPreferredSize(dimension);
            toNameField = new JTextField(20);
            toNameField.setEnabled(false);
            toNameField.setDisabledTextColor(Color.BLACK);
            fromField = new JFormattedTextField(refFormat);
            fromField.setPreferredSize(dimension);
            fromNameField = new JTextField(20);
            fromNameField.setEnabled(false);
            fromNameField.setDisabledTextColor(Color.BLACK);
            amountField = new JTextField(10);
            isDebitField = new JCheckBox();
            textArea = new JTextArea(3, 30);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    int toRef = 0;
                    int fromRef = 0;
                    double amount = 0;
                    String comment = "";

                    if (!toField.getText().isEmpty() && toField.getText() != null) {
                        toRef = Integer.parseInt(toField.getText());
                    }

                    if (!fromField.getText().isEmpty() && fromField.getText() != null) {
                        fromRef = Integer.parseInt(fromField.getText());
                    }

                    if (!amountField.getText().isEmpty() && amountField.getText() != null && Double.parseDouble(amountField.getText()) > 0) {
                        amount = Double.parseDouble(amountField.getText());
                    }

                    if (!textArea.getText().isEmpty() && textArea.getText() != null) {
                        comment = textArea.getText();
                    }

                    int result = 0;
                    try {
                        if (accRef > 0 && toRef > 0 && fromRef > 0 && amount > 0) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a transaction for " + transactionType + " "+ accRef + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (answer == JOptionPane.YES_OPTION) {
                                System.out.println("Transaction Type: " + transactionType);
                                switch (transactionType) {
                                    case "Rent Account":
                                        System.out.println("Create Rent Account Transaction");
                                        result = client.createRentAccTransaction(accRef, fromRef, toRef, amount, isDebitField.isSelected(), new Date(), comment);
                                        break;
                                        
                                    case "Lease Account":
                                        System.out.println("Create Lease Account Transaction");
                                        result = client.createLeaseAccTransaction(accRef, fromRef, toRef, amount, isDebitField.isSelected(), new Date(), comment);
                                        break;

                                    case "Employee Account":
                                        System.out.println("Create Employee Account Transaction");
                                        result = client.createEmployeeAccTransaction(accRef, fromRef, toRef, amount, isDebitField.isSelected(), new Date(), comment);
                                        break;
                                }
                                System.out.println("Acc Ref: " + accRef);
                                System.out.println("fromRef: " + fromRef);
                                System.out.println("toRef: " + toRef);
                                System.out.println("Amount: £" + amount);
                                System.out.println("Is Debit? " + isDebitField.isSelected());
                                System.out.println("Date: " + new Date());
                                System.out.println("Comment: " + comment);
                                System.out.println("Result: " + result);
                                if (result > 0) {
                                    String message = "The transaction has been created and assigned Transaction Ref " + result;
                                    String title = "Information";
                                    OKDialog.okDialog(CreateTransaction.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to CREATE the transaction\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(CreateTransaction.this, message, title);
                                }
                            }
                        } else {
                            String message = "There is some errors with the information supplied to CREATE the transaction\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateTransaction.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateTransaction.class.getName()).log(Level.SEVERE, null, ex);
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

            toField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (!toField.getText().isEmpty()) {
                        int pRef = Integer.parseInt(toField.getText());
                        setToNameField(pRef);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (!toField.getText().isEmpty()) {
                        int pRef = Integer.parseInt(toField.getText());
                        setToNameField(pRef);
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (!toField.getText().isEmpty()) {
                        int pRef = Integer.parseInt(toField.getText());
                        setToNameField(pRef);
                    }
                }
            });

            fromField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (!fromField.getText().isEmpty()) {
                        int pRef = Integer.parseInt(fromField.getText());
                        setFromNameField(pRef);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (!fromField.getText().isEmpty()) {
                        int pRef = Integer.parseInt(fromField.getText());
                        setFromNameField(pRef);
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (!fromField.getText().isEmpty()) {
                        int pRef = Integer.parseInt(fromField.getText());
                        setFromNameField(pRef);
                    }
                }
            });

            this.setSize(800, 400);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

            JPanel buttonsPanel = new JPanel();

            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("Create " + transactionType + " Transaction");

            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

            ////////// DETAILS PANEL ///////////
            detailsPanel = new JPanel();
            detailsPanel.setLayout(new GridBagLayout());
            detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

            GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
            gc.gridx = 0;
            gc.gridy = 0;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel accRefLabel = new JLabel("Account Ref    ");
            Font font = accRefLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 17);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

            accRefLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(accRefLabel, gc);

            accRefField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(accRefField, gc);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(new JLabel(""), gc);

            accNameField.setFont(plainFont);

            gc.gridx++;
            gc.gridwidth = 2;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(accNameField, gc);

            //////////// NEXT ROW //////////////
            
            JLabel toRefLabel = new JLabel("To Ref    ");
            toRefLabel.setFont(boldFont);

            gc.gridx = 0;
            gc.gridy++;

            gc.gridwidth = 1;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(toRefLabel, gc);

            toField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(toField, gc);

            Image srchImge = null;
            JLabel toRefThumb = new JLabel();

            try {
                srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(CreateTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (srchImge != null) {
                ImageIcon icon = new ImageIcon(srchImge);
                toRefThumb.setIcon(icon);
            }

            toRefThumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    PersonSearch empAccSearch = new PersonSearch(client);
                    empAccSearch.setVisible(true);
                    empAccSearch.setListener(new IntegerListener() {
                        @Override
                        public void intOmitted(int toRef) {
                            setToField(toRef);
                        }
                    });
                }
            });

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(toRefThumb, gc);

            toNameField.setFont(plainFont);

            gc.gridx++;
            gc.gridwidth = 2;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(toNameField, gc);

            ////////////// NEXT ROW ////////////////
            JLabel fromRefLabel = new JLabel("From Ref    ");
            fromRefLabel.setFont(boldFont);

            gc.gridx = 0;
            gc.gridy++;

            gc.gridwidth = 1;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(fromRefLabel, gc);

            fromField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(fromField, gc);

            Image srchImge2 = null;
            JLabel fromRefThumb = new JLabel();

            try {
                srchImge2 = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(CreateTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (srchImge2 != null) {
                ImageIcon icon2 = new ImageIcon(srchImge);
                fromRefThumb.setIcon(icon2);
            }

            fromRefThumb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    PersonSearch empAccSearch = new PersonSearch(client);
                    empAccSearch.setVisible(true);
                    empAccSearch.setListener(new IntegerListener() {
                        @Override
                        public void intOmitted(int fromRef) {
                            setFromField(fromRef);
                        }
                    });
                }
            });

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(fromRefThumb, gc);

            fromNameField.setFont(plainFont);

            gc.gridx++;
            gc.gridwidth = 2;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(fromNameField, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            JLabel amountLabel = new JLabel("Amount (£)    ");
            amountLabel.setFont(boldFont);

            gc.gridwidth = 1;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(amountLabel, gc);

            amountField.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(amountField, gc);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(new JLabel(""), gc);

            JLabel isDebitLabel = new JLabel("Debit    ");
            isDebitLabel.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(isDebitLabel, gc);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(isDebitField, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            JLabel commentLabel = new JLabel("Comment    ");
            commentLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(commentLabel, gc);

            gc.gridx++;
            gc.gridwidth = 4;
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
        } catch (RemoteException ex) {
            Logger.getLogger(CreateTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setToField(int toRef) {
        if (toField != null) {
            toField.setText(String.valueOf(toRef));
            setToNameField(toRef);
        }
    }
    
    private void setToNameField(int toRef) {
        if (toNameField != null) {
            try {
                PersonInterface person = client.getPerson(toRef);
                if (person != null) {
                    toNameField.setText(person.getName());
                }
            } catch (RemoteException ex) {
                Logger.getLogger(CreateTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setFromField(int fromRef) {
        if (fromField != null) {
            fromField.setText(String.valueOf(fromRef));
            setFromNameField(fromRef);
        }
    }

    private void setFromNameField(int fromRef) {
        if (fromNameField != null) {
            try {
                PersonInterface person = client.getPerson(fromRef);
                if (person != null) {
                    fromNameField.setText(person.getName());
                }
            } catch (RemoteException ex) {
                Logger.getLogger(CreateTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
