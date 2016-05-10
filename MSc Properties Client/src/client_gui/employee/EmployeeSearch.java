/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.employee;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.person.PeopleSearchPanel;
import client_gui.StringArrayListener;
import client_gui.StringListener;
import client_gui.office.OfficeDetails;
import interfaces.EmployeeInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Dwayne
 */
public class EmployeeSearch extends JFrame {

    private ClientImpl client = null;
    private IntegerListener listener = null;

    private JButton okButton;
    private JButton cancelButton;
    private JButton createButton;

    private EmployeePanel employeePanel;
    private JPanel searchResultsPanel;

    private SimpleDateFormat dateFormatter;

    public EmployeeSearch(ClientImpl client) {
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

    // Use of singleton pattern to ensure only one listener is initiated
    public void setListener(IntegerListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        createButton = new JButton("Create Employee");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (employeePanel.getSelectedObjectRef() != null) {
                    listener.intOmitted(employeePanel.getSelectedObjectRef());
                    setVisible(false);
                    dispose();
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
        
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                createEmployee();
            }
        });

        employeePanel = new EmployeePanel("Employees");

        employeePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });

        this.setSize(1200, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        PeopleSearchPanel personPanel = new PeopleSearchPanel(client);

        personPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 15) {
                    try {
                        Date dob = null;
                        Date createdDate = null;
                        
                        if (array.get(4) != null) {
                            dob = dateFormatter.parse(array.get(4));
                        }
                        if (array.get(14) != null) {
                            createdDate = dateFormatter.parse(array.get(14));
                        }
                        employeePanel.setData(client.getPeopleEmployees(array.get(0), array.get(1), array.get(2), array.get(3), dob, array.get(5), array.get(6), array.get(7), array.get(8), array.get(9), array.get(10), array.get(11), array.get(12), array.get(13), createdDate));
                        employeePanel.refresh();
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(EmployeeSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new BorderLayout());

        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(createButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        //Add components to searchResultsPanel
        searchResultsPanel.add(employeePanel, BorderLayout.CENTER);
        searchResultsPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(personPanel, BorderLayout.CENTER);
        add(searchResultsPanel, BorderLayout.SOUTH);
    }

    private void createEmployee() {
        CreateEmployee createEmployee = new CreateEmployee(client);
        createEmployee.setVisible(true);
        System.out.println("TEST - Create Employee");
    }

    private void updateEmployee() {
        Integer selection = employeePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                EmployeeInterface employee = client.getEmployee(selection);
                if (employee != null) {
                    UpdateEmployeePassword updateEmployeePassword = new UpdateEmployeePassword(client, employee.getEmployeeRef());
                    updateEmployeePassword.setVisible(true);
                    System.out.println("TEST - Update Employee Security");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewEmployee() {
        Integer selection = employeePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                EmployeeInterface employee = client.getEmployee(selection);
                if (employee != null) {
                    EmployeeDetails employeeDetails = new EmployeeDetails(client, employee);
                    employeeDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(EmployeeSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteEmployee() {
        Integer selection = employeePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE EMployee " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Contract Delete - Yes button clicked");
                    int result = client.deleteContract(selection);
                    if (result > 0) {
                        String message = "Employee " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(EmployeeSearch.this, message, title);
                    } else {
                        String message = "Employye " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(EmployeeSearch.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(OfficeDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        employeePanel.refresh();
    }
    
    private void actionChoice(String text) {
        switch (text) {
            case "Create":
                createEmployee();
                System.out.println("TEST - Create Employee");
                break;

            case "Delete":
                deleteEmployee();
                System.out.println("TEST - Delete Employee");
                break;

            case "Update":
                try {
                    if (client.getUser().getEmployeeUpdate()) {
                        updateEmployee();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(EmployeeSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("TEST - Update Employee Security");
                break;
            
            case "View Details":
                viewEmployee();
                System.out.println("TEST - View Employee");
                break;

            case "Refresh":
                refresh();
                break;
        }
    }
}
