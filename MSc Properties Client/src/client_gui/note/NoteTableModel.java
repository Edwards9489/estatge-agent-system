/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.note;

import interfaces.Note;
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
public class NoteTableModel extends AbstractTableModel {
    
    private List<Note> db = new ArrayList();
    
    private final String[] colNames = {"Ref", "Note", "Created By", "Created Date", "Modified Last By", "Modified Last Date"};
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public NoteTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<Note> db) {
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
            Note note = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return note.getReference();
                case 1:
                    return note.getNote();
                case 2:
                    return note.getCreatedBy();
                case 3:
                    return formatter.format(note.getCreatedDate());
                case 4:
                    if(note.hasBeenModified()) {
                        return note.getLastModifiedBy();
                    } else {
                        return "";
                    }
                case 5:
                    if(note.hasBeenModified()) {
                        return formatter.format(note.getLastModifiedDate());
                    } else {
                        return "";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(NoteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
