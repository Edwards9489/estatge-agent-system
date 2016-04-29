/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.address;

import interfaces.AddressInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dwayne
 */
public class AddressTableModel extends AbstractTableModel {
    
    private List<AddressInterface> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Building Number", "Building Name", "Sub Street Number", "Sub Street", "Street Number", "Street", "Area", "Town", "Country", "Postcode"};
    
    public AddressTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<AddressInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            AddressInterface address = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return address.getAddressRef();
                case 1:
                    if (address.getBuildingNumber() != null) {
                        return address.getBuildingNumber();
                    } else {
                        return "";
                    }
                case 2:
                    if (address.getBuildingName() != null) {
                        return address.getBuildingName();
                    } else {
                        return "";
                    }
                case 3:
                    if (address.getSubStreetNumber() != null) {
                        return address.getSubStreetNumber();
                    } else {
                        return "";
                    }
                case 4:
                    if (address.getSubStreet() != null) {
                        return address.getSubStreet();
                    } else {
                        return "";
                    }
                case 5:
                    if (address.getStreetNumber() != null) {
                        return address.getStreetNumber();
                    } else {
                        return "";
                    }
                case 6:
                    if (address.getStreet() != null) {
                        return address.getStreet();
                    } else {
                        return "";
                    }
                case 7:
                    if (address.getArea() != null) {
                        return address.getArea();
                    } else {
                        return "";
                    }
                case 8:
                    if (address.getTown() != null) {
                        return address.getTown();
                    } else {
                        return "";
                    }
                case 9:
                    if (address.getCountry() != null) {
                        return address.getCountry();
                    } else {
                        return "";
                    }
                case 10:
                    if (address.getPostcode() != null) {
                        return address.getPostcode();
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(AddressTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
