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
public interface Element {
    String getCode();
    String getDescription();
    boolean isCurrent();
    void setDescription(String description);
    void setCurrent(boolean current);
}