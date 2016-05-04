/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobRole;

import interfaces.JobRoleInterface;
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
public class JobRoleTableModel extends AbstractTableModel {
    
    private List<JobRoleInterface> db = new ArrayList();
    
    private final String[] colNames = {"Code", "Title", "Description", "Salary", "Full Time", "Current"};
    
    public JobRoleTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<JobRoleInterface> db) {
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
            JobRoleInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getJobRoleCode();
                case 1:
                    return address.getJobTitle();
                case 2:
                    return address.getJobDescription();
                case 3:
                    return "£" + address.getSalary();
                case 4:
                    if (address.isFullTime()) {
                        return 'Y';
                    } else {
                        return 'N';
                    }
                case 5:
                    if (address.isCurrent()) {
                        return 'Y';
                    } else {
                        return 'N';
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
