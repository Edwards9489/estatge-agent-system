/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.office;

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
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Dwayne
 */
public class CreateOffice extends JFrame {
    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    private int addrRef = -1;
    private JTextField addrField;
    private JXDatePicker dateField;
    private JTextField longField;
    private JTextField latField;
    private JTextField codeField;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public CreateOffice(ClientImpl client) {
        setClient(client);
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
        codeField = new JTextField(8);
        longField = new JTextField(8);
        latField = new JTextField(8);
        addrField = new JTextField(55);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int result = 0;
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Office?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    String code = codeField.getText();
                    Date date = dateField.getDate();
                    Double addrLong = null;
                    Double addrLat = null;
                    if (longField.getText() != null) {
                        addrLong = Double.parseDouble(longField.getText());
                    }
                    if (latField.getText() != null) {
                        addrLat = Double.parseDouble(latField.getText());
                    }
                    if (code != null && date != null && addrRef > 0 && addrLong != null && addrLat != null) {
                        try {
                            result = client.createOffice(code, addrRef, addrLong, addrLat, date);

                            if (result > 0) {
                                String message = "The new Office has been created with unique Office Code " + code;
                                String title = "Information";
                                OKDialog.okDialog(CreateOffice.this, message, title);
                                setVisible(false);
                                dispose();
                            } else {
                                String message = "There is some errors with the information supplied to CREATE the Office\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(CreateOffice.this, message, title);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateOffice.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
        
        this.setSize(1100, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Office");

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

        JLabel office = new JLabel("Office Code *    ");
        Font font = office.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        office.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(office, gc);
        
        codeField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(codeField, gc);
        
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
        
        JLabel longLabel = new JLabel("Address Long    ");
        longLabel.setFont(boldFont);
        

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(longLabel, gc);
        
        longField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(longField, gc);
        
        JLabel latLabel = new JLabel("Address Lat    ");
        latLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(latLabel, gc);
        
        latField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(latField, gc);
        

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel address = new JLabel("Address *    ");
        address.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(address, gc);

        addrField.setFont(plainFont);
        addrField.setEnabled(false);
        addrField.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 6;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(addrField, gc);

        Image srchImge = null;
        JLabel addrThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateOffice.class.getName()).log(Level.SEVERE, null, ex);
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
                                if (address != null) {
                                    setAddrField(address.printAddress());
                                    addrRef = address.getAddressRef();
                                }
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateOffice.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(addrThumb, gc);
        

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
