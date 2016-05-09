
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.systemConfig;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.EndObject;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.StringListener;
import client_gui.address.AddressDetails;
import client_gui.address.AddressPanel;
import client_gui.address.CreateAddress;
import client_gui.address.UpdateAddress;
import client_gui.element.CreateElement;
import client_gui.element.ElementDetails;
import client_gui.element.ElementPanel;
import client_gui.employee.CreateEmployee;
import client_gui.employee.EmployeeDetails;
import client_gui.employee.EmployeePanel;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.jobRole.CreateJobRole;
import client_gui.jobRole.JobRoleDetails;
import client_gui.jobRole.JobRolePanel;
import client_gui.jobRole.UpdateJobRole;
import client_gui.landlord.CreateLandlord;
import client_gui.landlord.LandlordDetails;
import client_gui.landlord.LandlordPanel;
import client_gui.login.LoginForm;
import client_gui.office.CreateOffice;
import client_gui.office.OfficeDetails;
import client_gui.office.OfficePanel;
import client_gui.office.UpdateOffice;
import client_gui.property.CreateProperty;
import client_gui.property.PropertyDetails;
import client_gui.property.PropertyPanel;
import client_gui.property.UpdateProperty;
import interfaces.AddressInterface;
import interfaces.Element;
import interfaces.EmployeeInterface;
import interfaces.JobRoleInterface;
import interfaces.LandlordInterface;
import interfaces.OfficeInterface;
import interfaces.PropertyInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Dwayne
 */
public class SysConfigFrame extends JFrame {
    
