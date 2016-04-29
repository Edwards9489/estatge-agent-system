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
public class JobRoleBenefitTest {
    
    public JobRoleBenefitTest() {
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
     * Test of modifiedBy method, of class JobRoleBenefit.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateJobRoleBenefit method, of class JobRoleBenefit.
     */
    @Test
    public void testUpdateJobRoleBenefit() {
        try {
            System.out.println("updateJobRoleBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 20);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getStringValue());
            assertEquals(true, instance.getDoubleValue() == 300.00);
            assertEquals(true, instance.isSalaryBenefit());
            assertEquals(date.getTime(), instance.getStartDate());
            assertEquals("TEST NOTE2", instance.getComment());
            assertEquals(0, instance.getModifiedBy().size());
            
            instance.updateJobRoleBenefit("", 500.00, true, date2.getTime(), "UPDATED NOTE", modifiedBy);
            
            assertEquals(null, instance.getStringValue());
            assertEquals(true, instance.getDoubleValue() == 500.00);
            assertEquals(true, instance.isSalaryBenefit());
            assertEquals(date2.getTime(), instance.getStartDate());
            assertEquals("UPDATED NOTE", instance.getComment());
            assertEquals(1, instance.getModifiedBy().size());
            
            assertEquals(false, instance.getDoubleValue() == 300.00);
            assertEquals(true, instance.isSalaryBenefit());
            assertEquals(false, instance.getStartDate().equals(date.getTime()));
            assertEquals(false, instance.getComment().equals("TEST NOTE2"));
            
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
            
            instance.updateJobRoleBenefit("IS NO LONGER A SALARY BENEFIT", null, false, date2.getTime(), "UPDATED NOTE AGAIN", modifiedBy2);
            
            assertEquals("IS NO LONGER A SALARY BENEFIT", instance.getStringValue());
            assertEquals(null, instance.getDoubleValue());
            assertEquals(false, instance.isSalaryBenefit());
            assertEquals(date2.getTime(), instance.getStartDate());
            assertEquals("UPDATED NOTE AGAIN", instance.getComment());
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(modifiedBy2, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setEndDate method, of class JobRoleBenefit.
     */
    @Test
    public void testSetEndDate() {
        try {
            System.out.println("setEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 1, 20);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(date.getTime(), modifiedBy);
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(endDate.getTime(), modifiedBy);
            assertEquals(endDate.getTime(), instance.getEndDate());
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getBenefitRef method, of class JobRoleBenefit.
     */
    @Test
    public void testGetBenefitRef() {
        try {
            System.out.println("getBenefitRef");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals(1, instance.getBenefitRef());
            assertEquals(false, instance.getBenefitRef() == 7);
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getBenefit method, of class JobRoleBenefit.
     */
    @Test
    public void testGetBenefit() {
        try {
            System.out.println("getBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Note note3 = new NoteImpl(3, "TEST NOTE3", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            Element element2 = new ElementImpl("TEST2", "TEST2", note3, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals(element, instance.getBenefit());
            assertEquals(false, instance.getBenefit().equals(element2));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getBenefitCode method, of class JobRoleBenefit.
     */
    @Test
    public void testGetBenefitCode() {
        try {
            System.out.println("getBenefitCode");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals("TEST", instance.getBenefitCode());
            assertEquals(false, instance.getBenefitCode().equals("EDM"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStringValue method, of class JobRoleBenefit.
     */
    @Test
    public void testGetStringValue() {
        try {
            System.out.println("getStringValue");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), false, "Testing String Value", null, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals("Testing String Value", instance.getStringValue());
            assertEquals(false, instance.getStringValue().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDoubleValue method, of class JobRoleBenefit.
     */
    @Test
    public void testGetDoubleValue() {
        try {
            System.out.println("getDoubleValue");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, instance.getDoubleValue() == 300.00);
            assertEquals(false, instance.getDoubleValue() == 500.00);
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getStartDate method, of class JobRoleBenefit.
     */
    @Test
    public void testGetStartDate() {
        try {
            System.out.println("getStartDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals(true, instance.getStartDate().equals(date.getTime()));
            assertEquals(false, instance.getStartDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEndDate method, of class JobRoleBenefit.
     */
    @Test
    public void testGetEndDate() {
        try {
            System.out.println("getEndDate");
            System.out.println("setEndDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 1, 20);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getEndDate());
            instance.setEndDate(endDate.getTime(), modifiedBy);
            assertEquals(endDate.getTime(), instance.getEndDate());
            assertEquals(false, instance.getEndDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isCurrent method, of class JobRoleBenefit.
     */
    @Test
    public void testIsCurrent() {
        try {
            System.out.println("isCurrent");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2015, 1, 16);
            Calendar testDate = Calendar.getInstance();
            testDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(testDate.getTime(), modifiedBy);
            assertEquals(true, instance.isCurrent());
            instance.setEndDate(endDate.getTime(), modifiedBy);
            assertEquals(false, instance.isCurrent());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class JobRoleBenefit.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.updateJobRoleBenefit("", 500.00, true, date2.getTime(), "UPDATED NOTE", modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class JobRoleBenefit.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.updateJobRoleBenefit("", 500.00, true, date2.getTime(), "UPDATED NOTE", modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class JobRoleBenefit.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Calendar modDate = Calendar.getInstance();
            modDate.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", modDate.getTime());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.updateJobRoleBenefit("", 500.00, true, date2.getTime(), "UPDATED NOTE", modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(date.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class JobRoleBenefit.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.updateJobRoleBenefit("", 500.00, true, date2.getTime(), "UPDATED NOTE", modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class JobRoleBenefit.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 16);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.updateJobRoleBenefit("", 500.00, true, date2.getTime(), "UPDATED NOTE", modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.updateJobRoleBenefit("", 500.00, true, date2.getTime(), "UPDATED NOTE AGAIN", modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class JobRoleBenefit.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals(note2, instance.getNote());
            assertEquals(false, instance.getNote().equals(note));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class JobRoleBenefit.
     */
    @Test
    public void testGetComment() {
        try {
            System.out.println("getComment");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals("TEST NOTE2", instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class JobRoleBenefit.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class JobRoleBenefit.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar createdDate = Calendar.getInstance();
            createdDate.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", createdDate.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(createdDate.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of isSalaryBenefit method, of class JobRoleBenefit.
     */
    @Test
    public void testIsSalaryBenefit() {
        try {
            System.out.println("isSalaryBenefit");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Calendar date2 = Calendar.getInstance();
            date2.set(2015, 1, 20);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            Element element = new ElementImpl("TEST", "TEST", note, "DEDWARDS", new Date());
            JobRoleBenefit instance = new JobRoleBenefit(1, element, date.getTime(), true, "", 300.00, note2, "EDM", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Boolean result = false;
            
            assertEquals(true, instance.isSalaryBenefit());
            assertEquals(false, result.equals(instance.isSalaryBenefit()));
            instance.updateJobRoleBenefit("IS NO LONGER A SALARY BENEFIT", null, false, date2.getTime(), "UPDATED NOTE AGAIN", modifiedBy);
            assertEquals(false, instance.isSalaryBenefit());
            assertEquals(true, result.equals(instance.isSalaryBenefit()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleBenefitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}