/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;

/**
 *
 * @author Dwayne
 */
public interface InvolvedPartyInterface {
    int getInvolvedPartyRef();
    int getPersonRef();
    PersonInterface getPerson();
    Date getStartDate();
    Date getEndDate();
    Element getEndReason();
    Element getRelationship();
    boolean getJointInd();
    boolean getMainInd();
    boolean isCurrent();
    String createdBy();
    Date createdDate();
    void endInvolvedParty(Date end, Element endReason);
    void updateInvolvedParty(boolean joint, Date start, Element relationhip);
}