/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.element;

import interfaces.Element;
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
public class ElementTableModel extends AbstractTableModel {
    
    private List<Element> db = new ArrayList();
    
    private final String[] colNames = {"Code", "Description", "Comment", "Current"};
    
    public ElementTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<Element> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Element element = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return element.getCode();
                case 1:
                    return element.getDescription();
                case 2:
                    return element.getComment();
                case 3:
                    if(element.isCurrent()) {
                        return "Y";
                    } else {
                        return "N";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(ElementTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
