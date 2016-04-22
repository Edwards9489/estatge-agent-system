/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.employee;

import client_application.ClientImpl;
import client_gui.DetailsPanel;
import interfaces.Note;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class NoteDetails extends JFrame {

    private ClientImpl client = null;
    private Note note = null;
    private JButton closeButton;
    private JTextArea textArea;
    private JPanel mainPanel;
    private JPanel controlsPanel;
    private int ref;

    public NoteDetails(ClientImpl client, Note note) {
        super("MSc Properties");
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

        closeButton = new JButton("Close");
        textArea = new JTextArea(9, 43);
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        try {
            textArea.setText(note.getNote());
        } catch (RemoteException ex) {
            Logger.getLogger(NoteDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                setVisible(false);
                dispose();
            }
        });
        
        this.setSize(550, 360);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("View Note");
        
        mainPanel = new JPanel();
        controlsPanel = new JPanel();
        controlsPanel.setSize(450, 200);
        
        mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.setLayout(new BorderLayout());
        controlsPanel.add(textArea, BorderLayout.CENTER);
        
        mainPanel.add(controlsPanel, BorderLayout.CENTER);
        
        try {
            JPanel mods = new DetailsPanel(note.getCreatedBy(), note.getCreatedDate(), note.getLastModifiedBy(), note.getLastModifiedDate());
            mainPanel.add(mods, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(NoteDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(closeButton);
        
        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new NoteDetails().setVisible(true);
//            }
//        });
//    }
}
