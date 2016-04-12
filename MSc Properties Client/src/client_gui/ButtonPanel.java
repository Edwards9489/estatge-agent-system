/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class ButtonPanel extends JPanel {
	private JButton appButton;
	private JButton propButton;
	private JButton tenButton;
	private JButton leaseButton;
	private JButton contractButton;
	private JButton rentAccButton;
	private JButton leaseAccButton;
	private JButton employeeAccButton;
	private JButton reportingButton;
	private JButton systemConfigButton;
	private StringListener listener = null;
	
    public ButtonPanel() {
        layoutComponents();
    }
    
    
        
	
    public void setButtonListener(StringListener ev) {
        listener = ev;
    }
	
    private void layoutComponents() {
        appButton = new JButton("Applications");
        propButton = new JButton("Properties");
        tenButton = new JButton("Tenancies");
        leaseButton = new JButton("Leases");
        contractButton = new JButton("Contracts");
        rentAccButton = new JButton("Rent Accounts");
        leaseAccButton = new JButton("Lease Accounts");
        employeeAccButton = new JButton("Employee Accounts");
        reportingButton = new JButton("Reporting");
        systemConfigButton = new JButton("System Config");

        Dimension buttonDim = employeeAccButton.getPreferredSize();
        appButton.setPreferredSize(buttonDim);
        propButton.setPreferredSize(buttonDim);
        tenButton.setPreferredSize(buttonDim);
        leaseButton.setPreferredSize(buttonDim);
        contractButton.setPreferredSize(buttonDim);
        rentAccButton.setPreferredSize(buttonDim);
        leaseAccButton.setPreferredSize(buttonDim);
        reportingButton.setPreferredSize(buttonDim);
        systemConfigButton.setPreferredSize(buttonDim);


        appButton.setMnemonic(KeyEvent.VK_A);
        propButton.setMnemonic(KeyEvent.VK_P);
        tenButton.setMnemonic(KeyEvent.VK_T);
        leaseButton.setMnemonic(KeyEvent.VK_L);
        contractButton.setMnemonic(KeyEvent.VK_C);
        rentAccButton.setMnemonic(KeyEvent.VK_R);
        leaseAccButton.setMnemonic(KeyEvent.VK_N);
        employeeAccButton.setMnemonic(KeyEvent.VK_E);
        reportingButton.setMnemonic(KeyEvent.VK_F);
        systemConfigButton.setMnemonic(KeyEvent.VK_S);

        appButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(appButton.getText());
                        }
                }
        });

        propButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(appButton.getText());
                        }
                }
        });

        tenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(appButton.getText());
                        }
                }
        });

        leaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(appButton.getText());
                        }
                }
        });

        contractButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(appButton.getText());
                        }
                }
        });

        rentAccButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(appButton.getText());
                        }
                }
        });

        leaseAccButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(leaseAccButton.getText());
                        }
                }
        });

        employeeAccButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(employeeAccButton.getText());
                        }
                }
        });

        reportingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(reportingButton.getText());
                        }
                }
        });

        systemConfigButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(systemConfigButton.getText());
                        }
                }
        });

        // Set up Border for ButtonPanel
        Border innerBorder = BorderFactory.createEtchedBorder();
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0; // zero x is at far left of screen
        gc.gridy = 0; // zero y is at top of screen

        gc.weightx = 1;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        // when gridx increases you move across the screen to the right 
        // when gridy increase you move down the screen to the bottom
        gc.fill = GridBagConstraints.NONE; //horizontal, vertical, none or both

        // have to set all of above for gt before you start
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(appButton, gc);

        ////////// SECOND ROW ///////////
        gc.gridx = 0;
        gc.gridy++; // by incrementing gridy by 1 each row it is moving the grid placement down a row by 1

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(propButton, gc);

        ////////// THIRD ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(tenButton, gc);

        ////////// FOURTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(leaseButton, gc);

        ////////// FIFTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(contractButton, gc);

        ////////// SIXTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(rentAccButton, gc);

        ////////// SEVENTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(leaseAccButton, gc);

        ////////// EIGTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(employeeAccButton, gc);

        ////////// NINTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(reportingButton, gc);

        ////////// TENTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(systemConfigButton, gc);

        this.setVisible(true);
    }
}