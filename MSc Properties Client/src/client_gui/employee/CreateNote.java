/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.employee;

import client_application.ClientImpl;
import client_gui.OKDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class CreateNote extends JFrame {

    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextArea textArea;
    private JPanel controlsPanel;
    private final String noteType;
    private String code;
    private int ref;

    public CreateNote(ClientImpl client, String noteType, int ref) {
        super("MSc Properties");
        this.noteType = noteType;
        this.ref = ref;
        setClient(client);
        this.layoutComponents();
    }

    public CreateNote(ClientImpl client, String noteType, String code) {
        super("MSc Properties");
        this.noteType = noteType;
        this.code = code;
        setClient(client);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    private void layoutComponents() {

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        textArea = new JTextArea();
        textArea.setLineWrap(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String comment = textArea.getText();
                int result = 0;
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Note?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    try {
                        switch (noteType) {
                            case "Person":
                                result = client.createPersonNote(ref, comment);
                                System.out.println("createPersonNote");
                                break;
                            case "Office":
                                result = client.createOfficeNote(code, comment);
                                System.out.println("createOfficeNote");
                                break;
                            case "Involved Party":
                                result = client.createInvolvedPartyNote(ref, comment);
                                System.out.println("createInvolvedPartyNote");
                                break;
                            case "Application":
                                result = client.createApplicationNote(ref, comment);
                                break;
                            case "Employee":
                                result = client.createEmployeeNote(ref, comment);
                                System.out.println("createEmployeeNote");
                                break;
                            case "Landlord":
                                result = client.createLandlordNote(ref, comment);
                                System.out.println("createLandlordNote");
                                break;
                            case "Property":
                                result = client.createPropertyNote(ref, comment);
                                System.out.println("createPropertyNote");
                                break;
                            case "Job Role":
                                result = client.createJobRoleNote(code, comment);
                                System.out.println("createJobRoleNote");
                                break;
                            case "Tenancy":
                                result = client.createTenancyNote(ref, comment);
                                System.out.println("createTenancyNote");
                                break;
                            case "Lease":
                                result = client.createLeaseNote(ref, comment);
                                System.out.println("createLeaseNote");
                                break;
                            case "Contract":
                                result = client.createContractNote(ref, comment);
                                System.out.println("createContractNote");
                                break;
                            case "Rent Account":
                                result = client.createRentAccNote(ref, comment);
                                System.out.println("createRentAccNote");
                                break;
                            case "Lease Account":
                                result = client.createLeaseAccNote(ref, comment);
                                System.out.println("createLeaseAccNote");
                                break;
                            case "Employee Account":
                                result = client.createEmployeeAccNote(ref, comment);
                                System.out.println("createEmployeeAccNote");
                                break;
                        }
                        
                        if (result > 0) {
                            String message = "The new Note has been created and assigned Note Ref " + result;
                            String title = "Information";
                            OKDialog.okDialog(CreateNote.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "There is some errors with the information supplied to CREATE the Note\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateNote.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateNote.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(300, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Note");

        controlsPanel = new JPanel();
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        controlsPanel.setLayout(new BorderLayout());
        controlsPanel.add(textArea);

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

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new CreateNote().setVisible(true);
//            }
//        });
//    }
}
