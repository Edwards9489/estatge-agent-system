/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.person;

import client_application.ClientImpl;
import client_gui.IntegerListener;
import client_gui.OKDialog;
import client_gui.StringArrayListener;
import client_gui.StringListener;
import interfaces.PersonInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Dwayne
 */
public class PersonSearch extends JFrame {

    private ClientImpl client = null;
    private IntegerListener listener = null;

    private JButton okButton;
    private JButton cancelButton;
    private JButton createButton;

    private PersonPanel personPanel;
    private JPanel searchResultsPanel;

    private SimpleDateFormat dateFormatter;

    public PersonSearch(ClientImpl client) {
        super("MSc Properties");
        setClient(client);
        this.layoutComponents();
    }

    // Use of singleton pattern to ensure only one Client is initiated
    private void setClient(ClientImpl model) {
        if (client == null) {
            this.client = model;
        }
    }

    // Use of singleton pattern to ensure only one listener is initiated
    public void setListener(IntegerListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
    }

    private void layoutComponents() {

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        createButton = new JButton("Create Person");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (personPanel.getSelectedObjectRef() != null) {
                    listener.intOmitted(personPanel.getSelectedObjectRef());
                    setVisible(false);
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                setVisible(false);
                dispose();
            }
        });
        
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                createPerson();
            }
        });

        personPanel = new PersonPanel("People");

        personPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });
        
        personPanel.setTableListener(new StringListener() {
            @Override
            public void textOmitted(String text) {
                actionChoice(text);
            }
        });

        this.setSize(1200, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        PeopleSearchPanel detailsPanel = new PeopleSearchPanel(client);

        detailsPanel.setListener(new StringArrayListener() {
            @Override
            public void arrayOmitted(List<String> array) {
                if (array.size() == 15) {
                    try {
                        Date dob = null;
                        Date createdDate = null;
                        
                        if (array.get(4) != null) {
                            dob = dateFormatter.parse(array.get(4));
                        }
                        if (array.get(14) != null) {
                            createdDate = dateFormatter.parse(array.get(14));
                        }
                        personPanel.setData(client.getPeople(array.get(0), array.get(1), array.get(2), array.get(3), dob, array.get(5), array.get(6), array.get(7), array.get(8), array.get(9), array.get(10), array.get(11), array.get(12), array.get(13), createdDate));
                        personPanel.refresh();
                    } catch (RemoteException | ParseException ex) {
                        Logger.getLogger(PersonSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder());

        searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new BorderLayout());

        ////////// BUTTONS PANEL //////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(createButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        //Add components to searchResultsPanel
        searchResultsPanel.add(personPanel, BorderLayout.CENTER);
        searchResultsPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(searchResultsPanel, BorderLayout.SOUTH);
    }

    private void createPerson() {
        CreatePerson createPerson = new CreatePerson(client);
        createPerson.setVisible(true);
        System.out.println("TEST - Create Person");
    }

    private void updatePerson() {
        Integer selection = personPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                System.out.println("Person Ref: " + selection);
                PersonInterface person = client.getPerson(selection);
                if (person != null) {
                    System.out.println("Person Name: " + person.getName());
                    UpdatePerson updatePersonDetails = new UpdatePerson(client, person);
                    updatePersonDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deletePerson() {
        Integer selection = personPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to DELETE Person " + selection + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("Person Delete - Yes button clicked");
                    int result = client.deletePerson(selection);
                    if (result > 0) {
                        String message = "Person " + selection + " has been successfully deleted";
                        String title = "Information";
                        OKDialog.okDialog(PersonSearch.this, message, title);
                    } else {
                        String message = "Person " + selection + " has dependent records and is not able to be deleted";
                        String title = "Error";
                        OKDialog.okDialog(PersonSearch.this, message, title);
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void viewPerson() {
        Integer selection = personPanel.getSelectedObjectRef();
        if (selection != null) {
            try {
                PersonInterface person = client.getPerson(selection);
                if (person != null) {
                    PersonDetails personDetails = new PersonDetails(client, person);
                    personDetails.setVisible(true);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PersonDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refresh() {
        personPanel.refresh();
    }

    private void actionChoice(String text) {
        switch (text) {
            case "Create":
                createPerson();
                break;

            case "Update":
                updatePerson();
                break;

            case "Delete":
                deletePerson();
                break;

            case "View Details":
                viewPerson();
                break;

            case "Refresh":
                refresh();
                break;

        }
    }

//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new PersonSearch().setVisible(true);
//            }
//        });
//    }
}
