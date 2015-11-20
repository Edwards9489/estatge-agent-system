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
public interface JobRoleBenefitInterface {
    int getBenefitRef();
    Element getBenefit();
    String getBenefitCode();
    String getStringValue();
    double getDoubleValue();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    Note getNote();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isSalaryBenefit();
}