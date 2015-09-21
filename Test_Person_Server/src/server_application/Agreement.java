/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AgreementInterface;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Agreement implements AgreementInterface {
    private final int agreementRef;
    private Date startDate;
    private Date expectedEndDate;
    private Date actualEndDate;
    private int length;
    private final String createdBy;
    private final Date createdDate;
    
    public Agreement(int agreementRef, Date startDate, int length, String createdBy) {
        this.agreementRef = agreementRef;
        this.startDate = startDate;
        this.length = length;
        expectedEndDate = calculateExpectedEndDate(startDate, length);
        this.createdBy = createdBy;
        this.createdDate = new Date();
    }

    /**
     * @return the agreementRef
     */
    public int getAgreementRef() {
        return agreementRef;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the expectedEndDate
     */
    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    /**
     * @return the actualEndDate
     */
    public Date getActualEndDate() {
        return actualEndDate;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public boolean isCurrent() {
        if(actualEndDate == null) {
            return true;
        }
        else {
            return actualEndDate.before(new Date());
        }
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param expectedEndDate the expectedEndDate to set
     */
    private void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    /**
     * @param actualEndDate the actualEndDate to set
     */
    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
        setExpectedEndDate(calculateExpectedEndDate(startDate, length));
    }
    
    private Date calculateExpectedEndDate(Date startDate, int length) {
        Calendar cal = DateConversion.dateToCalendar(this.startDate);
        cal.add(Calendar.MONTH, length);
        return expectedEndDate = cal.getTime();
    }
}