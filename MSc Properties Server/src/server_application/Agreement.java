/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.AgreementInterface;
import interfaces.ModifiedByInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dwayne
 */
public class Agreement implements AgreementInterface {
    
    ///   VARIABLES   ///
    
    private final int agreementRef;
    private final String agreementName;
    private Date startDate;
    private Date expectedEndDate;
    private Date actualEndDate;
    private int length;
    private final String createdBy;
    private final Date createdDate;
    private final ArrayList<ModifiedByInterface> modifiedBy;
    private final String officeCode;
    
    ///   CONSTRUCTORS ///
    
    public Agreement(int agreementRef, String agreementName, Date startDate, int length, String createdBy, String officeCode) {
        this.agreementRef = agreementRef;
        this.agreementName = agreementName;
        this.startDate = startDate;
        this.length = length;
        expectedEndDate = calculateExpectedEndDate(startDate, length);
        this.createdBy = createdBy;
        this.createdDate = new Date();
        modifiedBy = new ArrayList();
        this.officeCode = officeCode;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param startDate the startDate to set
     */
    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param expectedEndDate the expectedEndDate to set
     */
    private void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    /**
     * @param length the length to set
     */
    private void setLength(int length) {
        this.length = length;
        setExpectedEndDate(calculateExpectedEndDate(startDate, length));
    }
    
    /**
     * @param actualEndDate the actualEndDate to set
     */
    @Override
    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }
    
    @Override
    public void updateAgreement(Date startDate, int length) {
        setStartDate(startDate);
        setLength(length);
    }
    
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    private Date calculateExpectedEndDate(Date startDate, int length) {
        Calendar cal = DateConversion.dateToCalendar(this.startDate);
        cal.add(Calendar.MONTH, length);
        return expectedEndDate = cal.getTime();
    }

    /**
     * @return the agreementRef
     */
    @Override
    public int getAgreementRef() {
        return agreementRef;
    }
    
    /**
     * @return the agreementName
     */
    @Override
    public String getAgreementName() {
        return agreementName;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the expectedEndDate
     */
    @Override
    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    /**
     * @return the actualEndDate
     */
    @Override
    public Date getActualEndDate() {
        return actualEndDate;
    }

    /**
     * @return the length
     */
    @Override
    public int getLength() {
        return length;
    }
    
    /**
     * @return the length
     */
    @Override
    public String getOfficeCode() {
        return officeCode;
    }
    
    @Override
    public boolean isCurrent() {
        if(actualEndDate == null) {
            return true;
        }
        else {
            return actualEndDate.before(new Date());
        }
    }

    /**
     * @return the createdBy
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}