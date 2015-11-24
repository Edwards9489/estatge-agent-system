/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Document;
import interfaces.Note;
import java.io.File;
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
    
    public DocumentImpl(int documentRef, File file, String fileName, Note note, String createdBy, Date createdDate) {
        super(file, fileName);
        this.documentRef = documentRef;
        this.createdBy =  createdBy;
        this.createdDate = createdDate;
        this.note = note;
    }

    /**
     * @return the documentRef
     */
    @Override
    public int getDocumentRef() {
        return documentRef;
    }

    /**
     * @return the documentName
     */
    @Override
    public String getDocumentName() {
        return getName();
    }
    
    @Override
    public String getDocumentPath() {
        return this.getDocument().getAbsolutePath();
    }
    
    @Override
    public File getDocument() {
        return super.getParentFile();
    }
    
    @Override
    public Note getNote() {
        return note;
    }
    
    @Override
    public String getComment() {
        return note.getNote();
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}