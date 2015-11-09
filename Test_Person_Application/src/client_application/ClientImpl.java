/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;
import interfaces.Client;
import interfaces.RMISecurityPolicyLoader;
import interfaces.Server;
import interfaces.User;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Dwayne
 */
public class ClientImpl implements Client{
    
    String name;
    Server server = null;
    User user = null;
    
    public ClientImpl() {
    }

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.net.UnknownHostException
     * @throws java.net.MalformedURLException
     * @throws java.rmi.NotBoundException
     */
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException, NotBoundException {
        
        ClientImpl c1 = (ClientImpl) createClient(new String[]{"arnold"});//server
        ClientImpl c2 = (ClientImpl) createClient(new String[]{"donald", "127.0.0.1"});
        
    }
    
    public static Client createClient(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        RMISecurityPolicyLoader.LoadPolicy("RMISecurity.policy");

        ClientImpl c = new ClientImpl();
        //if the command line has at least one argument, the first one is the name
        if (args.length == 5) {
            c.registerWithServer(args[1], args[0]);
        } else {//we are on the same machine as the server
            c.registerWithServer(null, null);
        }
        
        return c;
    }
    
    public void registerWithServer(String host, String environment) throws RemoteException, NotBoundException {
        if (host == null) {
            host = "127.0.0.1";
        }
        if (environment == null) {
            environment = "ServerLIVE";
        } else {
            environment = "Server" + environment;
        }
        System.out.println("Environment: " + environment);
        System.out.println("Trying host : " + host);
        //get the registry
        Registry registry = LocateRegistry.getRegistry(host);
        //get the server stub from the registry
        server = (Server) registry.lookup(environment);
        //register the chatter with the server
        server.register(getStub());
        System.out.println("Server found!");
    }
    
    //Like a singleton pattern, we want a unique stub for this chat
    Client stub = null;
    protected Client getStub() throws RemoteException {
        if (stub == null) {
            stub = (Client) UnicastRemoteObject.exportObject(this, 0);
        }
        return stub;
    }
    
    @Override
    public String getName() throws RemoteException {
        return name;
    }
    
    private void setName(String string) {
        name = string;
    }
    
    //if i have died i won't reply!
    @Override
    public boolean isAlive() throws RemoteException {
        return true;
    }
    
    private void setUser(User usr) {
        if(user == null) {
            user = usr;
        }
    }
    
    public String getOfficeCode() {
        return user.getOfficeCode();
    }
    
    public String getUsername() {
        return user.getUsername();
    }
    
    public boolean isUser(String username, String password) {
        return server.isUser(username, password);
    }
}