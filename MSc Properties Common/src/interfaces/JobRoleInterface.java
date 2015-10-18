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
    List getJobRequirements();
    boolean isFullTime();
    double getSalary();
    boolean isCurrent();
    List getBenefits();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
    void updateJobRole(String title, String description, double salary, boolean current, ModifiedByInterface modifiedBy);
    void createJobRequirement(Element requirement, ModifiedByInterface modifiedBy);
    void removeJobRequirement(Element requirement, ModifiedByInterface modifiedBy);
    void createBenefit(JobRoleBenefitInterface benefit, ModifiedByInterface modifiedBy);
    void removeBenefit(JobRoleBenefitInterface benefit, ModifiedByInterface modifiedBy);
}