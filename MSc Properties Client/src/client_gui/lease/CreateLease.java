/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.property.PropertySearch;
import interfaces.OfficeInterface;
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
public class CreateLease extends JFrame {

    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField propField;
    private JTextField lengthField;
    private JXDatePicker dateField;
    private JTextField expenditureField;
    private JCheckBox isManagementField;
    private JComboBox officeField;
    private SimpleDateFormat formatter;
    private JPanel controlsPanel;

    public CreateLease(ClientImpl client) {
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
        NumberFormat propFormat = NumberFormat.getNumberInstance();
        propFormat.setMaximumFractionDigits(0);
        propFormat.setMaximumIntegerDigits(10);

        NumberFormat lengthFormat = NumberFormat.getNumberInstance();
        lengthFormat.setMaximumFractionDigits(0);
        lengthFormat.setMaximumIntegerDigits(2);

        formatter = new SimpleDateFormat("dd-MM-yyyy");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        propField = new JFormattedTextField(propFormat);
        lengthField = new JFormattedTextField(lengthFormat);
        expenditureField = new JTextField(10);
        dateField = new JXDatePicker();
        dateField.setFormats(formatter);
        isManagementField = new JCheckBox();
        officeField = new JComboBox();
        
        officeField.addItem("-");

        try {
            for (OfficeInterface temp : client.getOffices()) {
                if (temp.isCurrent()) {
                    String office = temp.getOfficeCode();
                    officeField.addItem(office);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CreateLease.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String propText = null;
                String lengthText = null;
                String expenditureText = null;
                boolean correctInput = false;
                try {
                    propText = propField.getText();
                    lengthText = lengthField.getText();
                    expenditureText = expenditureField.getText();
                    correctInput = true;
                } catch (Exception ex) {
                    Logger.getLogger(CreateLease.class.getName()).log(Level.SEVERE, null, ex);
                    errorMessage();
                }
                
                if (correctInput) {
                    Date startDate = dateField.getDate();
                    int propRef = 0;
                    int length = 0;
                    double expenditure = 0;
                    propRef = Integer.parseInt(propText);
                    length = Integer.parseInt(lengthText);
                    expenditure = Double.parseDouble(expenditureText);
                    
                    String officeCode = (String) officeField.getSelectedItem();

                    System.out.println("Prop Ref: " + propRef);
                    System.out.println("Length: " + length);
                    System.out.println("Start Date (Field): " + dateField.getDate());
                    System.out.println("Expenditure: " + expenditure);
                    System.out.println("Full Management: " + isManagementField.isSelected());
                    System.out.println("Office Code: " + officeCode);

                    int result;
                    try {
                        if (propRef > 0 && length > 0 && startDate != null && expenditure > 0 && officeCode != null) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new lease for Property " + propRef + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (answer == JOptionPane.YES_OPTION) {
                                result = client.createLease(startDate, length, propRef, isManagementField.isSelected(), expenditure, officeCode);
                                if (result > 0) {
                                    String message = "The new lease for Property " + propRef + " has been created and assigned Lease Ref " + result;
                                    String title = "Information";
                                    OKDialog.okDialog(CreateLease.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to CREATE a new lease for Property " + propRef + " and Property " + propRef + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(CreateLease.this, message, title);
                                }
                            }
                        } else {
                            String message = "There is some errors with the information supplied to CREATE a new lease for Property " + propRef + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateLease.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateLease.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(900, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Lease");
        
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

        JLabel prop = new JLabel("Property Ref *    ");
        Font font = prop.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        prop.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(prop, gc);

        propField.setFont(plainFont);
        Dimension dimension = propField.getPreferredSize();
        dimension.setSize(dimension.getWidth() + 80, dimension.getHeight());
        propField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(propField, gc);

        Image srchImge = null;
        JLabel propThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateLease.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            propThumb.setIcon(icon);
        }

        propThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PropertySearch propSearch = new PropertySearch(client);
                propSearch.setVisible(true);
                propSearch.setListener(new IntegerListener() {
                    @Override
                    public void intOmitted(int propRef) {
                        setPropField(propRef);
                    }
                });
            }
        });
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(propThumb, gc);

        JLabel length = new JLabel("Length *    ");
        length.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(length, gc);

        lengthField.setFont(plainFont);
        lengthField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(lengthField, gc);

        JLabel startDate = new JLabel("Start Date *    ");
        startDate.setFont(boldFont);

        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(startDate, gc);

        dateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(dateField, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel expenditure = new JLabel("Expenditure (£) *    ");
        expenditure.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(expenditure, gc);

        expenditureField.setFont(plainFont);
        expenditureField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(expenditureField, gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);

        JLabel management = new JLabel("Full Management *    ");
        management.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(management, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(isManagementField, gc);

        JLabel office = new JLabel("Office *    ");
        office.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(office, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(officeField, gc);
        
        
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

    public void setPropField(int propRef) {
        if (propField != null) {
            propField.setText(String.valueOf(propRef));
        }
    }

    public void setOfficeField(String officeCode) {
        if (officeField != null) {
            officeField.setSelectedItem(officeCode);
        }
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new CreateLease().setVisible(true);
//            }
//        });
//    }
}
