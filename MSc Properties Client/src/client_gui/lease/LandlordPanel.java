/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import client_gui.IntegerListener;
import interfaces.LandlordInterface;
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
public class LandlordPanel extends JPanel {
    private JTable table;
    private LandlordTableModel tableModel;
    private JPopupMenu popup;
    private IntegerListener tableListener;
    
    public LandlordPanel(String text) {
        tableModel = new LandlordTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        JMenuItem accountItem = new JMenuItem("Landlords");
        popup.add(accountItem);
        
        // Set up Border for ButtonPanel
        Border innerBorder = BorderFactory.createEtchedBorder();
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
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
        
        accountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                
                if(tableListener != null) {
                    int accountRef = (Integer) table.getModel().getValueAt(row, 0);
                    
                    System.out.println(accountRef);
                    tableListener.intOmitted(accountRef);
                    
//                    tableModel.fireTableRowsDeleted(row, row);
//                    System.out.println(row);
                }
            }
            
        });
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel(text);
        Font font = title.getFont();
        
        title.setFont(new Font(font.getName(), Font.BOLD, 17));
        add(title, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setData(List<LandlordInterface> landlords) {
        tableModel.setData(landlords);
    }
    
    public void refresh() {
        tableModel.fireTableDataChanged();
    }
    
    public void setTableListener(IntegerListener tenListener) {
        this.tableListener = tenListener;
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
