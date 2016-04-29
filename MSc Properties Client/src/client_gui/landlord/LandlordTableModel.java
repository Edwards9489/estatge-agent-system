/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.landlord;

import interfaces.LandlordInterface;
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
public class LandlordTableModel extends AbstractTableModel {
    
    private List<LandlordInterface> db = new ArrayList();
    
    private String[] colNames = {"Ref", "Name", "Date of Birth", "NI number", "Gender"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public LandlordTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<LandlordInterface> db) {
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
            LandlordInterface landlord = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return landlord.getLandlordRef();
                case 1:
                    return landlord.getPerson().getName();
                case 2:
                    return formatter.format(landlord.getPerson().getDateOfBirth());
                case 3:
                    if(landlord.getPerson().getNI() != null) {
                        return landlord.getPerson().getNI();
                    } else {
                        return "";
                    }
                case 4:
                    return landlord.getPerson().getGender().getCode();
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(LandlordTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
