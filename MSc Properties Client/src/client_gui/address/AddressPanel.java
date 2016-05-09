/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.address;

import client_gui.StringListener;
import interfaces.AddressInterface;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class AddressPanel extends JPanel {
    private JTable table;
    private final AddressTableModel tableModel;
    private JPopupMenu popup;
    private StringListener actionListener;
    
    public AddressPanel(String text) {
        tableModel = new AddressTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        // Set up Border for ButtonPanel
        Border innerBorder = BorderFactory.createEtchedBorder();
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        JMenuItem createItem = new JMenuItem("Create Address");
        JMenuItem viewItem = new JMenuItem("View Address");
        JMenuItem updateItem = new JMenuItem("Update Address");
        JMenuItem deleteItem = new JMenuItem("Delete Address");
        JMenuItem refreshItem = new JMenuItem("Refresh Addresses");
        
        popup.add(createItem);
        popup.add(viewItem);
        popup.add(updateItem);
        popup.add(deleteItem);
        popup.add(refreshItem);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                
                table.getSelectionModel().setSelectionInterval(row, row);
                
                if(e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(table, e.getX(), e.getY());
                }
            }
        });
        
        createItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    actionListener.textOmitted("Create");
                }
            }
        });
        
        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    actionListener.textOmitted("View Details");
                }
            }
        });
        
        updateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    actionListener.textOmitted("Update");
                }
            }
        });
        
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    actionListener.textOmitted("Delete");
                }
            }
        });
        
        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    actionListener.textOmitted("Refresh");
                }
            }
        });
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(100);
        table.getColumnModel().getColumn(10).setPreferredWidth(100);
        
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel(text);
        Font font = title.getFont();
        
        title.setFont(new Font(font.getName(), Font.BOLD, 17));
        add(title, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setData(List<AddressInterface> addresses) {
        tableModel.setData(addresses);
    }
    
    public void refresh() {
        tableModel.fireTableDataChanged();
    }
    
    public void setTableListener(StringListener actionListener) {
        this.actionListener = actionListener;
    }
    
    public Integer getSelectedObjectRef() {
        int row = table.getSelectedRow();
        if (row > -1) {
            int ref = (Integer) table.getModel().getValueAt(row, 0);
            return ref;
        }
        return null;
    }
}