/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Element;
import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dwayne
 */
public class JobRoleTest {
    
    public JobRoleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setCurrent method, of class JobRole.
     */
    @Test
    public void testSetCurrent() {
        try {
            System.out.println("setCurrent");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            assertEquals(true, instance.isCurrent());
            instance.setCurrent(true);
            assertEquals(true, instance.isCurrent());
            instance.setCurrent(false);
            assertEquals(false, instance.isCurrent());
            instance.setCurrent(true);
            assertEquals(true, instance.isCurrent());
            assertEquals(false, result.equals(instance.isCurrent()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of modifiedBy method, of class JobRole.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNote method, of class JobRole.
     */
    @Test
    public void testCreateNote() {
        try {
            System.out.println("createNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(true, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteNote method, of class JobRole.
     */
    @Test
    public void testDeleteNote() {
        try {
            System.out.println("deleteNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getNotes().size());
            instance.createNote(note, modifiedBy);
            assertEquals(1, instance.getNotes().size());
            instance.deleteNote(5, modifiedBy2);
            assertEquals(1, instance.getNotes().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.deleteNote(note.getReference(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(0, instance.getNotes().size());
            assertEquals(false, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateJobRole method, of class JobRole.
     */
    @Test
    public void testUpdateJobRole() {
        try {
            System.out.println("updateJobRole");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Boolean result = false;
            Boolean result2 = true;
            
            assertEquals("Manager", instance.getJobTitle());
            assertEquals("TEST", instance.getJobDescription());
            assertEquals(true, instance.getSalary() == 27000.00);
            assertEquals(true, instance.isCurrent());
            assertEquals(true, instance.getRead());
            assertEquals(true, instance.getWrite());
            assertEquals(true, instance.getUpdate());
            assertEquals(true, instance.getEmployeeRead());
            assertEquals(true, instance.getEmployeeWrite());
            assertEquals(true, instance.getEmployeeUpdate());
            
            instance.updateJobRole("Lead Manager", "UPDATED TEST", 30000.00, true, true, true, true, true, true, true, true, false, modifiedBy);
            
            assertEquals("Lead Manager", instance.getJobTitle());
            assertEquals("UPDATED TEST", instance.getJobDescription());
            assertEquals(true, instance.getSalary() == 30000.00);
            assertEquals(true, instance.isCurrent());
            assertEquals(true, instance.getRead());
            assertEquals(true, instance.getWrite());
            assertEquals(true, instance.getUpdate());
            assertEquals(true, instance.getEmployeeRead());
            assertEquals(true, instance.getEmployeeWrite());
            assertEquals(false, instance.getEmployeeUpdate());
            
            assertEquals(false, instance.getJobTitle().equals("Manager"));
            assertEquals(false, instance.getJobDescription().equals("TEST"));
            assertEquals(false, instance.getSalary() == 27000.00);
            assertEquals(false, result.equals(instance.isCurrent()));
            assertEquals(false, result.equals(instance.getRead()));
            assertEquals(false, result.equals(instance.getWrite()));
            assertEquals(false, result.equals(instance.getUpdate()));
            assertEquals(false, result.equals(instance.getEmployeeRead()));
            assertEquals(false, result.equals(instance.getEmployeeWrite()));
            assertEquals(false, result2.equals(instance.getEmployeeUpdate()));
            
            instance.updateJobRole("Lead Manager", "UPDATED TEST", 30000.00, false, true, true, true, true, true, true, true, true, modifiedBy2);
            
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createJobRequirement method, of class JobRole.
     */
    @Test
    public void testCreateJobRequirement() {
        try {
            System.out.println("createJobRequirement");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getJobRequirements().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(1, instance.getJobRequirements().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(1, instance.getJobRequirements().size());
            assertEquals(1, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of removeJobRequirement method, of class JobRole.
     */
    @Test
    public void testRemoveJobRequirement() {
        try {
            System.out.println("removeJobRequirement");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getJobRequirements().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(1, instance.getJobRequirements().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.removeJobRequirement(element2, modifiedBy2);
            assertEquals(1, instance.getJobRequirements().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.removeJobRequirement(element, modifiedBy2);
            assertEquals(0, instance.getJobRequirements().size());
            assertEquals(2, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createJobBenefit method, of class JobRole.
     */
    @Test
    public void testCreateJobBenefit() {
        try {
            System.out.println("createJobBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit benefit = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getBenefits().size());
            assertEquals(0, instance.getModifiedBy().size());
            instance.createJobBenefit(benefit, modifiedBy);
            assertEquals(1, instance.getBenefits().size());
            assertEquals(1, instance.getModifiedBy().size());
            instance.createJobBenefit(benefit, modifiedBy);
            assertEquals(1, instance.getBenefits().size());
            assertEquals(1, instance.getModifiedBy().size());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of endJobBenefit method, of class JobRole.
     */
    @Test
    public void testEndJobBenefit() {
        try {
            System.out.println("endJobBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit benefit = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.createJobBenefit(benefit, modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.hasBenefit(benefit.getBenefitRef()));
            assertEquals(true, instance.hasCurrentBenefit(benefit.getBenefitCode()));
            instance.endJobBenefit(benefit.getBenefitRef(), date.getTime(), modifiedBy2);
            assertEquals(true, instance.hasBenefit(benefit.getBenefitRef()));
            assertEquals(true, instance.hasCurrentBenefit(benefit.getBenefitCode()));
            instance.endJobBenefit(benefit.getBenefitRef(), date2.getTime(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(false, instance.hasCurrentBenefit(benefit.getBenefitCode()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteJobBenefit method, of class JobRole.
     */
    @Test
    public void testDeleteJobBenefit() {
        try {
            System.out.println("deleteJobBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit benefit = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.createJobBenefit(benefit, modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(true, instance.hasBenefit(benefit.getBenefitRef()));
            instance.deleteJobBenefit(benefit.getBenefitRef(), modifiedBy2);
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(false, instance.hasBenefit(benefit.getBenefitRef()));
            assertEquals(false, instance.hasCurrentBenefit(benefit.getBenefitCode()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getJobRoleCode method, of class JobRole.
     */
    @Test
    public void testGetJobRoleCode() {
        try {
            System.out.println("getJobRoleCode");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals("MNGR", instance.getJobRoleCode());
            assertEquals(false, instance.getJobRoleCode().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getJobTitle method, of class JobRole.
     */
    @Test
    public void testGetJobTitle() {
        try {
            System.out.println("getJobTitle");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals("Manager", instance.getJobTitle());
            assertEquals(false, instance.getJobTitle().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getJobDescription method, of class JobRole.
     */
    @Test
    public void testGetJobDescription() {
        try {
            System.out.println("getJobDescription");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals("TEST", instance.getJobDescription());
            assertEquals(false, instance.getJobDescription().equals("NOT TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getJobRequirements method, of class JobRole.
     */
    @Test
    public void testGetJobRequirements() {
        try {
            System.out.println("getJobRequirements");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE4", "DEDWARDS", new Date());
            Element element = new ElementImpl("CANT", "CANT", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("FIND", "FIND", note2, "DEDWARDS", new Date());
            Element element3 = new ElementImpl("THE", "THE", note3, "DEDWARDS", new Date());
            Element element4 = new ElementImpl("BUG", "BUG", note4, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED3", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED4", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED5", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getJobRequirements().size());
            
            List<Element> expResult = new ArrayList();
            List<Element> test = new ArrayList();
            
            instance.createJobRequirement(element, modifiedBy);
            expResult.add(element);
            assertEquals(1, instance.getJobRequirements().size());
            System.out.println("EXP" + expResult.size());
            System.out.println("RES" + instance.getJobRequirements().size());
            assertEquals(expResult, instance.getJobRequirements());
            
            instance.createJobRequirement(element2, modifiedBy2);
            expResult.add(element2);
            assertEquals(2, instance.getJobRequirements().size());
            System.out.println("EXP" + expResult.size());
            System.out.println("RES" + instance.getJobRequirements().size());
            assertEquals(expResult, instance.getJobRequirements());
            
            instance.createJobRequirement(element3, modifiedBy3);
            expResult.add(element3);
            assertEquals(3, instance.getJobRequirements().size());
            System.out.println("EXP" + expResult.size());
            System.out.println("RES" + instance.getJobRequirements().size());
            assertEquals(expResult, instance.getJobRequirements());
            
            instance.createJobRequirement(element4, modifiedBy4);
            expResult.add(element4);
            assertEquals(4, instance.getJobRequirements().size());
            System.out.println("EXP" + expResult.size());
            System.out.println("RES" + instance.getJobRequirements().size());
            assertEquals(expResult, instance.getJobRequirements());
            
            instance.removeJobRequirement(element, modifiedBy5);
            expResult.remove(element);
            assertEquals(3, instance.getJobRequirements().size());
            System.out.println("EXP" + expResult.size());
            System.out.println("RES" + instance.getJobRequirements().size());
            assertEquals(expResult, instance.getJobRequirements());
            
            assertEquals(false, instance.getJobRequirements().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isFullTime method, of class JobRole.
     */
    @Test
    public void testIsFullTime() {
        try {
            System.out.println("isFullTime");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.isFullTime());
            assertEquals(false, result.equals(instance.isFullTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getSalary method, of class JobRole.
     */
    @Test
    public void testGetSalary() {
        try {
            System.out.println("getSalary");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(true, instance.getSalary() == 27000.00);
            assertEquals(false, instance.getSalary() == 30000.00);
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class JobRole.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.isCurrent());
            assertEquals(false, result.equals(instance.isCurrent()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasRequirement method, of class JobRole.
     */
    @Test
    public void testHasRequirement() {
        try {
            System.out.println("hasRequirement");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(false, instance.hasRequirement(element.getCode()));
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(true, instance.hasRequirement(element.getCode()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBenefit method, of class JobRole.
     */
    @Test
    public void testHasBenefit() {
        try {
            System.out.println("hasBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit benefit = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(false, instance.hasBenefit(benefit.getBenefitRef()));
            instance.createJobBenefit(benefit, modifiedBy);
            assertEquals(true, instance.hasBenefit(benefit.getBenefitRef()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasCurrentBenefit method, of class JobRole.
     */
    @Test
    public void testHasCurrentBenefit() {
        try {
            System.out.println("hasCurrentBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit benefit = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(false, instance.hasCurrentBenefit(benefit.getBenefitCode()));
            instance.createJobBenefit(benefit, modifiedBy);
            assertEquals(true, instance.hasCurrentBenefit(benefit.getBenefitCode()));
            instance.endJobBenefit(benefit.getBenefitRef(), date2.getTime(), modifiedBy2);
            assertEquals(false, instance.hasCurrentBenefit(benefit.getBenefitCode()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getBenefits method, of class JobRole.
     */
    @Test
    public void testGetBenefits() {
        try {
            System.out.println("getBenefits");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST", note2, "DEDWARDS", new Date());
            Element element3 = new ElementImpl("TEST3", "TEST", note3, "DEDWARDS", new Date());
            Element element4 = new ElementImpl("TEST4", "TEST", note4, "DEDWARDS", new Date());
            JobRoleBenefit benefit = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            JobRoleBenefit benefit2 = new JobRoleBenefit(2, element2, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            JobRoleBenefit benefit3 = new JobRoleBenefit(3, element3, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            JobRoleBenefit benefit4 = new JobRoleBenefit(4, element4, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getBenefits().size());
            List<JobRoleBenefit> expResult = new ArrayList();
            List<JobRoleBenefit> test = new ArrayList();
            instance.createJobBenefit(benefit, modifiedBy);
            expResult.add(benefit);
            assertEquals(1, instance.getBenefits().size());
            assertEquals(expResult, instance.getBenefits());
            instance.createJobBenefit(benefit2, modifiedBy2);
            expResult.add(benefit2);
            assertEquals(2, instance.getBenefits().size());
            assertEquals(expResult, instance.getBenefits());
            instance.createJobBenefit(benefit3, modifiedBy3);
            expResult.add(benefit3);
            assertEquals(3, instance.getBenefits().size());
            assertEquals(expResult, instance.getBenefits());
            instance.createJobBenefit(benefit4, modifiedBy4);
            expResult.add(benefit4);
            assertEquals(4, instance.getBenefits().size());
            assertEquals(expResult, instance.getBenefits());
            instance.deleteJobBenefit(benefit.getBenefitRef(), modifiedBy5);
            expResult.remove(benefit);
            assertEquals(3, instance.getBenefits().size());
            assertEquals(expResult, instance.getBenefits());
            assertEquals(false, instance.getBenefits().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class JobRole.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class JobRole.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class JobRole.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date.getTime());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class JobRole.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.createJobRequirement(element, modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class JobRole.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.createJobRequirement(element, modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.createNote(note2, modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasNote method, of class JobRole.
     */
    @Test
    public void testHasNote() {
        try {
            System.out.println("hasNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(false, instance.hasNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(true, instance.hasNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class JobRole.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(false, note.equals(instance.getNote(note.getReference())));
            assertEquals(null, instance.getNote(note.getReference()));
            instance.createNote(note, modifiedBy);
            assertEquals(note, instance.getNote(note.getReference()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNotes method, of class JobRole.
     */
    @Test
    public void testGetNotes() {
        try {
            System.out.println("getNotes");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE", "DEDWARDS", new Date());
            Note note4 = new NoteImpl(4, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy3 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy4 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy5 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(0, instance.getNotes().size());
            List<Note> expResult = new ArrayList();
            List<Note> test = new ArrayList();
            instance.createNote(note, modifiedBy);
            expResult.add(note);
            assertEquals(1, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note2, modifiedBy2);
            expResult.add(note2);
            assertEquals(2, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note3, modifiedBy3);
            expResult.add(note3);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.createNote(note4, modifiedBy4);
            expResult.add(note4);
            assertEquals(4, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            instance.deleteNote(note.getReference(), modifiedBy5);
            expResult.remove(note);
            assertEquals(3, instance.getNotes().size());
            assertEquals(expResult, instance.getNotes());
            assertEquals(false, instance.getNotes().equals(test));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class JobRole.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("ADMIN"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class JobRole.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", date.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRead method, of class JobRole.
     */
    @Test
    public void testGetRead() {
        try {
            System.out.println("getRead");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.getRead());
            assertEquals(false, result.equals(instance.getRead()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getWrite method, of class JobRole.
     */
    @Test
    public void testGetWrite() {
        try {
            System.out.println("getWrite");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.getWrite());
            assertEquals(false, result.equals(instance.getWrite()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getUpdate method, of class JobRole.
     */
    @Test
    public void testGetUpdate() {
        try {
            System.out.println("getUpdate");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.getUpdate());
            assertEquals(false, result.equals(instance.getUpdate()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeRead method, of class JobRole.
     */
    @Test
    public void testGetEmployeeRead() {
        try {
            System.out.println("getEmployeeRead");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.getEmployeeRead());
            assertEquals(false, result.equals(instance.getEmployeeRead()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeWrite method, of class JobRole.
     */
    @Test
    public void testGetEmployeeWrite() {
        try {
            System.out.println("getEmployeeWrite");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.getEmployeeWrite());
            assertEquals(false, result.equals(instance.getEmployeeWrite()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEmployeeUpdate method, of class JobRole.
     */
    @Test
    public void testGetEmployeeUpdate() {
        try {
            System.out.println("getEmployeeUpdate");
            JobRole instance = new JobRole("MNGR", "Manager", "TEST", true, 27000.00, true, true, true, true, true, true, true, true, "ADMIN", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.getEmployeeUpdate());
            assertEquals(false, result.equals(instance.getEmployeeUpdate()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}