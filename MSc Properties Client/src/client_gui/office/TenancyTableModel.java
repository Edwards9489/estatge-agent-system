/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.office;

import interfaces.TenancyInterface;
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
public class TenancyTableModel extends AbstractTableModel {
    
    private List<TenancyInterface> db;
    
    private String[] colNames = {"Ref", "Name", "Start Date", "Actual End Date", "Rent", "Charges", "App Ref", "Office Code", "Account Ref"};
    
    public TenancyTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<TenancyInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            TenancyInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getAgreementRef();
                case 1:
                    return address.getAgreementName();
                case 2:
                    return new SimpleDateFormat("dd-MM-YYYY").format(address.getStartDate());
                case 3:
                    if(address.getActualEndDate() != null) {
                        return new SimpleDateFormat("dd-MM-YYYY").format(address.getActualEndDate());
                    } else {
                        return "";
                    }
                case 4:
                    return "£" + address.getRent();
                case 5:
                    return "£" + address.getCharges();
                case 6:
                    return address.getApplicationRef();
                case 7:
                    return address.getOfficeCode();
                case 8:
                    return address.getAccountRef();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(TenancyTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
