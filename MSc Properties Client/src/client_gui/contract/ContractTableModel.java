/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.contract;

import interfaces.ContractInterface;
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
public class ContractTableModel extends AbstractTableModel {
    
    private List<ContractInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Job Role Code", "Start Date", "End Date", "Account Ref"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public ContractTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<ContractInterface> db) {
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
            ContractInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getAgreementRef();
                case 1:
                    return address.getJobRoleCode();
                case 2:
                    return formatter.format(address.getStartDate());
                case 3:
                    if(address.getActualEndDate() != null) {
                        return formatter.format(address.getActualEndDate());
                    } else {
                        return "";
                    }
                case 4:
                    return address.getAccountRef();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(ContractTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
