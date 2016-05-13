/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class Document extends JFrame {
    private JButton okButton;
    private JButton cancelButton;
    private JPanel controlsPanel;
    private JTextField absolutePath;
    private JTextField canonicalPath;
    private JTextField parent;
    private JTextField path;
    private JTextField name;
    private JTextField toString;
    private File file = null;
    private JFileChooser fileChooser;
    
    public Document() {
        layoutComponents();
    }
    
    private void layoutComponents() {
        fileChooser = new JFileChooser();
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        canonicalPath = new JTextField(25);
        absolutePath = new JTextField(25);
        parent = new JTextField(25);
        path = new JTextField(25);
        name = new JTextField(25);
        toString = new JTextField(25);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to CREATE a new Document?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    if (file != null) {
                        try {
                            File file1 = new File(file.getCanonicalPath());
                            File file2 = new File(file.getAbsolutePath());
                            File file3 = new File(file.getParent());
                            File file4 = new File(file.getPath());
                            File file5 = new File(file.getName());
                            File file6 = new File(file.toString());
                            
                            System.out.println("CanonicalPath? " + file1.canRead());
                            System.out.println("AbsolutePath? " + file2.canRead());
                            System.out.println("Parent? " + file3.canRead());
                            System.out.println("Path? " + file4.canRead());
                            System.out.println("Name? " + file5.canRead());
                            System.out.println("toString? " + file6.canRead());
                        } catch (IOException ex) {
                            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                setVisible(false);
                dispose();
            }
        });
        
        this.setSize(700, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Create Document");

        controlsPanel = new JPanel();
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        controlsPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        ////////// FIRST ROW //////////
        
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel fileNameLabel = new JLabel("Canonical Path    ");
        Font font = fileNameLabel.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 15);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 15);
        fileNameLabel.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(fileNameLabel, gc);

        canonicalPath.setFont(plainFont);
        canonicalPath.setEnabled(false);
        canonicalPath.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(canonicalPath, gc);

        Image srchImge = null;
        JLabel fileSearchThumb = new JLabel();

        try {
            srchImge = ImageIO.read(new File("D:\\System Images\\search magnifying glass.png")).getScaledInstance(25, 25, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (srchImge != null) {
            ImageIcon icon = new ImageIcon(srchImge);
            fileSearchThumb.setIcon(icon);
        }

        fileSearchThumb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(fileChooser.showOpenDialog(Document.this) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if (file != null) {
                        try {
                            absolutePath.setText(file.getAbsolutePath());
                            canonicalPath.setText(file.getCanonicalPath());
                            path.setText(file.getPath());
                            parent.setText(file.getParent());
                            name.setText(file.getName());
                            toString.setText(file.toString());
                        } catch (IOException ex) {
                            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(new JLabel(""), gc);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        controlsPanel.add(fileSearchThumb, gc);

        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel filaName2 = new JLabel("Absolute Path    ");
        filaName2.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(filaName2, gc);

        absolutePath.setFont(plainFont);
        absolutePath.setEnabled(false);
        absolutePath.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(absolutePath, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel filaName3 = new JLabel("Parent    ");
        filaName3.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(filaName3, gc);

        parent.setFont(plainFont);
        parent.setEnabled(false);
        parent.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(parent, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel filaName4 = new JLabel("Path    ");
        filaName4.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(filaName4, gc);

        path.setFont(plainFont);
        path.setEnabled(false);
        path.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(path, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel filaName5 = new JLabel("Name    ");
        filaName5.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(filaName5, gc);

        name.setFont(plainFont);
        name.setEnabled(false);
        name.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(name, gc);
        
        ////////// NEXT ROW //////////
        
        gc.gridx = 0;
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        JLabel filaName6 = new JLabel("To String    ");
        filaName6.setFont(boldFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(filaName6, gc);

        toString.setFont(plainFont);
        toString.setEnabled(false);
        toString.setDisabledTextColor(Color.BLACK);

        gc.gridx++;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(toString, gc);
        

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
    
    public static void main(String[] args) {
        Document document = new Document();
        document.setVisible(true);
    }
}
