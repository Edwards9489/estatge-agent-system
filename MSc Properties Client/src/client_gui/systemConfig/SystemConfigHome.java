/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.systemConfig;

import client_gui.JTreeInfo;
import client_gui.IntegerListener;
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
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("System Configuration                        ");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("Addresses");
        DefaultMutableTreeNode branch1Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Address", 1));
        DefaultMutableTreeNode branch1Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Addresses", 2));

        branch1.add(branch1Leaf1);
        branch1.add(branch1Leaf2);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("Contact Types");
        DefaultMutableTreeNode branch2Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Contact Type", 3));
        DefaultMutableTreeNode branch2Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Contact Types", 4));

        branch2.add(branch2Leaf1);
        branch2.add(branch2Leaf2);

        DefaultMutableTreeNode branch3 = new DefaultMutableTreeNode("End Reasons");
        DefaultMutableTreeNode branch3Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create End Reason", 5));
        DefaultMutableTreeNode branch3Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View End Reasons", 6));

        branch3.add(branch3Leaf1);
        branch3.add(branch3Leaf2);

        DefaultMutableTreeNode branch4 = new DefaultMutableTreeNode("Ethnic Origins");
        DefaultMutableTreeNode branch4Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Ethnic Origin", 7));
        DefaultMutableTreeNode branch4Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Ethnic Origins", 8));

        branch4.add(branch4Leaf1);
        branch4.add(branch4Leaf2);

        DefaultMutableTreeNode branch5 = new DefaultMutableTreeNode("Genders");
        DefaultMutableTreeNode branch5Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Gender", 9));
        DefaultMutableTreeNode branch5Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Genders", 10));

        branch5.add(branch5Leaf1);
        branch5.add(branch5Leaf2);

        DefaultMutableTreeNode branch6 = new DefaultMutableTreeNode("Job Benefits");
        DefaultMutableTreeNode branch6Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Job Benefit", 11));
        DefaultMutableTreeNode branch6Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Job Benefits", 12));

        branch6.add(branch6Leaf1);
        branch6.add(branch6Leaf2);

        DefaultMutableTreeNode branch7 = new DefaultMutableTreeNode("Job Requirements");
        DefaultMutableTreeNode branch7Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Job Requirement", 13));
        DefaultMutableTreeNode branch7Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Job Requirements", 14));

        branch7.add(branch7Leaf1);
        branch7.add(branch7Leaf2);

        DefaultMutableTreeNode branch8 = new DefaultMutableTreeNode("Languages");
        DefaultMutableTreeNode branch8Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Language", 15));
        DefaultMutableTreeNode branch8Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Languages", 16));

        branch8.add(branch8Leaf1);
        branch8.add(branch8Leaf2);

        DefaultMutableTreeNode branch9 = new DefaultMutableTreeNode("Marital Statuses");
        DefaultMutableTreeNode branch9Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Marital Status", 17));
        DefaultMutableTreeNode branch9Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Marital Statuses", 18));

        branch9.add(branch9Leaf1);
        branch9.add(branch9Leaf2);

        DefaultMutableTreeNode branch10 = new DefaultMutableTreeNode("Nationalities");
        DefaultMutableTreeNode branch10Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Nationality", 19));
        DefaultMutableTreeNode branch10Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Nationalities", 20));

        branch10.add(branch10Leaf1);
        branch10.add(branch10Leaf2);

        DefaultMutableTreeNode branch11 = new DefaultMutableTreeNode("Property Elements");
        DefaultMutableTreeNode branch11Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Property Element", 21));
        DefaultMutableTreeNode branch11Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Property Elements", 22));

        branch11.add(branch11Leaf1);
        branch11.add(branch11Leaf2);

        DefaultMutableTreeNode branch12 = new DefaultMutableTreeNode("Property Types");
        DefaultMutableTreeNode branch12Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Property Type", 23));
        DefaultMutableTreeNode branch12Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Property Types", 24));

        branch12.add(branch12Leaf1);
        branch12.add(branch12Leaf2);

        DefaultMutableTreeNode branch13 = new DefaultMutableTreeNode("Property Sub Types");
        DefaultMutableTreeNode branch13Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Property Sub Type", 25));
        DefaultMutableTreeNode branch13Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Property Sub Types", 26));

        branch13.add(branch13Leaf1);
        branch13.add(branch13Leaf2);

        DefaultMutableTreeNode branch14 = new DefaultMutableTreeNode("Relationships");
        DefaultMutableTreeNode branch14Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Relationship", 27));
        DefaultMutableTreeNode branch14Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Relationships", 28));

        branch14.add(branch14Leaf1);
        branch14.add(branch14Leaf2);

        DefaultMutableTreeNode branch15 = new DefaultMutableTreeNode("Religions");
        DefaultMutableTreeNode branch15Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Religion", 29));
        DefaultMutableTreeNode branch15Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Religions", 30));

        branch15.add(branch15Leaf1);
        branch15.add(branch15Leaf2);

        DefaultMutableTreeNode branch16 = new DefaultMutableTreeNode("Sexualities");
        DefaultMutableTreeNode branch16Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Sexuality", 31));
        DefaultMutableTreeNode branch16Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Sexualities", 32));

        branch16.add(branch16Leaf1);
        branch16.add(branch16Leaf2);

        DefaultMutableTreeNode branch17 = new DefaultMutableTreeNode("Tenancy Types");
        DefaultMutableTreeNode branch17Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Tenancy Type", 33));
        DefaultMutableTreeNode branch17Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Tenancy Types", 34));

        branch17.add(branch17Leaf1);
        branch17.add(branch17Leaf2);

        DefaultMutableTreeNode branch18 = new DefaultMutableTreeNode("Titles");
        DefaultMutableTreeNode branch18Leaf1 = new DefaultMutableTreeNode(new JTreeInfo("Create Title", 35));
        DefaultMutableTreeNode branch18Leaf2 = new DefaultMutableTreeNode(new JTreeInfo("View Titles", 36));

        branch18.add(branch18Leaf1);
        branch18.add(branch18Leaf2);

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
        top.add(branch18);

        return top;
    }

    public void setListener(IntegerListener intListener) {
        if (listener == null) {
            listener = intListener;
        }
    }
}
