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
    PersonInterface getPerson();
    boolean getJointInd();
    boolean getMainInd();
    Date getStartDate();
    Date getEndDate();
    Element getEndReason();
    Element getRelationship();
    void endInvolvedParty(Date end, Element end_reason);
    void setInvolvedParty(boolean joint, Date startDate, Date endDate, Element endReason, Element relationship);
}