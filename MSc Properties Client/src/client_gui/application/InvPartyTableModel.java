/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.application;

import interfaces.InvolvedPartyInterface;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dwayne
 */
public class InvPartyTableModel extends AbstractTableModel {
    
    private List<InvolvedPartyInterface> db;
    
    private String[] colNames = {"Ref", "Name", "Relationship", "Start Date", "End Date", "End Reason", "Main", "Jnt"};
    
    public InvPartyTableModel() {
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setData(List<InvolvedPartyInterface> db) {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }
    
    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            InvolvedPartyInterface invParty = db.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    return invParty.getInvolvedPartyRef();
                case 1:
                    return invParty.getPerson().getName();
                case 2:
                    return invParty.getRelationship().getCode();
                case 3:
                    return new SimpleDateFormat("dd-MM-YYYY").format(invParty.getStartDate());
                case 4:
                    if(invParty.getEndDate() != null) {
                        return new SimpleDateFormat("dd-MM-YYYY").format(invParty.getEndDate());
                    } else {
                        return "";
                    }
                case 5:
                    if(invParty.getEndDate() != null) {
                        return invParty.getEndReason().getCode();
                    } else {
                        return "";
                    }
                case 6:
                    if(invParty.isMainInd()) {
                        return "Y";
                    } else {
                        return "N";
                    }
                case 7:
                    if(invParty.isJointInd()) {
                        return "Y";
                    } else {
                        return "N";
                    }
            }
            return null;
        } catch (RemoteException ex) {
            Logger.getLogger(InvPartyTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
