/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.contact;

import interfaces.ContactInterface;
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
public class ContactTableModel extends AbstractTableModel {
    
    private List<ContactInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Type", "Value", "Start Date", "End Date"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public ContactTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<ContactInterface> db) {
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
            ContactInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getContactRef();
                case 1:
                    return address.getContactType().getCode();
                case 2:
                    return address.getContactValue();
                case 3:
                    return formatter.format(address.getStartDate());
                case 4:
                    if(address.getEndDate() != null) {
                        return formatter.format(address.getEndDate());
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(ContactTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
