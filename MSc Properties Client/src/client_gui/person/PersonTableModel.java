/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

import interfaces.PersonInterface;
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
public class PersonTableModel extends AbstractTableModel {
    
    private List<PersonInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Name", "Date of Birth", "Gender", "NI Number"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public PersonTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<PersonInterface> db) {
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
            PersonInterface property = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return property.getPersonRef();
                case 1:
                    return property.getName();
                case 2:
                    return formatter.format(property.getDateOfBirth());
                case 3:
                    return property.getGender().getCode();
                case 4:
                    return property.getNI();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(PersonTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
