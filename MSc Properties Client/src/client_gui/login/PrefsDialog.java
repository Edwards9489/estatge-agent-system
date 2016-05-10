/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class PrefsDialog extends JDialog {
    private final JButton okButton;
    private final JButton cancelButton;
    private JTextField addrField;
    private JComboBox envList;
    
    private PrefsListener prefsListener;
    
    public PrefsDialog(JFrame parent) {
        super(parent, "Prefrences", false);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        addrField = new JTextField(10);
        envList = new JComboBox();
        
        // Set up List
        
        DefaultListModel envModel = new DefaultListModel();
        envModel.addElement(new EnvCategory(0, "LIVE"));
        envModel.addElement(new EnvCategory(1, "TEST"));
        envModel.addElement(new EnvCategory(2, "TRAIN"));
        envList.addItem(new EnvCategory(0, "LIVE"));
        envList.addItem(new EnvCategory(1, "TEST"));
        envList.addItem(new EnvCategory(2, "TRAIN"));
        envList.setSelectedIndex(0);
        
        layoutControls();
        
        // Add Action Listeners
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String user = addrField.getText();
                EnvCategory envCat = (EnvCategory) envList.getSelectedItem();

                if (prefsListener != null) {
                    prefsListener.preferenceSet(user, envCat.getId());
                }
                setVisible(false);
            }
        });
            
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
            setVisible(false);
        }
    });
        
        setSize(300, 200);
        setLocationRelativeTo(parent);
    }
    
    public void setDefaults(String serverAddr, int environment) {
        addrField.setText(serverAddr);
        if(envList.getSelectedIndex() != environment) {
            envList.setSelectedIndex(environment);
        }
    }

    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }

    private void layoutControls() {
        
        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("System Prefrences");
        
        
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        controlsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel("Envioronment: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(envList, gc);

        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(new JLabel("Server Address: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(addrField, gc);

        ////////// BUTTONS PANEL //////////
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        
        
        // Add sub panels to dialog
        
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    class EnvCategory {

        private final int id;
        private final String text;

        public EnvCategory(int id, String text) {
            this.id = id;
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int getId() {
            return id;
        }
    }
}