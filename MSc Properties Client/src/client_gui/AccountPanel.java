/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import interfaces.AccountInterface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Dwayne
 */
public class AccountPanel extends JPanel {
    private JTable table;
    private AccountTableModel tableModel;
    private JPopupMenu popup;
    private TableListener tableListener;
    
    public AccountPanel() {
        tableModel = new AccountTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        JMenuItem tenancyItem = new JMenuItem("Accounts");
        popup.add(tenancyItem);
        
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
        
        tenancyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                
                if(tableListener != null) {
                    tableListener.rowSelected(row);
                    tableModel.fireTableRowsDeleted(row, row);
                    System.out.println(row);
                }
            }
            
        });
        
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setData(List<AccountInterface> agreements) {
        tableModel.setData(agreements);
    }
    
    public void refresh() {
        tableModel.fireTableDataChanged();
    }
    
    public void setTableListener(TableListener tenListener) {
        this.tableListener = tenListener;
    }
}