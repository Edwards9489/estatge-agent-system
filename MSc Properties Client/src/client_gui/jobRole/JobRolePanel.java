/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobRole;

import client_gui.StringListener;
import interfaces.JobRoleInterface;
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
public class JobRolePanel extends JPanel {
    private JTable table;
    private JobRoleTableModel tableModel;
    private JPopupMenu popup;
    private StringListener tableListener;
    
    public JobRolePanel(String text) {
        tableModel = new JobRoleTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        JMenuItem addressItem = new JMenuItem("Job Roles");
        popup.add(addressItem);
        
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
        
        addressItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                
                if(tableListener != null) {
                    String code = (String) table.getModel().getValueAt(row, 0);
                    
                    System.out.println(code);
                    tableListener.textOmitted(code);
                    
//                    tableModel.fireTableRowsDeleted(row, row);
//                    System.out.println(row);
                }
            }
            
        });
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel(text);
        Font font = title.getFont();
        
        title.setFont(new Font(font.getName(), Font.BOLD, 17));
        add(title, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setData(List<JobRoleInterface> jobRoles) {
        tableModel.setData(jobRoles);
    }
    
    public void refresh() {
        tableModel.fireTableDataChanged();
    }
    
    public void setTableListener(StringListener listener) {
        this.tableListener = listener;
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
