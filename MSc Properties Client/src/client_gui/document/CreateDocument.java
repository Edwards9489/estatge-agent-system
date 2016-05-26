/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.document;

import client_application.ClientImpl;
import client_gui.DOCFileFilter;
import client_gui.JPEGFileFilter;
import client_gui.OKDialog;
import client_gui.PDFFileFilter;
import client_gui.PNGFileFilter;
import client_gui.TXTFileFilter;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
public class CreateDocument extends JFrame {
    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private final String documentType;
    private int objectRef;
    private String objectCode;
    private JPanel controlsPanel;
    private JTextField fileNameField;
    private File file = null;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    
    public CreateDocument(ClientImpl client, String documentType, int ref) {
        setClient(client);
        this.objectRef = ref;
        this.documentType = documentType;
        layoutComponents();
    }
    
    public CreateDocument(ClientImpl client, String documentType, String code) {
        setClient(client);
        this.objectCode = code;
        this.documentType = documentType;
        layoutComponents();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    private void layoutComponents() {
        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new DOCFileFilter());
        fileChooser.addChoosableFileFilter(new JPEGFileFilter());
        fileChooser.addChoosableFileFilter(new PDFFileFilter());
        fileChooser.addChoosableFileFilter(new PNGFileFilter());
        fileChooser.addChoosableFileFilter(new TXTFileFilter());
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        fileNameField = new JTextField(25);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int result = 0;
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Document for " + documentType + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    String comment = textArea.getText();
                    if (file != null) {
                        try {
                            switch (documentType) {
                                case "Person":
                                    result = client.createPersonDocument(objectRef, file, comment);
                                    System.out.println("createPersonDocument");
                                    break;
                                case "Application":
                                    result = client.createApplicationDocument(objectRef, file, comment);
                                    System.out.println("createApplicationDocument");
                                    break;
                                case "Office":
                                    result = client.createOfficeDocument(objectCode, file, comment);
                                    System.out.println("createOfficeDocument");
                                    break;
                                case "Property":
                                    result = client.createPropertyDocument(objectRef, file, comment);
                                    System.out.println("createPropertyDocument");
                                    break;
                                case "Tenancy":
                                    result = client.createTenancyDocument(objectRef, file, comment);
                                    System.out.println("createTenancyDocument");
                                    break;
                                case "Lease":
                                    result = client.createLeaseDocument(objectRef, file, comment);
                                    System.out.println("createLeaseDocument");
                                    break;
                                case "Contract":
                                    result = client.createContractDocument(objectRef, file, comment);
                                    System.out.println("createContractDocument");
                                    break;
                                case "Rent Account":
                                    result = client.createRentAccDocument(objectRef, file, comment);
                                    System.out.println("createRentAccDocument");
                                    break;
                                case "Lease Account":
                                    result = client.createLeaseAccDocument(objectRef, file, comment);
                                    System.out.println("createLeaseAccDocument");
                                    break;
                                case "Employee Account":
                                    result = client.createEmployeeAccDocument(objectRef, file, comment);
                                    System.out.println("createEmployeeAccDocument");
                                    break;
                                
                            }

                            if (result > 0) {
                                String message = "The new Document for " + documentType + " has been created and assigned Document Ref " + result;
                                String title = "Information";
                                OKDialog.okDialog(CreateDocument.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to CREATE the Document\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(CreateDocument.this, message, title);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateDocument.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        String message = "There is some errors with the information supplied to CREATE the Document\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(CreateDocument.this, message, title);
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
        
        this.setSize(700, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create " + documentType + " Document");

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

        JLabel fileNameLabel = new JLabel("File    ");
        Font font = fileNameLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        fileNameLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(fileNameLabel, gc);

        fileNameField.setFont(plainFont);
        fileNameField.setEnabled(false);
        fileNameField.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(fileNameField, gc);

        Image srchImge = null;
        JLabel fileSearchThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateDocument.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            fileSearchThumb.setIcon(icon);
        }

        fileSearchThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(fileChooser.showOpenDialog(CreateDocument.this) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if (file != null) {
                        fileNameField.setText(file.getName());
                    }
                }
            }
        });
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(fileSearchThumb, gc);

        ////////// NEXT ROW //////////
        
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
        
        textArea = new JTextArea(3, 30);
        textArea.setLineWrap(true);
        textArea.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 4;
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
