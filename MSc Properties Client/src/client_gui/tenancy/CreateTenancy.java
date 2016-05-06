/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.tenancy;

import client_gui.property.PropertySearch;
import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.application.AppSearch;
import interfaces.Element;
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
public class CreateTenancy extends JFrame {

    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField appField;
    private JTextField propField;
    private JTextField lengthField;
    private JXDatePicker dateField;
    private JComboBox tenTypeField;
    private JComboBox officeField;
    private SimpleDateFormat formatter;
    private JPanel controlsPanel;

    public CreateTenancy(ClientImpl client) {
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
        appField = new JFormattedTextField(propFormat);
        propField = new JFormattedTextField(propFormat);
        lengthField = new JFormattedTextField(lengthFormat);
        dateField = new JXDatePicker();
        dateField.setFormats(formatter);
        tenTypeField = new JComboBox();
        officeField = new JComboBox();
        
        officeField.addItem("-");
        tenTypeField.addItem("-");

        try {
            for (OfficeInterface temp : client.getOffices()) {
                if (temp.isCurrent()) {
                    String office = temp.getOfficeCode();
                    officeField.addItem(office);
                }
            }

            for (Element temp : client.getTenancyTypes()) {
                if (temp.isCurrent()) {
                    String tenTypeCode = temp.getCode();
                    tenTypeField.addItem(tenTypeCode);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CreateTenancy.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String appText = null;
                String propText = null;
                String lengthText = null;
                boolean correctInput = false;
                try {
                    appText = appField.getText();
                    propText = propField.getText();
                    lengthText = lengthField.getText();
                    correctInput = true;
                } catch (Exception ex) {
                    Logger.getLogger(CreateTenancy.class.getName()).log(Level.SEVERE, null, ex);
                    errorMessage();
                }
                
                if (correctInput) {
                    Date startDate = dateField.getDate();
                    int appRef = 0;
                    int propRef = 0;
                    int length = 0;
                    appRef = Integer.parseInt(appText);
                    propRef = Integer.parseInt(propText);
                    length = Integer.parseInt(lengthText);
                    
                    String tenTypeCode = (String) tenTypeField.getSelectedItem();
                    String officeCode = (String) officeField.getSelectedItem();

                    System.out.println("App Ref: " + appRef);
                    System.out.println("Prop Ref: " + propRef);
                    System.out.println("Length: " + length);
                    System.out.println("Start Date (Field): " + dateField.getDate());
                    System.out.println("Ten Type Code: " + tenTypeCode);
                    System.out.println("Office Code: " + officeCode);

                    int result;
                    try {
                        if (appRef > 0 && propRef > 0 && length > 0 && startDate != null && tenTypeCode != null && officeCode != null) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new tenancy for Application " + appRef + " and Property " + propRef + " ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (answer == JOptionPane.YES_OPTION) {
                                result = client.createTenancy(startDate, length, propRef, appRef, tenTypeCode, officeCode);
                                if (result > 0) {
                                    String message = "The new tenancy for Application " + appRef + " and Property " + propRef + " has been created and assigned Tenancy Ref " + result;
                                    String title = "Information";
                                    OKDialog.okDialog(CreateTenancy.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to CREATE a new tenancy for Application " + appRef + " and Property " + propRef + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(CreateTenancy.this, message, title);
                                }
                            }
                        } else {
                            String message = "There is some errors with the information supplied to CREATE a new tenancy for Application " + appRef + " and Property " + propRef + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateTenancy.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateTenancy.class.getName()).log(Level.SEVERE, null, ex);
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

        this.setSize(800, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Tenancy");
        
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

        JLabel app = new JLabel("Application Ref *    ");
        Font font = app.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        app.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(app, gc);

        appField.setFont(plainFont);
        Dimension dimension = appField.getPreferredSize();
        dimension.setSize(dimension.getWidth() + 80, dimension.getHeight());
        appField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(appField, gc);

        Image srchImge = null;
        JLabel appThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateTenancy.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        
        JLabel prop = new JLabel("Property Ref *    ");
        prop.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(prop, gc);

        propField.setFont(plainFont);
        propField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(propField, gc);

        Image srchImge2 = null;
        JLabel propThumb = new JLabel();

        try {
            srchImge2 = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateTenancy.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge2 != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            propThumb.setIcon(icon);
        }

        propThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PropertySearch propSearch = new PropertySearch(client, new IntegerListener() {
                    @Override
                    public void intOmitted(int propRef) {
                        setPropField(propRef);
                    }
                });
                propSearch.setVisible(true);
            }
        });
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(propThumb, gc);

        JLabel tenType = new JLabel("Tenancy Type *    ");
        tenType.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(tenType, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(tenTypeField, gc);

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

    public void setAppField(int appRef) {
        if (appField != null) {
            appField.setText(String.valueOf(appRef));
        }
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
//                new CreateTenancy().setVisible(true);
//            }
//        });
//    }
}
