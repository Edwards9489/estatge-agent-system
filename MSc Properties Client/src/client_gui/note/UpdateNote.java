/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.note;

import client_application.ClientImpl;
import client_gui.OKDialog;
import interfaces.Note;
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
public class UpdateNote extends JFrame {

    private ClientImpl client = null;
    private Note note = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextArea textArea;
    private JPanel controlsPanel;
    private final String noteType;
    private String code;
    private int ref;

    public UpdateNote(ClientImpl client, Note note, String noteType, int ref) {
        super("MSc Properties");
        this.noteType = noteType;
        this.ref = ref;
        setClient(client);
        setNote(note);
        this.layoutComponents();
    }

    public UpdateNote(ClientImpl client, Note note, String noteType, String code) {
        super("MSc Properties");
        this.noteType = noteType;
        this.code = code;
        setClient(client);
        setNote(note);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    // Use of singleton pattern to ensure only one Note is initiated
    private void setNote(Note note) {
        if (this.note == null) {
            this.note = note;
        }
    }

    private void layoutComponents() {

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        try {
            textArea.setText(note.getNote());
        } catch (RemoteException ex) {
            Logger.getLogger(UpdateNote.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    String comment = textArea.getText();
                    int result = 0;
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to UPDATE Note " + note.getReference() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        try {
                            switch (noteType) {
                                case "Person":
                                    result = client.updatePersonNote(ref, note.getReference(), comment);
                                    System.out.println("updatePersonNote");
                                    break;
                                case "Office":
                                    result = client.updateOfficeNote(code, note.getReference(), comment);
                                    System.out.println("updateOfficeNote");
                                    break;
                                case "Involved Party":
                                    result = client.updateInvolvedPartyNote(ref, note.getReference(), comment);
                                    System.out.println("updateInvolvedPartyNote");
                                    break;
                                case "Application":
                                    result = client.updateApplicationNote(ref, note.getReference(), comment);
                                    System.out.println("updateInvolvedPartyNote");
                                    break;
                                case "Employee":
                                    result = client.updateEmployeeNote(ref, note.getReference(), comment);
                                    System.out.println("updateEmployeeNote");
                                    break;
                                case "Landlord":
                                    result = client.updateLandlordNote(ref, note.getReference(), comment);
                                    System.out.println("updateLandlordNote");
                                    break;
                                case "Property":
                                    result = client.updatePropertyNote(ref, note.getReference(), comment);
                                    System.out.println("updatePropertyNote");
                                    break;
                                case "Job Role":
                                    result = client.updateJobRoleNote(code, note.getReference(), comment);
                                    System.out.println("updateJobRoleNote");
                                    break;
                                case "Tenancy":
                                    result = client.updateTenancyNote(ref, note.getReference(), comment);
                                    System.out.println("updateTenancyNote");
                                    break;
                                case "Lease":
                                    result = client.updateLeaseNote(ref, note.getReference(), comment);
                                    System.out.println("updateLeaseNote");
                                    break;
                                case "Contract":
                                    result = client.updateContractNote(ref, note.getReference(), comment);
                                    System.out.println("updateContractNote");
                                    break;
                                case "Rent Account":
                                    result = client.updateRentAccNote(ref, note.getReference(), comment);
                                    System.out.println("updateRentAccNote");
                                    break;
                                case "Lease Account":
                                    result = client.updateLeaseAccNote(ref, note.getReference(), comment);
                                    System.out.println("updateLeaseAccNote");
                                    break;
                                case "Employee Account":
                                    result = client.updateEmployeeAccNote(ref, note.getReference(), comment);
                                    System.out.println("updateEmployeeAccNote");
                                    break;
                            }
                            
                            if (result > 0) {
                                String message = "Note " + note.getReference() + " has been amended successfully";
                                String title = "Information";
                                OKDialog.okDialog(UpdateNote.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to UPDATE Note " + note.getReference() + "\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(UpdateNote.this, message, title);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(UpdateNote.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(UpdateNote.class.getName()).log(Level.SEVERE, null, ex);
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
//                new UpdateNote().setVisible(true);
//            }
//        });
//    }
}
