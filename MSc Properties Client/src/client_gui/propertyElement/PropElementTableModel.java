/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.propertyElement;

import interfaces.PropertyElementInterface;
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
public class PropElementTableModel extends AbstractTableModel {
    
    private List<PropertyElementInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Element Code", "Value", "Start Date", "End Date"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public PropElementTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<PropertyElementInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            PropertyElementInterface transaction = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return transaction.getPropertyElementRef();
                case 1:
                    return transaction.getElementCode();
                case 2:
                    if(transaction.getStringValue() != null) {
                        return transaction.getStringValue();
                    } else if (transaction.getDoubleValue() != null) {
                        return "£" + transaction.getDoubleValue();
                    }
                case 3:
                    return formatter.format(transaction.getStartDate());
                case 4:
                    if(transaction.getEndDate() != null) {
                        return formatter.format(transaction.getStartDate());
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(PropElementTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
