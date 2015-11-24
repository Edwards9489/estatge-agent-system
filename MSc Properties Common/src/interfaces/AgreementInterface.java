/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public interface AgreementInterface {
    int getAgreementRef();
    String getAgreementName();
    Date getStartDate();
    Date getExpectedEndDate();
    Date getActualEndDate();
    int getLength();
    String getOfficeCode();
    int getAccountRef();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    boolean hasNote(int ref);
    Note getNote(int ref);
    List<Note> getNotes();
    boolean hasDocument(int ref);
    Document getDocument(int ref);
    List<Document> getDocuments();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isCurrent();
}