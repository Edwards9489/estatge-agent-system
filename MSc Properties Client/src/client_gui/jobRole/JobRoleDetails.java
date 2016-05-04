/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.jobRole;

import client_gui.jobRequirement.CreateJobRoleRequirement;
import client_gui.jobBenefit.JobRoleBenefitDetails;
import client_gui.jobBenefit.CreateJobRoleBenefit;
import client_gui.jobBenefit.UpdateJobRoleBenefit;
import client_gui.jobBenefit.BenefitPanel;
import client_application.ClientImpl;
import client_gui.ButtonPanel;
import client_gui.DetailsPanel;
import client_gui.StringListener;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.element.ElementDetails;
import client_gui.element.ElementPanel;
import client_gui.modifications.ModPanel;
import client_gui.note.NotePanel;
import client_gui.note.CreateNote;
import client_gui.note.NoteDetails;
import client_gui.note.UpdateNote;
import interfaces.JobRoleInterface;
import interfaces.Element;
import interfaces.JobRoleBenefitInterface;
import interfaces.Note;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 *
 * @author Dwayne
 */
public class JobRoleDetails extends JFrame {
    
    private ClientImpl client = null;
    private JobRoleInterface jobRole = null;
    private JPanel detailsPanel;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private JTabbedPane tabbedPane;
    private ButtonPanel buttonPanel;    
    private ElementPanel requirementPanel;
    private BenefitPanel benefitPanel;
    private NotePanel notePanel;
    private ModPanel modPanel;
    private JLabel title;
    private JLabel salary;
    private JCheckBox full;
    private JCheckBox read;
    private JCheckBox write;
    private JCheckBox update;
    private JCheckBox delete;
    private JCheckBox eRead;
    private JCheckBox eWrite;
    private JCheckBox eUpdate;
    private JCheckBox eDelete;

    public JobRoleDetails(ClientImpl client, JobRoleInterface jobRole) {
        super("MSc Properties");
        setClient(client);
        setApplication(jobRole);
        layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one Application is initiated
    private void setApplication(JobRoleInterface jobRole) {
        if (this.jobRole == null) {
            this.jobRole = jobRole;
        }
    }

    private void layoutComponents() {
        try {
            setJMenuBar(createMenuBar());

            detailsPanel = new JPanel();
            
            mainPanel = new JPanel();
            
            centrePanel = new JPanel();

            setLayout(new BorderLayout());

            this.setMinimumSize(new Dimension(1200, 700));
            this.setSize(1200, 700);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

            setupDetails();
            
            setUpMainPanel();

            this.add(detailsPanel, BorderLayout.NORTH);
            
            this.add(mainPanel, BorderLayout.CENTER);
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupDetails() throws RemoteException {
        /////// SET UP APPLICATION DETAILS PANEL

        detailsPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Job Role Details");

        detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

            ////////// FIRST ROW //////////
        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.ipady = 20;

        JLabel leaseRef = new JLabel("Code    ");
        Font font = leaseRef.getFont();
        Font boldFont = new Font(font.getName(), Font.BOLD, 17);
        Font plainFont = new Font(font.getName(), Font.PLAIN, 17);

        leaseRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseRef, gc);

        JLabel ref = new JLabel(jobRole.getJobRoleCode());
        ref.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(ref, gc);

        JLabel leaseLength = new JLabel("Title    ");
        leaseLength.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseLength, gc);

        title = new JLabel(jobRole.getJobTitle());
        title.setFont(boldFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(title, gc);

        JLabel lExpenditure = new JLabel("Salary    ");
        lExpenditure.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lExpenditure, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        salary = new JLabel("£" + String.valueOf(jobRole.getSalary()));
        salary.setFont(boldFont);
        detailsPanel.add(salary, gc);

        JLabel lStartDate = new JLabel("Full Time    ");
        lStartDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lStartDate, gc);

        full = new JCheckBox();
        full.setSelected(jobRole.isFullTime());
        full.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(full, gc);

            ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel leaseName = new JLabel("Read    ");
        leaseName.setFont(plainFont);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseName, gc);

        read = new JCheckBox();
        read.setSelected(jobRole.getRead());
        read.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(read, gc);

        JLabel empty1 = new JLabel("Write    ");
        empty1.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(empty1, gc);
        
        write = new JCheckBox();
        write.setSelected(jobRole.getWrite());
        write.setEnabled(false);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(write, gc);

        JLabel leasePropRef = new JLabel("Update    ");
        leasePropRef.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leasePropRef, gc);
        
