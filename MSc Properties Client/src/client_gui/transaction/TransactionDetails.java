/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.transaction;

import client_application.ClientImpl;
import client_gui.DetailsPanel;
import interfaces.AccountInterface;
import interfaces.PersonInterface;
import interfaces.TransactionInterface;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class TransactionDetails extends JFrame {

    private ClientImpl client = null;
    private TransactionInterface transaction = null;
    private final String transactionType;
    private JButton closeButton;
    private JTextArea noteField;
    private JPanel mainPanel;
    private JPanel detailsPanel;
    
    private JLabel tranRefField;
    private JLabel accRefField;
    private JLabel accNameField;
    private JLabel toField;
    private JLabel toNameField;
    private JLabel fromField;
    private JLabel fromNameField;
    private JLabel amountField;
    private JCheckBox isDebitField;

    public TransactionDetails(ClientImpl client, TransactionInterface transaction, String transactionType) {
        super("MSc Properties");
        this.transactionType = transactionType;
        setClient(client);
        setTransaction(transaction);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Transaction is initiated
    private void setTransaction(TransactionInterface transaction) {
        if (this.transaction == null) {
            this.transaction = transaction;
        }
    }

    private void layoutComponents() {

        try {
            
            closeButton = new JButton("Close");
            noteField = new JTextArea(2, 50);
            noteField.setLineWrap(true);
            noteField.setEnabled(false);
            noteField.setDisabledTextColor(Color.BLACK);
            AccountInterface account = null;
            switch (transactionType) {
                case "Rent Account":
                    account = client.getRentAccount(transaction.getAccountRef());
                    break;
                    
                case "Lease Account":
                    account = client.getLeaseAccount(transaction.getAccountRef());
                    break;
                    
                case "Employee Account":
                    account = client.getEmployeeAccount(transaction.getAccountRef());
                    break;
            }
            
            PersonInterface toPerson = client.getPerson(transaction.getToRef());
            
            PersonInterface fromPerson = client.getPerson(transaction.getFromRef());
            
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    setVisible(false);
                    dispose();
                }
            });
            
            try {
                noteField.setText(transaction.getNote().getNote());
                
                this.setMinimumSize(new Dimension(650, 450));
                this.setSize(650, 450);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
                
                
                ///////// DETAILS PANEL ////////////
                detailsPanel = new JPanel();
                //detailsPanel.setSize(450, 200);
                detailsPanel.setLayout(new GridBagLayout());
                
                GridBagConstraints gc = new GridBagConstraints();
                
                ////////// FIRST ROW //////////
                
                gc.gridx = 0;
                gc.gridy++;
                
                gc.weightx = 1;
                gc.weighty = 1;
                
                JLabel tranRefLabel = new JLabel("Transaction Ref  ");
                Font font = tranRefLabel.getFont();
                Font boldFont = new Font(font.getName(), Font.BOLD, 17);
                Font plainFont = new Font(font.getName(), Font.PLAIN, 17);
                
                tranRefLabel.setFont(boldFont);
                
                gc.fill = GridBagConstraints.NONE;
                gc.anchor = GridBagConstraints.EAST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(tranRefLabel, gc);
                
                tranRefField = new JLabel(String.valueOf(transaction.getTransactionRef()));
                tranRefField.setFont(plainFont);
                
                gc.gridx++;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(tranRefField, gc);
                
                
                ////////// NEXT ROW ///////////
                
                gc.gridx = 0;
                gc.gridy++;
                
                gc.weightx = 1;
                gc.weighty = 1;
                
                JLabel accRefLabel = new JLabel("Account Ref  ");
                
                accRefLabel.setFont(boldFont);
                
                gc.fill = GridBagConstraints.NONE;
                gc.anchor = GridBagConstraints.EAST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(accRefLabel, gc);
                
                accRefField = new JLabel(String.valueOf(transaction.getAccountRef()));
                accRefField.setFont(plainFont);
                
                gc.gridx++;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(accRefField, gc);
                
                if (account != null) {
                    accNameField = new JLabel(account.getAccName());
                } else {
                    accNameField = new JLabel("");
                }
                accNameField.setFont(plainFont);
                
                gc.gridx++;
                gc.gridwidth = 2;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(accNameField, gc);
                
                //////////// NEXT ROW //////////////
                
                JLabel toRefLabel = new JLabel("To Ref  ");
                toRefLabel.setFont(boldFont);
                
                gc.gridx = 0;
                gc.gridy++;
                
                gc.gridwidth = 1;
                gc.fill = GridBagConstraints.NONE;
                gc.anchor = GridBagConstraints.EAST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(toRefLabel, gc);
                
                toField = new JLabel(String.valueOf(transaction.getToRef()));
                toField.setFont(plainFont);
                
                gc.gridx++;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(toField, gc);
                
                if (toPerson != null) {
                    toNameField = new JLabel(toPerson.getName());
                } else {
                    toNameField = new JLabel("");
                }
                toNameField.setFont(plainFont);
                
                gc.gridx++;
                gc.gridwidth = 2;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(toNameField, gc);
                
                ////////////// NEXT ROW ////////////////
                
                JLabel fromRefLabel = new JLabel("From Ref  ");
                fromRefLabel.setFont(boldFont);
                
                gc.gridx = 0;
                gc.gridy++;
                
                gc.gridwidth = 1;
                gc.fill = GridBagConstraints.NONE;
                gc.anchor = GridBagConstraints.EAST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(fromRefLabel, gc);
                
                fromField = new JLabel(String.valueOf(transaction.getFromRef()));
                fromField.setFont(plainFont);
                
                gc.gridx++;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(fromField, gc);
                
                if (fromPerson != null) {
                    fromNameField = new JLabel(fromPerson.getName());
                } else {
                    fromNameField = new JLabel("");
                }
                fromNameField.setFont(plainFont);
                
                gc.gridx++;
                gc.gridwidth = 2;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(fromNameField, gc);
                
                ////////// NEXT ROW //////////
                
                gc.gridx = 0;
                gc.gridy++;
                
                JLabel amountLabel = new JLabel("Amount  ");
                amountLabel.setFont(boldFont);
                
                gc.gridwidth = 1;
                gc.fill = GridBagConstraints.NONE;
                gc.anchor = GridBagConstraints.EAST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(amountLabel, gc);
                
                amountField = new JLabel(String.valueOf(transaction.getAmount()));
                amountField.setFont(plainFont);
                
                gc.gridx++;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(amountField, gc);
                
                JLabel isDebitLabel = new JLabel("Debit  ");
                isDebitLabel.setFont(boldFont);
                
                gc.gridx++;
                gc.anchor = GridBagConstraints.EAST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(isDebitLabel, gc);
                
                isDebitField = new JCheckBox();
                isDebitField.setSelected(transaction.isDebit());
                isDebitField.setEnabled(false);
                
                gc.gridx++;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(isDebitField, gc);
                
                ////////// NEXT ROW //////////
                gc.gridx = 0;
                gc.gridy++;
                
                JLabel commentLabel = new JLabel("Comment  ");
                commentLabel.setFont(boldFont);
                
                gc.fill = GridBagConstraints.NONE;
                gc.anchor = GridBagConstraints.EAST;
                gc.insets = new Insets(0, 0, 0, 0);
                detailsPanel.add(commentLabel, gc);
                
                noteField = new JTextArea(3, 30);
                noteField.setText(transaction.getComment());
                noteField.setEnabled(false);
                
                gc.gridx++;
                gc.gridwidth = 3;
                gc.anchor = GridBagConstraints.WEST;
                gc.insets = new Insets(0, 0, 0, 5);
                detailsPanel.add(noteField, gc);
                
                JPanel buttonsPanel = new JPanel();
                
                int space = 15;
                Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
                Border titleBorder = BorderFactory.createTitledBorder("View Transaction");
                
                mainPanel = new JPanel();
                
                mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
                buttonsPanel.setBorder(BorderFactory.createEmptyBorder());
                
                mainPanel.setLayout(new BorderLayout());
                mainPanel.add(detailsPanel, BorderLayout.CENTER);
                
                try {
                    JPanel mods = new DetailsPanel(transaction.getCreatedBy(), transaction.getCreatedDate(), null, null);
                    mainPanel.add(mods, BorderLayout.SOUTH);
                } catch (RemoteException ex) {
                    Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ////////// BUTTONS PANEL //////////
                buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                buttonsPanel.add(closeButton);
                
                // Add sub panels to dialog
                setLayout(new BorderLayout());
                add(mainPanel, BorderLayout.CENTER);
                add(buttonsPanel, BorderLayout.SOUTH);
            } catch (RemoteException ex) {
                Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new TransactionDetails().setVisible(true);
//            }
//        });
//    }