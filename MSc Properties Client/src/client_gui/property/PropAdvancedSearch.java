/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.property;

import client_application.ClientImpl;
import client_gui.StringArrayListener;
import client_gui.address.AddressSearchPanel;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Dwayne
 */
public class PropAdvancedSearch extends JFrame {

    private ClientImpl client = null;
    private PropertyArrayListener listener = null;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private SimpleDateFormat dateFormatter;

    public PropAdvancedSearch(ClientImpl client) {
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
    public void setListener(PropertyArrayListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {
        
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        
        tabbedPane = new JTabbedPane();
        
        PropertySearchPanel propPanel = new PropertySearchPanel(client);

        propPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 7) {
                    try {
                        Date acquiredDate = null;
                        Date leaseEndDate = null;
                        Date createdDate = null;
                        
                        if (array.get(0) != null) {
                            acquiredDate = dateFormatter.parse(array.get(0));
                        }
                        if (array.get(1) != null) {
                            leaseEndDate = dateFormatter.parse(array.get(1));
                        }
                        if (array.get(6) != null) {
                            createdDate = dateFormatter.parse(array.get(2));
                        }
                        
                        if (listener != null) {
                            listener.arrayOmitted(client.getProperties(acquiredDate, leaseEndDate, array.get(2), array.get(3), array.get(4), array.get(5), createdDate));
                            setVisible(false);
                            dispose();
                        }
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(PropAdvancedSearch.class.getName()).log(Level.SEVERE, null, ex);
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
                            listener.arrayOmitted(client.getAddressProperties(array.get(0), array.get(1), array.get(2), array.get(3), array.get(4), array.get(5), array.get(6), array.get(7), array.get(8), array.get(9), array.get(10), createdDate));
                            setVisible(false);
                            dispose();
                        }
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(PropAdvancedSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        tabbedPane.add(propPanel, "Property Details");
        tabbedPane.add(addressPanel, "Address Details");
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        this.setSize(1350, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }
}