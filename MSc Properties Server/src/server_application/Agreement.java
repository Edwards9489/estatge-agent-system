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
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Agreement implements AgreementInterface {
    
    ///   VARIABLES   ///
    
    private final int agreementRef;
    private String agreementName;
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
        this.setExpectedEndDate(this.calculateExpectedEndDate());
        this.createdBy = createdBy;
        this.createdDate = new Date();
        this.modifiedBy = new ArrayList();
        this.officeCode = officeCode;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param startDate the startDate to set
     */
    private void setAgreementName(String name) {
        this.agreementName = name;
    }
    
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
        if(expectedEndDate.after(this.startDate)) {
            this.expectedEndDate = expectedEndDate;
        }
    }

    /**
     * @param length the length to set
     */
    private void setLength(int length) {
        if(length > 0) {
            this.length = length;
            this.setExpectedEndDate(calculateExpectedEndDate());
        }
    }
    
    private void modifiedBy(ModifiedByInterface modifiedBy) {
        this.modifiedBy.add(modifiedBy);
    }
    
    /**
     * @param actualEndDate the actualEndDate to set
     * @param modifiedBy
     */
    @Override
    public void setActualEndDate(Date actualEndDate, ModifiedByInterface modifiedBy) {
        if(actualEndDate.after(this.startDate)) {
            this.actualEndDate = actualEndDate;
            this.modifiedBy(modifiedBy);
        }
    }
    
    @Override
    public void updateAgreement(String name, Date startDate, int length, ModifiedByInterface modifiedBy) {
        if(isCurrent()) {
            this.setAgreementName(name);
            this.setStartDate(startDate);
            this.setLength(length);
            this.modifiedBy(modifiedBy);
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    private Date calculateExpectedEndDate() {
        Calendar cal = DateConversion.dateToCalendar(this.startDate);
        cal.add(Calendar.MONTH, this.length);
        return cal.getTime();
    }
    
    private boolean isEndDateNull() {
        return this.actualEndDate == null;
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
        if(this.isEndDateNull()) {
            return true;
        }
        else {
            return actualEndDate.after(new Date());
        }
    }
    
    @Override
    public String getLastModifiedBy() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    @Override
    public Date getLastModifiedDate() {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    @Override
    public List getModifiedBy() {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    @Override
    public ModifiedByInterface getLastModification() {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
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
    
    @Override
    public String toString() {
        String temp = "\n\nAgreement Ref: " + this.getAgreementRef() + "\nAgreement Name: " + this.getAgreementName() +
                "\nOffice Code: " + this.getOfficeCode() + "\nAgreement Length: " + this.getLength() + "\nStart Date: " +
                this.getStartDate() + "\nExpected End Date: " + this.getExpectedEndDate() + "\nActual End Date: " +
                this.getActualEndDate() + "\nIs Current: " + this.isCurrent() + "\nCreated By: " + this.getCreatedBy() +
                "\nCreated Date: " + this.getCreatedDate() + "\nLast Modified By:" + this.getLastModifiedBy() +
                "\nLast Modified Date: " + this.getLastModifiedDate() + "\nModifiedBy\n" + this.getModifiedBy();
        return temp;
    }
}