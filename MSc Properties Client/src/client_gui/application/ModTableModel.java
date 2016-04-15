/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import interfaces.ModifiedByInterface;
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
public class ModTableModel extends AbstractTableModel {
    
    private List<ModifiedByInterface> db;
    
    private String[] colNames = {"Modification", "Modified By", "Modified Date"};
    
    public ModTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<ModifiedByInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            ModifiedByInterface modifiedBy = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return modifiedBy.getDescription();
                case 1:
                    return modifiedBy.getModifiedBy();
                case 2:
                    return new SimpleDateFormat("dd-MM-YYYY").format(modifiedBy.getModifiedDate());
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(ModTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
