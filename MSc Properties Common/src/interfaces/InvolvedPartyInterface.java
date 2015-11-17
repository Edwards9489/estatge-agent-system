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
public interface InvolvedPartyInterface {
    int getInvolvedPartyRef();
    int getApplicationRef();
    int getPersonRef();
    PersonInterface getPerson();
    Date getStartDate();
    Date getEndDate();
    Element getEndReason();
    Element getRelationship();
    boolean isJointInd();
    boolean isMainInd();
    boolean isCurrent();
    boolean isOver18();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}