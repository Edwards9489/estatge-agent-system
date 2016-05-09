/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.addressUsage;

import client_application.ClientImpl;
import client_gui.DetailsPanel;
import client_gui.EndObject;
import client_gui.OKDialog;
import client_gui.address.AddressDetails;
import client_gui.employee.UpdateEmployeeSecurity;
import client_gui.login.LoginForm;
import client_gui.modifications.ModPanel;
import interfaces.AddressInterface;
import interfaces.AddressUsageInterface;
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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class AddressUsageDetails extends JFrame {

    private ClientImpl client = null;
    private AddressUsageInterface address = null;
    private final String addressType;
    private final int objectRef;
    private JButton closeButton;
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel controlsPanel;
    private ModPanel modPanel;
    private SimpleDateFormat formatter;
    private JLabel ref;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel addressString;
    private JTextArea comment;

    public AddressUsageDetails(ClientImpl client, AddressUsageInterface address, String addressType, int objectRef) {
        setClient(client);
        setAddressUsage(address);
        this.addressType = addressType;
        this.objectRef = objectRef;
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Note is initiated
    private void setAddressUsage(AddressUsageInterface address) {
        if (this.address == null) {
            this.address = address;
        }
    }

    private void layoutComponents() {
        
        setJMenuBar(createMenuBar());

        closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                setVisible(false);
                dispose();
            }
        });

        tabbedPane = new JTabbedPane();

        formatter = new SimpleDateFormat("dd-MM-yyyy");

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Address Usage Details");

        modPanel = new ModPanel("Modifications");

        try {
            modPanel.setData(address.getModifiedBy());

            ////////// CONTROLS PANEL /////////
            controlsPanel = new JPanel();
            controlsPanel.setSize(450, 200);

            controlsPanel.setLayout(new GridBagLayout());

            GridBagConstraints gc = new GridBagConstraints();

           ////////// FIRST ROW //////////
            gc.gridx = 0;
            gc.gridy = 0;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel addressUsageRef = new JLabel("Ref  ");
            Font font = addressUsageRef.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 17);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

            addressUsageRef.setFont(plainFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addressUsageRef, gc);

            ref = new JLabel(String.valueOf(address.getAddressUsageRef()));
            ref.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(ref, gc);

            JLabel addrStartDate = new JLabel("Start Date  ");
            addrStartDate.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrStartDate, gc);

            startDate = new JLabel(formatter.format(address.getStartDate()));
            startDate.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(startDate, gc);

            JLabel addrEndDate = new JLabel("End Date  ");
            addrEndDate.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addrEndDate, gc);

            if (address.getEndDate() != null) {
                endDate = new JLabel(formatter.format(address.getEndDate()));
            } else {
                endDate = new JLabel("");
            }
            endDate.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(endDate, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel addressLabel = new JLabel("Address  ");
            addressLabel.setFont(plainFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(addressLabel, gc);

            addressString = new JLabel(address.getAddressString());
            addressString.setFont(boldFont);

            gc.gridx++;
            gc.gridwidth = 5;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(addressString, gc);

            ////////// NEXT ROW //////////
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel commentLabel = new JLabel("Comment  ");
            commentLabel.setFont(plainFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            gc.gridwidth = 1;
            controlsPanel.add(commentLabel, gc);

            comment = new JTextArea(3, 40);
            comment.setText(address.getComment());
            comment.setDisabledTextColor(Color.BLACK);
            comment.setEnabled(false);

            gc.gridx++;
            gc.gridwidth = 5;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(comment, gc);

            ////////// BUTTONS PANEL //////////
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(closeButton);

            /////////// MAIN PANEL ////////////
            mainPanel = new JPanel();
            mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(controlsPanel, BorderLayout.CENTER);

            JPanel mods = new DetailsPanel(address.getCreatedBy(), address.getCreatedDate(), address.getLastModifiedBy(), address.getLastModifiedDate());
            mainPanel.add(mods, BorderLayout.SOUTH);

            // SET UP MAIN FRAME
            this.setSize(650, 400);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            setLayout(new BorderLayout());

            // ADD COMPONENTS TO TABBED PANE
            tabbedPane.add(mainPanel, "Details");
            tabbedPane.add(modPanel, "Modiciations");

            // ADD COMPONENTS TO MAIN FRAME
            add(tabbedPane, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.SOUTH);

        } catch (RemoteException ex) {
            Logger.getLogger(AddressUsageDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {
        try {
            startDate.setText(formatter.format(address.getStartDate()));
            if (address.getEndDate() != null) {
                endDate.setText(formatter.format(address.getEndDate()));
            }
            addressString.setText(address.getAddressString());
            comment.setText(address.getComment());
            modPanel.setData(address.getModifiedBy());
            modPanel.refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(AddressUsageDetails.class.getName()).log(Level.SEVERE, null, ex);
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
        JMenuItem endItem = new JMenuItem("End");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        
        actionsMenu.add(updateItem);
        actionsMenu.add(endItem);
        actionsMenu.add(deleteItem);
        actionsMenu.add(refreshItem);
        
        
        // Link to Menu
        JMenu linksMenu = new JMenu("Link To");

        JMenuItem addressItem = new JMenuItem("Address");
        
        linksMenu.add(addressItem);
        

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");
        
        helpMenu.add(manualItem);
        helpMenu.add(aboutItem);
        

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(actionsMenu);
        menuBar.add(linksMenu);
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
                int action = JOptionPane.showConfirmDialog(AddressUsageDetails.this,
                        "Do you really want to exit the application?",
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

                int action = JOptionPane.showConfirmDialog(AddressUsageDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(AddressUsageDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                UpdateAddressUsage appDetails = new UpdateAddressUsage(client, address, addressType, objectRef);
                appDetails.setVisible(true);
            }
        });
        
        endItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    EndObject endAddress = new EndObject(client, addressType, address.getAddressUsageRef(), objectRef);
                    endAddress.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressUsageDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Address Usage " + address.getAddressUsageRef() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (answer == JOptionPane.YES_OPTION) {
                        int result = 0;
                        System.out.println("Address Usage Delete - Yes button clicked");
                        switch (addressType) {
                            case "Application":
                                result = client.deleteApplicationAddressUsage(address.getAddressUsageRef(), objectRef);
                                break;

                            case "Person":
                                result = client.deletePersonAddressUsage(address.getAddressUsageRef(), objectRef);
                                break;
                        }
                        if (result > 0) {
                            String message = "Address Usage " + address.getAddressUsageRef() + " has been successfully deleted";
                            String title = "Information";
                            OKDialog.okDialog(AddressUsageDetails.this, message, title);
                            setVisible(false);
                            dispose();
                        } else {
                            String message = "Address Usage " + address.getAddressUsageRef() + " has dependent records and is not able to be deleted";
                            String title = "Error";
                            OKDialog.okDialog(AddressUsageDetails.this, message, title);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressUsageDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                refresh();
            }
        });
        
        
        // Links Menu

        addressItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    AddressInterface temp = address.getAddress();
                    if (temp != null) {
                        AddressDetails addrDetails = new AddressDetails(client, temp);
                        addrDetails.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressUsageDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        // Help Menu

        manualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                
            }
        });
        
        return menuBar;
    }
}
