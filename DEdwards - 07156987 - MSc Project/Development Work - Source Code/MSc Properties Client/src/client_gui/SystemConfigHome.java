/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Dwayne
 */
public class SystemConfigHome extends JPanel {
    
    private JTree systemConfigTree;
    private IntegerListener listener;
    
    public SystemConfigHome() {
        
        systemConfigTree = new JTree(createTree());
        
        // Stops the user from selecting more than one tree node from System Config
        systemConfigTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        systemConfigTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent ev) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) systemConfigTree.getLastSelectedPathComponent();
                
                Object userSelection = (String) node.getUserObject();
                
                if(userSelection instanceof SystemConfigInfo) {
                    int id = ((SystemConfigInfo) userSelection).getId();
                    listener.intOmitted(id);
                    // NEED TO CREATE A INTEGERLISTENER IN SYSTEMCONFIG HOME (amend this to SystemConfigMenu) AND ASSIGN TO THIS OBJECT
                }
            }
        });
        
        this.setLayout(new BorderLayout());
        add(new JScrollPane(systemConfigTree), BorderLayout.CENTER);
    }
    
    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("System Configuration");
        
        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("NODE1");
        DefaultMutableTreeNode branch1Leaf1 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF1", 1));
        DefaultMutableTreeNode branch1Leaf2 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF1", 2));
        DefaultMutableTreeNode branch1Leaf3 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF2", 3));
        DefaultMutableTreeNode branch1Leaf4 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF3", 4));
        DefaultMutableTreeNode branch1Leaf5 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF4", 5));
        
        branch1.add(branch1Leaf1);
        branch1.add(branch1Leaf2);
        branch1.add(branch1Leaf3);
        branch1.add(branch1Leaf4);
        branch1.add(branch1Leaf5);
        
        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("NODE2");
        DefaultMutableTreeNode branch2Leaf1 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF1", 6));
        DefaultMutableTreeNode branch2Leaf2 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF1", 7));
        DefaultMutableTreeNode branch2Leaf3 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF2", 8));
        DefaultMutableTreeNode branch2Leaf4 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF3", 9));
        DefaultMutableTreeNode branch2Leaf5 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF4", 10));
        
        branch2.add(branch2Leaf1);
        branch2.add(branch2Leaf2);
        branch2.add(branch2Leaf3);
        branch2.add(branch2Leaf4);
        branch2.add(branch2Leaf5);
        
        DefaultMutableTreeNode branch3 = new DefaultMutableTreeNode("NODE3");
        DefaultMutableTreeNode branch3Leaf1 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF1", 11));
        DefaultMutableTreeNode branch3Leaf2 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF1", 12));
        DefaultMutableTreeNode branch3Leaf3 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF2", 13));
        DefaultMutableTreeNode branch3Leaf4 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF3", 14));
        DefaultMutableTreeNode branch3Leaf5 = new DefaultMutableTreeNode(new SystemConfigInfo("LEAF4", 15));
        
        branch3.add(branch3Leaf1);
        branch3.add(branch3Leaf2);
        branch3.add(branch3Leaf3);
        branch3.add(branch3Leaf4);
        branch3.add(branch3Leaf5);
        
        top.add(branch1);
        top.add(branch2);
        top.add(branch3);
        
        return top;
    }
    
    public void setListener(IntegerListener intListener) {
        if(listener == null) {
            listener = intListener;
        }
    }
}