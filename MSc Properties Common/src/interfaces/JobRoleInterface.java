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
public interface JobRoleInterface {
    String getJobRoleCode();
    String getJobTitle();
    String getJobDescription();
    List<Element> getJobRequirements();
    boolean isFullTime();
    double getSalary();
    boolean isCurrent();
    List<JobRoleBenefitInterface> getBenefits();
    boolean hasBeenModified();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List<ModifiedByInterface> getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
}