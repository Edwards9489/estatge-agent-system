/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import interfaces.AgreementInterface;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dwayne
 */
public class AgreementTableModel extends AbstractTableModel {
    
    private List<AgreementInterface> db;
    
    private String[] colNames = {"Reference", "Agreement Name", "Start Date", "Expected End Date", "Office Code"};
    
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
        AgreementInterface agreement = db.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return agreement.getAgreementRef();
            case 1:
                return agreement.getAgreementName();
            case 2:
                return agreement.getStartDate();
            case 3:
                return agreement.getExpectedEndDate();
            case 4:
                return agreement.getOfficeCode();
        }
        return null;
    }
}