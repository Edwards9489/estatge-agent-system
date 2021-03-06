/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.invParty;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.application.AppSearch;
import client_gui.person.PersonSearch;
import interfaces.Element;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
public class CreateInvParty extends JFrame {

    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField personRefField;
    private JTextField appRefField;
    private JCheckBox mainField;
    private JCheckBox jointField;
    private JXDatePicker dateField;
    private JComboBox relationshipField;
    private SimpleDateFormat formatter;
    private JPanel controlsPanel;

    public CreateInvParty(ClientImpl client) {
        super("MSc Properties");
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
        NumberFormat empFormat = NumberFormat.getNumberInstance();
        empFormat.setMaximumFractionDigits(0);
        empFormat.setMaximumIntegerDigits(10);

        NumberFormat lengthFormat = NumberFormat.getNumberInstance();
        lengthFormat.setMaximumFractionDigits(0);
        lengthFormat.setMaximumIntegerDigits(2);

        formatter = new SimpleDateFormat("dd-MM-yyyy");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        personRefField = new JFormattedTextField(empFormat);
        appRefField = new JFormattedTextField(lengthFormat);
        mainField = new JCheckBox();
        jointField = new JCheckBox();
        dateField = new JXDatePicker();
        dateField.setFormats(formatter);
        relationshipField = new JComboBox();
        relationshipField.addItem("  ---  ");

        try {
            for (Element temp : client.getRelationships()) {
                if (temp.isCurrent()) {
                    String relationshipCode = temp.getCode();
                    relationshipField.addItem(relationshipCode);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CreateInvParty.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String personRefText = null;
                String appRefText = null;
                String relationshipCode = null;
                Date startDate = null;
                boolean correctInput = false;
                try {
                    if (!relationshipField.getSelectedItem().equals("  ---  ")) {
                        if (dateField.getDate() != null) {
                            relationshipCode = (String) relationshipField.getSelectedItem();
                            personRefText = personRefField.getText();
                            appRefText = appRefField.getText();
                            startDate = dateField.getDate();
                            correctInput = true;
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CreateInvParty.class.getName()).log(Level.SEVERE, null, ex);
                    errorMessage();
                }
                
                if (correctInput) {
                    int pRef = 0;
                    int aRef = 0;
                    pRef = Integer.parseInt(personRefText);
                    aRef = Integer.parseInt(appRefText);

                    System.out.println("Person Ref: " + pRef);
                    System.out.println("App Ref: " + aRef);
                    System.out.println("Start Date: " + dateField.getDate());
                    System.out.println("Main: " + mainField.isSelected());
                    System.out.println("Joint: " + jointField.isSelected());
                    System.out.println("Relationship: " + relationshipCode);

                    int result;
                    try {
                        if (pRef > 0 && aRef > 0 && startDate != null && relationshipCode != null) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Involved Party for Application " + aRef + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (answer == JOptionPane.YES_OPTION) {
                                result = client.createInvolvedParty(pRef, aRef, jointField.isSelected(), mainField.isSelected(), startDate, relationshipCode);
                                if (result > 0) {
                                    String message = "The new involved party for Application " + aRef + " has been created and assigned Involved Party Ref " + result;
                                    String title = "Information";
                                    OKDialog.okDialog(CreateInvParty.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to CREATE a new Involved Party for Application " + aRef + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(CreateInvParty.this, message, title);
                                }
                            }
                        } else {
                            String message = "There is some errors with the information supplied to CREATE a new Involved Party for Application " + aRef + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateInvParty.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateInvParty.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (!correctInput) {
                    errorMessage();
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

        this.setSize(700, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Involved Party");
        
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

        JLabel personRef = new JLabel("Person Ref *    ");
        Font font = personRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        personRef.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(personRef, gc);

        personRefField.setFont(plainFont);
        Dimension dimension = personRefField.getPreferredSize();
        dimension.setSize(dimension.getWidth() + 80, dimension.getHeight());
        personRefField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(personRefField, gc);

        Image srchImge = null;
        JLabel perThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateInvParty.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            perThumb.setIcon(icon);
        }

        perThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PersonSearch personSearch = new PersonSearch(client);
                personSearch.setVisible(true);
                personSearch.setListener(new IntegerListener() {
                    @Override
                    public void intOmitted(int empRef) {
                        setPersonField(empRef);
                    }
                });
            }
        });
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(perThumb, gc);

        JLabel appRef = new JLabel("App Ref *    ");
        appRef.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(appRef, gc);

        appRefField.setFont(plainFont);
        appRefField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(appRefField, gc);
        
        JLabel appThumb = new JLabel();
        
        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            appThumb.setIcon(icon);
        }
        
        appThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AppSearch appSearch = new AppSearch(client, new IntegerListener() {
                    @Override
                    public void intOmitted(int appRef) {
                        setAppField(appRef);
                    }
                });
                appSearch.setVisible(true);
            }
        });
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(appThumb, gc);

        JLabel main = new JLabel("Main Ind *    ");
        main.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(main, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(mainField, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel startDate = new JLabel("Start Date *    ");
        startDate.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(startDate, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(dateField, gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);

        JLabel relationship = new JLabel("Relationship *    ");
        relationship.setFont(boldFont);
        
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(relationship, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(relationshipField, gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        JLabel joint = new JLabel("Joint Ind *    ");
        joint.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(joint, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(jointField, gc);
        
        
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

    private void errorMessage() {
        GridBagConstraints gc = new GridBagConstraints();

        ////////// ERROR ROW //////////
        gc.gridx = 0;
        gc.gridy = 2;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel error = new JLabel("Check Information Entered and Try Again!");
        Font font = error.getFont();
        Font errorFont = new Font(font.getName(), Font.BOLD, 17);
        error.setForeground(Color.red);
        error.setFont(errorFont);

        gc.gridwidth = 7;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(error, gc);
        
        pack();
        repaint();
    }

    public void setPersonField(int pRef) {
        if (personRefField != null) {
            personRefField.setText(String.valueOf(pRef));
        }
    }

    public void setAppField(int appRef) {
        if (appRefField != null) {
            appRefField.setText(String.valueOf(appRef));
        }
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new CreateInvParty().setVisible(true);
//            }
//        });
//    }
}
