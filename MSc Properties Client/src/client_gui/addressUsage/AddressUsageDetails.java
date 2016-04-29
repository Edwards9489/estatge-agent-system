/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.addressUsage;

import client_application.ClientImpl;
import client_gui.DetailsPanel;
import client_gui.modifications.ModPanel;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class AddressUsageDetails extends JFrame {

    private ClientImpl client = null;
    private AddressUsageInterface address = null;
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

    public AddressUsageDetails(ClientImpl client, AddressUsageInterface address) {
        setClient(client);
        setAddressUsage(address);
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
}
