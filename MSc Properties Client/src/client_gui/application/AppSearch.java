/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.StringListener;
import interfaces.ApplicationInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class AppSearch extends JFrame {

    private ClientImpl client = null;
    private IntegerListener listener = null;

    private JComboBox searchOn;
    private JTextField searchValue;
    private JButton searchButton;
    private JButton advSearchButton;
    private JButton createButton;

    private JButton okButton;
    private JButton cancelButton;

    private ApplicationPanel applicationPanel;
    private JPanel searchResultsPanel;
    private JPanel detailsPanel;

    public AppSearch(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        this.layoutComponents();
    }

    public AppSearch(ClientImpl client, IntegerListener listener) {
        super("MSc Properties");
        setListener(listener);
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
    private void setListener(IntegerListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {

        searchButton = new JButton("Search");
        advSearchButton = new JButton("Adv Search");
        createButton = new JButton("Create");
        searchValue = new JTextField(15);
        searchOn = new JComboBox();
        searchOn.addItem("  ---  ");
        searchOn.addItem("App Ref");
        searchOn.addItem("Inv Party Ref");
        searchOn.addItem("Corr Name");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (!searchOn.getSelectedItem().equals("  ---  ") && !searchValue.getText().isEmpty()) {
                    try {
                        List<ApplicationInterface> applications = new ArrayList();
                        String searchOnText = (String) searchOn.getSelectedItem();
                        searchOnText = searchOnText.trim();
                        switch (searchOnText) {
                            case "App Ref":
                                int appRef = Integer.parseInt(searchValue.getText());
                                if (appRef > 0) {
                                    ApplicationInterface app = client.getApplication(appRef);
                                    if (app != null) {
                                        applications.add(app);
                                    }
                                }
                                break;

                            case "Inv Party Ref":
                                int invPartyRef = Integer.parseInt(searchValue.getText());
                                if (invPartyRef > 0) {
                                    ApplicationInterface app = client.getInvPartyApplication(invPartyRef);
                                    if (app != null) {
                                        applications.add(app);
                                    }
                                }
                                break;

                            case "Corr Name":
                                String corrName = searchValue.getText();
                                applications = client.getCorrNameApplications(corrName);
                                break;
                        }
                        setData(applications);
                    } catch (RemoteException ex) {
                        Logger.getLogger(AppSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        advSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                AppAdvancedSearch advSearch = new AppAdvancedSearch(client);
                advSearch.setListener(new ApplicationArrayListener() {
                    @Override
                    public void arrayOmitted(List<ApplicationInterface> array) {
                        if (array != null) {
                            applicationPanel.setData(array);
                        }
                    }
                });
                advSearch.setVisible(true);
                System.out.println("TEST - Adv App Search");
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                createApp();
            }
        });

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        if (listener != null) {

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (listener != null && applicationPanel.getSelectedObjectRef() != null) {
                        listener.intOmitted(applicationPanel.getSelectedObjectRef());
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
        }

        // RESULTS PANEL
        searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new BorderLayout());

        applicationPanel = new ApplicationPanel("Applications");

        applicationPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });

        searchResultsPanel.add(applicationPanel, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();

        if (listener != null) {

            System.out.println("ADDING BUTTON PANEL");

            ////////// BUTTONS PANEL //////////
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.add(okButton);
            buttonsPanel.add(cancelButton);

            Dimension btnSize = cancelButton.getPreferredSize();
            okButton.setPreferredSize(btnSize);
            searchResultsPanel.add(buttonsPanel, BorderLayout.SOUTH);
        }

        // SEARCH PANEL
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEmptyBorder());
        searchPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //////// FIRST ROW //////////
        gc.gridy = 0;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel searchOnLabel = new JLabel("Search On");
        Font font = searchOnLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        searchOnLabel.setFont(boldFont);

        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 10, 10, 5);
        searchPanel.add(searchOnLabel, gc);

        searchOn.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 5, 10, 10);
        searchPanel.add(searchOn, gc);

        searchValue.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 10, 10, 10);
        searchPanel.add(searchValue, gc);

        searchButton.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(25, 10, 10, 20);
        searchPanel.add(searchButton, gc);

        advSearchButton.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 20, 10, 10);
        searchPanel.add(advSearchButton, gc);

        createButton.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(25, 10, 10, 10);
        searchPanel.add(createButton, gc);

        // DETAILS PANEL
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        JLabel titleLabel = new JLabel("Application Search");

        Font titleFont = new Font(font.getName(), Font.BOLD, font.getSize() + 10);
        titleLabel.setFont(titleFont);

        detailsPanel.add(titleLabel, BorderLayout.NORTH);
        detailsPanel.add(searchPanel, BorderLayout.CENTER);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(searchResultsPanel, BorderLayout.SOUTH);

        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void createApp() {
        CreateApp createApp = new CreateApp(client);
        createApp.setVisible(true);
        System.out.println("TEST - Create Application");
    }

    private void updateApp() {
        Integer selection = applicationPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Application Ref: " + selection);
                ApplicationInterface app = client.getApplication(selection);
                if (app != null) {
                    System.out.println("Application Name: " + app.getAppCorrName());
                    UpdateApp updateAppDetails = new UpdateApp(client, app);
                    updateAppDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteApp() {
        Integer selection = applicationPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Application " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Application Delete - Yes button clicked");
                    int result = client.deleteApplication(selection);
                    if (result > 0) {
                        String message = "Application " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(AppSearch.this, message, title);
                    } else {
                        String message = "Application " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(AppSearch.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewApp() {
        Integer selection = applicationPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                ApplicationInterface app = client.getApplication(selection);
                if (app != null) {
                    AppDetails appDetails = new AppDetails(client, app);
                    appDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(AppDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refresh() {
        applicationPanel.refresh();
    }

    private void actionChoice(String text) {
        switch (text) {
            case "Create":
                createApp();
                break;

            case "Update":
                updateApp();
                break;

            case "Delete":
                deleteApp();
                break;

            case "View Details":
                viewApp();
                break;

            case "Refresh":
                refresh();
                break;

        }
    }

    private void setData(List<ApplicationInterface> applications) {
        applicationPanel.setData(applications);
        repaint();
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new AppSearch().setVisible(true);
//            }
//        });
//    }
}
