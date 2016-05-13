/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import classes.Utils;
import interfaces.AddressInterface;
import interfaces.Document;
import interfaces.Element;
import interfaces.LandlordInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PropertyElementInterface;
import interfaces.PropertyInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dwayne
 */
public class Property extends UnicastRemoteObject implements PropertyInterface {
    
    ///   VARIABLES   ///
        
    private final int propRef;
    private AddressInterface address;
    private List<LandlordInterface> landlords;
    private Date acquiredDate;
    private Date leaseEndDate;
    private Integer leaseRef;
    private Element propType;
    private Element propSubType;
    private String propStatus; // Occupied, Void, New, End etc
    private final Map<Integer, PropertyElementInterface> propertyElements;
    private final List<Note> notes;
    private final List<ModifiedByInterface> modifiedBy;
    private final List<Document> documents;
    private final String createdBy;
    private final Date createdDate;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Property
     * @param propRef
     * @param address
     * @param acquiredDate
     * @param propType
     * @param propSubType
     * @param createdBy
     * @param createdDate 
     * @throws java.rmi.RemoteException 
     */
    public Property(int propRef, AddressInterface address, Date acquiredDate, Element propType, Element propSubType, String createdBy, Date createdDate) throws RemoteException {
        // initialise instance variables
        this.propRef = propRef;
        this.address = address;
        this.landlords = new ArrayList();
        this.acquiredDate = acquiredDate;
        this.leaseRef = null;
        this.propType = propType;
        this.propSubType = propSubType;
        this.propStatus = "NEW";
        this.propertyElements = new HashMap<>();
        this.notes = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.documents = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    
    
    ///   MUTATOR METHODS   ///
    
    /**
     * @param address
     */
    private void setAddress(AddressInterface address) {
        this.address = address;
    }
    
    /**
     * @param acquiredDate
     */
    private void setAcquiredDate(Date acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    /**
     * @param propType
     */
    private void setPropType(Element propType) {
        this.propType = propType;
    }

    /**
     * @param propSubType=
     */
    private void setPropSubType(Element propSubType) {
        this.propSubType = propSubType;
    }
    
    /**
     * 
     * @param modifiedBy
     */
    public void modifiedBy(ModifiedByInterface modifiedBy) {
        if(modifiedBy != null) {
            this.modifiedBy.add(modifiedBy);
        }
    }

    /**
     * @param landlords
     * @param modifiedBy
     */
    public void setLandlords(List<LandlordInterface> landlords, ModifiedByInterface modifiedBy) {
        this.landlords = landlords;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param leaseEndDate
     * @param modifiedBy
     */
    public void setLeaseEndDate(Date leaseEndDate, ModifiedByInterface modifiedBy) {
        this.leaseEndDate = leaseEndDate;
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * @param ref
     * @param modifiedBy
     */
    public void setLeaseRef(Integer ref, ModifiedByInterface modifiedBy) {
        this.leaseRef = ref;
        this.modifiedBy(modifiedBy);
    }

    /**
     * @param propStatus
     * @param modifiedBy
     */
    public void setPropStatus(String propStatus, ModifiedByInterface modifiedBy) {
        this.propStatus = propStatus;
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param address
     * @param acquiredDate
     * @param propType
     * @param propSubType
     * @param modifiedBy 
     */
    public void updateProperty(AddressInterface address, Date acquiredDate, Element propType, Element propSubType, ModifiedByInterface modifiedBy) {
        this.setAddress(address);
        this.setAcquiredDate(acquiredDate);
        this.setPropType(propType);
        this.setPropSubType(propSubType);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param element
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void createPropertyElement(PropertyElement element, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasCurrentPropElement(element.getElementCode())) {
            this.propertyElements.put(element.getPropertyElementRef(), element);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param ref
     * @param endDate
     * @param modifiedBy
     * @throws RemoteException
     */
    public void endPropertyElement(int ref, Date endDate, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasPropElement(ref)) {
            PropertyElement propElement = (PropertyElement) propertyElements.get(ref);
            if(!propElement.getStartDate().after(endDate)) {
                propElement.setEndDate(endDate, modifiedBy);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    public void deletePropertyElement(PropertyElement element, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasCurrentPropElement(element.getElementCode()) && !element.hasBeenModified()) {
            this.propertyElements.remove(element.getPropertyElementRef());
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(!note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    /**
     *
     * @param document
     * @param modifiedBy
     * @throws RemoteException
     */
    public void createDocument(Document document, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.hasDocument(document.getDocumentRef())) {
            documents.add(document);
            this.modifiedBy(modifiedBy);
        }
    }
    
    /**
     *
     * @param ref
     * @param modifiedBy
     * @throws RemoteException
     */
    public void deleteDocument(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasDocument(ref)) {
            documents.remove(this.getDocument(ref));
            this.modifiedBy(modifiedBy);
        }
    }

    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return propRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPropRef() throws RemoteException {
        return propRef;
    }

    /**
     * @return address
     * @throws java.rmi.RemoteException
     */
    @Override
    public AddressInterface getAddress() throws RemoteException {
        return address;
    }
    
    /**
     * @return landlords
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<LandlordInterface> getLandlords() throws RemoteException {
        return Collections.unmodifiableList(landlords);
    }

    /**
     * @return acquiredDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getAcquiredDate() throws RemoteException {
        return acquiredDate;
    }

    /**
     * @return leaseEndDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLeaseEndDate() throws RemoteException {
        return leaseEndDate;
    }
    
    /**
     * @return leaseRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public Integer getLeaseRef() throws RemoteException {
        return leaseRef;
    }

    /**
     * @return propType
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getPropType() throws RemoteException {
        return propType;
    }

    /**
     * @return propSubType
     * @throws java.rmi.RemoteException
     */
    @Override
    public Element getPropSubType() throws RemoteException {
        return propSubType;
    }

    /**
     * @return propStatus
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getPropStatus() throws RemoteException {
        return propStatus;
    }

    /**
     * @return propertyElements
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<PropertyElementInterface> getPropertyElements() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList(propertyElements.values()));
    }
    
    /**
     * 
     * @param code
     * @return true if propertyElements contains PropertyElement with code == code
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean hasPropElement(String code) throws RemoteException {
        if(!propertyElements.isEmpty()) {
            for(PropertyElementInterface element : propertyElements.values()) {
                if(element.isCurrent() && Utils.compareStrings(code, element.getElementCode())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @param code
     * @return true if propertyElements contains PropertyElement with code == code and current
     * @throws java.rmi.RemoteException
     */
    public boolean hasCurrentPropElement(String code) throws RemoteException {
        if(!propertyElements.isEmpty()) {
            for(PropertyElementInterface element : propertyElements.values()) {
                if(element.isCurrent() && code.equals(element.getElementCode())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @param ref
     * @return true if propertyElements contains PropertyElement with ref == ref
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean hasPropElement(int ref) throws RemoteException {
        return propertyElements.containsKey(ref);
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public PropertyElementInterface getPropElement(int ref) throws RemoteException {
        if(this.hasPropElement(ref)) {
            return this.propertyElements.get(ref);
        }
        return null;
    }
    
    /**
     * 
     * @return true if leaseEndDate == null || (leaseEndDate != null && leaseEndDate > TODAY)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(leaseEndDate == null) {
            return false;
        }
        else {
            return leaseEndDate.after(new Date());
        }
    }
    
    /**
     * 
     * @return value assigned to RENT element from propertyElements
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getRent() throws RemoteException {
        double rent = 0;
        if (!propertyElements.isEmpty()) {
            for (PropertyElementInterface temp : propertyElements.values()) {
                if (temp.isCurrent() && temp.isElementCode("RENT")) {
                    rent = temp.getDoubleValue();
                }
            }
        }
        return rent;
    }
    
    /**
     * 
     * @return sum of values assigned to charge elements from propertyElements
     * @throws java.rmi.RemoteException
     */
    @Override
    public double getCharges() throws RemoteException {
        double charges = 0;
        if(!propertyElements.isEmpty()) {
            for (PropertyElementInterface temp : propertyElements.values()) {
                if(temp.isCurrent() && temp.isCharge() && !temp.isElementCode("RENT")) {
                    charges = charges + temp.getDoubleValue();
                }
            }
        }
        return charges;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * 
     * @return the name of the last user who modified the Property
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getLastModifiedBy() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedBy();
        }
        return null;
    }
    
    /**
     * 
     * @return the last date a user modified the Property
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getLastModifiedDate() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.getLastModification().getModifiedDate();
        }
        return null;
    }
    
    /**
     * 
     * @return the list of modifiedBy objects for the Property
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifedBy object for the Property
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasNote(int ref) throws RemoteException {
        if(!notes.isEmpty()) {
            for(Note note : notes) {
                if(note.getReference() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public Note getNote(int ref) throws RemoteException {
        if(this.hasNote(ref)) {
            for (Note note : notes) {
                if(note.getReference() == ref) {
                    return note;
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasDocument(int ref) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param fileName
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean hasDocument(String fileName) throws RemoteException {
        if(!documents.isEmpty()) {
            for(Document document : documents) {
                if(Utils.compareStrings(fileName, document.getCurrentDocumentName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param ref
     * @return
     * @throws RemoteException
     */
    @Override
    public Document getDocument(int ref) throws RemoteException {
        if(this.hasDocument(ref)) {
            for (Document document : documents) {
                if(document.getDocumentRef() == ref) {
                    return document;
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Document> getDocuments() throws RemoteException {
        return Collections.unmodifiableList(this.documents);
    }
    
    /**
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return this.createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return this.createdDate;
    }
}