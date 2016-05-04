/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.ContractInterface;
import interfaces.EmployeeInterface;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import interfaces.PersonInterface;
import interfaces.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class Employee extends UnicastRemoteObject implements EmployeeInterface {
    
    ///   VARIABLES   ///
    
    private final int employeeRef;
    private final PersonInterface person;
    private final List<ContractInterface> contracts;
    private String officeCode; // Create a class for office, which stores the office information
    private final List<Note> notes;
    private final List<ModifiedByInterface> modifiedBy;
    private final String createdBy;
    private final Date createdDate;
    private final User user;
    private String memorableLocation;
    
    ///   CONSTRUCTORS ///
    
    /**
     * Constructor for objects of class Employee
     * @param employeeRef
     * @param person
     * @param username
     * @param password
     * @param createdBy
     * @param createdDate
     * @throws RemoteException 
     */
    public Employee(int employeeRef, PersonInterface person, String username, String password, String createdBy, Date createdDate) throws RemoteException {
        this.employeeRef = employeeRef;
        this.person = person;
        this.contracts = new ArrayList();
        this.notes = new ArrayList();
        this.modifiedBy = new ArrayList();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.user = new UserImpl(employeeRef, person.getPersonRef(), username, password, null);
    }
    
    
    
    ///   MUTATOR METHODS   ///
        
    /**
     * @param officeCode the officeCode to set
     */
    private void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
        UserImpl temp = (UserImpl) this.user;
        temp.setOfficeCode(officeCode);
    }
    
    public void setPasswordReset(boolean reset) {
        UserImpl temp = (UserImpl) this.user;
        temp.setPasswordReset(reset);
    }
    
    public void setMemorableLocation(String answer) {
        this.memorableLocation = answer;
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
     * 
     * @param password 
     * @param modifiedBy 
     */
    public void updatePassword(String password, ModifiedByInterface modifiedBy) {
        UserImpl temp = (UserImpl) this.user;
        temp.setPassword(password);
        this.modifiedBy(modifiedBy);
    }
    
    public void updatePermissions(Boolean read, Boolean write, Boolean update, Boolean delete, Boolean employeeRead, Boolean employeeWrite, Boolean employeeUpdate, Boolean employeeDelete, ModifiedByInterface modifiedBy) {
        UserImpl temp = (UserImpl) this.user;
        temp.setUserPermissions(read, write, update, delete, employeeRead, employeeWrite, employeeUpdate, employeeDelete);
        this.modifiedBy(modifiedBy);
    }
    
    /**
     * 
     * @param contract
     * @param modifiedBy 
     * @throws java.rmi.RemoteException 
     */
    public void createContract(ContractInterface contract, ModifiedByInterface modifiedBy) throws RemoteException {
        if(!this.contracts.contains(contract)) {
            contracts.add(contract);
            this.modifiedBy(modifiedBy);
            setOfficeCode(contract.getOfficeCode());
        }
    }
    
    /**
     * 
     * @param ref
     * @param modifiedBy
     * @throws java.rmi.RemoteException 
     */
    public void deleteContract(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasContract(ref) && !(this.getContract(ref).hasBeenModified())) {
            contracts.remove(this.getContract(ref));
            this.modifiedBy(modifiedBy);
        }
    }
    
    public void createNote(Note note, ModifiedByInterface modifiedBy) {
        notes.add(note);
        this.modifiedBy(modifiedBy);
    }
    
    public void deleteNote(int ref, ModifiedByInterface modifiedBy) throws RemoteException {
        if(this.hasNote(ref)) {
            Note note = this.getNote(ref);
            if(!note.hasBeenModified()) {
                notes.remove(note);
                this.modifiedBy(modifiedBy);
            }
        }
    }
    
    
    
    ///   ACCESSOR METHODS   ///
    
    /**
     * @return employeeRef
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getEmployeeRef() throws RemoteException {
        return employeeRef;
    }

    /**
     * @return person
     * @throws java.rmi.RemoteException
     */
    @Override
    public PersonInterface getPerson() throws RemoteException {
        return person;
    }
    
    /**
     * 
     * @return true if contracts.isEmpty() == false || getContract.isCurrent()
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean isCurrent() throws RemoteException {
        if(contracts.isEmpty()) {
            return false;
        } else {
            return this.getContract().isCurrent();
        }
    }
    
    @Override
    public boolean hasBeenModified() throws RemoteException {
        return !this.modifiedBy.isEmpty();
    }
    
    /**
     * @return ref of person
     * @throws java.rmi.RemoteException
     */
    @Override
    public int getPersonRef() throws RemoteException {
        return person.getPersonRef();
    }
    
    /**
     * 
     * @return user
     * @throws java.rmi.RemoteException
     */
    @Override
    public User getUser() throws RemoteException {
        return this.user;
    }

    /**
     * @return officeCode
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getOfficeCode() throws RemoteException {
        return officeCode;
    }
    
    public String getMemorableLocation() {
        return memorableLocation;
    }
    
    /**
     * 
     * @return the name of the last user to modify the Employee
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
     * @return the date of the last modification for the Employee
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
     * @return the list of modifiedBy objects for the Employee
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ModifiedByInterface> getModifiedBy() throws RemoteException {
        return Collections.unmodifiableList(this.modifiedBy);
    }
    
    /**
     * 
     * @return the last modifiedBy object for the Employee
     * @throws java.rmi.RemoteException
     */
    @Override
    public ModifiedByInterface getLastModification() throws RemoteException {
        if(!this.modifiedBy.isEmpty()) {
            return this.modifiedBy.get(this.modifiedBy.size()-1);
        }
        return null;
    }
    
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
    
    @Override
    public List<Note> getNotes() throws RemoteException {
        return Collections.unmodifiableList(this.notes);
    }

    /**
     * @return createdBy
     * @throws java.rmi.RemoteException
     */
    @Override
    public String getCreatedBy() throws RemoteException {
        return createdBy;
    }

    /**
     * @return createdDate
     * @throws java.rmi.RemoteException
     */
    @Override
    public Date getCreatedDate() throws RemoteException {
        return createdDate;
    }
    
    public boolean hasContract(int ref) throws RemoteException {
        if(!contracts.isEmpty()) {
            for(ContractInterface contract : getContracts()) {
                if(contract.getAgreementRef() == ref) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public ContractInterface getContract(int ref) throws RemoteException {
        if(this.hasContract(ref)) {
            for(ContractInterface contract : getContracts()) {
                if(contract.getAgreementRef() == ref) {
                    return contract;
                }
            }
        }
        return null;
    }
    
    /**
     * @return most recent contract
     * @throws java.rmi.RemoteException
     */
    @Override
    public ContractInterface getContract() throws RemoteException {
        if(!contracts.isEmpty()) {
            return contracts.get(contracts.size()-1);
        }
        return null;
    }
    
    /**
     * @return contracts
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ContractInterface> getContracts() throws RemoteException {
        return Collections.unmodifiableList(contracts);
    }
}