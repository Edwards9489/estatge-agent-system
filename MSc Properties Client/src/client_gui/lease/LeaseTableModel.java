/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.lease;

import interfaces.LeaseInterface;
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
public class LeaseTableModel extends AbstractTableModel {
    
    private List<LeaseInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Name", "Start Date", "Actual End Date", "Expenditure", "Prop Ref", "Office Code", "Account Ref"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public LeaseTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<LeaseInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            LeaseInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getAgreementRef();
                case 1:
                    return address.getAgreementName();
                case 2:
                    return formatter.format(address.getStartDate());
                case 3:
                    if(address.getActualEndDate() != null) {
                        return formatter.format(address.getActualEndDate());
                    } else {
                        return "";
                    }
                case 4:
                    return "£" + address.getExpenditure();
                case 5:
                    return address.getPropertyRef();
                case 6:
                    return address.getOfficeCode();
                case 7:
                    return address.getAccountRef();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(LeaseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
