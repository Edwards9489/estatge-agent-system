/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.employee;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import interfaces.JobRoleInterface;
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
public class CreateContract extends JFrame {

    private ClientImpl client = null;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField empField;
    private JTextField lengthField;
    private JXDatePicker dateField;
    private JComboBox jobRoleField;
    private JComboBox officeField;
    private SimpleDateFormat formatter;
    private JPanel controlsPanel;

    public CreateContract(ClientImpl client) {
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

        formatter = new SimpleDateFormat("dd-MM-YYYY");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        empField = new JFormattedTextField(empFormat);
        lengthField = new JFormattedTextField(lengthFormat);
        dateField = new JXDatePicker();
        dateField.setFormats(formatter);
        jobRoleField = new JComboBox();
        officeField = new JComboBox();
        
        officeField.addItem("-");
        jobRoleField.addItem("-");

        try {
            for (OfficeInterface temp : client.getOffices()) {
                if (temp.isCurrent()) {
                    String office = temp.getOfficeCode();
                    officeField.addItem(office);
                }
            }

            for (JobRoleInterface temp : client.getJobRoles()) {
                if (temp.isCurrent()) {
                    String jobRole = temp.getJobRoleCode();
                    //jobRole += " : " + temp.getJobDescription();
                    jobRoleField.addItem(jobRole);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CreateContract.class.getName()).log(Level.SEVERE, null, ex);
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String empText = null;
                String lengthText = null;
                boolean correctInput = false;
                try {
                    empText = empField.getText();
                    lengthText = lengthField.getText();
                    correctInput = true;
                } catch (Exception ex) {
                    Logger.getLogger(CreateContract.class.getName()).log(Level.SEVERE, null, ex);
                    errorMessage();
                }
                
                if (correctInput) {
                    Date startDate = dateField.getDate();
                    int emp = 0;
                    int length = 0;
                    emp = Integer.parseInt(empText);
                    length = Integer.parseInt(lengthText);
                    
                    String jobCode = (String) jobRoleField.getSelectedItem();
                    String officeCode = (String) officeField.getSelectedItem();

                    System.out.println("Emp: " + emp);
                    System.out.println("Length: " + length);
                    System.out.println("Start Date (Field): " + dateField.getDate());
                    System.out.println("Job Role Code: " + jobCode);
                    System.out.println("Office Code: " + officeCode);

                    int result;
                    try {
                        if (emp > -1 && length > -1 && startDate != null && jobCode != null && officeCode != null) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new contract for Employee " + emp + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (answer == JOptionPane.YES_OPTION) {
                                result = client.createContract(startDate, length, emp, jobCode, officeCode);
                                if (result > 0) {
                                    String message = "The new contract for Employee " + emp + " has been created and assigned Contract Ref " + result;
                                    String title = "Information";
                                    OKDialog.okDialog(CreateContract.this, message, title);
                                    setVisible(false);
                                    dispose();
                                } else {
                                    String message = "There is some errors with the information supplied to CREATE a new contract for Employee " + emp + "\nPlease check the information supplied";
                                    String title = "Error";
                                    OKDialog.okDialog(CreateContract.this, message, title);
                                }
                            }
                        } else {
                            String message = "There is some errors with the information supplied to CREATE a new contract for Employee " + emp + "\nPlease check the information supplied";
                            String title = "Error";
                            OKDialog.okDialog(CreateContract.this, message, title);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CreateContract.class.getName()).log(Level.SEVERE, null, ex);
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
        Border titleBorder = BorderFactory.createTitledBorder("Create Contract");
        
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

        JLabel emp = new JLabel("Employee Ref *    ");
        Font font = emp.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        emp.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(emp, gc);

        empField.setFont(plainFont);
        Dimension dimension = empField.getPreferredSize();
        dimension.setSize(dimension.getWidth() + 80, dimension.getHeight());
        empField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(empField, gc);

        Image srchImge = null;
        JLabel empThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateContract.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            empThumb.setIcon(icon);
        }

        empThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EmployeeSearch empSearch = new EmployeeSearch(client);
                empSearch.setVisible(true);
                empSearch.setListener(new IntegerListener() {
                    @Override
                    public void intOmitted(int empRef) {
                        setEmpField(empRef);
                    }
                });
            }
        });
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(empThumb, gc);

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
        dateField.setPreferredSize(dimension);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(dateField, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel jobRole = new JLabel("Job Role *    ");
        jobRole.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(jobRole, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(jobRoleField, gc);

        JLabel office = new JLabel("Office *    ");
        office.setFont(boldFont);

        gc.gridx++;
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(office, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(officeField, gc);

//        ////////// NEXT ROW //////////
//        
//        gc.gridx = 0;
//        gc.gridy++;
//
//        gc.weightx = 1;
//        gc.weighty = 1;
//        
//        JLabel newDate = new JLabel("New Date *    ");
//        newDate.setFont(boldFont);
//
//        gc.fill = GridBagConstraints.NONE;
//        gc.anchor = GridBagConstraints.EAST;
//        gc.insets = new Insets(0, 0, 0, 0);
//        controlsPanel.add(newDate, gc);
//
//        gc.gridx++;
//        gc.anchor = GridBagConstraints.WEST;
//        gc.insets = new Insets(0, 0, 0, 5);
//        controlsPanel.add(dateField, gc);
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

    private void setEmpField(int empRef) {
        if (empField != null) {
            empField.setText(String.valueOf(empRef));
        }
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new CreateContract().setVisible(true);
//            }
//        });
//    }
}
