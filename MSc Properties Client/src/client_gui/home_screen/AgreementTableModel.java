/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.home_screen;

import interfaces.AgreementInterface;
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
public class AgreementTableModel extends AbstractTableModel {
    
    private List<AgreementInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Agreement Name", "Start Date", "Expected End Date", "Office Code"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public AgreementTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<AgreementInterface> db) {
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
            AgreementInterface agreement = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return agreement.getAgreementRef();
                case 1:
                    return agreement.getAgreementName();
                case 2:
                    return formatter.format(agreement.getStartDate());
                case 3:
                    return formatter.format(agreement.getExpectedEndDate());
                case 4:
                    return agreement.getOfficeCode();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(AgreementTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}