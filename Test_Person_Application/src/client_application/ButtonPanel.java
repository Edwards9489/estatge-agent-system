/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
		Dimension dim = getPreferredSize();
		dim.width = 200;
                dim.height = 400;
		setPreferredSize(dim);
		
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
                
                appButton.setPreferredSize(new Dimension(60,60));
                propButton.setPreferredSize(new Dimension(60,60));
                tenButton.setPreferredSize(new Dimension(60,60));
                leaseButton.setPreferredSize(new Dimension(60,60));
                contractButton.setPreferredSize(new Dimension(60,60));
                rentAccButton.setPreferredSize(new Dimension(60,60));
                leaseAccButton.setPreferredSize(new Dimension(60,60));
                employeeAccButton.setPreferredSize(new Dimension(60,60));
                reportingButton.setPreferredSize(new Dimension(60,60));
                systemConfigButton.setPreferredSize(new Dimension(60,60));
                
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
                
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                layoutComponents();
	}
	
	public void setButtonListener(StringListener ev) {
		listener = ev;
	}
	
	public void layoutComponents() {
            GridBagConstraints gc = new GridBagConstraints();

//            //////////     FIRST ROW     //////////
//            gc.gridx = 0;
//            gc.gridy = 0;
//
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//
//            gc.fill = GridBagConstraints.HORIZONTAL;
//
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(appButton, gc);
//
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(propButton, gc);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(tenButton);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(leaseButton);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(contractButton);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(rentAccButton);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(leaseAccButton);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(employeeAccButton);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(reportingButton);
//            
//            //////////     NEXT ROW     //////////
//            
//            gc.gridx = 0;
//            gc.gridy++;
//            
//            gc.weightx = 2;
//            gc.weighty = 0.2;
//            
//            gc.anchor = GridBagConstraints.LINE_START;
//            gc.insets = new Insets(0, 0, 0, 5);
//            add(systemConfigButton);
            
            appButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(appButton);
            
            propButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(propButton);
            
            tenButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(tenButton);
            
            leaseButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(leaseButton);
            
            contractButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(contractButton);
            
            rentAccButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(rentAccButton);
            
            leaseAccButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(leaseAccButton);
            
            employeeAccButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(employeeAccButton);
            
            reportingButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(reportingButton);
            
            systemConfigButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(systemConfigButton);
            
            this.setVisible(true);
//	
//	appButton;
//	private JButton propButton;
//	private JButton tenButton;
//	private JButton leaseButton;
//	private JButton contractButton;
//	private JButton rentAccButton;
//	private JButton leaseAccButton;
//	private JButton employeeAccButton;
//	private JButton reportingButton;
//	private JButton systemConfigButton;
	}
}