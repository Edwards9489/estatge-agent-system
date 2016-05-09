/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.addressUsage;

import interfaces.AddressUsageInterface;
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
public class AddressUsageTableModel extends AbstractTableModel {
    
    private List<AddressUsageInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Address", "Start Date", "End Date"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public AddressUsageTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<AddressUsageInterface> db) {
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
            AddressUsageInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getAddressUsageRef();
                case 1:
                    return address.getAddress().printAddress();
                case 2:
                    return formatter.format(address.getStartDate());
                case 3:
                    if(address.getEndDate() != null) {
                        return formatter.format(address.getEndDate());
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(AddressUsageTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}