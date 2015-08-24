/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_application;
import client_gui.*;
import interfaces.Client;
import interfaces.Server;
import interfaces.RMISecurityPolicyLoader;
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
    
    private static ClientImpl test;
    String name;
    Server server = null;
    
    public ClientImpl() {
        test = this;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException, NotBoundException {
        
        Home_Form form = new Home_Form(test);
        form.setVisible(true);
        ClientImpl c1 = (ClientImpl) CreateClient(new String[]{"arnold"});//server
        ClientImpl c2 = (ClientImpl) CreateClient(new String[]{"donald", "127.0.0.1"});
        
    }
    
    public static Client CreateClient(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        RMISecurityPolicyLoader.LoadPolicy("RMISecurity.policy");

        ClientImpl c = new ClientImpl();
        //if the command line has at least one argument, the first one is the name
//        if (args.length > 0) {
//            c.setName(args[0]);
//        } else {
//            c.setName("bob");
//        }
        if (args.length > 1) {//we have the address of the server!
            c.registerWithServer(args[1]);
        } else {//we are on the same machine as the server
            c.registerWithServer(null);
        }
        return c;
    }
    
    public void registerWithServer(String host) throws RemoteException, NotBoundException {
        if (host == null) {
            host = "127.0.0.1";
        }
        System.out.println("Trying host : " + host);
        //get the registry
        Registry registry = LocateRegistry.getRegistry(host);
        //get the server stub from the registry
        server = (Server) registry.lookup("ChatterServer");
        //register the chatter with the server
//        server.register(getStub());
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
}