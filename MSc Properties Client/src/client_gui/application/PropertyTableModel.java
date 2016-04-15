/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import interfaces.PropertyInterface;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dwayne
 */
public class PropertyTableModel extends AbstractTableModel {
    
    private List<PropertyInterface> db;
    
    private String[] colNames = {"Ref", "Address", "Prop Status", "Prop Type", "Prop Sub Type", "Lease End Date"};
    
    public PropertyTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<PropertyInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            PropertyInterface property = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return property.getPropRef();
                case 1:
                    return property.getAddress().printAddress();
                case 2:
                    return property.getPropStatus();
                case 3:
                    return property.getPropType().getCode();
                case 4:
                    return property.getPropSubType().getCode();
                case 5:
                    return new SimpleDateFormat("dd-MM-YYYY").format(property.getLeaseEndDate());
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(PropertyTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
