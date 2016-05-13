/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.office;

import interfaces.OfficeInterface;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dwayne
 */
public class OfficeTableModel extends AbstractTableModel {
    
    private List<OfficeInterface> db = new ArrayList();
    
    private final String[] colNames = {"Code", "Address", "Start Date", "End Date"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public OfficeTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<OfficeInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            OfficeInterface element = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return element.getOfficeCode();
                case 1:
                    return element.getAddress().printAddress();
                case 2:
                    return formatter.format(element.getStartDate());
                case 3:
                    if(element.getEndDate() != null) {
                        return formatter.format(element.getEndDate());
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(OfficeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
