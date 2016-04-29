/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.empAccount;

import interfaces.EmployeeAccountInterface;
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
public class EmpAccTableModel extends AbstractTableModel {
    
    private List<EmployeeAccountInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Name", "Start Date", "End Date", "Salary", "Balance", "Contract Ref", "Office Code"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public EmpAccTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<EmployeeAccountInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            EmployeeAccountInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getAccRef();
                case 1:
                    return address.getAccName();
                case 2:
                    return formatter.format(address.getStartDate());
                case 3:
                    if(address.getEndDate() != null) {
                        return formatter.format(address.getEndDate());
                    } else {
                        return "";
                    }
                case 4:
                    return "£" + address.getSalary();
                case 5:
                    return "£" + address.getBalance();
                case 6:
                    return address.getContractRef();
                case 7:
                    return address.getOfficeCode();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(EmpAccTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
