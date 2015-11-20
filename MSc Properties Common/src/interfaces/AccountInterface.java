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
public interface AccountInterface {
    int getAccRef();
    String getAccName();
    Date getStartDate();
    Date getEndDate();
    double getBalance();
    String getOfficeCode();
    boolean isNegativeInd();
    boolean isCurrent();
    boolean hasBeenModified();
    boolean hasTransaction(int ref);
    boolean hasNote(int ref);
    TransactionInterface getTransaction(int ref);
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    Note getNote(int ref);
    List<Note> getNotes();
    String getCreatedBy();
    Date getCreatedDate();
    List<TransactionInterface> getTransactions();
}