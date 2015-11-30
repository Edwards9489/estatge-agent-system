/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Document;
import interfaces.Note;
import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class DocumentImpl extends File implements Document {
    private final int documentRef;
    private final Note note;
    private final String createdBy;
    private final Date createdDate;
    
    public DocumentImpl(int documentRef, File file, String fileName, Note note, String createdBy, Date createdDate) throws RemoteException {
        super(file, fileName);
        this.documentRef = documentRef;
        this.createdBy =  createdBy;
        this.createdDate = createdDate;
        this.note = note;
    }

    /**
     * @return the documentRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getDocumentRef() throws RemoteException {
        return documentRef;
    }

    /**
     * @return the documentName
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getDocumentName() throws RemoteException {
        return getName();
    }
    
    @Override
    public String getDocumentPath() throws RemoteException {
        return this.getDocument().getAbsolutePath();
    }
    
    @Override
    public File getDocument() throws RemoteException {
        return super.getParentFile();
    }
    
    @Override
    public Note getNote() throws RemoteException {
        return note;
    }
    
    @Override
    public String getComment() throws RemoteException {
        return note.getNote();
    }

    /**
     * @return the createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return createdBy;
    }

    /**
     * @return the createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }
}