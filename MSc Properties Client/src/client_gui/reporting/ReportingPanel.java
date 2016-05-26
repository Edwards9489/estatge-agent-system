/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.reporting;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.StringArrayListener;
import client_gui.employee.EmployeeSearch;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class ReportingPanel extends JPanel {
    private ClientImpl client = null;
    private JButton runButton;
    private JPanel controlsPanel;
    private final int reportType;
    private JTextField employeeField;
    private JComboBox officeField;
    private JXDatePicker startDateField;
    private JXDatePicker endDateField;
    private StringArrayListener listener;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public ReportingPanel(ClientImpl client, int reportType) {
        this.reportType = reportType;
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
        
        runButton = new JButton("Run");
        
        employeeField = new JTextField(3);
        officeField = new JComboBox();
        officeField.addItem("  ---  ");
        try {
            for (OfficeInterface office : client.getOffices()) {
                officeField.addItem(office.getOfficeCode());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReportingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        startDateField = new JXDatePicker();
        startDateField.setFormats(dateFormatter);
        endDateField = new JXDatePicker();
        endDateField.setFormats(dateFormatter);
        
        
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int result = 0;
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to RUN the report?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    
                    String value = employeeField.getText();
                    String office = (String) officeField.getSelectedItem();
                    Date startDate = startDateField.getDate();
                    Date endDate = endDateField.getDate();
                    
                    List<String> array = new ArrayList();
                    
                    switch (reportType) {
                        case 1:
                            if (value != null && !value.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(value);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getTenanciesByEmployee");
                                    result = 1;
                                }
                            }
                            break;
                        case 2:
                            if (value != null && !value.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(value);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getLeasesByEmployee");
                                    result = 1;
                                }
                            }
                            break;
                        case 3:
                            if (value != null && !value.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(value);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getContractsByEmployee");
                                    result = 1;
                                }
                            }
                            break;
                        case 4:
                            if (office != null && !office.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) { 
                                    array.add(office);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getTenanciesByOffice");
                                    result = 1;
                                }
                            }
                            break;
                        case 5:
                            if (office != null && !office.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(office);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getLeasesByOffice");
                                    result = 1;
                                }
                            }
                            break;
                        case 6:
                            if (office != null && !office.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(office);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getContractsByOffice");
                                    result = 1;
                                }
                            }
                            break;
                        case 7:
                            if (office != null && !office.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(office);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getExpenditureForOffice");
                                    result = 1;
                                }
                            }
                            break;
                        case 8:
                            if (office != null && !office.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(office);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getRevenueForOffice");
                                    result = 1;
                                }
                            }
                            break;
                        case 9:
                            if (office != null && !office.isEmpty() && startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(office);
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getProfitForOffice");
                                    result = 1;
                                }
                            }
                            break;
                        case 10:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getExpenditureForOverall");
                                    result = 1;
                                }
                            }
                            break;
                        case 11:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getRevenueForOverall");
                                    result = 1;
                                }
                            }
                            break;
                        case 12:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("getProfitForOverall");
                                    result = 1;
                                }
                            }
                            break;
                        case 13:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("generateEmployeeReport");
                                    result = 1;
                                }
                            }
                            break;
                        case 14:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("generateOfficeReport");
                                    result = 1;
                                }
                            }
                            break;
                        case 15:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("generateOfficeFinanceReport");
                                    result = 1;
                                }
                            }
                            break;
                        case 16:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("generateFinanceReport");
                                    result = 1;
                                }
                            }
                            break;
                        case 17:
                            if (startDate != null && endDate != null) {
                                if (listener != null) {
                                    array.add(dateFormatter.format(startDate));
                                    array.add(dateFormatter.format(endDate));
                                    listener.arrayOmitted(array);
                                    System.out.println("generateReport");
                                    result = 1;
                                }
                            }
                            break;
                    }
                    if (result < 1) {
                        String message = "There is some errors with the information supplied to RUN the Report\nPlease check the information supplied";
                        String title = "Error";
                        OKDialog.okDialog(ReportingPanel.this, message, title);
                    }
                }
            }
        });
        
        this.setSize(700, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 5;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Run Report");

        controlsPanel = new JPanel();
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        controlsPanel.setLayout(new GridBagLayout());
        
        controlsPanel.setSize(700, 350);
        
        GridBagConstraints gc = new GridBagConstraints();
        
        ////////// FIRST ROW //////////
        
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel employeeLabel = new JLabel("Employee Ref    ");
        Font font = employeeLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        employeeLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(employeeLabel, gc);

        employeeField.setFont(plainFont);
        employeeField.setEnabled(false);
        employeeField.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(employeeField, gc);

        Image srchImge = null;
        JLabel empSearchThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(ReportingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            empSearchThumb.setIcon(icon);
        }

        if (reportType > 0 && reportType < 4) {
            empSearchThumb.addMouseListener(new MouseAdapter() {
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
        }
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(empSearchThumb, gc);
        
        JLabel officeLabel = new JLabel("Office    ");
        officeLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(officeLabel, gc);

        officeField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(officeField, gc);
        
        

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy += 2;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel startDateLabel = new JLabel("Start Date    ");
        startDateLabel.setFont(boldFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(startDateLabel, gc);
        
        startDateField.setFont(plainFont);

        gc.gridx++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(startDateField, gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        JLabel endDateLabel = new JLabel("End Date    ");
        endDateLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(endDateLabel, gc);
        
        endDateField.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(endDateField, gc);
        
        if (reportType >= 4 && reportType <= 9) {
            officeField.setEnabled(true);
        } else {
            officeField.setEnabled(false);
        }
        

        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(runButton);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void setEmpField(int empRef) {
        if (employeeField != null) {
            employeeField.setText(String.valueOf(empRef));
        }
    }
    
    public void setListener(StringArrayListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }
}
