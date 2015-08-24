/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Server;
import interfaces.RegistryLoader;
import interfaces.RMISecurityPolicyLoader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.*;

/**
 *
 * @author Dwayne
 */
public class ServerImpl extends UnicastRemoteObject implements Server {
    
    public ServerImpl() throws RemoteException {
        super();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
        // TODO code application logic here
        // start a registry on this machine
        RegistryLoader.Load();
        String myName = "Server";
        String myIP = java.net.InetAddress.getLocalHost().getHostAddress();
        String myURL = "rmi://" + myIP + "/" + myName;
        // register RMI object
        ServerImpl serv = new ServerImpl();
        Server serverStub = (Server) serv;
        //NB rebind will replace any stub with the given name 'ChatterServer'
        Naming.rebind(myName, serverStub);
        System.out.println("Server started");
    }
    
    // List of business data
    
    private HashMap<String,Person> people = new HashMap<String,Person>();
    private HashMap<String, Involved_Party> involved_parties = new HashMap<String, Involved_Party>();
    private HashMap<String, Employee> employees = new HashMap<String, Employee>();
    private HashMap<String, Landlord> landlord = new HashMap<String, Landlord>();
    
    private HashMap<String, Application> applications = new HashMap<String, Application>();
    
    private HashMap<String,Property> properties = new HashMap<String,Property>();
    
    private HashMap<String, Tenancy> tenancies = new HashMap<String, Tenancy>();
    private HashMap<String, Lease> leases = new HashMap<String, Lease>();
    
    private HashMap<String, RentAccount> rentAccounts = new HashMap<String, RentAccount>();
    private HashMap<String, LeaseAccount> leaseAccounts = new HashMap<String, LeaseAccount>();
    
    
    
    
    // List of People details
    
    private ArrayList<String> titles = new ArrayList();            // AMEND ALL OF THE LISTS FOR DETAILS OF THINGS
    private ArrayList<String> genders = new ArrayList();           // TO ACTUAL OBJECTS WITH
    private ArrayList<String> marital_statuses = new ArrayList();  // (ref, code and description)
    private ArrayList<String> ethnic_origins = new ArrayList();
    private ArrayList<String> languages = new ArrayList();
    private ArrayList<String> nationalities = new ArrayList();
    private ArrayList<String> sexualities = new ArrayList();
    private ArrayList<String> religions = new ArrayList();
    
    // List of Involved Party details
    
    private ArrayList<String> end_reasons = new ArrayList();
    private ArrayList<String> relationships = new ArrayList();
        
    // Lists of Property details
    
    private HashMap<String,Address> addresses = new HashMap<String,Address>();
    private ArrayList<String> property_types = new ArrayList(); // House, Flat, Bungalow
    private ArrayList<String> property_sub_types = new ArrayList(); // Terraced, Semi-detached
    
    // List of employee details
    
    private ArrayList<String> employeeBenefits = new ArrayList();
    
    
    
    // List of reference counters
    
    private int personRef = 1;
    private int addressRef = 1;
    private int propRef = 1;
    private int propsubTypeRef = 1;
    private int propTypeRef = 1;
    private int propTypeValueRef = 1;
    private int applicationRef = 1;
    
    
    //add a Chatter to the list
    public void createPerson(String title, String forename, String surname, int year, int month, int day, String gender) throws RemoteException {
        Person p = new Person(personRef, title, forename, surname, year, month, day, gender);
        personRef++;
        people.put(Integer.toString(p.getPersonRef()), p);
    }

    //remove a chatter
    public void deletePerson(Person p) throws RemoteException{
        people.remove(Integer.toString(p.getPersonRef()));
    }
}