        update = new JCheckBox();
        update.setSelected(jobRole.getUpdate());
        update.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(update, gc);

        JLabel leaseEndDate = new JLabel("Delete    ");
        leaseEndDate.setFont(plainFont);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(leaseEndDate, gc);
        
        JCheckBox endDate = new JCheckBox();
        endDate.setSelected(jobRole.getDelete());
        endDate.setEnabled(false);
        
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(endDate, gc);
        
        
        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel lAccRef = new JLabel("eRead    ");
        lAccRef.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lAccRef, gc);
        
        eRead = new JCheckBox();
        eRead.setSelected(jobRole.getEmployeeRead());
        eRead.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eRead, gc);

        JLabel lofficeCode = new JLabel("eWrite    ");
        lofficeCode.setFont(plainFont);
        
        gc.gridx++;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lofficeCode, gc);

        eWrite = new JCheckBox();
        eWrite.setSelected(jobRole.getEmployeeWrite());
        eWrite.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eWrite, gc);

        JLabel lfull = new JLabel("eUpdate    ");
        lfull.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lfull, gc);

        eUpdate = new JCheckBox();
        eUpdate.setSelected(jobRole.getEmployeeUpdate());
        eUpdate.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eUpdate, gc);

        JLabel lAcEndDate = new JLabel("eDelete    ");
        lAcEndDate.setFont(plainFont);

        gc.gridx++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lAcEndDate, gc);

        eDelete = new JCheckBox();
        eDelete.setSelected(jobRole.getEmployeeDelete());
        eDelete.setEnabled(false);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(eDelete, gc);
        
        ////////// NEXT ROW //////////
        gc.gridx = 0;
        gc.gridy++;

        JLabel lAccDescrip = new JLabel("Description    ");
        lAccDescrip.setFont(plainFont);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 0, 0, 0);
        detailsPanel.add(lAccDescrip, gc);

        JLabel descrip = new JLabel(jobRole.getJobDescription());
        descrip.setFont(boldFont);

        gc.gridx++;
        gc.gridwidth = 5;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(descrip, gc);
    }
    
    private void setUpMainPanel() {
        /////// SET UP TABBED PANE
        
        mainPanel.setSize(1000, 250);

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));

        mainPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        mainPanel.setLayout(new BorderLayout());
        
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));
        
        buttonPanel = new ButtonPanel();
        
        buttonPanel.setButtonListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                int pane = tabbedPane.getSelectedIndex();

                System.out.println(text);
                switch (text) {
                    case "Create":
                        System.out.println("TEST - Create Button");

                        if (pane == 0) {
                            //Requirements
                            createJobRoleRequirement();
                            System.out.println("Create Job Requirement");
                            
                        } else if (pane == 1) {
                            //Benefits
                            createJobRoleBenefit();
                            System.out.println("Create Job Benefit");
                            
                        } else if (pane == 2) {
                            //Notes
                            createNote();
                            System.out.println("Create Note");
                            
                        }
                        break;
                        
                    case "Update":
                        System.out.println("TEST - Update Button");

                        if (pane == 1) {
                            //Benefits
                            updateJobRoleBenefit();
                            System.out.println("TEST - Update Job Benefit");
                            
                        } else if (pane == 2) {
                            //Notes
                            updateNote();
                            System.out.println("TEST - Update Note");
                            
                        }
                        break;

                    case "Delete":
                        System.out.println("TEST - Delete Button");
                        if (pane == 0) {
                            //Requirements
                            deleteJobRoleRequirement();
                            System.out.println("TEST - Delete Job Requirement");
                            
                        } else if (pane == 1) {
                            //Benefits
                            deleteJobRoleBenefit();
                            System.out.println("TEST - Delete Job Benefit");
                            
                        } else if (pane == 2) {
                            //Notes
                            deleteNote();
                            System.out.println("TEST - Delete Note");
                            
                        }
                        break;

                    case "View Details":
                        System.out.println("TEST - View Details Button");
                        if (pane == 0) {
                            //Requirements
                            viewJobRoleRequirement();
                            System.out.println("TEST - View Job Requirement");

                        } else if (pane == 1) {
                            //Benefits
                            viewJobRoleBenefit();
                            System.out.println("TEST - View Job Benefit");

                        } else if (pane == 2) {
                            //Notes
                            viewNote();
                            System.out.println("TEST - View Note");

                        }
                        break;
                    
                    case "Refresh":
                        refresh();
                        break;
                }
            }
        });
        
        tabbedPane = new JTabbedPane();
        
        requirementPanel = new ElementPanel("Requirements");
        
        try {
            requirementPanel.setData(jobRole.getJobRequirements());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        requirementPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String requirementCode) {
                if (requirementCode != null) {
                    try {
                        Element element = jobRole.getJobRequirement(requirementCode);
                        if (element != null) {
                            System.out.println(element.getCode() + " : " + element.getDescription());
                            System.out.println("TEST1-Job Role Requirement");
                            ElementDetails requirementGUI = new ElementDetails(client, element, "Job Requirement");
                            requirementGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        benefitPanel = new BenefitPanel("Benefits");
        
        try {
            benefitPanel.setData(jobRole.getBenefits());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        benefitPanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int documentRef) {
                if(documentRef > 0) {
                    try {
                        JobRoleBenefitInterface jobRoleBenefit = jobRole.getJobBenefit(documentRef);
                        if(jobRoleBenefit != null) {
                            System.out.println(jobRoleBenefit.getBenefitRef() + " : " + jobRoleBenefit.getBenefitCode());
                            System.out.println("TEST1-Job Role Benefit");
                            JobRoleBenefitDetails addressGUI = new JobRoleBenefitDetails(client, jobRoleBenefit);
                            addressGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        notePanel = new NotePanel("Notes");
        
        try {
            notePanel.setData(jobRole.getNotes());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        notePanel.setTableListener(new IntegerListener() {
            @Override
            public void intOmitted(int noteRef) {
                if(noteRef > 0) {
                    try {
                        Note note = jobRole.getNote(noteRef);
                        if(note != null) {
                            System.out.println(note.getReference());
                            System.out.println("TEST1-Note");
                            NoteDetails noteGUI = new NoteDetails(client, note);
                            noteGUI.setVisible(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        modPanel = new ModPanel("Modifications");
        
        try {
            modPanel.setData(jobRole.getModifiedBy());
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tabbedPane.setSize(800, 175);
        
        tabbedPane.add(requirementPanel, "Requiremenmts");
        tabbedPane.add(benefitPanel, "Benefits");
        tabbedPane.add(notePanel, "Notes");
        tabbedPane.add(modPanel, "Modifications");
        
        centrePanel.add(tabbedPane);
        try {
            centrePanel.add(new DetailsPanel(jobRole.getCreatedBy(), jobRole.getCreatedDate(), jobRole.getLastModifiedBy(), jobRole.getLastModifiedDate()));
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
    }
    
    private void createJobRoleRequirement() {
        CreateJobRoleRequirement createJobRequirement = new CreateJobRoleRequirement(client, jobRole);
        createJobRequirement.setVisible(true);
        System.out.println("TEST - Create Note");
    }

    private void deleteJobRoleRequirement() {
        String selection = requirementPanel.getSelectedObjectCode();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE requirement " + selection + " for Job Role " + jobRole.getJobRoleCode() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Requirement Delete - Yes button clicked");
                    int result = client.deleteJobRoleRequirement(jobRole.getJobRoleCode(), selection);
                    if (result > 0) {
                        String message = "Requirement " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(JobRoleDetails.this, message, title);
                    } else {
                        String message = "Requirement " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(JobRoleDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewJobRoleRequirement() {
        if (requirementPanel.getSelectedObjectCode() != null) {
            Element element;
            try {
                element = client.getJobRequirement(requirementPanel.getSelectedObjectCode());
                if (element != null) {
                    ElementDetails requirementDetails = new ElementDetails(client, element, "Job Requirement");
                    requirementDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void createJobRoleBenefit() {
        try {
            CreateJobRoleBenefit createJobBenefit = new CreateJobRoleBenefit(client, jobRole.getJobRoleCode());
            createJobBenefit.setVisible(true);
            System.out.println("TEST - Create Job Benefit");
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateJobRoleBenefit() {
        Integer selection = benefitPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                JobRoleBenefitInterface jobRoleBenefit = client.getJobRoleBenefit(selection);
                if (jobRoleBenefit != null) {
                    UpdateJobRoleBenefit jobRoleBenefitDetails = new UpdateJobRoleBenefit(client, jobRoleBenefit);
                    jobRoleBenefitDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteJobRoleBenefit() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE benefit " + selection + " for Job Role " + jobRole.getJobRoleCode() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Benefit Delete - Yes button clicked");
                    int result = client.deleteJobRoleNote(jobRole.getJobRoleCode(), selection);
                    if (result > 0) {
                        String message = "Benefit " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(JobRoleDetails.this, message, title);
                    } else {
                        String message = "Benefit " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(JobRoleDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewJobRoleBenefit() {
        if (notePanel.getSelectedObjectRef() != null) {
            JobRoleBenefitInterface jobRoleBenefit;
            try {
                jobRoleBenefit = client.getJobRoleBenefit(notePanel.getSelectedObjectRef());
                if (jobRoleBenefit != null) {
                    JobRoleBenefitDetails contractDetails = new JobRoleBenefitDetails(client, jobRoleBenefit);
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void createNote() {
        try {
            CreateNote createNote = new CreateNote(client, "Job Role", jobRole.getJobRoleCode());
            createNote.setVisible(true);
            System.out.println("TEST - Create Note");
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                Note note = jobRole.getNote(selection);
                if (note != null) {
                    UpdateNote noteDetails = new UpdateNote(client, note, "Job Role", jobRole.getJobRoleCode());
                    noteDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteNote() {
        Integer selection = notePanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE note " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Note Delete - Yes button clicked");
                    int result = client.deleteJobRoleNote(jobRole.getJobRoleCode(), selection);
                    if (result > 0) {
                        String message = "Note " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(JobRoleDetails.this, message, title);
                    } else {
                        String message = "Note " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(JobRoleDetails.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewNote() {
        if (notePanel.getSelectedObjectRef() != null) {
            Note note;
            try {
                note = jobRole.getNote(notePanel.getSelectedObjectRef());
                if (note != null) {
                    NoteDetails contractDetails = new NoteDetails(client, note);
                    contractDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void refresh() {
        try {
            title = new JLabel(jobRole.getJobTitle());
            salary = new JLabel("£" + String.valueOf(jobRole.getSalary()));
            full.setSelected(jobRole.isFullTime());
            read.setSelected(jobRole.getRead());
            write.setSelected(jobRole.getWrite());
            update.setSelected(jobRole.getUpdate());
            delete.setSelected(jobRole.getDelete());
            eRead.setSelected(jobRole.getEmployeeRead());
            eWrite.setSelected(jobRole.getEmployeeWrite());
            eUpdate.setSelected(jobRole.getEmployeeUpdate());
            eDelete.setSelected(jobRole.getEmployeeDelete());
            notePanel.setData(jobRole.getNotes());
            modPanel.setData(jobRole.getModifiedBy());
            notePanel.refresh();
            modPanel.refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem userAccount = new JMenuItem("User Account");
        JMenuItem changeUser = new JMenuItem("Change User");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(userAccount);
        fileMenu.add(changeUser);
        fileMenu.addSeparator(); // Is the faint lines between grouped menu items
        fileMenu.add(exitItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manualItem = new JMenuItem("User Manual");
        JMenuItem aboutItem = new JMenuItem("About");

        // Add Menubar items
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Set up Mnemonics for Menus
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // Set up Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        changeUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        userAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        manualItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));

        //Set up ActionListeners
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

            }
        });

        userAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                int action = JOptionPane.showConfirmDialog(JobRoleDetails.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    if (client != null) {
                        try {
                            client.logout();
                        } catch (RemoteException ex) {
                            Logger.getLogger(JobRoleDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new JobRoleDetails().setVisible(true);
//            }
//        });
//    }
}
