/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.document;

import interfaces.Document;
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
public class DocumentTableModel extends AbstractTableModel {
    
    private List<Document> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Document Name", "Created By", "Created Date"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public DocumentTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<Document> db) {
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
            Document document = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return document.getDocumentRef();
                case 1:
                    return document.getCurrentDocumentName();
                case 2:
                    return document.getCreatedBy();
                case 3:
                    return formatter.format(document.getCreatedDate());
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
