/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import interfaces.ApplicationInterface;
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
public class ApplicationTableModel extends AbstractTableModel {
    
    private List<ApplicationInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Correspondence Name", "Status Code", "Start Date", "End Date", "Tenancy Ref"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public ApplicationTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<ApplicationInterface> db) {
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
            ApplicationInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getApplicationRef();
                case 1:
                    return address.getAppCorrName();
                case 2:
                    return address.getAppStatusCode();
                case 3:
                    return formatter.format(address.getAppStartDate());
                case 4:
                    if(address.getAppEndDate() != null) {
                        return formatter.format(address.getAppEndDate());
                    } else {
                        return "";
                    }
                case 5:
                    if (address.hasTenancyRef()) {
                        return address.getTenancyRef();
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(ApplicationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