    private JLabel frameTitle;
    private ClientImpl client = null;
    private OfficePanel officePanel;
    private JobRolePanel jobRolePanel;
    private PropertyPanel propertyPanel;
    private EmployeePanel employeePanel;
    private LandlordPanel landlordPanel;
    private AddressPanel addressPanel;
    private ElementPanel elementPanel;
    private JPanel panel;
    private SystemConfigHome configPanel;
    
    
    public SysConfigFrame(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        layoutComponents();
        setJMenuBar(createMenuBar());
        createMenuBar();
    }
    
    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }
    
    private void layoutComponents() {
        frameTitle = new JLabel("MSc Properties");
        Font font = frameTitle.getFont();
        frameTitle.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        this.setLayout(new BorderLayout());
        
        officePanel = new OfficePanel("Offices");
        
        officePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String action) {
                action = action.trim();
                switch (action) {
                    case "Create":
                        createOffice();
                        break;
                    
                    case "End":
                        endOffice();
                        break;
                    
                    case "View":
                        viewOffice();
                        break;
                        
                    case "Update":
                        updateOffice();
                        break;
                        
                    case "Delete":
                        deleteOffice();
                        break;
                }
            }
        });
        
        propertyPanel = new PropertyPanel("Properties");
        
        propertyPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String action) {
                action = action.trim();
                switch (action) {
                    case "Create":
                        createProperty();
                        break;
                    
                    case "View":
                        viewProperty();
                        break;
                        
                    case "Update":
                        updateProperty();
                        break;
                        
                    case "Delete":
                        deleteProperty();
                        break;
                }
            }
        });
        
        jobRolePanel = new JobRolePanel("Job Roles");
        
        jobRolePanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String action) {
                action = action.trim();
                switch (action) {
                    case "Create":
                        createJobRole();
                        break;
                    
                    case "View":
                        viewJobRole();
                        break;
                        
                    case "Update":
                        updateJobRole();
                        break;
                        
                    case "Delete":
                        deleteJobRole();
                        break;
                }
            }
        });
        
        addressPanel = new AddressPanel("Addresses");
        
        addressPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String action) {
                action = action.trim();
                switch (action) {
                    case "Create":
                        createAddress();
                        break;
                    
                    case "View":
                        viewAddress();
                        break;
                        
                    case "Update":
                        updateAddress();
                        break;
                        
                    case "Delete":
                        deleteAddress();
                        break;
                }
            }
        });
        
        elementPanel = new ElementPanel("Elements");
        
        elementPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                Integer ref = configPanel.getSelectedTreeBranch();
                if (ref != null) {
                    switch (ref) {
                        case 4:
                            System.out.println("View Contact Types");
                            viewContactType(text);
                            break;

                        case 6:
                            System.out.println("View End Reasons");
                            viewEndReason(text);
                            break;

                        case 8:
                            System.out.println("View Ethnic Origins");
                            viewEthnicOrigin(text);
                            break;

                        case 10:
                            System.out.println("View Genders");
                            viewGender(text);
                            break;

                        case 12:
                            System.out.println("View Job Benefits");
                            viewJobBenefit(text);
                            break;

                        case 14:
                            System.out.println("View Job Requirements");
                            viewJobRequirement(text);
                            break;

                        case 16:
                            System.out.println("View Languages");
                            viewLanguage(text);
                            break;

                        case 18:
                            System.out.println("View Marital Statuses");
                            viewMaritalStatus(text);
                            break;

                        case 20:
                            System.out.println("View Nationalities");
                            viewNationality(text);
                            break;

                        case 22:
                            System.out.println("View Property Elements");
                            viewPropertyElement(text);
                            break;

                        case 24:
                            System.out.println("View Property Types");
                            viewPropertyType(text);
                            break;

                        case 26:
                            System.out.println("View Property Sub Types");
                            viewPropertySubType(text);
                            break;

                        case 28:
                            System.out.println("View Relationships");
                            viewRelationship(text);
                            break;

                        case 30:
                            System.out.println("View Religions");
                            viewReligion(text);
                            break;

                        case 32:
                            System.out.println("View Sexualties");
                            viewSexuality(text);
                            break;

                        case 34:
                            System.out.println("View Tenancy Types");
                            viewTenancyType(text);
                            break;

                        case 36:
                            System.out.println("View Titles");
                            viewTitle(text);
                            break;
                    }
                }
            }
        });
        
        configPanel = new SystemConfigHome();
        panel = new JPanel();
        
        this.setSize(1200, 750);
        this.setMinimumSize(new Dimension(1200, 750));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        configPanel.setListener(new IntegerListener() {
            @Override
            public void intOmitted(int ref) {
                try {
                    System.out.println("Sys Config Input: " + ref);
                    switch (ref) {
                        case 1:
                            System.out.println("Create Address");
                            createAddress();
                            break;
                            
                        case 2:
                            System.out.println("View Addresses");
                            viewAddresses();
                            break;
                            
                        case 3:
                            System.out.println("Create Contact Type");
                            createContactType();
                            break;
                            
                        case 4:
                            System.out.println("View Contact Types");
                            viewElements(client.getContactTypes());
                            break;
                            
                        case 5:
                            System.out.println("Create End Reason");
                            createEndReason();
                            break;
                            
                        case 6:
                            System.out.println("View End Reasons");
                            viewElements(client.getEndReasons());
                            break;
                            
                        case 7:
                            System.out.println("Create Ethnic Origin");
                            createEthnicOrigin();
                            break;
                            
                        case 8:
                            System.out.println("View Ethnic Origins");
                            viewElements(client.getEthnicOrigins());
                            break;
                            
                        case 9:
                            System.out.println("Create Gender");
                            createGender();
                            break;
                            
                        case 10:
                            System.out.println("View Genders");
                            viewElements(client.getGenders());
                            break;
                            
                        case 11:
                            System.out.println("Create Job Benefit");
                            createJobBenefit();
                            break;
                            
                        case 12:
                            System.out.println("View Job Benefits");
                            viewElements(client.getJobBenefits());
                            break;
                            
                        case 13:
                            System.out.println("Create Job Requirement");
                            createJobRequirement();
                            break;
                            
                        case 14:
                            System.out.println("View Job Requirements");
                            viewElements(client.getJobRequirements());
                            break;
                            
                        case 15:
                            System.out.println("Create Language");
                            createLanguage();
                            break;
                            
                        case 16:
                            System.out.println("View Languages");
                            viewElements(client.getLanguages());
                            break;
                            
                        case 17:
                            System.out.println("Create Marital Status");
                            createMaritalStatus();
                            break;
                            
                        case 18:
                            System.out.println("View Marital Statuses");
                            viewElements(client.getMaritalStatuses());
                            break;
                            
                        case 19:
                            System.out.println("Create Nationality");
                            createNationality();
                            break;
                            
                        case 20:
                            System.out.println("View Nationalities");
                            viewElements(client.getNationalities());
                            break;
                            
                        case 21:
                            System.out.println("Create Proprty Element");
                            createPropertyElement();
                            break;
                            
                        case 22:
                            System.out.println("View Property Elements");
                            viewElements(client.getPropElements());
                            break;
                            
                        case 23:
                            System.out.println("Create Property Type");
                            createPropertyType();
                            break;
                            
                        case 24:
                            System.out.println("View Property Types");
                            viewElements(client.getPropertyTypes());
                            break;
                            
                        case 25:
                            System.out.println("Create Property Sub Type");
                            createPropertySubType();
                            break;
                            
                        case 26:
                            System.out.println("View Property Sub Types");
                            viewElements(client.getPropertySubTypes());
                            break;
                            
                        case 27:
                            System.out.println("Create Relationship");
                            createRelationship();
                            break;
                            
                        case 28:
                            System.out.println("View Relationships");
                            viewElements(client.getRelationships());
                            break;
                            
                        case 29:
                            System.out.println("Create Religion");
                            createReligion();
                            break;
                            
                        case 30:
                            System.out.println("View Religions");
                            viewElements(client.getReligions());
                            break;
                            
                        case 31:
                            System.out.println("Create Sexuality");
                            createSexuality();
                            break;
                            
                        case 32:
                            System.out.println("View Sexualties");
                            viewElements(client.getSexualities());
                            break;
                            
                        case 33:
                            System.out.println("Create Tenancy Type");
                            createTenancyType();
                            break;
                            
                        case 34:
                            System.out.println("View Tenancy Types");
                            viewElements(client.getTenancyTypes());
                            break;
                            
                        case 35:
                            System.out.println("Create Title");
                            createTitle();
                            break;
                            
                        case 36:
                            System.out.println("View Titles");
                            viewElements(client.getTitles());
                            break;
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        panel.setLayout(new BorderLayout());
        
        this.add(configPanel, BorderLayout.WEST);
        this.add(panel, BorderLayout.CENTER);
        pack();
    }
    
    private void removeCenterPanel() {
        BorderLayout layout = (BorderLayout) panel.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            panel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
    }
    
    private void createOffice() {
        CreateOffice createOffice = new CreateOffice(client);
        createOffice.setVisible(true);
    }
    
    private void endOffice() {
        String selection = officePanel.getSelectedObjectCode();
        if (selection != null) {
            System.out.println("Office Code: " + selection);
            EndObject endOffice = new EndObject(client, "Office", selection);
            endOffice.setVisible(true);
        }
    }

    private void updateOffice() {
        String selection = officePanel.getSelectedObjectCode();
        if (selection != null) {
            try {
                OfficeInterface office = client.getOffice(selection);
                if (office != null) {
                    UpdateOffice updateOffice = new UpdateOffice(client, office);
                    updateOffice.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteOffice() {
        String selection = officePanel.getSelectedObjectCode();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE office " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Office Delete - Yes button clicked");
                    int result = client.deleteOffice(selection);
                    if (result > 0) {
                        String message = "Office " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    } else {
                        String message = "Office " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewOffice() {
        if (officePanel.getSelectedObjectCode() != null) {
            OfficeInterface office;
            try {
                office = client.getOffice(officePanel.getSelectedObjectCode());
                if (office != null) {
                    OfficeDetails officeDetails = new OfficeDetails(client, office);
                    officeDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewOffices() {
        try {
            officePanel.setData(client.getOffices());
            officePanel.refresh();
            removeCenterPanel();
            panel.add(officePanel, BorderLayout.CENTER);
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createJobRole() {
        CreateJobRole createJobRole = new CreateJobRole(client);
        createJobRole.setVisible(true);
    }

    private void updateJobRole() {
        String selection = jobRolePanel.getSelectedObjectCode();
        if (selection != null) {
            try {
                JobRoleInterface jobRole = client.getJobRole(selection);
                if (jobRole != null) {
                    UpdateJobRole updateJobRole = new UpdateJobRole(client, jobRole);
                    updateJobRole.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteJobRole() {
        String selection = jobRolePanel.getSelectedObjectCode();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Job Role " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Address Delete - Yes button clicked");
                    int result = client.deleteOffice(selection);
                    if (result > 0) {
                        String message = "Job Role " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    } else {
                        String message = "Job Role " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewJobRole() {
        if (jobRolePanel.getSelectedObjectCode() != null) {
            JobRoleInterface jobRole;
            try {
                jobRole = client.getJobRole(jobRolePanel.getSelectedObjectCode());
                if (jobRole != null) {
                    JobRoleDetails jobRoleDetails = new JobRoleDetails(client, jobRole);
                    jobRoleDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewJobRoles() {
        try {
            jobRolePanel.setData(client.getJobRoles());
            jobRolePanel.refresh();
            removeCenterPanel();
            panel.add(jobRolePanel, BorderLayout.CENTER);
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createProperty() {
        CreateProperty createProperty = new CreateProperty(client);
        createProperty.setVisible(true);
    }

    private void updateProperty() {
        Integer selection = propertyPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                PropertyInterface property = client.getProperty(selection);
                if (property != null) {
                    UpdateProperty updateProperty = new UpdateProperty(client, property);
                    updateProperty.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteProperty() {
        Integer selection = propertyPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Property " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Property Delete - Yes button clicked");
                    int result = client.deleteProperty(selection);
                    if (result > 0) {
                        String message = "Property " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    } else {
                        String message = "Property " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewProperty() {
        if (propertyPanel.getSelectedObjectRef() != null) {
            PropertyInterface property;
            try {
                property = client.getProperty(propertyPanel.getSelectedObjectRef());
                if (property != null) {
                    PropertyDetails propertyDetails = new PropertyDetails(client, property);
                    propertyDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewProperties() {
        try {
            propertyPanel.setData(client.getProperties());
            propertyPanel.refresh();
            removeCenterPanel();
            panel.add(propertyPanel, BorderLayout.CENTER);
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createEmployee() {
        CreateEmployee createEmployee = new CreateEmployee(client);
        createEmployee.setVisible(true);
    }

    private void deleteEmployee() {
        Integer selection = employeePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Employee " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Employee Delete - Yes button clicked");
                    int result = client.deleteEmployee(selection);
                    if (result > 0) {
                        String message = "Employee " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    } else {
                        String message = "Employee " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewEmployee() {
        if (employeePanel.getSelectedObjectRef() != null) {
            EmployeeInterface employee;
            try {
                employee = client.getEmployee(employeePanel.getSelectedObjectRef());
                if (employee != null) {
                    EmployeeDetails employeeDetails = new EmployeeDetails(client, employee);
                    employeeDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewEmployees() {
        try {
            employeePanel.setData(client.getEmployees());
            employeePanel.refresh();
            removeCenterPanel();
            panel.add(employeePanel, BorderLayout.CENTER);
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createLandlord() {
        CreateLandlord createLandlord = new CreateLandlord(client);
        createLandlord.setVisible(true);
    }

    private void deleteLandlord() {
        Integer selection = landlordPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Landlord " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Landlord Delete - Yes button clicked");
                    int result = client.deleteLandlord(selection);
                    if (result > 0) {
                        String message = "Landlord " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    } else {
                        String message = "Landlord " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewLandlord() {
        if (landlordPanel.getSelectedObjectRef() != null) {
            LandlordInterface landlord;
            try {
                landlord = client.getLandlord(landlordPanel.getSelectedObjectRef());
                if (landlord != null) {
                    LandlordDetails landlordDetails = new LandlordDetails(client, landlord);
                    landlordDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewLandlords() {
        try {
            landlordPanel.setData(client.getLandlords());
            landlordPanel.refresh();
            removeCenterPanel();
            panel.add(landlordPanel, BorderLayout.CENTER);
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createAddress() {
        CreateAddress createAddress = new CreateAddress(client);
        createAddress.setVisible(true);
    }

    private void updateAddress() {
        Integer selection = addressPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                AddressInterface address = client.getAddress(selection);
                if (address != null) {
                    UpdateAddress updateAddress = new UpdateAddress(client, address);
                    updateAddress.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteAddress() {
        Integer selection = addressPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE address " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Address Delete - Yes button clicked");
                    int result = client.deleteAddress(selection);
                    if (result > 0) {
                        String message = "Address " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    } else {
                        String message = "Address " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(SysConfigFrame.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewAddress() {
        if (addressPanel.getSelectedObjectRef() != null) {
            AddressInterface address;
            try {
                address = client.getAddress(addressPanel.getSelectedObjectRef());
                if (address != null) {
                    AddressDetails addressDetails = new AddressDetails(client, address);
                    addressDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewAddresses() {
        try {
            addressPanel.setData(client.getAddresses());
            addressPanel.refresh();
            removeCenterPanel();
            panel.add(addressPanel, BorderLayout.CENTER);
            repaint();
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createContactType() {
        CreateElement createContactType = new CreateElement(client, "Contact Type");
        createContactType.setVisible(true);
    }
    
    private void createEndReason() {
        CreateElement createEndReason = new CreateElement(client, "End Reason");
        createEndReason.setVisible(true);
    }
    
    private void createEthnicOrigin() {
        CreateElement createEthnicOrigin = new CreateElement(client, "Ethnic Origin");
        createEthnicOrigin.setVisible(true);
    }
    
    private void createGender() {
        CreateElement createGender = new CreateElement(client, "Gender");
        createGender.setVisible(true);
    }
    
    private void createJobBenefit() {
        CreateElement createJobBenefit = new CreateElement(client, "Job Benefit");
        createJobBenefit.setVisible(true);
    }
    
    private void createJobRequirement() {
        CreateElement createJobRequirement = new CreateElement(client, "Job Requirement");
        createJobRequirement.setVisible(true);
    }
    
    private void createLanguage() {
        CreateElement createLanguage = new CreateElement(client, "Language");
        createLanguage.setVisible(true);
    }
    
    private void createMaritalStatus() {
        CreateElement createMaritalStatus = new CreateElement(client, "Marital Status");
        createMaritalStatus.setVisible(true);
    }
    
    private void createNationality() {
        CreateElement createNationality = new CreateElement(client, "Nationality");
        createNationality.setVisible(true);
    }
    
    private void createPropertyElement() {
        CreateElement createPropertyElement = new CreateElement(client, "Property Element");
        createPropertyElement.setVisible(true);
    }
    
    private void createPropertyType() {
        CreateElement createPropertyType = new CreateElement(client, "Property Type");
        createPropertyType.setVisible(true);
    }
    
    private void createPropertySubType() {
        CreateElement createPropertySubType = new CreateElement(client, "Property Sub Type");
        createPropertySubType.setVisible(true);
    }
    
    private void createRelationship() {
        CreateElement createRelationship = new CreateElement(client, "Relationship");
        createRelationship.setVisible(true);
    }
    
    private void createReligion() {
        CreateElement createReligion = new CreateElement(client, "Religion");
        createReligion.setVisible(true);
    }
    
    private void createSexuality() {
        CreateElement createSexuality = new CreateElement(client, "Sexuality");
        createSexuality.setVisible(true);
    }
    
    private void createTenancyType() {
        CreateElement createTenancyType = new CreateElement(client, "TenancyType");
        createTenancyType.setVisible(true);
    }
    
    private void createTitle() {
        CreateElement createTitle = new CreateElement(client, "Title");
        createTitle.setVisible(true);
    }
    
    private void viewContactType(String code) {
        try {
            if (this.client.contactTypeExists(code)) {
                Element element = this.client.getContactType(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Contact Type Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewEndReason(String code) {
        try {
            if (this.client.endReasonExists(code)) {
                Element element = this.client.getEndReason(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "End Reason Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewEthnicOrigin(String code) {
        try {
            if (this.client.ethnicOriginExists(code)) {
                Element element = this.client.getEthnicOrigin(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Ethnic Origin Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewGender(String code) {
        try {
            if (this.client.genderExists(code)) {
                Element element = this.client.getGender(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Gender Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewJobBenefit(String code) {
        try {
            if (this.client.jobRoleRequirementExists(code)) {
                Element element = this.client.getJobBenefit(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Job Benefit Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewJobRequirement(String code) {
        try {
            if (this.client.jobRoleRequirementExists(code)) {
                Element element = this.client.getJobRequirement(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Job Requirement Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewLanguage(String code) {
        try {
            if (this.client.languageExists(code)) {
                Element element = this.client.getLanguage(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Language Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewMaritalStatus(String code) {
        try {
            if (this.client.maritalStatusExists(code)) {
                Element element = this.client.getMaritalStatus(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Marital Status Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewNationality(String code) {
        try {
            if (this.client.nationalityExists(code)) {
                Element element = this.client.getNationality(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Nationality Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewPropertyElement(String code) {
        try {
            if (this.client.propElementExists(code)) {
                Element element = this.client.getPropElement(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Property Element Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewPropertyType(String code) {
        try {
            if (this.client.propTypeExists(code)) {
                Element element = this.client.getPropertyType(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Property Type Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewPropertySubType(String code) {
        try {
            if (this.client.propSubTypeExists(code)) {
                Element element = this.client.getPropertySubType(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Property Sub Type Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewRelationship(String code) {
        try {
            if (this.client.relationshipExists(code)) {
                Element element = this.client.getRelationship(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Relationship Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewReligion(String code) {
        try {
            if (this.client.religionExists(code)) {
                Element element = this.client.getReligion(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Religion Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewSexuality(String code) {
        try {
            if (this.client.sexualityExists(code)) {
                Element element = this.client.getSexuality(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Sexuality Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewTenancyType(String code) {
        try {
            if (this.client.tenancyTypeExists(code)) {
                Element element = this.client.getTenancyType(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Tenancy Type Details");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewTitle(String code) {
        try {
            if (this.client.titleExists(code)) {
                Element element = this.client.getTitle(code);
                ElementDetails elementDetails = new ElementDetails(client, element, "Title");
                elementDetails.setVisible(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void viewElements(List<Element> elements) {
        elementPanel.setData(elements);
        elementPanel.refresh();
        removeCenterPanel();
        panel.add(elementPanel, BorderLayout.CENTER);
        repaint();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem userAccount = new JMenuItem("User Account");
        JMenuItem changeUser = new JMenuItem("Change User");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(userAccount);
        fileMenu.add(changeUser);
        fileMenu.addSeparator(); // Is the faint lines between grouped menu items
        fileMenu.add(exitItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Set up Mnemonics for Menus
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // Set up Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        changeUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        userAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        manualItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        
        
        //Set up ActionListeners
        
        //File Menu
        
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int action = JOptionPane.showConfirmDialog(SysConfigFrame.this,
                        "Do you really want to change user?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                
                if (action == JOptionPane.OK_OPTION) {
                    System.gc();
                    Window windows[] = Window.getWindows(); 
                    for (int i=0; i<windows.length; i++) {
                        windows[i].dispose(); 
                        windows[i]=null;
                    }
                    new LoginForm().setVisible(true);
                    dispose();
                }
            }
        });

        userAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                UpdateEmployeeSecurity securityGUI = new UpdateEmployeeSecurity(client);
                securityGUI.setVisible(true);
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                int action = JOptionPane.showConfirmDialog(SysConfigFrame.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(SysConfigFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        
        
        // Help Menu

        manualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                // NEED TO DEVELOP USER MANUAL
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                AboutFrame about = new AboutFrame(client);
                about.setVisible(true);
            }
        });
        
        return menuBar;
    }
}