/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.employee;

import interfaces.ContractInterface;
import interfaces.EmployeeInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dwayne
 */
public class EmployeeTableModel extends AbstractTableModel {
    
    private List<EmployeeInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Name", "Job Role Code", "Office Code"};
    
    public EmployeeTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<EmployeeInterface> db) {
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
            EmployeeInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getEmployeeRef();
                case 1:
                    return address.getPerson().getName();
                case 2:
                    ContractInterface contract = address.getContract();
                    if (contract != null) {
                        return contract.getJobRole().getJobRoleCode();
                    } else {
                        return "";
                    }
                case 3:
                    if (address.getOfficeCode() != null) {
                        return address.getOfficeCode();
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
