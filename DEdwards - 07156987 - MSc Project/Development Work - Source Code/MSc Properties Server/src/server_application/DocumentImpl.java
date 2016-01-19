/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Document;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class DocumentImpl extends UnicastRemoteObject implements Document {
    private final int documentRef;
    private final Note note;
    private final String createdBy;
    private final Date createdDate;
    private final List<File> files;
    private final List<ModifiedByInterface> modifiedBy;
    
    public DocumentImpl(int documentRef, File file, Note note, String createdBy, Date createdDate) throws RemoteException {
        this.documentRef = documentRef;
        this.createdBy =  createdBy;
        this.createdDate = createdDate;
        this.note = note;
        files = new ArrayList();
        files.add(file);
        modifiedBy = new ArrayList();
    }
    
    /**
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }
    
    /**
     * Adds a new version of the file to the list of files
     * @param file
     * @param modifiedBy 
     */
    public void createNewVersion(File file, ModifiedByInterface modifiedBy) {
        files.add(file);
        this.modifiedBy(modifiedBy);
    }
    
    public void setComment(String comment, ModifiedByInterface modifiedBy) throws RemoteException {
        NoteImpl temp = (NoteImpl) this.getNote();
        temp.setNote(comment, modifiedBy);
        this.modifiedBy(modifiedBy);
    }

    /**
     * @return the documentRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getDocumentRef() throws RemoteException {
        return documentRef;
    }
    
    @Override
    public String getCurrentDocumentName() throws RemoteException {
        return files.get(files.size() - 1).getName();
    }

    /**
     * @param version
     * @return the documentName
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getDocumentName(int version) throws RemoteException {
        File file = this.getDocument(version);
        if(file != null) {
            return file.getName();
        }
        return null;
    }
    
    @Override
    public String getDocumentPath(int version) throws RemoteException {
        File file = this.getDocument(version);
        if(file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }
    
    @Override
    public File getDocument(int version) throws RemoteException {
        if(this.hasVersion(version)) {
            return files.get(version -1);
        }
        return null;
    }
    
    
    @Override
    public List<File> getPreviousVersions() throws RemoteException {
        List<File> pVersions = new ArrayList();
        if(files.size() > 1) {
            for(int i = 0; i < (files.size() - 1); i++) {
                pVersions.add(files.get(i));
            }
        }
        return pVersions;
    }
    
    @Override
    public boolean hasVersion(int version) throws RemoteException {
        return (version > 0 && version <= files.size());
    }
    
    @Override
    public Note getNote() throws RemoteException {
        return note;
    }
    
    @Override
    public String getComment() throws RemoteException {
        return note.getNote();
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return the name of the last user to modify the account
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * @return the last date the account was modified
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * @return the list of modifiedBy objects
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * @return the last modifiedBy object for the account
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
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