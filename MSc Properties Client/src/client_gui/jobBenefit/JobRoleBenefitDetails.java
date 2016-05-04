/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobBenefit;

import client_application.ClientImpl;
import client_gui.DetailsPanel;
import client_gui.modifications.ModPanel;
import interfaces.JobRoleBenefitInterface;
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
public class JobRoleBenefitDetails extends JFrame {

    private ClientImpl client = null;
    private JobRoleBenefitInterface jobRoleBenefit = null;
    private JButton closeButton;
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel controlsPanel;
    private ModPanel modPanel;
    private SimpleDateFormat formatter;
    private JLabel ref;
    private JLabel element;
    private JCheckBox charge;
    private JLabel startDate;
    private JLabel value;
    private JTextArea comment;

    public JobRoleBenefitDetails(ClientImpl client, JobRoleBenefitInterface jobRoleBenefit) {
        setClient(client);
        setJobRoleBenefit(jobRoleBenefit);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Note is initiated
    private void setJobRoleBenefit(JobRoleBenefitInterface jobRoleBenefit) {
        if (this.jobRoleBenefit == null) {
            this.jobRoleBenefit = jobRoleBenefit;
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
        Border titleBorder = BorderFactory.createTitledBorder("Job Role Benefit Details");

        modPanel = new ModPanel("Modifications");

        try {
            modPanel.setData(jobRoleBenefit.getModifiedBy());

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

            JLabel propertyElementRef = new JLabel("Ref  ");
            Font font = propertyElementRef.getFont();
            Font boldFont = new Font(font.getName(), Font.BOLD, 17);
            Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

            propertyElementRef.setFont(plainFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(propertyElementRef, gc);

            ref = new JLabel(String.valueOf(jobRoleBenefit.getBenefitRef()));
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

            startDate = new JLabel(formatter.format(jobRoleBenefit.getStartDate()));
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

            if (jobRoleBenefit.getEndDate() != null) {
                element = new JLabel(formatter.format(jobRoleBenefit.getEndDate()));
            } else {
                element = new JLabel("");
            }
            element.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(element, gc);

            ////////// NEXT ROW //////////
            
            gc.gridx = 0;
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1;

            JLabel valueLabel = new JLabel("Value  ");
            valueLabel.setFont(plainFont);

            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(valueLabel, gc);

            if (jobRoleBenefit.isSalaryBenefit()) {
                value = new JLabel(String.valueOf(jobRoleBenefit.getDoubleValue()));
            } else {
                value = new JLabel(jobRoleBenefit.getStringValue());
            }
            value.setFont(boldFont);

            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(value, gc);
            
            gc.gridx++;
            gc.gridwidth = 1;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            JLabel elementLabel = new JLabel("Element  ");
            elementLabel.setFont(plainFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(elementLabel, gc);

            element = new JLabel(jobRoleBenefit.getBenefitCode());
            element.setFont(boldFont);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(element, gc);
            
            

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

            comment = new JTextArea(2, 25);
            comment.setText(jobRoleBenefit.getComment());
            comment.setDisabledTextColor(Color.BLACK);
            comment.setEnabled(false);

            gc.gridx++;
            gc.gridwidth = 3;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(comment, gc);
            
            JLabel isChargeLabel = new JLabel("Salary Benefit  ");
            isChargeLabel.setFont(plainFont);
            
            gc.gridx++;
            gc.gridwidth = 1;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);
            
            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(new JLabel(""), gc);

            gc.gridx++;
            gc.anchor = GridBagConstraints.EAST;
            gc.insets = new Insets(0, 0, 0, 0);
            controlsPanel.add(isChargeLabel, gc);
            
            charge = new JCheckBox();
            charge.setSelected(jobRoleBenefit.isSalaryBenefit());
            charge.setEnabled(false);

            gc.gridx++;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(0, 0, 0, 5);
            controlsPanel.add(charge, gc);
            
            

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

            JPanel mods = new DetailsPanel(jobRoleBenefit.getCreatedBy(), jobRoleBenefit.getCreatedDate(), jobRoleBenefit.getLastModifiedBy(), jobRoleBenefit.getLastModifiedDate());
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
            Logger.getLogger(JobRoleBenefitDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {
        try {
            startDate.setText(formatter.format(jobRoleBenefit.getStartDate()));
            if (jobRoleBenefit.getEndDate() != null) {
                element.setText(formatter.format(jobRoleBenefit.getEndDate()));
            }
            if (jobRoleBenefit.isSalaryBenefit()) {
                value.setText(String.valueOf(jobRoleBenefit.getDoubleValue()));
            } else {
                value.setText(jobRoleBenefit.getStringValue());
            }
            comment.setText(jobRoleBenefit.getComment());
            modPanel.setData(jobRoleBenefit.getModifiedBy());
            modPanel.refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
