/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class DetailsPanel extends JPanel {
    
    private final String createdBy;
    private final Date createdDate;
    private final String modifiedBy;
    private final Date modifiedDate;
    private final SimpleDateFormat formatter;
    
    public DetailsPanel(String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        layoutComponents();
    }
    
    private void layoutComponents() {
        setSize(400, 75);
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));

        setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 1;
        
        JLabel cBy = new JLabel("Created By    ");
        Font font = cBy.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);
        
        cBy.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        add(cBy, gc);

        JLabel createdByLabel = new JLabel(createdBy);
        createdByLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        add(createdByLabel, gc);

        JLabel cDate = new JLabel("Created Date    ");
        cDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        add(cDate, gc);

        JLabel createdDateLabel = new JLabel(formatter.format(createdDate));
        createdDateLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        add(createdDateLabel, gc);
        
        
        ////////// NEXT ROW //////////
        
        JLabel modifiedByLabel;
        JLabel modifiedDateLabel;
        
        if (modifiedBy != null) {
            modifiedByLabel = new JLabel(modifiedBy);
            modifiedDateLabel = new JLabel(formatter.format(modifiedDate));
        } else {
            modifiedByLabel = new JLabel("");
            modifiedDateLabel = new JLabel("");
        }
        
        gc.gridx = 0;
        gc.gridy = 1;

        gc.weightx = 1;
        gc.weighty = 1;
        

        JLabel mBy = new JLabel("Modified By    ");
        mBy.setFont(plainFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        add(mBy, gc);

        modifiedByLabel.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        add(modifiedByLabel, gc);

        JLabel mDate = new JLabel("Modified Date    ");
        mDate.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        add(mDate, gc);
        
        modifiedDateLabel.setFont(boldFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        add(modifiedDateLabel, gc);
    }
}
