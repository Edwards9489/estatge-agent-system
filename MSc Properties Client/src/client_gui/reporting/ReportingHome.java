/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.reporting;

import client_gui.IntegerListener;
import client_gui.systemConfig.JTreeInfo;
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
public class ReportingHome extends JPanel {

    private JTree systemConfigTree;
    private IntegerListener listener;

    public ReportingHome() {

        systemConfigTree = new JTree(createTree());

        // Stops the user from selecting more than one tree node from System Config
        //systemConfigTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        systemConfigTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent ev) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) systemConfigTree.getLastSelectedPathComponent();

                if (node != null) {
                    Object userSelection = node.getUserObject();

                    if (userSelection instanceof JTreeInfo) {
                        int id = ((JTreeInfo) userSelection).getId();
                        listener.intOmitted(id);
                    }
                }
            }
        });

        this.setLayout(new BorderLayout());
        add(new JScrollPane(systemConfigTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Reporting                        ");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(new JTreeInfo("Get Tenancies By Employee", 1));

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(new JTreeInfo("Get Leases By Employee", 2));

        DefaultMutableTreeNode branch3 = new DefaultMutableTreeNode(new JTreeInfo("Get Contracts By Employee", 3));

        DefaultMutableTreeNode branch4 = new DefaultMutableTreeNode(new JTreeInfo("Get Tenancies By Office", 4));

        DefaultMutableTreeNode branch5 = new DefaultMutableTreeNode(new JTreeInfo("Get Leases By Office", 5));

        DefaultMutableTreeNode branch6 = new DefaultMutableTreeNode(new JTreeInfo("Get Contracts By Office", 6));

        DefaultMutableTreeNode branch7 = new DefaultMutableTreeNode(new JTreeInfo("Get Expenditure For Office", 7));

        DefaultMutableTreeNode branch8 = new DefaultMutableTreeNode(new JTreeInfo("Get Revenue For Office", 8));

        DefaultMutableTreeNode branch9 = new DefaultMutableTreeNode(new JTreeInfo("Get Profit For Office", 9));

        DefaultMutableTreeNode branch10 = new DefaultMutableTreeNode(new JTreeInfo("Get Expenditure Overall", 10));

        DefaultMutableTreeNode branch11 = new DefaultMutableTreeNode(new JTreeInfo("Get Revenue Overall", 11));

        DefaultMutableTreeNode branch12 = new DefaultMutableTreeNode(new JTreeInfo("Get Profit Overall", 12));

        DefaultMutableTreeNode branch13 = new DefaultMutableTreeNode(new JTreeInfo("Generate Employee Report", 13));

        DefaultMutableTreeNode branch14 = new DefaultMutableTreeNode(new JTreeInfo("Generate Office Report", 14));

        DefaultMutableTreeNode branch15 = new DefaultMutableTreeNode(new JTreeInfo("Generate Office Finance Report", 15));

        DefaultMutableTreeNode branch16 = new DefaultMutableTreeNode(new JTreeInfo("Generate Finance Report", 16));

        DefaultMutableTreeNode branch17 = new DefaultMutableTreeNode(new JTreeInfo("Generate Report", 17));
        
        top.add(branch1);
        top.add(branch2);
        top.add(branch3);
        top.add(branch4);
        top.add(branch5);
        top.add(branch6);
        top.add(branch7);
        top.add(branch8);
        top.add(branch9);
        top.add(branch10);
        top.add(branch11);
        top.add(branch12);
        top.add(branch13);
        top.add(branch14);
        top.add(branch15);
        top.add(branch16);
        top.add(branch17);

        return top;
    }
    
    public Integer getSelectedTreeBranch() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) systemConfigTree.getLastSelectedPathComponent();
        if (node != null) {
            Object userSelection = node.getUserObject();

            if (userSelection instanceof JTreeInfo) {
                int id = ((JTreeInfo) userSelection).getId();
                return id;
            }
        }
        return null;
    }

    public void setListener(IntegerListener intListener) {
        if (listener == null) {
            listener = intListener;
        }
    }
}
