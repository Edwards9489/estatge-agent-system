/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.home_screen;

import interfaces.AccountInterface;
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
public class AccountTableModel extends AbstractTableModel {
    
    private List<AccountInterface> db;
    
    private String[] colNames = {"Ref", "Account Name", "Start Date", "Balance", "Office Code"};
    
    public AccountTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<AccountInterface> db) {
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
            AccountInterface account = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return account.getAccRef();
                case 1:
                    return account.getAccName();
                case 2:
                    return new SimpleDateFormat("dd-MM-YYYY").format(account.getStartDate());
                case 3:
                    return "£" + account.getBalance();
                case 4:
                    return account.getOfficeCode();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(AccountTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}