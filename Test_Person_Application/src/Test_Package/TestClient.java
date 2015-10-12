/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Dwayne
 */
public class TestClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        System.out.println("********************Running Client Test********************");
        ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"Dwayne", "127.0.0.1"});
        
        System.out.println(client.getName());
        
        System.out.println(client.isAlive());
        
    }
}