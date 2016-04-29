/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobBenefit;

import interfaces.JobRoleBenefitInterface;
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
public class BenefitTableModel extends AbstractTableModel {
    
    private List<JobRoleBenefitInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Benefit Code", "Value", "Start Date", "End Date", "Comment"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public BenefitTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<JobRoleBenefitInterface> db) {
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
            JobRoleBenefitInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getBenefitRef();
                case 1:
                    return address.getBenefitCode();
                case 2:
                    if(address.getDoubleValue() != null) {
                        return "£" + address.getDoubleValue();
                    } else if(address.getStringValue() != null) {
                        return address.getStringValue();
                    }
                case 3:
                    return formatter.format(address.getStartDate());
                case 4:
                    if(address.getEndDate() != null) {
                        return formatter.format(address.getEndDate());
                    } else {
                        return "";
                    }
                case 5:
                    return address.getComment();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(BenefitTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
