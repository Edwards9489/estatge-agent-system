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
    void updateJobRoleBenefit(String stringValue, double doubleValue, boolean salaryBenefit, Date startDate, ModifiedByInterface modifiedBy);
    void setEndDate(Date endDate, ModifiedByInterface modifiedBy);
    int getBenefitRef();
    Element getBenefit();
    String getBenefitCode();
    String getStringValue();
    double getDoubleValue();
    Date getStartDate();
    Date getEndDate();
    boolean isCurrent();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    ModifiedByInterface getLastModification();
    List getModifiedBy();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isSalaryBenefit();
}