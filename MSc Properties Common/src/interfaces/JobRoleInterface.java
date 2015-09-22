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
    List getBenefits();
    String getCreatedBy();
    Date getCreatedDate();
    void updateJobRole(String title, String description);
    void createJobRequirement(Element requirement);
    void removeJobRequirement(Element requirement);
    void createBenefit(JobRoleBenefitInterface benefit);
    void removeBenefit(JobRoleBenefitInterface benefit);
}