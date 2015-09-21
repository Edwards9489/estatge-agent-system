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
public interface ModifiedByInterface {
    String getModifiedBy();
    Date getModifiedDate();
    String getDescription();
    String toString();
}