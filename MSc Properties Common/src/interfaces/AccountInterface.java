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
    boolean isPositiveInd();
    boolean isCurrent();
    String getCreatedBy();
    Date getCreatedDate();
    List getDebitTransactions();
    List getCreditTransactions();
    void setEndDate(Date endDate);
    void updateAccount(Date startDate, String accName);
}
