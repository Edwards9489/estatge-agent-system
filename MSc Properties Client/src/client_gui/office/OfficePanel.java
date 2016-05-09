/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.office;

import client_gui.StringListener;
import interfaces.OfficeInterface;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Dwayne
 */
public class OfficePanel extends JPanel {
    private JTable table;
    private final OfficeTableModel tableModel;
    private JPopupMenu popup;
    private StringListener actionListener;
    
    public OfficePanel(String text) {
        tableModel = new OfficeTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        JMenuItem createItem = new JMenuItem("Create Office");
        JMenuItem viewItem = new JMenuItem("View Office");
        JMenuItem endItem = new JMenuItem("End Office");
        JMenuItem updateItem = new JMenuItem("Update Office");
        JMenuItem deleteItem = new JMenuItem("Delete Office");
        JMenuItem refreshItem = new JMenuItem("Refresh Offices");
        
        popup.add(createItem);
        popup.add(viewItem);
        popup.add(endItem);
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
        
        endItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    actionListener.textOmitted("End");
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
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel(text);
        Font font = title.getFont();
        
        title.setFont(new Font(font.getName(), Font.BOLD, 17));
        add(title, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setData(List<OfficeInterface> offices) {
        tableModel.setData(offices);
    }
    
    public void refresh() {
        tableModel.fireTableDataChanged();
    }
    
    public void setTableListener(StringListener listener) {
        this.actionListener = listener;
    }
    
    public String getSelectedObjectCode() {
        int row = table.getSelectedRow();
        if (row > -1) {
            String code = (String) table.getModel().getValueAt(row, 0);
            return code;
        }
        return null;
    }
}
