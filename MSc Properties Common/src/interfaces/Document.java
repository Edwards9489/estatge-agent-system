/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public interface Document {
    int getDocumentRef();
    String getDocumentName();
    File getDocument();
    String getDocumentPath();
    Note getNote();
    String getComment();
    String getCreatedBy();
    Date getCreatedDate();
}