/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.addressUsage;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.address.AddressSearch;
import interfaces.AddressInterface;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class CreateAddressUsage extends JFrame {
    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private final String addressType;
    private final int ref;
    private JPanel controlsPanel;
    private JTextField addrField;
    private int addrRef = -1;
    private JXDatePicker dateField;
    private JTextArea textArea;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public CreateAddressUsage(ClientImpl client, String addressType, int ref) {
        setClient(client);
        this.ref = ref;
        this.addressType = addressType;
        layoutComponents();
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
        
        addrField = new JTextField();
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int result = 0;
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Address Usage for " + addressType + ref + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    Date date = dateField.getDate();
                    String comment = textArea.getText();
                    try {
                        switch (addressType) {
                            case "Person":
                                result = client.createPersonAddressUsage(ref, addrRef, date, comment);
                                System.out.println("createPersonAddress");
                                break;
                            case "Application":
                                result = client.createApplicationAddressUsage(ref, addrRef, date, comment);
                                System.out.println("createApplicationAddress");
                                break;
                        }
                        
                        if (result > 0) {
                            String message = "The new Address Usage  for " + addressType + ref + " has been created and assigned Address Usage Ref " + result;
                            String title = "Information";
                            OKDialog.okDialog(CreateAddressUsage.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "There is some errors with the information supplied to CREATE the Address Usage\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateAddressUsage.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateAddressUsage.class.getName()).log(Level.SEVERE, null, ex);
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
        
        this.setSize(1100, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Address Usage");

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

        JLabel address = new JLabel("Address    ");
        Font font = address.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        address.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(address, gc);

        addrField.setFont(plainFont);
        Dimension dimension = addrField.getPreferredSize();
        dimension.setSize(dimension.getWidth() + 390, dimension.getHeight());
        addrField.setPreferredSize(dimension);
        addrField.setEnabled(false);
        addrField.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 4;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(addrField, gc);

        Image srchImge = null;
        JLabel addrThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateAddressUsage.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            addrThumb.setIcon(icon);
        }

        addrThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AddressSearch addrSearch = new AddressSearch(client);
                addrSearch.setVisible(true);
                addrSearch.setListener(new IntegerListener() {
                    @Override
                    public void intOmitted(int addressRef) {
                        try {
                            if (client.addressExists(addressRef)) {
                                AddressInterface address = client.getAddress(addressRef);
                                setAddrField(address.printAddress());
                                addrRef = address.getAddressRef();
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateAddressUsage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
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
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(addrThumb, gc);

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
        
        dateField = new JXDatePicker();
        dateField.setFormats(formatter);
        dateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(dateField, gc);

        JLabel comment = new JLabel("Comment    ");
        comment.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
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
    }
    
    private void setAddrField(String text) {
        addrField.setText(text);
    }
}
