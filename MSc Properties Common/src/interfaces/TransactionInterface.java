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
public interface TransactionInterface {
    int getTransactionRef();
    int getAccountRef();
    int getFromRef();
    int getToRef();
    double getAmount();
    Date getTransactionDate();
    String getCreatedBy();
    Date getCreatedDate();
    boolean isDebit();
}
