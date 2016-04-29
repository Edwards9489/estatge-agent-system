/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.transaction;

import interfaces.TransactionInterface;
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
public class TransactionTableModel extends AbstractTableModel {
    
    private List<TransactionInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "From Ref", "To Ref", "Amount", "Is Debit?", "Date"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public TransactionTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<TransactionInterface> db) {
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
            TransactionInterface transaction = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return transaction.getTransactionRef();
                case 1:
                    return transaction.getFromRef();
                case 2:
                    return transaction.getToRef();
                case 3:
                    return "£" + transaction.getAmount();
                case 4:
                    if(transaction.isDebit()) {
                        return "Y";
                    } else {
                        return "N";
                    }
                case 5:
                    return formatter.format(transaction.getTransactionDate());
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(TransactionTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
