/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import client_gui.address.AddressSearchPanel;
import client_application.ClientImpl;
import client_gui.StringArrayListener;
import client_gui.person.PeopleSearchPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class AppAdvancedSearch extends JFrame {

    private ClientImpl client = null;
    private ApplicationArrayListener listener = null;
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private SimpleDateFormat dateFormatter;

    public AppAdvancedSearch(ClientImpl client) {
        setClient(client);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Client is initiated
    public void setListener(ApplicationArrayListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {
        
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        
        ApplicationSearchPanel appPanel = new ApplicationSearchPanel(client);

        appPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 6) {
                    try {
                        Date startDate = null;
                        Date endDate = null;
                        Date createdDate = null;
                        
                        if (array.get(1) != null) {
                            startDate = dateFormatter.parse(array.get(1));
                        }
                        if (array.get(2) != null) {
                            endDate = dateFormatter.parse(array.get(2));
                        }
                        if (array.get(5) != null) {
                            createdDate = dateFormatter.parse(array.get(5));
                        }
                        
                        if (listener != null) {
                            listener.arrayOmitted(client.getApplications(array.get(0), startDate, endDate, array.get(3), array.get(4), createdDate));
                            setVisible(false);
                            dispose();
                        }
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(AppAdvancedSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        PeopleSearchPanel invPartyPanel = new PeopleSearchPanel(client);

        invPartyPanel.setListener(new StringArrayListener() {
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
                        
                        if (listener != null) {
                            listener.arrayOmitted(client.getPeopleApplications(array.get(0), array.get(1), array.get(2), array.get(3), dob, array.get(5), array.get(6), array.get(7), array.get(8), array.get(9), array.get(10), array.get(11), array.get(12), array.get(13), createdDate));
                            setVisible(false);
                            dispose();
                        }
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(AppAdvancedSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        AddressSearchPanel addressPanel = new AddressSearchPanel(client);

        addressPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 12) {
                    try {
                        Date createdDate = null;
                        
                        if (array.get(11) != null) {
                            createdDate = dateFormatter.parse(array.get(11));
                        }
                        
                        if (listener != null) {
                            listener.arrayOmitted(client.getAddressApplications(array.get(0), array.get(1), array.get(2), array.get(3), array.get(4), array.get(5), array.get(6), array.get(7), array.get(8), array.get(9), array.get(10), createdDate));
                            setVisible(false);
                            dispose();
                        }
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(AppAdvancedSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
        // TABBED PANE

        tabbedPane = new JTabbedPane();
        
        tabbedPane.add(appPanel, "App Details");
        tabbedPane.add(invPartyPanel, "Inv Party Details");
        tabbedPane.add(addressPanel, "Address Details");

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Application Advanced Search");
        
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        this.setSize(1350, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }
}
