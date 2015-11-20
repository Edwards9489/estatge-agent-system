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
public interface EmployeeInterface {
    int getEmployeeRef();
    PersonInterface getPerson();
    int getPersonRef();
    String getOfficeCode();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    boolean hasNote(int ref);
    Note getNote(int ref);
    List<Note> getNotes();
    String getCreatedBy();
    Date getCreatedDate();
    ContractInterface getContract();
    List<ContractInterface> getContracts();
}