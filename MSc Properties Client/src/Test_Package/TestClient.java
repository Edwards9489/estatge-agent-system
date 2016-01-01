/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Package;

import client_application.ClientImpl;
import interfaces.Element;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Dwayne
 */
public class TestClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        System.out.println("********************Running Client Test********************");
        
        ClientImpl client = (ClientImpl) ClientImpl.createClient(new String[]{"127.0.0.1", "TEST2", "ADMIN", "MScProperties"});
        List<Element> titles = client.getTitles();
        if(!titles.isEmpty()) {
            for(Element temp : titles) {
                System.out.println(temp.getCode() + "\n");
            }
        }
        System.out.println(client.getUsers().size());
    }
}