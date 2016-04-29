/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.element;

import client_application.ClientImpl;
import client_gui.DetailsPanel;
import client_gui.modifications.ModPanel;
import client_gui.employee.EmployeeDetails;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ElementDetails().setVisible(true);
//            }
//        });
//    }
}
