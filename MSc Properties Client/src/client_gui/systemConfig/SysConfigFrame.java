/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.systemConfig;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.address.AddressPanel;
import client_gui.address.CreateAddress;
import client_gui.element.CreateElement;
import client_gui.element.ElementPanel;
import interfaces.Element;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
    
    private JLabel title;
    private ClientImpl client = null;
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
        title = new JLabel("MSc Properties");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 10));
        this.setLayout(new BorderLayout());
        
        addressPanel = new AddressPanel("Addresses");
        elementPanel = new ElementPanel("Elements");
        
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
    
    private void createAddress() {
        CreateAddress createAddress = new CreateAddress(client);
        createAddress.setVisible(true);
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
        
        changeUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                
            }
        });
        
        userAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
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
        return menuBar;
    }
}