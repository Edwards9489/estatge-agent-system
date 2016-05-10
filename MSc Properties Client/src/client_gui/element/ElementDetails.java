/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.element;

import client_application.ClientImpl;
import client_gui.AboutFrame;
import client_gui.DetailsPanel;
import client_gui.OKDialog;
import client_gui.modifications.ModPanel;
import client_gui.employee.EmployeeDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.login.LoginForm;
import interfaces.Element;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class ElementDetails extends JFrame {

    private ClientImpl client = null;
    private Element element = null;
    private JButton closeButton;
    private JTextArea noteField;
    private final String elementType;
    private JCheckBox currentField;
    private JPanel mainPanel;
    private JPanel detailsPanel;
    private ModPanel modPanel;
    private JTabbedPane tabbedPane;

    public ElementDetails(ClientImpl client, Element element, String elementType) {
        super("MSc Properties");
        setClient(client);
        setElement(element);
        this.elementType = elementType;
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Element is initiated
    private void setElement(Element element) {
        if (this.element == null) {
            this.element = element;
        }
    }

    private void layoutComponents() {
        
        setJMenuBar(createMenuBar());

        closeButton = new JButton("Close");
        modPanel = new ModPanel("Modifications");
        tabbedPane = new JTabbedPane();
        noteField = new JTextArea(2, 40);
        noteField.setLineWrap(true);
        noteField.setEnabled(false);
        noteField.setDisabledTextColor(Color.BLACK);
        currentField = new JCheckBox();
        currentField.setEnabled(false);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                setVisible(false);
                dispose();
            }
        });

        try {
            modPanel.setData(element.getModifiedBy());
            noteField.setText(element.getNote().getNote());
            currentField.setSelected(element.isCurrent());

            this.setSize(600, 350);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

            ///////// DETAILS PANEL ////////////
            detailsPanel = new JPanel();
            //detailsPanel.setSize(450, 200);
            detailsPanel.setLayout(new GridBagLayout());

            GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
            gc.gridx = 0;
            gc.gridy = 0;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel codeLabel = new JLabel("Code    ");
            Font font = codeLabel.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 14);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 14);
            codeLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(codeLabel, gc);

            JLabel code = new JLabel(element.getCode());
            code.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(code, gc);

            JLabel descriptionLabel = new JLabel("Description    ");
            descriptionLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(descriptionLabel, gc);

            JLabel description = new JLabel(element.getDescription());
            description.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(description, gc);

            JLabel currentLabel = new JLabel("Current    ");
            currentLabel.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(currentLabel, gc);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(currentField, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel commentLabel = new JLabel("Comment    ");
            commentLabel.setFont(boldFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            detailsPanel.add(commentLabel, gc);

            noteField.setFont(plainFont);

            noteField.setText(element.getNote().getNote());

            gc.gridx++;
            gc.gridwidth = 5;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            detailsPanel.add(noteField, gc);

            JPanel buttonsPanel = new JPanel();

            int space = 15;
            Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
            Border titleBorder = BorderFactory.createTitledBorder("View " + elementType + " Element");

            mainPanel = new JPanel();

            mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(detailsPanel, BorderLayout.CENTER);

            try {
                JPanel mods = new DetailsPanel(element.getCreatedBy(), element.getCreatedDate(), element.getLastModifiedBy(), element.getLastModifiedDate());
                mainPanel.add(mods, BorderLayout.SOUTH);
            } catch (RemoteException ex) {
                Logger.getLogger(ElementDetails.class.getName()).log(Level.SEVERE, null, ex);
            }

            ////////// BUTTONS PANEL //////////
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(closeButton);

            ///////// TABBED PANE /////////////
            tabbedPane.add(mainPanel, "Details");
            tabbedPane.add(modPanel, "Modifications");

            // Add sub panels to dialog
            setLayout(new BorderLayout());
            add(tabbedPane, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.SOUTH);
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {
        try {
            modPanel.setData(element.getModifiedBy());
            modPanel.refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(ElementDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        
        // Actions Menu
        JMenu actionsMenu = new JMenu("Actions");

        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(updateItem);
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(actionsMenu);
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
                int action = JOptionPane.showConfirmDialog(ElementDetails.this,
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

                int action = JOptionPane.showConfirmDialog(ElementDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(ElementDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        
        
        // Actions Menu
        
        updateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                UpdateElement elementDetails = new UpdateElement(client, element, elementType);
                elementDetails.setVisible(true);
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE " + elementType + " Element " + element.getCode() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        System.out.println(elementType + " Element Delete - Yes button clicked");
                        int result = 0;
                        switch (elementType) {
                            case "Title":
                                    result = client.deleteTitle(element.getCode());
                                    System.out.println("deleteTitle");
                                    break;
                                case "Gender":
                                    result = client.deleteGender(element.getCode());
                                    System.out.println("deleteGender");
                                    break;
                                case "Marital Status":
                                    result = client.deleteMaritalStatus(element.getCode());
                                    System.out.println("deleteMaritalStatus");
                                    break;
                                case "Ethnic Origin":
                                    result = client.deleteEthnicOrigin(element.getCode());
                                    System.out.println("deleteEthnicOrigin");
                                    break;
                                case "Language":
                                    result = client.deleteLanguage(element.getCode());
                                    System.out.println("deleteLanguage");
                                    break;
                                case "Nationality":
                                    result = client.deleteNationality(element.getCode());
                                    System.out.println("deleteNationality");
                                    break;
                                case "Sexuality":
                                    result = client.deleteSexuality(element.getCode());
                                    System.out.println("deleteSexuality");
                                    break;
                                case "Religion":
                                    result = client.deleteReligion(element.getCode());
                                    System.out.println("deleteReligion");
                                    break;
                                case "Contact Type":
                                    result = client.deleteContactType(element.getCode());
                                    System.out.println("deleteContactType");
                                    break;
                                case "End Reason":
                                    result = client.deleteEndReason(element.getCode());
                                    System.out.println("deleteEndReason");
                                    break;
                                case "Relationship":
                                    result = client.deleteRelationship(element.getCode());
                                    System.out.println("deleteRelationship");
                                    break;
                                case "Tenancy Type":
                                    result = client.deleteTenancyType(element.getCode());
                                    System.out.println("deleteTenancyType");
                                    break;
                                case "Job Benefit":
                                    result = client.deleteJobBenefit(element.getCode());
                                    System.out.println("deleteJobBenefit");
                                    break;
                                case "Job Requirement":
                                    result = client.deleteJobRequirement(element.getCode());
                                    System.out.println("deleteJobRequirement");
                                    break;
                                case "Property Type":
                                    result = client.deletePropertyType(element.getCode());
                                    System.out.println("deletePropertyType");
                                    break;
                                case "Property Sub Type":
                                    result = client.deletePropertySubType(element.getCode());
                                    System.out.println("deletePropertySubType");
                                    break;
                                case "Property Element":
                                    result = client.deletePropertyElement(element.getCode());
                                    System.out.println("deletePropertyElement");
                                    break;
                                
                        }
                        if (result > 0) {
                            String message = elementType + " Element " + element.getCode() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(ElementDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = elementType + " Element " + element.getCode() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(ElementDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(ElementDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                refresh();
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
                AboutFrame about = new AboutFrame();
                about.setVisible(true);
            }
        });
        
        return menuBar;
    }
}
