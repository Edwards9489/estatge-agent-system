/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.landlord;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.person.PersonSearch;
import interfaces.PersonInterface;
import java.awt.BorderLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class CreateLandlord extends JFrame {
    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    
    private JTextField personField;
    private int personRef = -1;
    
    public CreateLandlord(ClientImpl client) {
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
        personField = new JTextField(30);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int result = 0;
                
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Landlord?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    if (personRef > 0) {
                        try {
                            result = client.createLandlord(personRef);
                            
                            System.out.println("Result: " + result);
                            
                            if (result > 0) {
                                String message = "The new Landlord has been created with and assigned Landlord Ref " + result;
                                String title = "Information";
                                OKDialog.okDialog(CreateLandlord.this, message, title);
                                setVisible(false);
                                dispose();
                                System.out.println("DISPOSED");
                            } else {
                                String message = "There is some errors with the information supplied to CREATE the new Landlord\nPlease check the information supplied";
                                String title = "Error";
                                OKDialog.okDialog(CreateLandlord.this, message, title);
                                System.out.println("ERRORS");
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateLandlord.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        String message = "There is some errors with the information supplied to CREATE the new Landlord\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(CreateLandlord.this, message, title);
                        System.out.println("ERRORS");
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
        
        this.setMinimumSize(new Dimension(650, 250));
        this.setSize(650, 250);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Landlord");
        
        controlsPanel = new JPanel();
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());
        controlsPanel.setLayout(new GridBagLayout());
        
        
        GridBagConstraints gc = new GridBagConstraints();
        
        //////////////// FIRST ROW ///////////////////
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel personLabel = new JLabel("Person  ");
        Font font = personLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        personLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(personLabel, gc);
        
        personField.setFont(plainFont);
        
        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(personField, gc);
        
        Image srchImge = null;
        JLabel personThumb = new JLabel();
        
        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateLandlord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            personThumb.setIcon(icon);
        }
        
        personThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PersonSearch personSearch = new PersonSearch(client);
                personSearch.setVisible(true);
                personSearch.setListener(new IntegerListener() {
                    @Override
                    public void intOmitted(int pRef) {
                        try {
                            if (client.personExists(pRef) && !client.personLandlordExists(pRef)) {
                                PersonInterface person = client.getPerson(pRef);
                                if (person != null) {
                                    setPersonField(person.getName());
                                    personRef = person.getPersonRef();
                                }
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(CreateLandlord.class.getName()).log(Level.SEVERE, null, ex);
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
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(personThumb, gc);
        
        
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    private void setPersonField(String text) {
        personField.setText(text);
    }
}
