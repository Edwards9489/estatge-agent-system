/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.transaction;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.DetailsPanel;
import client_gui.OKDialog;
import client_gui.address.AddressDetails;
import client_gui.empAccount.EmpAccDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.leaseAcc.LeaseAccDetails;
import client_gui.login.LoginForm;
import client_gui.person.PersonDetails;
import client_gui.rentAcc.RentAccDetails;
import interfaces.AccountInterface;
import interfaces.EmployeeAccountInterface;
import interfaces.LeaseAccountInterface;
import interfaces.PersonInterface;
import interfaces.RentAccountInterface;
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
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
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
        
        setJMenuBar(createMenuBar());
        
        closeButton = new JButton("Close");
        noteField = new JTextArea(2, 50);
        noteField.setLineWrap(true);
        noteField.setEnabled(false);
        noteField.setDisabledTextColor(Color.BLACK);
        AccountInterface account = null;
        
        try {
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

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem userAccount = new JMenuItem("User Account");
        JMenuItem changeUser = new JMenuItem("Change User");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(userAccount);
        fileMenu.add(changeUser);
        fileMenu.addSeparator(); // Is the faint lines between grouped menu items
        fileMenu.add(exitItem);
        
        
        // Actions Menu
        JMenu actionsMenu = new JMenu("Actions");

        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(updateItem);
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        
        
        // Links Menu
        JMenu linksMenu = new JMenu("Link To");
        
        JMenuItem accountItem = new JMenuItem("Account");
        JMenuItem toItem = new JMenuItem("To Person");
        JMenuItem fromItem = new JMenuItem("From Person");
        
        linksMenu.add(accountItem);
        linksMenu.add(toItem);
        linksMenu.add(fromItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(actionsMenu);
        menuBar.add(helpMenu);

        // Set up Mnemonics for Menus
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // Set up Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        changeUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        userAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        manualItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        
        
        //Set up ActionListeners
        
        //File Menu
        
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int action = JOptionPane.showConfirmDialog(TransactionDetails.this,
                        "Do you really want to change user?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                
                if (action == JOptionPane.OK_OPTION) {
                    System.gc();
                    Window windows[] = Window.getWindows(); 
                    for (int i=0; i<windows.length; i++) {
                        windows[i].dispose(); 
                        windows[i]=null;
                    }
                    new LoginForm().setVisible(true);
                    dispose();
                }
            }
        });

        userAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                UpdateEmployeeSecurity securityGUI = new UpdateEmployeeSecurity(client);
                securityGUI.setVisible(true);
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                int action = JOptionPane.showConfirmDialog(TransactionDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(AddressDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        
        
        // Actions Menu

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Transaction " + transaction.getTransactionRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println("Transaction Delete - Yes button clicked");
                        int result = 0;
                        
                        switch (transactionType) {
                            case "Rent Account":
                                result = client.deleteRentAccTransaction(transaction.getTransactionRef(), transaction.getAccountRef());
                                break;

                            case "Lease Account":
                                result = client.deleteLeaseAccTransaction(transaction.getTransactionRef(), transaction.getAccountRef());
                                break;

                            case "Employee Account":
                                result = client.deleteEmployeeAccTransaction(transaction.getTransactionRef(), transaction.getAccountRef());
                                break;
                        }
                        
                        if (result > 0) {
                            String message = "Transaction " + transaction.getTransactionRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(TransactionDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Transaction " + transaction.getTransactionRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(TransactionDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        // Links Menu
        
        accountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    switch(transactionType) {
                        case "Rent Account":
                            RentAccountInterface rentAcc = client.getRentAccount(transaction.getAccountRef());
                            if (rentAcc != null) {
                                RentAccDetails rentAccDetails = new RentAccDetails(client, rentAcc);
                                rentAccDetails.setVisible(true);
                            }
                            break;
                            
                        case "Lease Account":
                            LeaseAccountInterface leaseAcc = client.getLeaseAccount(transaction.getAccountRef());
                            if (leaseAcc != null) {
                                LeaseAccDetails leaseAccDetails = new LeaseAccDetails(client, leaseAcc);
                                leaseAccDetails.setVisible(true);
                            }
                            break;
                            
                        case "Employee Account":
                            EmployeeAccountInterface empAcc = client.getEmployeeAccount(transaction.getAccountRef());
                            if (empAcc != null) {
                                EmpAccDetails empAccDetails = new EmpAccDetails(client, empAcc);
                                empAccDetails.setVisible(true);
                            }
                            break;
                            
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        toItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    PersonInterface person = client.getPerson(transaction.getToRef());
                    if (person != null) {
                        PersonDetails personDetails = new PersonDetails(client, person);
                        personDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        fromItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    PersonInterface person = client.getPerson(transaction.getFromRef());
                    if (person != null) {
                        PersonDetails personDetails = new PersonDetails(client, person);
                        personDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        // Help Menu

        manualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                // NEED TO DEVELOP USER MANUAL
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                AboutFrame about = new AboutFrame();
                about.setVisible(true);
            }
        });
        
        return menuBar;
    }
}
