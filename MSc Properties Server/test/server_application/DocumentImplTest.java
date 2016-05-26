/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.ModifiedByInterface;
import interfaces.Note;
import java.io.File;
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
public class DocumentImplTest {
    
    public DocumentImplTest() {
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
     * Test of modifiedBy method, of class DocumentImpl.
     */
    @Test
    public void testModifiedBy() {
        try {
            System.out.println("modifiedBy");
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = null;
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy2);
            assertEquals(0, instance.getModifiedBy().size());
            instance.modifiedBy(modifiedBy);
            assertEquals(1, instance.getModifiedBy().size());
            assertEquals(modifiedBy, instance.getLastModification());
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createNewVersion method, of class DocumentImpl.
     */
    @Test
    public void testCreateNewVersion() {
        try {
            System.out.println("createNewVersion");
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            File file2 = new File("TESTv2.pdf");
            File file3 = new File("TESTv3.pdf");
            
            assertEquals(true, instance.hasVersion(1));
            assertEquals(0, instance.getModifiedBy().size());
            instance.createNewVersion(file2, modifiedBy);
            assertEquals(true, instance.hasVersion(2));
            assertEquals(1, instance.getModifiedBy().size());
            instance.createNewVersion(file3, modifiedBy2);
            assertEquals(true, instance.hasVersion(3));
            assertEquals(2, instance.getModifiedBy().size());
            assertEquals(false, instance.hasVersion(6));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocumentRef method, of class DocumentImpl.
     */
    @Test
    public void testGetDocumentRef() {
        try {
            System.out.println("getDocumentRef");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(1, instance.getDocumentRef());
            assertEquals(false, instance.getDocumentRef() == 4);
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCurrentDocumentName method, of class DocumentImpl.
     */
    @Test
    public void testGetCurrentDocumentName() {
        try {
            System.out.println("getCurrentDocumentName");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            File file2 = new File("TESTv2.pdf");
            File file3 = new File("TESTv3.pdf");
            
            assertEquals("TEST.pdf", instance.getCurrentDocumentName());
            instance.createNewVersion(file2, modifiedBy);
            assertEquals("TESTv2.pdf", instance.getCurrentDocumentName());
            instance.createNewVersion(file3, modifiedBy2);
            assertEquals("TESTv3.pdf", instance.getCurrentDocumentName());
            assertEquals(false, instance.getCurrentDocumentName().equals("TEST.pdf"));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocumentName method, of class DocumentImpl.
     */
    @Test
    public void testGetDocumentName() {
        try {
            System.out.println("getDocumentName");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            File file2 = new File("TESTv2.pdf");
            File file3 = new File("TESTv3.pdf");
            
            assertEquals("TEST.pdf", instance.getDocumentName(1));
            instance.createNewVersion(file2, modifiedBy);
            assertEquals("TESTv2.pdf", instance.getDocumentName(2));
            instance.createNewVersion(file3, modifiedBy2);
            assertEquals("TESTv3.pdf", instance.getDocumentName(3));
            assertEquals(false, instance.getDocumentName(2).equals("TEST.pdf"));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDocumentPath method, of class DocumentImpl.
     */
    @Test
    public void testGetDocumentPath() {
        try {
            System.out.println("getDocumentPath");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            File file2 = new File("TESTv2.pdf");
            File file3 = new File("TESTv3.pdf");
            
            String location = "C:\\Users\\Dwayne\\Documents\\University Work\\Masters\\MSc Dissertation\\project\\MSc Properties Server";
            
            instance.createNewVersion(file2, modifiedBy);
            instance.createNewVersion(file3, modifiedBy2);
            assertEquals(location + "\\TEST.pdf", instance.getDocumentPath(1));
            assertEquals(location + "\\TESTv2.pdf", instance.getDocumentPath(2));
            assertEquals(location + "\\TESTv3.pdf", instance.getDocumentPath(3));
            assertEquals(false, instance.getDocumentPath(1).equals(location + "\\TESTv3.pdf"));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of getDocument method, of class DocumentImpl.
     */
    @Test
    public void testGetDocument() {
        try {
            System.out.println("getDocument");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            File file = new File("TEST.pdf");
            DocumentImpl instance = new DocumentImpl(1, file, note, "DEDWARDS", new Date());
            
            File file2 = new File("TESTv2.pdf");
            File file3 = new File("TESTv3.pdf");
            
            instance.createNewVersion(file2, modifiedBy);
            instance.createNewVersion(file3, modifiedBy2);
            assertEquals(file, instance.getDocument(1));
            assertEquals(file2, instance.getDocument(2));
            assertEquals(file3, instance.getDocument(3));
            assertEquals(false, instance.getDocument(2).equals(file));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPreviousVersions method, of class DocumentImpl.
     */
    @Test
    public void testGetPreviousVersions() {
        try {
            System.out.println("getPreviousVersions");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            File file = new File("TEST.pdf");
            DocumentImpl instance = new DocumentImpl(1, file, note, "DEDWARDS", new Date());
            
            File file2 = new File("TESTv2.pdf");
            File file3 = new File("TESTv3.pdf");
            
            assertEquals(0, instance.getCurrentVersion());
            List<File> expResult = new ArrayList();
            List<File> test = new ArrayList();
            
            instance.createNewVersion(file2, modifiedBy);
            expResult.add(file);
            assertEquals(1, instance.getCurrentVersion());
            assertEquals(expResult, instance.getCurrentVersion());
            
            instance.createNewVersion(file3, modifiedBy2);
            expResult.add(file2);
            assertEquals(2, instance.getCurrentVersion());
            assertEquals(expResult, instance.getCurrentVersion());
            
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasVersion method, of class DocumentImpl.
     */
    @Test
    public void testHasVersion() {
        try {
            System.out.println("hasVersion");
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            File file = new File("TEST.pdf");
            DocumentImpl instance = new DocumentImpl(1, file, note, "DEDWARDS", new Date());
            
            File file2 = new File("TESTv2.pdf");
            File file3 = new File("TESTv3.pdf");
                    
            assertEquals(0, instance.getCurrentVersion());
            assertEquals(false, instance.hasVersion(2));
            instance.createNewVersion(file2, modifiedBy);
            assertEquals(true, instance.hasVersion(2));
            assertEquals(1, instance.getCurrentVersion());
            assertEquals(false, instance.hasVersion(3));
            instance.createNewVersion(file3, modifiedBy2);
            assertEquals(true, instance.hasVersion(3));
            assertEquals(2, instance.getCurrentVersion());
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNote method, of class DocumentImpl.
     */
    @Test
    public void testGetNote() {
        try {
            System.out.println("getNote");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            Note note2 = new NoteImpl(2, "TEST NOTE2", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(note, instance.getNote());
            assertEquals(false, instance.getNote().equals(note2));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getComment method, of class DocumentImpl.
     */
    @Test
    public void testGetComment() {
        try {
            System.out.println("getComment");
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals("TEST NOTE", instance.getComment());
            instance.setComment("UPDATED NOTE", modifiedBy);
            assertEquals("UPDATED NOTE", instance.getComment());
            assertEquals(false, instance.getComment().equals("TEST NOTE"));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of hasBeenModified method, of class DocumentImpl.
     */
    @Test
    public void testHasBeenModified() {
        try {
            System.out.println("hasBeenModified");
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(false, instance.hasBeenModified());
            instance.setComment("UPDATED NOTE", modifiedBy);
            assertEquals(true, instance.hasBeenModified());
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedBy method, of class DocumentImpl.
     */
    @Test
    public void testGetLastModifiedBy() {
        try {
            System.out.println("getLastModifiedBy");
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedBy());
            instance.setComment("UPDATED NOTE", modifiedBy);
            assertEquals(modifiedBy.getModifiedBy(), instance.getLastModifiedBy());
            assertEquals(false, instance.getLastModifiedBy().equals("TEST"));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModifiedDate method, of class DocumentImpl.
     */
    @Test
    public void testGetLastModifiedDate() {
        try {
            System.out.println("getLastModifiedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", date.getTime());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModifiedDate());
            instance.setComment("UPDATED NOTE", modifiedBy);
            assertEquals(modifiedBy.getModifiedDate(), instance.getLastModifiedDate());
            assertEquals(false, instance.getLastModifiedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getModifiedBy method, of class DocumentImpl.
     */
    @Test
    public void testGetModifiedBy() {
        try {
            System.out.println("getModifiedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            List<ModifiedByInterface> modifiedByList = new ArrayList();
            List<ModifiedByInterface> modifiedByList2 = new ArrayList();
            assertEquals(modifiedByList, instance.getModifiedBy());
            instance.setComment("UPDATED NOTE", modifiedBy);
            modifiedByList.add(modifiedBy);
            assertEquals(true, instance.getModifiedBy().equals(modifiedByList));
            assertEquals(false, instance.getModifiedBy().equals(modifiedByList2));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLastModification method, of class DocumentImpl.
     */
    @Test
    public void testGetLastModification() {
        try {
            System.out.println("getLastModification");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy = new ModifiedBy("MODIFIED", "DEDWARDS", new Date());
            ModifiedByInterface modifiedBy2 = new ModifiedBy("MODIFIED2", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(null, instance.getLastModification());
            instance.setComment("UPDATED NOTE", modifiedBy);
            assertEquals(modifiedBy, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy2));
            instance.setComment("UPDATED NOTE AGAIN", modifiedBy2);
            assertEquals(modifiedBy2, instance.getLastModification());
            assertEquals(false, instance.getLastModification().equals(modifiedBy));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedBy method, of class DocumentImpl.
     */
    @Test
    public void testGetCreatedBy() {
        try {
            System.out.println("getCreatedBy");
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", new Date());
            
            assertEquals(false, instance.getCreatedBy().equals(null));
            assertEquals(true, instance.getCreatedBy().equals("DEDWARDS"));
            assertEquals(false, instance.getCreatedBy().equals("JBLOOGS"));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCreatedDate method, of class DocumentImpl.
     */
    @Test
    public void testGetCreatedDate() {
        try {
            System.out.println("getCreatedDate");
            Calendar date = Calendar.getInstance();
            date.set(2015, 1, 10);
            Note note = new NoteImpl(1, "TEST NOTE", "DEDWARDS", new Date());
            DocumentImpl instance = new DocumentImpl(1, new File("TEST.pdf"), note, "DEDWARDS", date.getTime());
            
            assertEquals(false, instance.getCreatedDate().equals(null));
            assertEquals(true, instance.getCreatedDate().equals(date.getTime()));
            assertEquals(false, instance.getCreatedDate().equals(new Date()));
        } catch (RemoteException ex) {
            Logger.getLogger(DocumentImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}