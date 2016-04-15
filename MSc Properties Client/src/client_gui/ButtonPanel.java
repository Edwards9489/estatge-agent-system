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
	private JButton createBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JButton viewDetailsBtn;
	private StringListener listener = null;
	
    public ButtonPanel() {
        layoutComponents();
    }
    
    
    public void setButtonListener(StringListener ev) {
        listener = ev;
    }
	
    private void layoutComponents() {
        createBtn = new JButton("Create");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        viewDetailsBtn = new JButton("View Details");

        Dimension buttonDim = viewDetailsBtn.getPreferredSize();
        buttonDim.setSize(buttonDim.getWidth(), (buttonDim.getHeight() + 10));
        createBtn.setPreferredSize(buttonDim);
        updateBtn.setPreferredSize(buttonDim);
        deleteBtn.setPreferredSize(buttonDim);
        viewDetailsBtn.setPreferredSize(buttonDim);
        

        createBtn.setMnemonic(KeyEvent.VK_C);
        updateBtn.setMnemonic(KeyEvent.VK_U);
        deleteBtn.setMnemonic(KeyEvent.VK_D);
        viewDetailsBtn.setMnemonic(KeyEvent.VK_V);

        createBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(createBtn.getText());
                        }
                }
        });

        updateBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(createBtn.getText());
                        }
                }
        });

        deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(createBtn.getText());
                        }
                }
        });

        viewDetailsBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                        if(listener != null) {
                                listener.textOmitted(createBtn.getText());
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
        add(createBtn, gc);

        ////////// SECOND ROW ///////////
        gc.gridx = 0;
        gc.gridy++; // by incrementing gridy by 1 each row it is moving the grid placement down a row by 1

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(updateBtn, gc);

        ////////// THIRD ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(deleteBtn, gc);

        ////////// FOURTH ROW ///////////
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 0.5;
        gc.weighty = 0.1; // weight controls for a given cell how much space to take up

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(viewDetailsBtn, gc);
        
        this.setSize(150, 200);

        this.setVisible(true);
    }
}